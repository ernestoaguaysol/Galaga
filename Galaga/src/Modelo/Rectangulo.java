package Modelo;

public class Rectangulo {
	private Punto esquina_min;
	private Punto esquina_max;
	
	// 
	public Rectangulo(Punto min, Punto max){
		this.esquina_min = min;
		this.esquina_max = max;
	}
	
	public boolean colisiona(Rectangulo otro){
		if(this.esquina_max.getX() < otro.esquina_min.getX() || otro.esquina_max.getX() < this.esquina_min.getX())
			return false;
		if(this.esquina_max.getY() < otro.esquina_min.getY() || otro.esquina_max.getY() < this.esquina_min.getY())
			return false;
		return true;
	}

}