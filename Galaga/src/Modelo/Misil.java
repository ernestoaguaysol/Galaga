package Modelo;

public class Misil extends Disparo {

	public Misil(Punto posicion, Punto velocidad, int radio) {
		//
		this.posicion = posicion;
		this.superficie = new Circulo(posicion, radio);
		this.velocidad = velocidad;
		this.danio = 50;
	}
	
}
