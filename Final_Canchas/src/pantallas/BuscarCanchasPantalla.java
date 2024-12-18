package pantallas;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controlador.Controlador;
import modelo.exceptions.CanchaNoEncontradaException;
import modelo.exceptions.CanchaOcupadaException;
import modelo.exceptions.ClubNoEncontradoException;

public class BuscarCanchasPantalla extends JFrame {
    private JTextField fechaField, horaField;
    private Controlador controlador;

    public BuscarCanchasPantalla(Controlador controlador) {
        this.controlador = controlador;
        initUI();
    }

    private void initUI() {
        setTitle("Buscar Canchas Disponibles");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        fechaField = new JTextField();
        fechaField.setMaximumSize(new Dimension(200, 25));

        horaField = new JTextField();
        horaField.setMaximumSize(new Dimension(200, 25));

        JButton buscarButton = new JButton("Buscar");

        // ActionListener para el botón Buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fecha = fechaField.getText();
                String hora = horaField.getText();

                try {
                    // Validar fecha y hora antes de llamar al controlador
                    validarFechaYHora(fecha, hora);

                    // Llamar al controlador si los datos son válidos
                    controlador.mostrarPantallaResultados(fecha, hora);
                    dispose(); // Cerrar esta pantalla si todo está bien

                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(BuscarCanchasPantalla.this,
                            "Fecha u hora con formato inválido. \nFormato correcto: Fecha (AAAA-MM-DD), Hora (HH:MM)",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (CanchaNoEncontradaException ex) {
                    JOptionPane.showMessageDialog(BuscarCanchasPantalla.this,
                            "No se encontró ninguna cancha disponible: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ClubNoEncontradoException ex) {
                    JOptionPane.showMessageDialog(BuscarCanchasPantalla.this,
                            "No se encontró el club: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (CanchaOcupadaException ex) {
                    JOptionPane.showMessageDialog(BuscarCanchasPantalla.this,
                            "La cancha seleccionada está ocupada: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(BuscarCanchasPantalla.this,
                            "Ocurrió un error inesperado: " + ex.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(new JLabel("Fecha (Año-Mes-Dia):"));
        panel.add(fechaField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(new JLabel("Hora (Hora:Minutos):"));
        panel.add(horaField);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(buscarButton);

        add(panel);
        setVisible(true);
    }

    // Método para validar fecha y hora
    private void validarFechaYHora(String fecha, String hora) throws DateTimeParseException {
        if (fecha.isEmpty() || hora.isEmpty()) {
            throw new DateTimeParseException("Los campos no pueden estar vacíos", fecha + " " + hora, 0);
        }
        
        // Validar formato de fecha
        LocalDate.parse(fecha); // Lanza DateTimeParseException si el formato es incorrecto

        // Validar formato de hora
        LocalTime.parse(hora); // Lanza DateTimeParseException si el formato es incorrecto
    }
}