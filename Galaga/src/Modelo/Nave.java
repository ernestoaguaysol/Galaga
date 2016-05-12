package Modelo;

public abstract class Nave extends ObjetoMovil {
	//todas las naves deben tener energia(ej del 0 al 100)
	protected int energia;

	public int getEnergia() {
		return energia;
	}
	
	//metodo: disminuye la energia según en daño.
	public void disminuirEnergia(int disminuir) {
		//
		this.energia -= disminuir;
		
	}
}
