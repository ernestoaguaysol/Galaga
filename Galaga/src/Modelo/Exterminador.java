package Modelo;

public class Exterminador extends NaveEnemiga{
	
	public Exterminador(Punto posicion, Punto velocidad, int ancho, int alto) {
		//estado de la nave demoledor al iniciar
		estado = Estado.PASIVO;
		//posicion al iniciar
		this.posicion = posicion;
		this.posicioInicial = posicion;
		this.ancho = ancho;
		this.alto = alto;
		
		//velocidad del enemigo
		this.velocidad = velocidad;
		//energia
		this.energia = 100;
	}

	@Override
	public Disparo disparar() {
		int anchoDisparo = 16;
		int altoDisparo = 16;
		// posicion del disparo
		Punto nuevaPosicion = new Punto(this.posicion.getX()+(ancho/2)-(anchoDisparo/2), this.posicion.getY()-altoDisparo);
		// tira laser baja en velocidad -posY 
		Laser laser = new Laser(nuevaPosicion, new Punto(0, -1), anchoDisparo, altoDisparo);
		// retornamos
		return laser;
	}

}
