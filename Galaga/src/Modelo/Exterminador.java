package Modelo;

public class Exterminador extends NaveEnemiga{
	
	public Exterminador(Punto posicion, Punto velocidad, int ancho, int alto) {
		//estado de la nave demoledor al iniciar
		estado = Estado.PASIVO;
		//posicion al iniciar
		this.posicion = posicion;
		this.ancho = ancho;
		this.alto = alto;
		
		//velocidad del enemigo
		this.velocidad = velocidad;
		//energia
		this.energia = 100;
	}

	@Override
	public Disparo disparar() {
		int anchoLaser = 16;
		int altoLaser = 16;
		// posicion del disparo
		Punto nuevaPosicion = new Punto(this.posicion.getX()+(ancho/2)-(anchoLaser/2), this.posicion.getY()-altoLaser);
		// exterminador tira laser baja en velocidad -posY 
		Laser laser = new Laser(nuevaPosicion, new Punto(0, -1), anchoLaser, altoLaser);
		// retornamos el misil
		return laser;
	}

}
