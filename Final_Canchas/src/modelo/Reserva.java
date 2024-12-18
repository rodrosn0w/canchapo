package modelo;

public class Reserva {
    private static int idSiguiente = 1;

    private int id;
    private Cliente cliente;
    private Cancha cancha;
    private String fecha;
    private String hora;
    private EstadoReserva estado;

    public Reserva(Cliente cliente, Cancha cancha, String fecha, String hora) {
        this.id = idSiguiente++;
        this.cliente = cliente;
        this.cancha = cancha;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = EstadoReserva.ACTIVA;
    }

    public int getId() {
        return id;
    }

    public boolean estaActiva() {
        return estado == EstadoReserva.ACTIVA;
    }

    public Object getFecha() {
        return this.fecha;
    }

    public Object getHora() {
        return this.hora;
    }

    public boolean sosReserva(int idReserva) {
        return this.id == idReserva;
    }

    public float calcularPrecio() {
        return cancha.calcularPrecio();
    }

}
