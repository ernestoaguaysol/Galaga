package Modelo;

public class Espacio {
	//ancho y alto del espacio
	private int ancho;
	private int alto;
	
	public Espacio(int ancho , int alto) {
		// 
		this.ancho = 512;
		this.alto = 512;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public boolean estaDentroDeEspacio(Punto posicion) {
		// 
		if (posicion.getX() < 0 || posicion.getX() > this.ancho-1) {
			return false;
		}
		if (posicion.getY() < 0 || posicion.getY() > this.alto-1) {
			return false;
		}
		return true;
	}
}
