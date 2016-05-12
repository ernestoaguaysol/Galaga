package Modelo;

public class Circulo {
	private Punto centro;
	private int radio;
	
	public Circulo(Punto centro, int radio){
		this.centro = centro;
		this.radio = radio;
	}
	
	public void imprimir(){
		System.out.println("centro: "+this.centro.getX()+","+this.centro.getY());
		System.out.println("radio: "+this.radio);
	}
	
	public double perimetro(){
		return 2 * radio * Math.PI;
	}
	
	public double area(){
		return Math.pow(radio,2) * Math.PI;
	}
	
	void escalar(int factor){
		this.radio *= factor;
	}
	
	void desplazar(int desp_x, int desp_y){
		this.centro.setX(this.centro.getX()+ desp_x);
		this.centro.setY(this.centro.getY()+ desp_y);
	}
	
	static double distancia(Circulo c1, Circulo c2){
		return Punto.distancia(c1.centro, c2.centro)-(c1.radio+c2.radio);
	}
	
	static boolean seTocan(Circulo c1, Circulo c2){
		if(distancia(c1,c2) <= 0)
			return true;
		return false;
	}
	
	boolean loContiene(Circulo otro){
		if(this.radio >= Punto.distancia(this.centro,otro.centro)+otro.radio)
			return true;
		return false;
	}
}
