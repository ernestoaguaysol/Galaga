package Modelo;

import java.util.LinkedList;

public class MotorJuego {
	private Espacio espacio;
	private Jugador naveJugador;
	private LinkedList<ObjetoMovil> objetosMoviles;
//	private LinkedList<Municion> municiones;
	
	public MotorJuego() {
		this.espacio = new Espacio(512, 512);
//		this.naveJugador = new Jugador(x, y, vidas);
		this.objetosMoviles = new LinkedList<>();
//		this.municiones = new LinkedList<>();
	}
	
	public void agregarObjetoMovil(Nave naveEnemiga) {
		this.objetosMoviles.add(naveEnemiga);
	}
	
//	public Nave getNaveEnemiga(int posicion){
//		return this.objetosMoviles.
//	}
	
	public Nave getNaveJugador() {
		return naveJugador;
	}
	
//	public void agregarMuniciones(Municion municion) {
//		// TODO Auto-generated method stub
//		this.municiones.add(municion);
//
//	}
//	
//	public Municion getMuniciones(int posicion) {
//		return this.municiones.get(posicion);
//	}
	
	public void jugar() {
		
		
		for (int i = 0; i < 10; i++) {
			
			for (int j = 0; j < this.objetosMoviles.size(); j++) {
				this.objetosMoviles.get(j).mover();
			}
		}
		
		
		
	}
	
}
