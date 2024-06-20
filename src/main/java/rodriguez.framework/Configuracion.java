package rodriguez.framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Configuracion {

    public List<Accion> configToActionsList(String path){
        List<Accion> acciones = new ArrayList<>();

        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(path)){
            properties.load(input);

            for (String key : properties.stringPropertyNames()){
                String nombreDeClase = properties.getProperty(key);
                try{
                    Class<?> clazz = Class.forName(nombreDeClase);

                    Accion accion = (Accion) clazz.getConstructor().newInstance();

                    acciones.add(accion);
                }catch (Exception e) {
                    System.out.println("Error al cargar la clase: " + nombreDeClase);
                    e.printStackTrace();
                }
            }
        }catch (IOException e) {
            System.out.println("Error al leer el archivo de configuración.");
            e.printStackTrace();
        }

        return acciones;
    }




}