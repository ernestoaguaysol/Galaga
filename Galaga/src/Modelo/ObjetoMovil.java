package Modelo;


public abstract class ObjetoMovil {
	private Punto posicion;
	private Punto velocidad;
	
	public ObjetoMovil(int x, int y) {
		// 
		this.posicion = new Punto(x, y);
		this.velocidad = new Punto(x, y);
		
	}

	public Punto getPosicion(){
		return this.posicion;
	}
	
	public void setPosicion(int x, int y) {
		this.posicion.setX(x);
		this.posicion.setY(y);
	}
	
	public Punto getVelocidad() {
		return velocidad;
	}
	
	public void mover() {
		this.posicion.setX(this.posicion.getX()+this.velocidad.getX());
		this.posicion.setY(this.posicion.getY()+this.velocidad.getY());
	}
	
}
