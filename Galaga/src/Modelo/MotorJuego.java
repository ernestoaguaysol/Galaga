package Modelo;

import java.util.LinkedList;

public class MotorJuego {
	private Espacio espacio;
	private Jugador naveJugador;
	private LinkedList<Nave> navesEnemigas;
	private LinkedList<Municion> municiones;
	
	public MotorJuego() {
		this.espacio = new Espacio(512, 512);
//		this.naveJugador = new Jugador(x, y, vidas);
		this.navesEnemigas = new LinkedList<>();
		this.municiones = new LinkedList<>();
	}
	
	public void agregarNaveEnemiga(Nave naveEnemiga) {
		this.navesEnemigas.add(naveEnemiga);
	}
	
	public Nave getNaveEnemiga(int posicion){
		return this.navesEnemigas.get(posicion);
	}
	
	public Nave getNaveJugador() {
		return naveJugador;
	}
	
	public void agregarMuniciones(Municion municion) {
		// TODO Auto-generated method stub
		this.municiones.add(municion);

	}
	
	public Municion getMuniciones(int posicion) {
		return this.municiones.get(posicion);
	}
	
	public void jugar() {
		
		
	}
	
}
