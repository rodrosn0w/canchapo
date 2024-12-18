package modelo;

import java.util.List;

import modelo.exceptions.ClubNoEncontradoException;

public class ClubView {
	
	//Necesito NOMBRE y DIRECCION
	//Ademas, necesito tener una referencia de las canchas, y como un club tiene una lista de canchas, agrego eso
	private String nombre;
	private String direccion;
	private List<CanchaView> canchas;
	
	//Creo el constructor de la clase view para club
	public ClubView(String nombre, String direccion, List<CanchaView> canchas) {
		this.nombre = nombre;
	    this.direccion = direccion;
	    this.canchas = canchas;
	}

	//Creo getters que voy a necesitar (click derecho, resaurce, generate getters and setters)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	
	
	

}
