package Modelo;

public class NaveJugador extends Nave{
	//las vidas del jugador
	private int vidas;
	
	public NaveJugador() {
		//cada nave del jugador comienza con 3 vidas
		this.vidas = 3;
	}
	
	//metodo. devuelve la cantidad de vidas del jugador
	public int getVidas() {
		return vidas;
	}
	
	//metodo: suma una vida
	public void sumarVida() {
		if (this.vidas < 3) {			
			this.vidas+=1;
		}
	}
	
	//--------------------------------------------------//
	//metodos para mover izq, der, arriba, abajo
	public void moverDerecha() {
		velocidad.setX(1);
	}
	
	public void moverIzquierda() {
		velocidad.setX(-1);
		
	}
	
	public void moverArriba() {
		velocidad.setY(1);
	}
	
	public void moverAbajo() {
		velocidad.setY(-1);		
	}
	
	//-------------------------------------------------//

}
