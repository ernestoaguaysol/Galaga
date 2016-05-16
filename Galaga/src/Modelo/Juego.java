package Modelo;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Juego {
	private Espacio espacio;
	private NaveJugador naveJugador;
	private LinkedList<NaveEnemiga> navesEnemigas;
	private LinkedList<ObjetoEspacial> objetosEspaciales;
	private LinkedList<Disparo> disparosEnemigos;
	private LinkedList<Disparo> disparosJugador;
	
	//aleatorio para usar en varios
	private Random aleatorio = new Random();
	
	
	public Juego() {
		this.espacio = new Espacio(512,512);
		this.naveJugador = new NaveJugador(new Punto(255, 100), 30);
		this.objetosEspaciales = new LinkedList<>();
		this.disparosEnemigos = new LinkedList<>();
		this.disparosJugador = new LinkedList<>();
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
		Destructor des2 = new Destructor(new Punto(155, 355), new Punto(2, 2), 15);
		Destructor des3 = new Destructor(new Punto(255, 355), new Punto(2, 2), 15);
		Destructor des4 = new Destructor(new Punto(355, 355), new Punto(2, 2), 15);
		Destructor des5 = new Destructor(new Punto(455, 355), new Punto(2, 2), 15);
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
//		for (int i = 0; i < 10; i++) {
			
		// movemos todos los objetos moviles
		this.moverTodo();
			
		//chequeamos si hay colicionas..Continuar metodo..
		/*
		 *jugador con enemigos (Mueren ambos)
		 *jugador con objetos espaciales (...)
		 *jugador con disparos enemigos (...)
		 *enemigo con disparos jugador (...)
		 */
		this.chequearColiciones();
		
		// 
		for (NaveEnemiga naveEnemiga : navesEnemigas) {				
			if (this.decidirDisparo()) {
				this.disparosEnemigos.add(naveEnemiga.disparar());
			}
		}

		//si hay que atacar
		if (this.decidirAtaque()) {
			//ataca la primer nave enemiga que encuentra 
			// en estado PASIVO
			this.atacar();
		}
		
		//decide si aparece un objeto espacial en el espacio
		this.decidirObjetoEspacial();

		//la pantala es el espacio 512x512
		this.chequearFueraDePantalla();
		
		
		
//		}//FIN FOR
	}
	
	//movemos todos los objetos moviles
	private void moverTodo() {
		//
		//movemos la nave jugador
		this.naveJugador.mover();
		
		//movemos todas las naves enemigas
		for (NaveEnemiga n : navesEnemigas) {
			n.mover();
		}

		//movemos todos los objetos espaciales
		for (ObjetoEspacial o : objetosEspaciales) {
			o.mover();
		}
		
		//movemos todos lOS DISPAROS ENEMIGOS
		for (Disparo disparoEnemigo : disparosEnemigos) {
			disparoEnemigo.mover();
		}
		
		//movemos todos lOS disparos del jugador
		for (Disparo disparoJugador : disparosJugador) {
			disparoJugador.mover();
		}
		
	}

	// metodo decide si lanzar un objeto espacial
	// cual objeto va a ser creado y en que posicion
	private void decidirObjetoEspacial() {
		//contamos la cantidad de estrellas fugaz
		int cantEstrellaFugaz = 0;
		//contamos la cantidad de estrellas fugaz
		int cantMeteorito = 0;
		//contamos la cantidad de estrellas fugaz
		int cantAsteriode = 0;
		// recorremos los objetos espaciales
		for (ObjetoEspacial objetoEspacial : objetosEspaciales) {
			// si el objeto espacian es una estrella fugaz 
			if (objetoEspacial.getClass().equals(EstrellaFugaz.class)) {
				// incrementamos el contador
				cantEstrellaFugaz++;
			}
			//
			if (objetoEspacial.getClass().equals(Meteorito.class)) {
				cantMeteorito++;
			}
			// 
			if (objetoEspacial.getClass().equals(Asteroide.class)) {
				cantAsteriode++;
			}
			
		}
		
		// si no hay estrella fugaz
		if (cantEstrellaFugaz == 0) {
			// aleatorio de 0 a 9 ¿si es 0?
			if (this.aleatorio.nextInt(10) == 0) {
				// creamos una nueva estrella fugaz
				EstrellaFugaz nuevaEstrellaFugaz = new EstrellaFugaz(new Punto(0, 300), 15, new Punto(2, 2));
				// agregamos la nueva estrella a la lista de objetos espaciales
				this.objetosEspaciales.add(nuevaEstrellaFugaz);
			}
		}
		if (cantMeteorito < 3) {
			// aleatorio de 0 a 4 ¿si es 0?
			if (this.aleatorio.nextInt(5) == 0) {
				// creamos un nuevo meteorito
				Meteorito nuevoMeteorito = new Meteorito(new Punto(0, 255), 10, new Punto(2, 2));
				// agregamos el nuevo meteorito a la lista de objetos espaciales
				this.objetosEspaciales.add(nuevoMeteorito);
			}
		}
		if (cantAsteriode < 2) {
			// aleatorio de 0 a 8 ¿si es 0?
			if (this.aleatorio.nextInt(8) == 0) {
				// creamos un nuevo asteroide
				Asteroide nuevoAsteroide = new Asteroide(new Punto(511, 300), 20, new Punto(3, 2));
				// agregamos el nuevo asteroide la a la lista de objetos espaciales
				this.objetosEspaciales.add(nuevoAsteroide);
			}
		}
		
	}

	// metodo atacar. manda a atacar la primera nave enemiga que
	// encuentre con el estado PASIVO
	private void atacar() {
		//recorremos las naves enemigas 
		for (NaveEnemiga naveEnemiga : navesEnemigas) {
			// si la nave enemiga tiene estado pasivo
			if (naveEnemiga.estado.equals(Estado.PASIVO)) {
				// cambiamos el estado a KAMIKAZE
				naveEnemiga.estado = Estado.KAMIKAZE;
				// retornamos
				return;
			}
		}
	}

	// metodo para decidir los ataques de las naves enemigas
	private boolean decidirAtaque() {
		/*
		 * si hay menos de 2 naves enemigas atacando entonces 
		 * vamos a atacar con la primer nave enemiga de la lista
		 * */
		//contador de naves kamikazes
		int contador = 0;
		
		//recorremos la lista de naves enemigas
		for (NaveEnemiga naveEnemiga : navesEnemigas) {
			//si la nave tiene estado kamikaze
			if (naveEnemiga.estado.equals(Estado.KAMIKAZE)) {
				// incrementamos contador
				contador++;
				// si el contador es menor a 2
				if (contador <= 2) {
					// retornamos verdadero (es decir que se va a atacar)
					return true;
				}
			}
		}
		
		// de otro modo no atacamos
		return false;
	}

	//
	private void chequearFueraDePantalla() {
		//chequeamos el jugador
		if (!this.espacio.estaDentroDeEspacio(this.naveJugador.posicion)) {
			this.reubicarPosicion(this.naveJugador);
		}
		
		//chequeamos todas las naves enemigas
		for (NaveEnemiga naveEnemiga : navesEnemigas) {
			if (!this.espacio.estaDentroDeEspacio(naveEnemiga.posicion)) {
				this.reubicarPosicion(naveEnemiga);
			}
		}
		
		//chequeamos todos los objetos espaciales del momento
		for (ObjetoEspacial objetoEspacial : objetosEspaciales) {
			//si está afuera del espacio
			if (!this.espacio.estaDentroDeEspacio(objetoEspacial.posicion)) {
				//si se cumple la condición lo eliminamos de la lista
				this.objetosEspaciales.remove(objetoEspacial);
			}
		}
		
		//chequeamos todos lod disparos del momento
		for (Disparo disparo : disparosEnemigos) {
			//si está fuera del espacio
			if (!this.espacio.estaDentroDeEspacio(disparo.posicion)) {
				//lo eliminamos de la lista de disparos
				this.disparosEnemigos.remove(disparo);
			}
		}
		
	}
	
	private void reubicarPosicion(ObjetoMovil objeto){
		//reubicar X
		if (objeto.posicion.getX() < 0) {
			objeto.posicion.setX(511);
		}
		if (objeto.posicion.getX() >= 512) {
			objeto.posicion.setX(0);
		}
		//----------------------------------//
		//reubicar Y
		if (objeto.posicion.getY() < 0) {
			objeto.posicion.setY(511);
		}
		if (objeto.posicion.getY() >= 512) {
			objeto.posicion.setY(0);
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

	/*las coliciones son entre:
	 * naveEnemiga y naveJugador
	 * 
	 * */
	private void chequearColiciones() {
		//recorremos todas las naves enemigas
		for (NaveEnemiga naveEnemiga : navesEnemigas) {
			//si las superficies colisionan
			if (Circulo.colisionan(naveEnemiga.superficie, this.naveJugador.superficie)) {
				// disminuimos el 100% de energia
				this.naveJugador.disminuirEnergia(100);
				
				// quitamos la nave enemiga que chocó
				this.navesEnemigas.remove(naveEnemiga);
			}
		}
		
		//recorremos los disparos "actuales"
		for (Disparo disparo : disparosEnemigos) {
			//si las superficies colisionan
			if (Circulo.colisionan(disparo.superficie, this.naveJugador.superficie)) {
				//
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
	
	public void menu(){ 
		//iteraciones	
		int iteraciones = 0;
		//mientras las iteraciones sean menor a 10
		while (iteraciones < 10) {
			System.out.println("Que desea hacer con la Nave Jugador?");
			System.out.println("1. Mover a la izquierda");
			System.out.println("2. Mover a la derecha");
			System.out.println("3. Mover arriba");
			System.out.println("4. Mover abajo");
			System.out.println("5. Disparar");
			System.out.println("6. Detener la nave");
			System.out.print("Ingrese su opcion: ");
			int opcionJugar = Integer.parseInt(extraer().next());
			
			switch (opcionJugar) {
			case 1:
				naveJugador.moverIzquierda();
				break;
			case 2:
				naveJugador.moverDerecha();
				break;
			case 3:
				naveJugador.moverArriba();
				break;
			case 4:
				naveJugador.moverAbajo();
				break;
			case 5:
				// agrego un nuevo disparo a la lista de disparos jugador
				this.disparosJugador.add(this.naveJugador.disparar());
				break;
			case 6:
				// detener la nave
				this.naveJugador.velocidad.setX(0);
				this.naveJugador.velocidad.setY(0);
				break;
			default:
				break;
			}
			// jugar
			this.jugar();
			
			// imprimir los objetos del espacio
			this.imprimir();
			
			// incrementamos iteraciones
			iteraciones++;
		}			
			
			
	}
	
	private void imprimir() {
		// jugador
		System.out.println("NAVE JUGADOR: PosX="+naveJugador.getPosicion().getX()+
				"; PosY="+naveJugador.getPosicion().getY()+"; Energia="+this.naveJugador.getEnergia());
		// enemigos
		for (NaveEnemiga naveEnemiga : navesEnemigas) {			
			System.out.println("Nave Enemiga Estado:"+naveEnemiga.estado+" posX="+naveEnemiga.getPosicion().getX()+
					"; posY="+naveEnemiga.getPosicion().getY()+"; Energia="+naveEnemiga.getEnergia());
		}
		
		//disparos jugador
		for (Disparo disparoJ : disparosJugador) {
			System.out.println("Disparo Jugador; Daño="+disparoJ.danio+"; PosX="+disparoJ.posicion.getX()+"; PosY"+disparoJ.posicion.getY());
		}
		
		//disparo enemigo
		for (Disparo disparoE : disparosEnemigos) {
			System.out.println("Disparo Enemigo; Daño="+disparoE.danio+"; PosX="+disparoE.posicion.getX()+"; PosY"+disparoE.posicion.getY());
		}
		
		//objetos espaciales
		for (ObjetoEspacial objetoEspacial : objetosEspaciales) {
			System.out.println("Objetos Espaciales; PosX="+objetoEspacial.posicion.getX()+
					"; PosY="+objetoEspacial.posicion.getY());
		}
		
	}

	private static Scanner extraer() {
		return new Scanner(System.in);
	}
	
}
