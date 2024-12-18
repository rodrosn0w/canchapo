package modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Cancha {

    protected String numero;
    protected List<Reserva> reservas;
    protected float precioBase;


    protected Cancha(String numero, float precioBase) {
        this.numero = numero;
        this.precioBase = precioBase;
        this.reservas = new ArrayList<>();
    }

    public String getNumero() {	
        return numero;
    }

    public boolean sosCancha(String numeroCancha) {
        return this.numero.equals(numeroCancha);
    }

    public boolean estaDisponible(String fecha, String hora) {
        for (Reserva reserva : reservas) {
            if (reserva.estaActiva() && reserva.getFecha().equals(fecha) && reserva.getHora().equals(hora)) {
                return false;
            }
        }
        return true;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    protected abstract float calcularPrecio();
    
    //Este toview es abstracto porque lo van a implementar de diferentes formas las subclases
    public abstract CanchaView toView();

}
