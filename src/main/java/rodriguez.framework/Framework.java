package rodriguez.framework;

import java.util.List;
import java.util.Scanner;

public class Framework {
    private List<Accion> acciones;

    public Framework(List<Accion> acciones) {
        this.acciones = acciones;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Bienvenido, seleccione una accion:");

            //Listado de acciones en consola
            for (int i = 0; i < acciones.size(); i++) {
                System.out.println((i + 1) + ". " + acciones.get(i).nombreItemMenu() + " - (" + acciones.get(i).descripcionItemMenu() + ")");
            }
            System.out.println((acciones.size() + 1) + ". Salir");

            //
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            //Mensajes de flujo
            if (opcion == acciones.size() + 1) {
                //Opcion de Salida
                System.out.println("Saliendo...");
                break;
            } else if (opcion > 0 && opcion <= acciones.size()) {
                //Opcion seleccionada
                Accion accion = acciones.get(opcion - 1);
                try {
                    accion.ejecutar();
                    System.out.println("Accion ejecutada con exito.");
                } catch (Exception e) {
                    System.out.println("Error al ejecutar la accion: " + e.getMessage());
                }
            } else {
                //Ninguna opcion
                System.out.println("Opcion invalida, por favor intente nuevamente.");
            }
        }
        scanner.close();
    }
}