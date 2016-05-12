package Modelo;

public class Punto {
	private int x;
	private int y;
	
	public Punto(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Punto){
			Punto otro = (Punto) o;
			if(otro.x == this.x && otro.y == this.y){
				return true;
			}
		}
		return false;
	}
	
	static double distancia(Punto p1, Punto p2){
		return Math.sqrt(Math.pow(p1.x-p2.x, 2)+Math.pow(p1.y-p2.y, 2));
	}
}
