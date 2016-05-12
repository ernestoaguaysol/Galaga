package Modelo;

public class Exterminador extends NaveEnemiga{
	
	public Exterminador(Punto posicion) {
		//estado de la nave demoledor al iniciar
		estado = Estado.PASIVO;
		//posicion al iniciar
		this.posicion = posicion;
		//velocidad del enemigo
		this.velocidad = new Punto(2, 2);
	}

}
