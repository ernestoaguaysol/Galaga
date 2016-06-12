package Modelo;

public class Demoledor extends NaveEnemiga {
	
	//constructor
	public Demoledor(Punto posicion,Punto velocidad,int ancho, int alto) {
		//estado de la nave demoledor al iniciar
		estado = Estado.PASIVO;
		//posicion al iniciar
		this.posicion = posicion;
		this.posicioInicial = new Punto(posicion.getX(),posicion.getY());//tomaba siempre los valores de la posicion en un instante dado lo cambie para que genere un punto y concerve su posicion original de creacion.
		this.ancho = ancho;
		this.alto = alto;
		//velocidad del enemigo
		this.velocidad = velocidad;
		//energia
		this.energia = 100;
	}

	@Override
	public Disparo disparar() {
		// ancho de disparo
		int anchoDisparo = 16;
		// alto de disparo
		int altoDisparo = 16;
		// posicion del disparo  
		Punto nuevaPosicion = new Punto((this.posicion.getX()+(this.ancho/2))-(anchoDisparo/2), this.posicion.getY()-altoDisparo);
		// tira disparo baja en velocidad -y
		Misil misil = new Misil(nuevaPosicion, new Punto(0, -1), anchoDisparo, altoDisparo);
		// retornamos disparo
		return misil;
	}
}
