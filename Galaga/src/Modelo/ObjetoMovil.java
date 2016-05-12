package Modelo;


public abstract class ObjetoMovil {
	//posicion, velocidad, y superficie de todos los objetos
	protected Punto posicion;
	protected Punto velocidad;
	protected Rectangulo superficie;

	public Punto getPosicion(){
		return this.posicion;
	}
	
	public void mover() {
		this.posicion.setX(this.posicion.getX()+this.velocidad.getX());
		this.posicion.setY(this.posicion.getY()+this.velocidad.getY());
	}
	
}
