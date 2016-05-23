package Modelo;

public class Meteorito extends ObjetoEspacial {
		
	public Meteorito(Punto posicion, Punto velocidad, int ancho, int alto, int danio) {
		//
		this.posicion = posicion;
		this.ancho = ancho;
		this.alto = alto;
		this.velocidad = velocidad;		
		this.danio = danio;
	}

}
