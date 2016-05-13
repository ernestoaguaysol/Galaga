package Modelo;

public class Asteroide extends ObjetoEspacial{
	
	public Asteroide(Punto posicion, int radio, Punto velocidad) {
		//
		this.posicion = posicion;
		this.superficie = new Circulo(posicion, radio);
		this.velocidad = velocidad;
		
	}
}
