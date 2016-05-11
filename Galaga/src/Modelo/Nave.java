package Modelo;

public class Nave extends ObjetoMovil {
	private String estado;
	private int energia;
	
	public Nave(int x, int y) {
		super(x, y);
		this.energia = 100;
		this.estado = "Pasivo";
	}
	
	public String getEstado() {
		return estado;
	}
	
	public int getEnergia() {
		return energia;
	}

	public void disminuirEnergia(int disminuir) {
		// TODO Auto-generated method stub
		this.energia -= disminuir;
		
	}
	
	public void cambiarEstadoAPasivo() {
		// TODO Auto-generated method stub
		this.estado = "Pasivo";

	}
	
	public void cambiarEstadoAActivo() {
		// TODO Auto-generated method stub
		this.estado = "Activo";

	}
}
