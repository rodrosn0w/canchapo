package main;
import controlador.Controlador;
import modelo.Cliente;
import modelo.Club;
import modelo.Reserva;
import modelo.SistemaAlquiler;
import modelo.exceptions.CanchaOcupadaException;
import pantallas.BuscarCanchasPantalla;

public class Main {
    public static void main(String[] args) throws Exception {
        initData();
        
        Controlador controlador = new Controlador();
        new BuscarCanchasPantalla(controlador);
    }

    private static void initData() throws Exception {
    	//Llamo a mi intstancia de la fachada para crear los clubs y las canchas
        SistemaAlquiler sistemaAlquiler = SistemaAlquiler.getInstance();

        Club clubA = sistemaAlquiler.crearClub("Club A", "Calle 123, Ciudad");
        sistemaAlquiler.crearCanchaFutbol(clubA.getId(), "1", 300, 5);
        sistemaAlquiler.crearCanchaPadel(clubA.getId(), "2", 500, 50, 100);
        sistemaAlquiler.crearCanchaTenis(clubA.getId(), "3", 200, 150);
        Club clubB = sistemaAlquiler.crearClub("Club B", "Calle 456, Ciudad");
        sistemaAlquiler.crearCanchaFutbol(clubB.getId(), "1", 1000, 7);
        sistemaAlquiler.crearCanchaTenis(clubB.getId(), "2", 1200, 50);
        sistemaAlquiler.crearCanchaPadel(clubB.getId(), "3", 1500, 10, 20);

        //Creo un par de clientes
        Cliente clienteJohn = sistemaAlquiler.crearCliente("John", "Doe", "1234567890");
        Cliente clienteJane = sistemaAlquiler.crearCliente("Jane", "Smith", "9876543210");

        //Creo algunas reservas
        Reserva reserva = sistemaAlquiler.reservarCancha(clubA.getId(), "1", clienteJohn.getId(), "2022-01-01", "10:00");
        System.out.println("Reserva generada con ID: " + reserva.getId());
        
        //Hago esto para ver si salta algun error
        try {	
            sistemaAlquiler.reservarCancha(clubA.getId(), "1", clienteJane.getId(), "2022-01-01", "10:00");
        } catch (CanchaOcupadaException e) {
            System.out.println("No se pudo realizar la reserva: " + e.getMessage());
        }

        float precioReserva = sistemaAlquiler.calcularPrecioReserva(reserva.getId());
        System.out.println("El precio de la reserva es: $" + precioReserva);
    }
}
