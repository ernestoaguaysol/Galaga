package Modelo;

public abstract class Municion extends ObjetoMovil {
	
	private int danio;
	
	public Municion(int danio,int x, int y) {
		//
		super(x,y);
		this.danio = danio;
	}
	
	public int getDanio() {
		return danio;
	}
}
