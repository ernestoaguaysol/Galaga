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
		// posicion del disparo : posX es la misma de la nave, posY es la misma menos el radio  
		Punto nuevaPosicion = new Punto(this.posicion.getX(), this.posicion.getY()-this.superficie.getRadio());
		
		// tira bomba baja en velocidad -posY 
		Bomba bomba = new Bomba(nuevaPosicion, new Punto(0, -1), 6);
		// retornamos bomba
		return bomba;
	}
}
