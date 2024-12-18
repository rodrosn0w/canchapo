package pantallas;

import java.awt.Font;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import modelo.CanchaView;

public class ResultadoCanchasPantalla extends JFrame {
    public ResultadoCanchasPantalla(List<CanchaView> canchas) {
        setTitle("Canchas Disponibles");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear un panel para listar las canchas
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Agregar una etiqueta de encabezado
        JLabel header = new JLabel("Canchas Disponibles:");
        header.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(header);

        // Mostrar cada cancha disponible
        for (CanchaView cancha : canchas) {
            JLabel canchaLabel = new JLabel(
                "Cancha: " + cancha.getNumero() +
                " | Tipo: " + cancha.getTipo() +
                " | Precio: $" + cancha.getPrecio()
            );
            canchaLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            panel.add(canchaLabel);
        }

        // Agregar el panel a un scroll pane por si hay muchas canchas
        JScrollPane scrollPane = new JScrollPane(panel);
        add(scrollPane);

        setVisible(true);
    }
}