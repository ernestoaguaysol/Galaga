package Modelo;

public class Meteorito extends ObjetoEspacial {
	
	public Meteorito(Punto posicion, Punto velocidad, int ancho, int alto) {
		//
		this.posicion = posicion;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = velocidad;		
	}

}
