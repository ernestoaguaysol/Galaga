package Modelo;

public class Rectangulo {
	Punto esquina_min;
	Punto esquina_max;
	
	Rectangulo(Punto min, Punto max){
		this.esquina_min = min;
		this.esquina_max = max;
	}
	
	public boolean interseca(Rectangulo otro){
		if(this.esquina_max.getX() < otro.esquina_min.getX() || otro.esquina_max.getX() < this.esquina_min.getX())
			return false;
		if(this.esquina_max.getY() < otro.esquina_min.getY() || otro.esquina_max.getY() < this.esquina_min.getY())
			return false;
		return true;
	}
	
	public boolean contiene(Rectangulo otro){
		if(this.esquina_max.getX() < otro.esquina_max.getX() || otro.esquina_min.getX() < this.esquina_min.getX())
			return false;
		if(this.esquina_max.getY() < otro.esquina_max.getY() || otro.esquina_min.getY() < this.esquina_min.getY())
			return false;
		return true;
	}

}