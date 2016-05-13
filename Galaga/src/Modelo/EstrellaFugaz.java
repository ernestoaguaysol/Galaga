package Modelo;

public class EstrellaFugaz extends ObjetoEspacial{
	
	public EstrellaFugaz(Punto posicion, int radio, Punto velocidad) {
		//
		this.posicion = posicion;
		this.superficie = new Circulo(posicion, radio);
		this.velocidad = velocidad;
		
	}
}
