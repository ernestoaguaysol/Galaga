package Modelo;

public class Demoledor extends NaveEnemiga {
	
	//constructor
	public Demoledor(Punto posicion,Punto velocidad,int ancho, int alto) {
		//estado de la nave demoledor al iniciar
		estado = Estado.PASIVO;
		//posicion al iniciar
		this.posicion = posicion;
		this.posicioInicial = posicion;
		this.ancho = ancho;
		this.alto = alto;
		//velocidad del enemigo
		this.velocidad = velocidad;
		//energia
		this.energia = 100;
	}

	@Override
	public Disparo disparar() {
		// ancho de bomba
		int anchoBomba = 16;
		// alto de bomba
		int altoBomba = 16;
		// posicion del disparo  
		Punto nuevaPosicion = new Punto((this.posicion.getX()+(this.ancho/2))-(anchoBomba/2), this.posicion.getY()-altoBomba);
		// tira bomba baja en velocidad -y
		Bomba bomba = new Bomba(nuevaPosicion, new Punto(0, -1), anchoBomba, altoBomba);
		// retornamos bomba
		return bomba;
	}
}
