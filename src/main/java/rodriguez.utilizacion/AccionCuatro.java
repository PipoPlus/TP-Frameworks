package rodriguez.utilizacion;

import rodriguez.framework.Accion;

public class AccionCuatro implements Accion {
    @Override
    public void ejecutar() {
        System.out.println("Testeador de acciones...");
        for (int i = 0; i < 5; i++) {
            System.out.println(i + " desde cuarta accion");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @Override
    public String nombreItemMenu() {
        return "Accion 4";
    }
    @Override
    public String descripcionItemMenu() {
        return "Esto testea accion...";
    }
}