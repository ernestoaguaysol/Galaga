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
		//superficie
		this.superficie = new Circulo(posicion, radio);
	}

	@Override
	public Disparo disparar() {
		// depredador tira misil
		Misil misil = new Misil(posicion, velocidad, 4);
		return misil;
	}

}
