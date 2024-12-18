package modelo;

public class CanchaTenis extends Cancha {
    private float precioAlquilerRaqueta;

    public CanchaTenis(String numero, float precioBase, float precioAlquilerRaqueta) {
        super(numero, precioBase);
        this.precioAlquilerRaqueta = precioAlquilerRaqueta;
    }

    @Override
    protected float calcularPrecio() {
        return precioBase + precioAlquilerRaqueta;
    }
    

    public CanchaView toView() {
    	return new CanchaView(this.numero, "Tenis", calcularPrecio());
    }

}
