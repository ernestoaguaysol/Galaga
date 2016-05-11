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
}
