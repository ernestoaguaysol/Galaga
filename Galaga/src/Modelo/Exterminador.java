package Modelo;

public class Exterminador extends NaveEnemiga{
	
	public Exterminador(Punto posicion, Punto velocidad, int radio) {
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
		// posicion del disparo : posX es la misma de la nave, posY es la misma menos el radio  
		Punto nuevaPosicion = new Punto(this.posicion.getX(), this.posicion.getY()-this.superficie.getRadio());
		// tira laser baja en velocidad -posY 
		Laser laser = new Laser(nuevaPosicion, new Punto(0, -2), 2);
		// retornamos laser
		return laser;
	}

}
