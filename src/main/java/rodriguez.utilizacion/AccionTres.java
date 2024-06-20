package rodriguez.utilizacion;

import rodriguez.framework.Accion;

public class AccionTres implements Accion {
    @Override
    public void ejecutar() {
        System.out.println("Promocionando Objetos...");
        for (int i = 0; i < 5; i++) {
            System.out.println(i + " desde tercera accion");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public String nombreItemMenu() {
        return "Accion 3";
    }
    @Override
    public String descripcionItemMenu() {
        return "Esto hace que promociones Objetos 2";
    }
}