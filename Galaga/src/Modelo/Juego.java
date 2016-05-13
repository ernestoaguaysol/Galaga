package Modelo;

import java.util.LinkedList;
import java.util.Random;

public class Juego {
	private Espacio espacio;
	private NaveJugador naveJugador;
	private LinkedList<NaveEnemiga> navesEnemigas;
	private LinkedList<ObjetoEspacial> objetosEspaciales;
	private LinkedList<Disparo> disparos;
	
	//aleatorio para usar en varios
	private Random aleatorio = new Random();
	
	
	public Juego() {
		this.espacio = new Espacio(512,512);
		this.naveJugador = new NaveJugador(new Punto(255, 100), 30);
		this.objetosEspaciales = new LinkedList<>();
		this.disparos = new LinkedList<>();
		this.navesEnemigas = new LinkedList<>();
	}
	
	public void cargar() {
		// lista de naves enemigas
		//cracion de demoledores
		Demoledor dem1 = new Demoledor(new Punto(55, 455), new Punto(2, 2), 15);
		Demoledor dem2 = new Demoledor(new Punto(155, 455), new Punto(2, 2), 15);
		Demoledor dem3 = new Demoledor(new Punto(255, 455), new Punto(2, 2), 15);
		Demoledor dem4 = new Demoledor(new Punto(355, 455), new Punto(2, 2), 15);
		Demoledor dem5 = new Demoledor(new Punto(455, 455), new Punto(2, 2), 15);
		//creacion de destructores
		Destructor des1 = new Destructor(new Punto(55, 355), new Punto(2, 2), 15);
		Destructor des2 = new Destructor(new Punto(55, 355), new Punto(2, 2), 15);
		Destructor des3 = new Destructor(new Punto(55, 355), new Punto(2, 2), 15);
		Destructor des4 = new Destructor(new Punto(55, 355), new Punto(2, 2), 15);
		Destructor des5 = new Destructor(new Punto(55, 355), new Punto(2, 2), 15);
		//los añadimos a la lista de naves enemigas
		this.navesEnemigas.add(dem1);
		this.navesEnemigas.add(dem2);
		this.navesEnemigas.add(dem3);
		this.navesEnemigas.add(dem4);
		this.navesEnemigas.add(dem5);
		///
		this.navesEnemigas.add(des1);
		this.navesEnemigas.add(des2);
		this.navesEnemigas.add(des3);
		this.navesEnemigas.add(des4);
		this.navesEnemigas.add(des5);
		//
		//.....continuar....
		
	}
	
	
	//el bucle principal del juego
	public void jugar() {
		
		//diez iteraciones
		for (int i = 0; i < 10; i++) {
			
			naveJugador.mover();

			for (NaveEnemiga n : navesEnemigas) {
				n.mover();
			}
			
			this.chequearColiciones();
			
			
			for (NaveEnemiga naveEnemiga : navesEnemigas) {				
				if (this.decidirDisparo()) {
					this.disparos.add(naveEnemiga.disparar());
				}
			}
			
			
		}
	}

	//la dejamos publica por ahora
	public boolean decidirDisparo() {
		//si aleatorio es cero
		if (aleatorio.nextInt(5) == 0) {
//			System.out.println("es cero");
			return true;
		}
//		System.out.println("no es cero");
		return false;
	}

	private void chequearColiciones() {
		//recorremos todas las naves enemigas
		for (NaveEnemiga naveEnemiga : navesEnemigas) {
			//si las superficies colisionan
			if (Circulo.colisionan(naveEnemiga.superficie, this.naveJugador.superficie)) {
				//continuar...
			}
		}
		
		//recorremos los disparos "actuales"
		for (Disparo disparo : disparos) {
			//si las superficies colisionan
			if (Circulo.colisionan(disparo.superficie, this.naveJugador.superficie)) {
				//continuar....
			}
		}
		
		//recorremos lo objetos espaciales "actuales"
		for (ObjetoEspacial objetoEspacial : objetosEspaciales) {
			//si las superficies colisionan
			if (Circulo.colisionan(objetoEspacial.superficie, this.naveJugador.superficie)) {
				//continuar....
			}
		}
		
	}
	
	
}
