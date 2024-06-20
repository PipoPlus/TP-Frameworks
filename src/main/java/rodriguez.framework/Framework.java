package rodriguez.framework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Framework {

    private List<Accion> acciones;

    public Framework(List<Accion> acciones) {
        this.acciones = acciones;
    }

    public void mostrarMenu() {
        // Crear el marco de la aplicación
        JFrame frame = new JFrame("Menú de Acciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(new Color(238, 238, 238));

        // Crear el panel de checkboxes
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(238, 238, 238));
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
            checkBoxes[i].setBackground(new Color(238, 238, 238));
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
        executeButton.setBackground(new Color(173, 216, 230));
        executeButton.setOpaque(true);
        executeButton.setBorderPainted(false);
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < checkBoxes.length; i++) {
                    if (checkBoxes[i].isSelected()) {
                        acciones.get(i).ejecutar();
                    }
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = acciones.size();
        gbc.gridwidth = 2;
        panel.add(executeButton, gbc);

        // Añadir botón para salir
        JButton exitButton = new JButton("Salir");
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setBackground(new Color(255, 182, 193));
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
}
