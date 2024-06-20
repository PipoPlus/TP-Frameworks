package rodriguez.utilizacion;

import rodriguez.framework.Accion;

public class AccionDos implements Accion {
    @Override
    public void ejecutar() {
        System.out.println("Ejecutando AccionDos...");
        for (int i = 0; i < 5; i++) {
            System.out.println(i + " desde segunda accion");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public String nombreItemMenu() {
        return "Accion 2";
    }
    @Override
    public String descripcionItemMenu() {
        return "Esto trae las primeras diez personas de la BD...";
    }
}