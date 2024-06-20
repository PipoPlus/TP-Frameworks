package rodriguez.framework;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



public class Configuracion {

    public List<Accion> configToActionsList(String path) {
        List<Accion> acciones = new ArrayList<>();

        try (InputStream input = new FileInputStream(path)) {
            if (path.endsWith(".properties")) {
                // Manejar archivo .properties
                Properties properties = new Properties();
                properties.load(input);

                for (String key : properties.stringPropertyNames()) {
                    acciones.add(crearAccion(properties.getProperty(key)));
                }

            } else if (path.endsWith(".json")) {
                // Manejar archivo .json
                JSONObject jsonObject = new JSONObject(new JSONTokener(input));
                JSONArray accionesArray = jsonObject.getJSONArray("acciones");

                for (int i = 0; i < accionesArray.length(); i++) {
                    acciones.add(crearAccion(accionesArray.getString(i)));
                }

            } else {
                System.out.println("Formato de archivo no soportado.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo de configuración.");
            e.printStackTrace();
        }

        return acciones;
    }

    public int getMaxThreads(String path) {
        int maxThreads = 1; // Valor predeterminado si no se encuentra la configuración

        try (InputStream input = new FileInputStream(path)) {
            if (path.endsWith(".properties")) {
                Properties properties = new Properties();
                properties.load(input);
                String maxThreadsStr = properties.getProperty("max-threads");
                if (maxThreadsStr != null) {
                    maxThreads = Integer.parseInt(maxThreadsStr);
                }
            } else if (path.endsWith(".json")) {
                JSONObject jsonObject = new JSONObject(new JSONTokener(input));
                if (jsonObject.has("max-threads")) {
                    maxThreads = jsonObject.getInt("max-threads");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return maxThreads;
    }

    private Accion crearAccion(String nombreDeClase) {
        try {
            Class<?> clazz = Class.forName(nombreDeClase);
            return (Accion) clazz.getConstructor().newInstance();

        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
