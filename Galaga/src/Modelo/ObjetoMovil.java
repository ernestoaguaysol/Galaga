package Modelo;

import java.util.Observable;

public abstract class ObjetoMovil extends Observable{
	//posicion, velocidad, y superficie de todos los objetos
	protected Punto posicion;
	protected Punto velocidad;
	protected int ancho;
	protected int alto;
	protected int danio;
	private Rectangulo superficie;
	

	//
	public Punto getPosicion(){
		return this.posicion;
	}
	
	//para XML
	public Punto getVelocidad() {
		return velocidad;
	}
	
	// para XML
	public int getAncho() {
		return ancho;
	}
	
	public int getAlto() {
		return alto;
	}
	
	public Rectangulo getSuperficie() {
		this.superficie = new Rectangulo(posicion, new Punto(posicion.getX() + (ancho), posicion.getY() + (alto)));
		return this.superficie; 
	}
	
	//
	public void mover() {
		this.posicion.setX(this.posicion.getX()+this.velocidad.getX());
		this.posicion.setY(this.posicion.getY()+this.velocidad.getY());
		this.setChanged();
		this.notifyObservers();
	}
	
	public int getDanio(){
		return this.danio;
	}
	
	public void setDanio(int danio) {
		this.danio = danio;
	}
}
