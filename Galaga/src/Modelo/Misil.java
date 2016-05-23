package Modelo;

public class Misil extends Disparo {

	public Misil(Punto posicion, Punto velocidad,int ancho, int alto) {
		// 
		this.posicion = posicion;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = velocidad;
		this.danio = 50;
	}
	
}
