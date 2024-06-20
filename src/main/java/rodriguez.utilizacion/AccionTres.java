package rodriguez.utilizacion;

import rodriguez.framework.Accion;

public class AccionTres implements Accion {
    @Override
    public void ejecutar() {
        System.out.println("Promocionando Objetos...");
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