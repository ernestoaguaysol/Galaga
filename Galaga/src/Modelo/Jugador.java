package Modelo;

public class Jugador extends Nave{
	private int vidas;
	
	public Jugador(int x, int y,int vidas) {
		super(x, y);
		this.vidas = vidas;
		// TODO Auto-generated constructor stub
	}
	
	public void quitarVida() {
		this.vidas-=1;
	}
	
	public void sumarVida() {
		this.vidas+=1;
	}
	
	public int getVidas() {
		return vidas;
	}
	
	public void moverDerecha() {
		
	}
	public void moverIzquierda() {
		
	}
	public void moverArriba() {
		
	}
	public void moverAbajo() {
		
	}

}
