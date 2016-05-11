package Modelo;

import java.awt.Point;

public class Espacio {
	private int ancho;
	private int alto;
	
	public Espacio(int ancho, int alto) {
		// 
		this.ancho = ancho;
		this.alto = alto;
	}
	
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public boolean estaDentroDeEspacio(Point posicion) {
		// 
		if (posicion.getX() < 0 || posicion.getX() > this.ancho) {
			return false;
		}
		if (posicion.getY() < 0 || posicion.getY() > this.alto) {
			return false;
		}
		return true;
	}
}
