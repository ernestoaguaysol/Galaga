package Modelo;

public class Bomba extends Disparo{
	private Circulo explosion;

	public Bomba(Punto posicion, Punto velocidad, int ancho, int alto) {
		this.explosion = new Circulo(posicion, 70);
		this.posicion = posicion;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = velocidad;
		this.danio = 100;
	}
	
	public Circulo getExplosion() {
		return explosion;
	}
}
