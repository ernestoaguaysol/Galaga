package Modelo;

public class NaveJugador extends Nave{
	//las vidas del jugador
	private int vidas;
	
	public NaveJugador(Punto posicion,int ancho, int alto) {
		//cada nave del jugador comienza con 3 vidas
		this.vidas = 3;
		this.energia = 100;
		this.posicion = posicion;
		this.velocidad = new Punto(0, 0);
		
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
	//metodos para mover izq, der, arriba, abajo, detener
	public void moverDerecha() {
		velocidad.setX(2);
	}
	
	public void moverIzquierda() {
		velocidad.setX(-2);	
	}
	
	public void moverArriba() {
		velocidad.setY(2);
	}
	
	public void moverAbajo() {
		velocidad.setY(-2);		
	}
	
	public void detener() {
		this.velocidad.setX(0);
		this.velocidad.setY(0);
	}
	
	//-------------------------------------------------//
	
	@Override
	public void disminuirEnergia(int disminuir) {
		// disminuyo energia
		this.energia -= disminuir;			
		// si energia es menor o igual a cero
		if (this.energia <= 0) {
			// energia es cero
			this.energia = 0;
			// descontamos una vida
			this.vidas -=1;
		}
	}

	
	@Override
	public Disparo disparar() {
		int altoDisparo = 16;
		int anchoDisparo = 16;
		// posicion inicial del disparo  
		Punto nuevaPosicion = new Punto(this.posicion.getX()+(this.ancho/2)-(anchoDisparo/2), this.posicion.getY()+this.alto);
		// tira misil sube en velocidad posY 
		Misil misil = new Misil(nuevaPosicion, new Punto(0, 1), anchoDisparo, altoDisparo);
		// retornamos el misil
		return misil;
	}
}
