package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.exceptions.CanchaNoEncontradaException;

public class Club {
    private static int idSiguiente = 1;

    private int id;
    private String nombre;
    private String direccion;
    //El club tiene una lista de canchas donde va guardando las canchas que tiene
    private List<Cancha> canchas;

    public Club(String nombre, String direccion) {
        this.id = idSiguiente++;
        this.nombre = nombre;
        this.direccion = direccion;
        this.canchas = new ArrayList<>();
    }

    
    //Estos metodos agregan canchas a la lista de canchas que tiene un club. 
    //Ademas, este metodo se utiliza en la fachada cada vez que creamos una cancha
    public void agregarCanchaTenis(String numero, float precioBase, float precioAlquilerRaqueta) {
        CanchaTenis canchaTenis = new CanchaTenis(numero, precioBase, precioAlquilerRaqueta);
        canchas.add(canchaTenis);
    }

    public void agregarCanchaPadel(String numero, float precioBase, float precioAlquilerPelota, float precioAlquilerPaleta) {
        CanchaPadel canchaPadel = new CanchaPadel(numero, precioBase, precioAlquilerPelota, precioAlquilerPaleta);
        canchas.add(canchaPadel);
    }

    public void agregarCanchaFutbol(String numero, float precioBase, int cantidadJugadores) {
        CanchaFutbol canchaFutbol = new CanchaFutbol(numero, precioBase, cantidadJugadores);
        canchas.add(canchaFutbol);
    }

    public boolean tenesId(int idClub) {
        return this.id == idClub;
    }

    public int getId() {
        return id;
    }

    public Cancha buscarCancha(String numeroCancha) throws CanchaNoEncontradaException {
        for (Cancha cancha : canchas) {
            if (cancha.sosCancha(numeroCancha)) {
                return cancha;
            }
        }
        throw new CanchaNoEncontradaException("Cancha no encontrada con número: " + numeroCancha);
    }
    
    public ClubView toView() {
    	//Crea una lista de objetos canchaView
        List<CanchaView> canchasView = new ArrayList<>();
        //Itera la lista de ESTA CLASE y va transformando cada cancha a canchaView
        for (Cancha cancha : this.canchas) {
            canchasView.add(cancha.toView());
        }
        return new ClubView(this.nombre, this.direccion, canchasView);
    }
    
    public List<CanchaView> canchasDisponiblesToView(String fecha, String hora) {
        List<CanchaView> disponibles = new ArrayList<>();
        for (Cancha cancha : this.canchas) { // Recorre todas las canchas del club
            if (cancha.estaDisponible(fecha, hora)) { // Verifica si la cancha está disponible
                disponibles.add(cancha.toView()); // Transforma la cancha en CanchaView
            }
        }
        return disponibles;
    }


}
