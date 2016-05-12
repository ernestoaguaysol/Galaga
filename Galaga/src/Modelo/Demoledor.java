package Modelo;

public class Demoledor extends NaveEnemiga {
	
	public Demoledor(Punto posicion) {
		//estado de la nave demoledor al iniciar
		estado = Estado.PASIVO;
		//posicion al iniciar
		this.posicion = posicion;
		//velocidad del enemigo
		this.velocidad = new Punto(2, 2);
		
	}
}
