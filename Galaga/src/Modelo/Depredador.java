package Modelo;

public class Depredador extends NaveEnemiga {
	
	public Depredador(Punto posicion, Punto velocidad, int radio) {
		//estado de la nave demoledor al iniciar
		estado = Estado.PASIVO;
		//posicion al iniciar
		this.posicion = posicion;
		//velocidad del enemigo
		this.velocidad = velocidad;
		//energia
		this.energia = 100;
	}

	@Override
	public Disparo disparar() {
		int anchoMisil = 16;
		int altoMisil = 16;
		// posicion del disparo : posX es la misma de la nave, posY es la misma menos el radio  
		Punto nuevaPosicion = new Punto(this.posicion.getX()+(ancho/2)-(anchoMisil/2), this.posicion.getY()-altoMisil);
		// depredador tira misil baja en velocidad -posY 
		Misil misil = new Misil(nuevaPosicion, new Punto(0, -1), anchoMisil, altoMisil);
		// retornamos el misil
		return misil;
	}

}
