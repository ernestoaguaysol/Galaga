package Modelo;

public class Bomba extends Disparo{
	private Circulo explosion;

	public Bomba(Punto posicion, Punto velocidad, int radio) {
		this.explosion = new Circulo(posicion, 70);
		this.posicion = posicion;
		this.velocidad = velocidad;
		this.superficie = new Circulo(posicion, radio);
		this.danio = 100;
	}
	
	public Circulo getExplosion() {
		return explosion;
	}

}
