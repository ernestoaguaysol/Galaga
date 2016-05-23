package Modelo;


public abstract class ObjetoMovil {
	//posicion, velocidad, y superficie de todos los objetos
	protected Punto posicion;
	protected Punto velocidad;
	protected Rectangulo superficie;
	protected int ancho;
	protected int alto;

	//
	public Punto getPosicion(){
		return this.posicion;
	}
	
	public Rectangulo getSuperficie() {
		this.superficie = new Rectangulo(posicion, new Punto(posicion.getX() + (ancho), posicion.getY() + (alto)));
		return this.superficie; 
	}
	
	//
	public void mover() {
		this.posicion.setX(this.posicion.getX()+this.velocidad.getX());
		this.posicion.setY(this.posicion.getY()+this.velocidad.getY());
	}
}
