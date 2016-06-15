package Modelo;

public class Depredador extends NaveEnemiga {
	
	public Depredador(Punto posicion, Punto velocidad, int alto, int ancho) {
		//estado de la nave demoledor al iniciar
		estado = Estado.PASIVO;
		//posicion al iniciar
		this.posicion = posicion;
		this.posicioInicial = new Punto(posicion.getX(),posicion.getY());//tomaba siempre los valores de la posicion en un instante dado lo cambie para que genere un punto y concerve su posicion original de creacion.
		//velocidad del enemigo
		this.velocidad = velocidad;
		//energia
		this.energia = 100;
		
		this.alto = alto;
		this.ancho = ancho;
	}

	@Override
	public Disparo disparar() {
		int anchoDisparo = 16;
		int altoDisparo = 16;
		// posicion del disparo : posX es la misma de la nave, posY es la misma menos el radio  
		Punto nuevaPosicion = new Punto(this.posicion.getX()+(ancho/2)-(anchoDisparo/2), this.posicion.getY()-altoDisparo);
		// tira dispar baja en velocidad -posY 
		Laser laser = new Laser(nuevaPosicion, new Punto(0, -1), anchoDisparo, altoDisparo);
		// retornamos
		return laser;
	}

}
