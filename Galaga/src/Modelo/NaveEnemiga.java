package Modelo;

public abstract class NaveEnemiga extends Nave{
	//solo las naves enemigas tienen estados
	//pueden ser...
	Estado estado;
	Punto posicioInicial;
	
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Punto getPosicioInicial() {
		return posicioInicial;
	}
	
	public void setPosicioInicial(Punto posicioInicial) {
		this.posicioInicial = posicioInicial;
	}

	
	
	
}
