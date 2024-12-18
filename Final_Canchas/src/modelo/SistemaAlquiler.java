package modelo;

import java.util.ArrayList;
import java.util.List;

import modelo.exceptions.CanchaNoEncontradaException;
import modelo.exceptions.CanchaOcupadaException;
import modelo.exceptions.ClienteNoEncontradoException;
import modelo.exceptions.ClubNoEncontradoException;
import modelo.exceptions.MaximoReservasAlcanzadoException;
import modelo.exceptions.ReservaNoEncontradaException;

public class SistemaAlquiler {
    private static SistemaAlquiler instancia = new SistemaAlquiler();
    private List<Club> clubs;
    private List<Cliente> clientes;

    //Constructor
    private SistemaAlquiler() {
        this.clubs = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    //Singleton
    public static SistemaAlquiler getInstance() {
        return instancia;
    }

    
    //Metodos para crear al club, cliente y diferentes canchas. Estos metodos los llamo desde el main para crear los objetos
    //ademas, quedan agregados a la lista de clubs o clientes de esta clase.
    public Club crearClub(String nombre, String direccion) {
        Club club = new Club(nombre, direccion);
        clubs.add(club);
        return club;
    }

    public Cliente crearCliente(String nombre, String apellido, String telefono) {
        Cliente cliente = new Cliente(nombre, apellido, telefono);
        clientes.add(cliente);
        return cliente;
    }

    public void crearCanchaTenis(int idClub, String numero, float precioBase, float precioAlquilerRaqueta) throws ClubNoEncontradoException {
        Club club = buscarClub(idClub);
        club.agregarCanchaTenis(numero, precioBase, precioAlquilerRaqueta);
    }

    public void crearCanchaPadel(int idClub, String numero, float precioBase, float precioAlquilerPelota, float precioAlquilerPaleta) throws ClubNoEncontradoException {
        Club club = buscarClub(idClub);
        club.agregarCanchaPadel(numero, precioBase, precioAlquilerPelota, precioAlquilerPaleta);
    }

    public void crearCanchaFutbol(int idClub, String numero, float precioBase, int cantidadJugadores) throws ClubNoEncontradoException {
        Club club = buscarClub(idClub);
        club.agregarCanchaFutbol(numero, precioBase, cantidadJugadores);
    }

    
    //Metodo para reservar una cancha
    public Reserva reservarCancha(int idClub, String numeroCancha, int idCliente, String fecha, String hora) throws ClubNoEncontradoException, ClienteNoEncontradoException, CanchaOcupadaException, MaximoReservasAlcanzadoException, CanchaNoEncontradaException {
        Club club = buscarClub(idClub);
        Cliente cliente = buscarCliente(idCliente);
                
        return cliente.reservarCancha(club, numeroCancha, fecha, hora);
    }        
    
    //Metodo para buscar un club. Busca en la lista de esta clase
    private Club buscarClub(int idClub) throws ClubNoEncontradoException {
        for (Club club : clubs) {
            if (club.tenesId(idClub)) {
                return club;
            }
        }
        throw new ClubNoEncontradoException("Club no encontrado con ID: " + idClub);
    }

    //Lo mismo para clientes
    private Cliente buscarCliente(int idCliente) throws ClienteNoEncontradoException {
        for (Cliente cliente : clientes) {
            if (cliente.tenesId(idCliente)) {
                return cliente;
            }
        }
        throw new ClienteNoEncontradoException("Cliente no encontrado con ID: " + idCliente);
    }

    public float calcularPrecioReserva(int idReserva) throws ReservaNoEncontradaException {
        Cliente cliente = buscarClienteConReserva(idReserva);
        return cliente.calcularPrecioReserva(idReserva);
    }
        
    private Cliente buscarClienteConReserva(int idReserva) throws ReservaNoEncontradaException{
        for (Cliente cliente : clientes) {
            if (cliente.tenesReserva(idReserva)) {
                return cliente;
            }
        }
        throw new ReservaNoEncontradaException("No se encontró el cliente con la reserva con ID: " + idReserva);
    }
    
    public List<Club> getClubs() {
        return this.clubs;
    }
    
    public List<CanchaView> buscarCanchasDisponibles(String fecha, String hora) {
        List<CanchaView> canchasDisponibles = new ArrayList<>();
        // Iterar sobre todos los clubes y obtener las canchas disponibles
        for (Club club : clubs) {
            List<CanchaView> canchasDelClub = club.canchasDisponiblesToView(fecha, hora);
            canchasDisponibles.addAll(canchasDelClub); // Agregar todas las canchas disponibles del club
        }
        
        return canchasDisponibles;
    }


}
