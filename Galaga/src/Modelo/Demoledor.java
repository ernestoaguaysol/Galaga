package Modelo;

public class Demoledor extends NaveEnemiga {
	
	//probamos con CIRCULO 
	public Demoledor(Punto posicion,Punto velocidad,int radio) {
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
		// el demoledor dispara bombas con radio de 6
		Bomba bomba = new Bomba(posicion, velocidad, 6);
		return bomba;
	}
}
