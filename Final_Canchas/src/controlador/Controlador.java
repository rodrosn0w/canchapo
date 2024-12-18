package controlador;

import java.util.List;

import modelo.CanchaView;
import modelo.SistemaAlquiler;
import modelo.exceptions.CanchaNoEncontradaException;
import modelo.exceptions.CanchaOcupadaException;
import modelo.exceptions.ClubNoEncontradoException;
import pantallas.ResultadoCanchasPantalla;

public class Controlador {
    private SistemaAlquiler sistemaAlquiler = SistemaAlquiler.getInstance();

    public void mostrarPantallaResultados(String fecha, String hora) 
            throws CanchaNoEncontradaException, ClubNoEncontradoException, CanchaOcupadaException {
        // Buscar canchas disponibles
        List<CanchaView> canchasDisponibles = sistemaAlquiler.buscarCanchasDisponibles(fecha, hora);

        if (canchasDisponibles.isEmpty()) {
            throw new CanchaNoEncontradaException("No hay canchas disponibles en la fecha y hora seleccionadas.");
        }

        // Crear pantalla de resultados si todo está bien
        new ResultadoCanchasPantalla(canchasDisponibles);
    }
}
