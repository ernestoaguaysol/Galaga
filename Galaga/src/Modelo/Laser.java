package Modelo;

public class Laser extends Disparo {

	public Laser(Punto posicion, Punto velocidad, int ancho, int alto) {
		//
		this.posicion = posicion;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = velocidad;
		this.danio = 20;
	}

}
