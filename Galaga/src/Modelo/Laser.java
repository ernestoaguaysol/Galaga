package Modelo;

public class Laser extends Disparo {

	public Laser(Punto posicion, Punto velocidad, int radio) {
		//
		this.posicion = posicion;
		this.superficie = new Circulo(posicion, radio);
		this.velocidad = velocidad;
		this.danio = 20;
	}

}
