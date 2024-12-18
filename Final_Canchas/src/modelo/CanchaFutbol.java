package modelo;

public class CanchaFutbol extends Cancha {

    private int cantidadJugadores;
    

    public CanchaFutbol(String numero, float precioBase, int cantidadJugadores) {
        super(numero, precioBase);
        this.cantidadJugadores = cantidadJugadores;
    }

    @Override
    protected float calcularPrecio() {
        return precioBase * cantidadJugadores;
    }
    

    public CanchaView toView() {
    	return new CanchaView(this.numero, "Futbol", calcularPrecio());
    }
}
