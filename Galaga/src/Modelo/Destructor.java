package Modelo;

public class Destructor extends NaveEnemiga{
	
	public Destructor(Punto posicion,Punto velocidad, int radio) {
		//estado de la nave demoledor al iniciar
		estado = Estado.PASIVO;
		//posicion al iniciar
		this.posicion = posicion;
		//velocidad del enemigo
		this.velocidad = new Punto(2, 2);
		//energia
		this.energia = 100;
		//superficie
		this.superficie = new Circulo(posicion, radio);
	}

	@Override
	public Disparo disparar() {
		//
		Laser laser = new Laser(posicion, velocidad, 4);
		return laser;
	}
}
