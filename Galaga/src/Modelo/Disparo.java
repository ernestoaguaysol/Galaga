package Modelo;

public abstract class Disparo extends ObjetoMovil {
	
	private int danio;
	
	public Disparo(int danio,int x, int y) {
		//
	
		this.danio = danio;
	}
	
	public int getDanio() {
		return danio;
	}
}
