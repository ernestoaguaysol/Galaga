package Modelo;

public class NaveJugador extends Nave{
	//las vidas del jugador
	private int vidas;
	
	public NaveJugador(Punto posicion,int radio) {
		//cada nave del jugador comienza con 3 vidas
		this.vidas = 3;
		this.energia = 100;
		this.posicion = posicion;
		this.superficie = new Circulo(posicion, radio);
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
	//metodos para mover izq, der, arriba, abajo
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
		// posicion del disparo : posX es la misma de la nave, posY es la misma mas el radio  
		Punto nuevaPosicion = new Punto(this.posicion.getX(), this.posicion.getY()+this.superficie.getRadio());
		// tira misil sube en velocidad posY 
		Misil misil = new Misil(nuevaPosicion, new Punto(0, 4), 2);
		// retornamos el misil
		return misil;
	}

	public void detener() 
	{
		this.velocidad.setX(0);
		this.velocidad.setY(0);
		
	}
}
