// rodriguez/framework/Framework.java
package rodriguez.framework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Framework {

    private List<Accion> acciones;
    private int maxThreads;

    public Framework(List<Accion> acciones, int maxThreads) {
        this.acciones = acciones;
        this.maxThreads = maxThreads;
    }


    public void mostrarMenu() {
        // Crear el marco de la aplicación
        JFrame frame = new JFrame("Menú de Acciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 260);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(255, 255, 255));

        // Crear el panel de checkboxes
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0;

        // Añadir checkboxes y etiquetas de descripción para cada acción
        JCheckBox[] checkBoxes = new JCheckBox[acciones.size()];
        for (int i = 0; i < acciones.size(); i++) {
            Accion accion = acciones.get(i);
            checkBoxes[i] = new JCheckBox(accion.nombreItemMenu());
            checkBoxes[i].setBackground(new Color(255, 255, 255, 255));
            checkBoxes[i].setFont(new Font("Arial", Font.PLAIN, 14));
            gbc.gridx = 0;
            gbc.gridy = i;
            panel.add(checkBoxes[i], gbc);

            JLabel descriptionLabel = new JLabel(accion.descripcionItemMenu());
            descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 12));
            gbc.gridx = 1;
            panel.add(descriptionLabel, gbc);
        }

        // Añadir botón para ejecutar acciones seleccionadas
        JButton executeButton = new JButton("Ejecutar Seleccionadas");
        executeButton.setFont(new Font("Arial", Font.BOLD, 14));
        executeButton.setBackground(new Color(94, 255, 81));
        executeButton.setOpaque(true);
        executeButton.setBorderPainted(false);
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExecutorService executor = Executors.newFixedThreadPool(maxThreads);
                List<Callable<Void>> listaAdapters =  new ArrayList<>();

                for (int i = 0; i < checkBoxes.length; i++) {
                    if (checkBoxes[i].isSelected()) {
                        Accion accion = acciones.get(i);
                         listaAdapters.add(new AccionAdapter(accion));
                    }
                }
                ejecutaHilos(executor, listaAdapters);
                executor.shutdown(); // Indicar que no se aceptarán más tareas

                // Verificación de threads
                //verificaEstadosThreads(executor);

            }
        });
        gbc.gridx = 0;
        gbc.gridy = acciones.size();
        gbc.gridwidth = 2;
        panel.add(executeButton, gbc);

        // Añadir botón para salir
        JButton exitButton = new JButton("Salir");
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBackground(new Color(255, 41, 65, 255));
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = acciones.size() + 1;
        gbc.gridwidth = 2;
        panel.add(exitButton, gbc);

        // Añadir el panel al marco y hacerlo visible
        frame.add(panel, new GridBagConstraints());
        frame.setVisible(true);
    }

    private static void verificaEstadosThreads(ExecutorService executor) {
        try {
            // Espera a que todos los threads terminen (con un timeout de 5 segundos)
            if (!executor.awaitTermination(7, TimeUnit.SECONDS)) {
                System.err.println("Error: Algunos threads no terminaron en el tiempo esperado.");
                // Puedes agregar más acciones aquí, como mostrar un mensaje al usuario
            } else {
                System.out.println("Todas las tareas han finalizado correctamente.");
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            System.err.println("Error: La espera de finalización de los threads fue interrumpida.");
        }
    }

    private static void ejecutaHilos(ExecutorService executor, List<Callable<Void>> listaAdapters) {
        try {
            executor.invokeAll(listaAdapters);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
