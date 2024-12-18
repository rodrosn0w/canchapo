package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.exceptions.CanchaNoEncontradaException;
import modelo.exceptions.CanchaOcupadaException;
import modelo.exceptions.MaximoReservasAlcanzadoException;
import modelo.exceptions.ReservaNoEncontradaException;

public class Cliente {
    private static int idSiguiente = 1;
    
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private List<Reserva> reservas;

    public Cliente(String nombre, String apellido, String telefono) {
        this.id = idSiguiente++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.reservas = new ArrayList<>();
    }

    public boolean tenesId(int idCliente) {
        return this.id == idCliente;
    }

    public int getId() {
        return id;
    }

    public Reserva reservarCancha(Club club, String numeroCancha, String fecha, String hora) throws CanchaOcupadaException, MaximoReservasAlcanzadoException, CanchaNoEncontradaException {
        validarPuedeReservar();
        Cancha cancha = club.buscarCancha(numeroCancha);
        
        if (cancha.estaDisponible(fecha, hora)) {
            Reserva reserva = new Reserva(this, cancha, fecha, hora);
            reservas.add(reserva);
            cancha.agregarReserva(reserva);
            return reserva;
        } else {
            throw new CanchaOcupadaException("La cancha está ocupada en la fecha y hora especificadas.");
        }
    }

    private void validarPuedeReservar() throws MaximoReservasAlcanzadoException {
        int cantidadReservasActivas = calcularReservasActivas();

        if (cantidadReservasActivas >= 2) {
            throw new MaximoReservasAlcanzadoException("El cliente tiene demasiadas reservas activas.");
        }
    }

    private int calcularReservasActivas() {
        int cantidadReservasActivas = 0;
        for (Reserva reserva : reservas) {
            if (reserva.estaActiva()) {
                cantidadReservasActivas++;
            }
        }
        return cantidadReservasActivas;
    }

    public boolean tenesReserva(int idReserva) {
        for (Reserva reserva : reservas) {
            if (reserva.sosReserva(idReserva)) {
                return true;
            }
        }
        return false;
    }

    public float calcularPrecioReserva(int idReserva) throws ReservaNoEncontradaException {
        Reserva reserva = buscarReserva(idReserva);
        return reserva.calcularPrecio();
    }

    private Reserva buscarReserva(int idReserva) throws ReservaNoEncontradaException {
        for (Reserva reserva : reservas) {
            if (reserva.sosReserva(idReserva)) {
                return reserva;
            }
        }
        throw new ReservaNoEncontradaException("No se encontró la reserva con ID: " + idReserva);
    }

}
