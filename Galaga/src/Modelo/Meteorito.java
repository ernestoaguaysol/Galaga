package Modelo;

public class Meteorito extends ObjetoEspacial {
	
	public Meteorito(Punto posicion, int radio, Punto velocidad) {
		//
		this.posicion = posicion;
		this.superficie = new Circulo(posicion, radio);
		this.velocidad = velocidad;
		
	}

}
