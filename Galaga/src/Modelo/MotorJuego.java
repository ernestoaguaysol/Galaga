package Modelo;

import java.util.LinkedList;

public class MotorJuego {
	private Espacio espacio;
	private NaveJugador naveJugador;
	private LinkedList<NaveEnemiga> navesEnemigas;
	private LinkedList<ObjetoEspacial> objetosEspaciales;
	private LinkedList<Disparo> disparos;
	
	public MotorJuego() {
		this.espacio = new Espacio();
		this.naveJugador = new NaveJugador(null, 0);
		this.objetosEspaciales = new LinkedList<>();
		this.disparos = new LinkedList<>();
	}
	
	public void jugar() {
		
		for (int i = 0; i < 10; i++) {
			
		}
	}
}
