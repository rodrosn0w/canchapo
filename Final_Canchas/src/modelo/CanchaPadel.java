package modelo;

public class CanchaPadel extends Cancha {
    private float precioAlquilerPelota;
    private float precioAlquilerPaleta;

    public CanchaPadel(String numero, float precioBase, float precioAlquilerPelota, float precioAlquilerPaleta) {
        super(numero, precioBase);
        this.precioAlquilerPelota = precioAlquilerPelota;
        this.precioAlquilerPaleta = precioAlquilerPaleta;
    }

    @Override
    protected float calcularPrecio() {
        return precioBase + precioAlquilerPelota + precioAlquilerPaleta;
    }
    

    public CanchaView toView() {
    	return new CanchaView(this.numero, "Padel", calcularPrecio());
    }
    

}
