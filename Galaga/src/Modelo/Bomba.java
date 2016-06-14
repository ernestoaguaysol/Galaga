package Modelo;

public class Bomba extends Disparo{

	public Bomba(Punto posicion, Punto velocidad, int ancho, int alto) {
		this.posicion = posicion;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = velocidad;
		this.danio = 100;
	}
	
}
