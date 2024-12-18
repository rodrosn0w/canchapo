package modelo;

import java.security.KeyStore.PrivateKeyEntry;

public class CanchaView {
	//Necesito NUMERO, TIPO y PRECIO
	//Ademas, como necesito datos del club, tambien me creo una referencia al clubView. 
	//Esto es porque una cancha pertenece a un club
	private String numero;
	private String tipo;
	private float precio;
	
	public CanchaView(String numero, String tipo, float precio) {
        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;

    }

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	
	

}
