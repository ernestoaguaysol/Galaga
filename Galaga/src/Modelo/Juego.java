package Modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

import Modelo.NaveJugador;



public class Juego extends Observable{
	//posicion inicial para el jugador
	private final int posInicialX = 239;
	private final int posInicialY = 15;
	
	// contiene un espacio, un jugador, y listas de naves enemigas
	//objetos espaciales, disparos enemigos, disparos jugador
	private Espacio espacio;
	private NaveJugador naveJugador;
	private LinkedList<NaveEnemiga> navesEnemigas;
	private LinkedList<NaveEnemiga> navesNuevas;
	private LinkedList<ObjetoEspacial> objetosEspaciales;
	private LinkedList<ObjetoEspacial> objetosEspacialesNuevos;
	private LinkedList<Disparo> disparosEnemigos;
	private LinkedList<Disparo> disparosEnemigosNuevos;
	private CopyOnWriteArrayList<Disparo> disparosJugador;
	private LinkedList<Disparo> disparosJugadorNuevos;
	private LinkedList<ObjetoMovil> objetosMuertos;
	
	
	//
	private int puntaje = 0;
	private boolean gameWin;
	private Historial historial;
	
	private boolean pausa;
	//aleatorio para usar en varios
	private Random aleatorio = new Random();
	
	private Semaphore semaforo;
	
	// constructor Juego
	public Juego() {
		this.espacio = new Espacio(512,512);
		this.naveJugador = new NaveJugador(new Punto(this.posInicialX, this.posInicialY),32,32,3,100);
		this.objetosEspaciales = new LinkedList<>();
		this.objetosEspacialesNuevos = new LinkedList<>();
		this.disparosEnemigos = new LinkedList<>();
		this.disparosEnemigosNuevos = new LinkedList<>();
		this.disparosJugador = new CopyOnWriteArrayList<>();
		this.disparosJugadorNuevos = new LinkedList<>();
		this.navesEnemigas = new LinkedList<>();
		this.navesNuevas = new LinkedList<>();
		this.objetosMuertos = new LinkedList<>();
		this.historial = new Historial();
		this.pausa = false;
		this.semaforo = new Semaphore(1);
	}
	
	public void cargarNivel1() {
		// lista de naves enemigas
		//cracion de demoledores
		Demoledor dem1 = new Demoledor(new Punto(47, 447), new Punto(0, 0), 32,32);
		Demoledor dem2 = new Demoledor(new Punto(111, 447), new Punto(0, 0), 32,32);
		Demoledor dem3 = new Demoledor(new Punto(175, 447), new Punto(0, 0), 32,32);
		Demoledor dem4 = new Demoledor(new Punto(239, 447), new Punto(0, 0), 32,32);
		Demoledor dem5 = new Demoledor(new Punto(303, 447), new Punto(0, 0), 32,32);
		Demoledor dem6 = new Demoledor(new Punto(367, 447), new Punto(0, 0), 32,32);
		Demoledor dem7 = new Demoledor(new Punto(431, 447), new Punto(0, 0), 32,32);
		//creacion de destructores
		Destructor des1 = new Destructor(new Punto(47, 383), new Punto(0, 0), 32,32);
		Destructor des2 = new Destructor(new Punto(111, 383), new Punto(0, 0), 32,32);
		Destructor des3 = new Destructor(new Punto(175, 383), new Punto(0, 0), 32,32);
		Destructor des4 = new Destructor(new Punto(239, 383), new Punto(0, 0), 32,32);
		Destructor des5 = new Destructor(new Punto(303, 383), new Punto(0, 0), 32,32);
		Destructor des6 = new Destructor(new Punto(367, 383), new Punto(0, 0), 32,32);
		Destructor des7 = new Destructor(new Punto(431, 383), new Punto(0, 0), 32,32);
		
		
		
		//creacion de depredador
		Depredador dep1 = new Depredador(new Punto(47, 319), new Punto(0, 0), 32,32);
		Depredador dep2 = new Depredador(new Punto(111, 319), new Punto(0, 0), 32,32);
		Depredador dep3 = new Depredador(new Punto(175, 319), new Punto(0, 0), 32,32);
		Depredador dep4 = new Depredador(new Punto(239, 319), new Punto(0, 0), 32,32);
		Depredador dep5 = new Depredador(new Punto(303, 319), new Punto(0, 0), 32,32);
		Depredador dep6 = new Depredador(new Punto(367, 319), new Punto(0, 0), 32,32);
		Depredador dep7 = new Depredador(new Punto(431, 319), new Punto(0, 0), 32,32);
		//los a�adimos a la lista de naves enemigas
		this.navesNuevas.add(dem1);
		this.navesNuevas.add(dem2);
		this.navesNuevas.add(dem3);
		this.navesNuevas.add(dem4);
		this.navesNuevas.add(dem5);
		this.navesNuevas.add(dem6);
		this.navesNuevas.add(dem7);
		//
		this.navesNuevas.add(des1);
		this.navesNuevas.add(des2);
		this.navesNuevas.add(des3);
		this.navesNuevas.add(des4);
		this.navesNuevas.add(des5);
		this.navesNuevas.add(des6);
		this.navesNuevas.add(des7);
		///
		this.navesNuevas.add(dep1);
		this.navesNuevas.add(dep2);
		this.navesNuevas.add(dep3);
		this.navesNuevas.add(dep4);
		this.navesNuevas.add(dep5);
		this.navesNuevas.add(dep6);
		this.navesNuevas.add(dep7);
		//
		
		this.setChanged();
		this.notifyObservers();
	}
	
	
	//el bucle principal del juego
	public void jugar() {

		while (this.naveJugador.getVidas() > 0 && this.navesEnemigas.size() > 0){
			
			if(!pausa){
			
				try {
					Thread.sleep(15); // tiempo de pausa
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// si el estado del jugador est� en cero 
				if (this.naveJugador.getEnergia() <= 0) {
					// seteamos la posicion del jugador a la pocicion inicial
					this.naveJugador.getPosicion().setX(posInicialX);
					this.naveJugador.getPosicion().setY(posInicialY);
					// detenemos la nave
					this.naveJugador.detener(); // para cuando vuelva a la posicion inicial no se este moviendo
					// renovamos la energia al 100%
					this.naveJugador.renovarEnergia();
				}
				
				//la pantala es el espacio 512x512
				this.chequearFueraDePantalla();
					
				// movemos todos los objetos moviles
				this.moverTodo();
				// recorremos cada nave enemiga
				for (NaveEnemiga naveEnemiga : navesEnemigas) {				
					// decidimos si la nave enemiga dispara
					if (this.decidirDisparo()) {
						this.disparosEnemigosNuevos.add(naveEnemiga.disparar());
						this.setChanged();
						this.notifyObservers();
					}
				}
				
				
				
				//decidimos si la nave enemiga ataca
				this.decidirAtaque();
						
				//decide si aparece un objeto espacial en el espacio
				this.decidirObjetoEspacial();
						
				//movemos la nave jugador
				this.naveJugador.mover();

				//chequeamos si hay colicionas
				this.chequearColisiones();
				
			}else{ // entra en pausa
				
				try {
					Thread.sleep(500); //
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}// fin if else			
			if(this.navesEnemigas.size()==0){
				gameWin=true;
			}
		}//fin while
		
		
	}
	
	//movemos todos los objetos moviles
	private void moverTodo() {
		
		//movemos todas las naves enemigas
		for (NaveEnemiga n : navesEnemigas) {
			
			//chequeo si la nave tiene estado KAMIKASE tiene otro patron asignado
			if (n.getEstado().equals(Estado.KAMIKAZE)){
				//metodo mover de la clase Kamikase Algoritmo
				Mover.moverKamikaze(this.naveJugador.getPosicion(), n);
				n.mover();
			}else if(n.getEstado().equals(Estado.VOLVIENDO)){//si el estado es volviendo
				Mover.moverVolviendo(n);
				n.mover();
			}else {
				Mover.moverPasivo(n); //o si es Pasivo...
				n.mover();
			}
			
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
		try {
			this.semaforo.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Disparo disparoJugador : disparosJugador) {
			disparoJugador.mover();
		}
		this.semaforo.release();
		
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
			// si	 el objeto espacian es una estrella fugaz 
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
			
		}// fin for
		
		// si no hay estrella fugaz
		if (cantEstrellaFugaz == 0) {
			int pos = this.aleatorio.nextInt(384)+64;
			if (this.naveJugador.getVidas() == 1) { // si el jugador tiene una vida
				// aleatorio
				if (this.aleatorio.nextInt(500) == 50) {
					// creamos una nueva estrella fugaz
					EstrellaFugaz nuevaEstrellaFugaz = new EstrellaFugaz(new Punto(pos,511), new Punto(0, -1), 32,32);
					// agregamos la nueva estrella a la lista de objetos espaciales
					this.objetosEspacialesNuevos.add(nuevaEstrellaFugaz);
					setChanged();
					notifyObservers();
				}
				
			}
			if (this.naveJugador.getEnergia() < 50) {
				// aleatorio
				if (this.aleatorio.nextInt(500) == 50) {
					// creamos una nueva estrella fugaz
					EstrellaFugaz nuevaEstrellaFugaz = new EstrellaFugaz(new Punto(pos,511), new Punto(0, -1), 32,32);
					// agregamos la nueva estrella a la lista de objetos espaciales
					this.objetosEspacialesNuevos.add(nuevaEstrellaFugaz);
					setChanged();
					notifyObservers();
				}				
			}
		}
		
		if (cantMeteorito < 2) {
			// aleatorio
			if (this.aleatorio.nextInt(500) == 0) {
				int i = this.aleatorio.nextInt(3); // para decidir de que lado sale izq,der,centro
				int pos = this.aleatorio.nextInt(384)+64;
				if (i == 0) { // salimos de la izquierda
					if (pos < 192) {
						// creamos un nuevo meteorito
						Meteorito nuevoMeteorito = new Meteorito(new Punto(0, pos), new Punto(1, 0),32,32,25);
						// agregamos el nuevo meteorito a la lista de objetos espaciales
						this.objetosEspacialesNuevos.add(nuevoMeteorito);
						setChanged();
						notifyObservers();	
						
					}else{
						
						// creamos un nuevo meteorito
						Meteorito nuevoMeteorito = new Meteorito(new Punto(0, pos), new Punto(1, -1),32,32,25);
						// agregamos el nuevo meteorito a la lista de objetos espaciales
						this.objetosEspacialesNuevos.add(nuevoMeteorito);
						setChanged();
						notifyObservers();	
						
					}
					
				}else if (i == 1) {// derecha
					if (pos < 192) {
						// creamos un nuevo meteorito
						Meteorito nuevoMeteorito = new Meteorito(new Punto(479, pos), new Punto(-1, 0),32,32,25);
						// agregamos el nuevo meteorito a la lista de objetos espaciales
						this.objetosEspacialesNuevos.add(nuevoMeteorito);
						setChanged();
						notifyObservers();	
						
					}else{
						
						// creamos un nuevo meteorito
						Meteorito nuevoMeteorito = new Meteorito(new Punto(479, pos), new Punto(-1, -1),32,32,25);
						// agregamos el nuevo meteorito a la lista de objetos espaciales
						this.objetosEspacialesNuevos.add(nuevoMeteorito);
						setChanged();
						notifyObservers();	
						
					}
				}else if (i == 2) { // centro
					// creamos un nuevo meteorito
					Meteorito nuevoMeteorito = new Meteorito(new Punto(pos, 511), new Punto(0, -1),32,32,25);
					// agregamos el nuevo meteorito a la lista de objetos espaciales
					this.objetosEspacialesNuevos.add(nuevoMeteorito);
					setChanged();
					notifyObservers();
				}// fin if
			}// fin if aleatorio
		}// fin meteorito
		
		if (cantAsteriode < 2) {
			if (this.aleatorio.nextInt(500) == 0) {
				int i = this.aleatorio.nextInt(3); // para decidir de que lado sale izq,der,centro
				int pos = this.aleatorio.nextInt(384)+64;
				if (i == 0) { // derecha
					if (pos < 192) { // lo lanzamos horizontal
						// creamos un nuevo asteroide
						Asteroide nuevoAsteroide = new Asteroide(new Punto(0, pos),new Punto(1, 0),32,32,40);
						// agregamos el nuevo asteroide la a la lista de objetos espaciales
						this.objetosEspacialesNuevos.add(nuevoAsteroide);
						setChanged();
						notifyObservers();
						
					}else { // lo lanzamos diagonal
						// creamos un nuevo asteroide
						Asteroide nuevoAsteroide = new Asteroide(new Punto(0, pos),new Punto(1, -1),32,32,40);
						// agregamos el nuevo asteroide la a la lista de objetos espaciales
						this.objetosEspacialesNuevos.add(nuevoAsteroide);
						setChanged();
						notifyObservers();
						
					}
				}else if (i == 1) { // izquierda
					if (pos < 192) { // lo lanzamos horizontal
						// creamos un nuevo asteroide
						Asteroide nuevoAsteroide = new Asteroide(new Punto(479, pos),new Punto(-1, 0),32,32,40);
						// agregamos el nuevo asteroide la a la lista de objetos espaciales
						this.objetosEspacialesNuevos.add(nuevoAsteroide);
						setChanged();
						notifyObservers();
						
					}else { // lo lanzamos diagonal
						// creamos un nuevo asteroide
						Asteroide nuevoAsteroide = new Asteroide(new Punto(479, pos),new Punto(-1, -1),32,32,40);
						// agregamos el nuevo asteroide la a la lista de objetos espaciales
						this.objetosEspacialesNuevos.add(nuevoAsteroide);
						setChanged();
						notifyObservers();
						
					}
				}else if(i == 2){ // centro
					
					// creamos un nuevo asteroide
					Asteroide nuevoAsteroide = new Asteroide(new Punto(pos, 511),new Punto(0, -1),32,32,40);
					// agregamos el nuevo asteroide la a la lista de objetos espaciales
					this.objetosEspacialesNuevos.add(nuevoAsteroide);
					setChanged();
					notifyObservers();
					
				}
			}//fin if
		}//
	}////fin

	// metodo para decidir los ataques de las naves enemigas
	private void decidirAtaque() {
		//contador de naves kamikazes
		int contadorDeKamikaze = 0;
		//recorremos la lista de naves enemigas
		for (NaveEnemiga naveEnemiga : navesEnemigas) {
			//si la nave tiene estado kamikaze
			if (naveEnemiga.getEstado().equals(Estado.KAMIKAZE)) {
				// incrementamos contador
				contadorDeKamikaze++;
			}
		}
		
		// si quedan menos 4 naves enemigas
		if (this.navesEnemigas.size() < 4 && navesEnemigas.size() > 0) {
			if (0 == contadorDeKamikaze) {
				// cambiamos el estato de algunas de las naves aleatorio 
				this.navesEnemigas.get(aleatorio.nextInt(navesEnemigas.size())).estado = Estado.KAMIKAZE;
			}
		}else if (contadorDeKamikaze < 1) {// si hay menos de 1 kamikaze generamos uno aleatorio
			// recorremos las naves enemigas
			for (NaveEnemiga naveEnemiga : navesEnemigas) {
				//si la nave enemiga no tiene estado kamikaze
				if (!naveEnemiga.estado.equals(Estado.KAMIKAZE)) {
					// decidimos si se lanza al ataque de modo aleatorio
					if (this.aleatorio.nextInt(10) == 0) {
						// cambiamos el estado de la nave enemiga
						naveEnemiga.estado = Estado.KAMIKAZE;	
						// retornamos
						return;
					}
				}
			}
		}
	}

	//
	private void chequearFueraDePantalla() {
		//chequeamos el jugador
		if (!this.espacio.estaDentroDeEspacio(this.naveJugador.getSuperficie())) {
			this.reubicarPosicion(this.naveJugador);
		}
		
		//chequeamos todas las naves enemigas		
		for (int i = 0; i < this.navesEnemigas.size(); i++) {
			// si no esta en el espacio
			if (!this.espacio.estaDentroDeEspacio(this.navesEnemigas.get(i).getSuperficie())) {
				//si la esquina max X es menor a cero o esquina min X es meyor al ancho-1 del espacio
				if (this.navesEnemigas.get(i).getSuperficie().getEsquina_max().getX() < 0 || this.navesEnemigas.get(i).getSuperficie().getEsquina_min().getX() > this.espacio.getAncho()-1) {
					this.objetosMuertos.add(this.navesEnemigas.get(i));
					this.setChanged();
					this.notifyObservers();
					// eliminamos la nave
					this.navesEnemigas.remove(i);
					//
					i--;
				}else {
					this.reubicarPosicion(this.navesEnemigas.get(i));
				}
	
			}
			
		}
		
		//chequeamos todos los objetos espaciales del momento
		for (int i = 0; i < this.objetosEspaciales.size(); i++) {
			//si est� afuera del espacio
			if (!this.espacio.estaDentroDeEspacio(this.objetosEspaciales.get(i).getSuperficie())) {
				this.objetosMuertos.add(this.objetosEspaciales.get(i));
				this.setChanged();
				this.notifyObservers();
				//lo eliminamos de la lista
				this.objetosEspaciales.remove(i);
				//
				i--;
			}
		}
		
		//chequeamos todos lod disparos del momento
		for (int i = 0; i < this.disparosEnemigos.size(); i++) {
			//si est� fuera del espacio
			if (!this.espacio.estaDentroDeEspacio(this.disparosEnemigos.get(i).getSuperficie())) {
				this.objetosMuertos.add(this.disparosEnemigos.get(i));
				this.setChanged();
				this.notifyObservers();
				//lo eliminamos de la lista de disparos
				this.disparosEnemigos.remove(i);
				// 
				i--;
			}
		}
		
		// chequeamos todos los disparos del jugador
		for (int i = 0; i < this.disparosJugador.size(); i++) {
			//si est� fuera del espacio
			if (!this.espacio.estaDentroDeEspacio(this.disparosJugador.get(i).getSuperficie())) {
				this.objetosMuertos.add(this.disparosJugador.get(i));
				this.setChanged();
				this.notifyObservers();
				//lo eliminamos del espacio
				this.disparosJugador.remove(i);
				//
				i--;
			}
		}
	}
	
	private void reubicarPosicion(ObjetoMovil objeto){
		//reubicar X
		if (objeto.getSuperficie().getEsquina_max().getX() < 0) {
			objeto.getPosicion().setX(511);
		}
		if (objeto.getSuperficie().getEsquina_min().getX() >= 512) {
			objeto.getPosicion().setX(0);
		}
		//----------------------------------//
		//reubicar Y
		if (objeto.getSuperficie().getEsquina_max().getY() < 0) {
			objeto.getPosicion().setY(511);
		}
		if (objeto.getSuperficie().getEsquina_min().getY() >= 512) {
			objeto.getPosicion().setY(0);
		}
		
	}

	// metodo decidir disparo, si hay mas de tres disparos
	// en el espacio ninguna nave dispara,
	// si hay menos de 3, hacemos un ramdom
	private boolean decidirDisparo() {
		// cantidad de disparos es menor a 3
		if (this.disparosEnemigos.size() < 3) {
			//si aleatorio es cero
			if (aleatorio.nextInt(20) == 0) {
				return true;
			}
		}
		return false;
	}

	/*las coliciones*/
	private void chequearColisiones() {
		// recorremos todas las naves enemigas
		for (int i = 0; i < navesEnemigas.size(); i++) {
			//si las superficies colicionan
			if (navesEnemigas.get(i).getSuperficie().colisiona(this.naveJugador.getSuperficie())) {
				// si la energia del jugador es mayor a cero
				if (this.naveJugador.getEnergia() > 0) {
					// descontamos 50% de energia de jugador
					this.naveJugador.disminuirEnergia(50);					
				}
				this.objetosMuertos.add(navesEnemigas.get(i));
				this.setChanged();
				this.notifyObservers();
				//eliminamos el enemigo
				navesEnemigas.remove(i);
				//retrocedemos un paso en i (ya que al remover
				// la lista se modifica el tama�o e indices tambien)
				i--;
			}
		}
		
		//recorremos los disparos enemigos "actuales"
		for (int i = 0; i < disparosEnemigos.size(); i++) {
			//si el disparo enemigo coliciona con jugador
			if (disparosEnemigos.get(i).getSuperficie().colisiona(this.naveJugador.getSuperficie())) {
				// si la energia del jugador es mayor a cero
				if (this.naveJugador.getEnergia() > 0) {
					//descontamos energia segun el da�o que cause el disparo
					this.naveJugador.disminuirEnergia(disparosEnemigos.get(i).getDanio());					
				}
				// agregamos a objetos muertos
				objetosMuertos.add(disparosEnemigos.get(i));
				this.setChanged();
				this.notifyObservers();
				//eliminamos el disparo que impact� en la nave
				disparosEnemigos.remove(i);
				// retocedemos un paso en i
				i--;
			}
		}
		
		//recorremos lo objetos espaciales "actuales"
		for (int i = 0; i < this.objetosEspaciales.size(); i++) {
			//si el objeto espacial colisiona con nave jugador
			if (this.objetosEspaciales.get(i).getSuperficie().colisiona(this.naveJugador.getSuperficie())) {
				// si el objeto espacial es una estrella fugaz
				if (this.objetosEspaciales.get(i).getClass().equals(EstrellaFugaz.class)) {
					// le sumamos una vida al jugador
					this.naveJugador.sumarVida();
					System.out.println("Estrella Fugaz te ha regalado una vida");
					// agregamos a objetos muertos
					objetosMuertos.add(objetosEspaciales.get(i));
					this.setChanged();
					this.notifyObservers();
					// eliminamos la estrella fugaz
					this.objetosEspaciales.remove(i);
					//retrocedemos un paso en i
					i--;
					//----------------------------------------
					//faltaria sumar puntaje
				}else { //si no es una estrella fugaz
					//si la energia del jugador es mayor a cero
					if (this.naveJugador.getEnergia() > 0) {
						// diminuir energia segun el da�o que cause el objeto espacial 
						this.naveJugador.disminuirEnergia(this.objetosEspaciales.get(i).getDanio());						
					}
					// agregamos a objetos muertos
					objetosMuertos.add(objetosEspaciales.get(i));
					this.setChanged();
					this.notifyObservers();
					// eliminemos el objeto espacial
					this.objetosEspaciales.remove(i);
					//retrocedemos un paso en i
					i--;
				}
			}		
		}
		
		// ahora los disparos del jugador con las naves enemigas
		for (int j = 0; j < this.disparosJugador.size(); j++) {
			// recorremos todos los enemigos
			for (int i = 0; i < this.navesEnemigas.size(); i++) {
				// si la nave enemiga coliciona con el el disparo jugador
				if (this.navesEnemigas.get(i).getSuperficie().colisiona(this.disparosJugador.get(j).getSuperficie())) {
					// disminuimos energia del enemigo
					this.navesEnemigas.get(i).disminuirEnergia(this.disparosJugador.get(j).getDanio());
					// si la energia es menor o igual a cero
					if (this.navesEnemigas.get(i).getEnergia() <= 0) {
						// agregamos a objetos muertos
						objetosMuertos.add(navesEnemigas.get(i));
						this.setChanged();
						this.notifyObservers();
						// eliminamos la nave enemiga
						this.navesEnemigas.remove(i);
						// retrocedemos un paso en i
						i--;
					}
					// agregamos a objetos muertos
					objetosMuertos.add(disparosJugador.get(j));
					this.setChanged();
					this.notifyObservers();
					// eliminamos el disparo jugador que impact�
					this.disparosJugador.remove(j);
					// retrocedemos un paso en j
					j--;
					// salimos del for i
					break;
				}
			}
		}
		
		//ahora los disparos jugador con los disparos enemigos
		for (int i = 0; i < this.disparosJugador.size(); i++) {
			//
			for (int j = 0; j < this.disparosEnemigos.size(); j++) {
				// si los disparos oponentes colicionan
				if (this.disparosJugador.get(i).getSuperficie().colisiona(this.disparosEnemigos.get(j).getSuperficie())) {
					// agregamos a objetos muertos
					objetosMuertos.add(disparosJugador.get(i));
					this.setChanged();
					this.notifyObservers();
					// removemos el disparo jugador
					this.disparosJugador.remove(i);
					// retrocedemos u n paso en i
					i--;
					// agregamos a objetos muertos
					objetosMuertos.add(disparosEnemigos.get(j));
					// removemos el disparo enemigo
					this.setChanged();
					this.notifyObservers();
					this.disparosEnemigos.remove(j);
					// retrocetemos en j;
					j--;
					// volvemos a iterar el primer for
					break;
				}
			}
		}
		
		
		// disparos jugador con objetos espaciales
		for (int i = 0; i < this.disparosJugador.size(); i++) {
			//
			for (int j = 0; j < this.objetosEspaciales.size(); j++) {
				//
				if (this.disparosJugador.get(i).getSuperficie().colisiona(this.objetosEspaciales.get(j).getSuperficie())) {
					// 
					if (this.objetosEspaciales.get(j).getClass().equals(EstrellaFugaz.class)) {
						// le sumamos una vida al jugador
						this.naveJugador.sumarVida();
						System.out.println("Estrella Fugaz te ha regalado una vida");
					}else{
						// agregamos a objetos muertos
						objetosMuertos.add(objetosEspaciales.get(j));
						this.setChanged();
						this.notifyObservers();
						// eliminamos la estrella fugaz
						this.objetosEspaciales.remove(j);
						//retrocedemos un paso en j
						j--;
						// agregamos a objetos muertos
						objetosMuertos.add(disparosJugador.get(i));
						this.setChanged();
						this.notifyObservers();
						// eliminamos disparo jugador
						this.disparosJugador.remove(i);
						// retrocedemos en i
						i--;
						// comenzamos de nuevo
						break;
					}
					//----------------------------------------
					//faltaria sumar puntaje
					
				}
			}
			
		}
		
//		this.setChanged();
//		this.notifyObservers();
		
	}
	
	
	public void imprimir() {
		// enemigos
		for (NaveEnemiga naveEnemiga : navesEnemigas) {			
			System.out.println("Nave Enemiga Estado:"+naveEnemiga.estado+" posX="+naveEnemiga.getPosicion().getX()+
					"; posY="+naveEnemiga.getPosicion().getY()+"; Energia="+naveEnemiga.getEnergia());
		}
		// jugador
		System.out.println("NAVE JUGADOR: PosX="+naveJugador.getPosicion().getX()+
				"; PosY="+naveJugador.getPosicion().getY()+"; Energia="+this.naveJugador.getEnergia()+"; Vidas="+this.naveJugador.getVidas());
		
		//disparos jugador
		for (Disparo disparoJ : disparosJugador) {
			System.out.println("Disparo Jugador; Da�o="+disparoJ.danio+"; PosX="+disparoJ.getPosicion().getX()+"; PosY="+disparoJ.getPosicion().getY());
		}
		
		//disparo enemigo
		for (Disparo disparoE : disparosEnemigos) {
			System.out.println("Disparo Enemigo; Da�o="+disparoE.danio+"; PosX="+disparoE.getPosicion().getX()+"; PosY="+disparoE.getPosicion().getY());
		}
		
		//objetos espaciales
		for (ObjetoEspacial objetoEspacial : objetosEspaciales) {
			System.out.println(objetoEspacial.getClass().getSimpleName()+"; PosX="+objetoEspacial.getPosicion().getX()+
					"; PosY="+objetoEspacial.getPosicion().getY());
		}
		
	}

	// metodo para disparar 
	public void dispararJugador()
	{
		// agregamos un nuevo disparo a la lista de disparo jugador nuevos
		try {
			this.semaforo.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.disparosJugadorNuevos.add(this.naveJugador.disparar());
		this.semaforo.release();
		
		this.setChanged();
		this.notifyObservers();
	}

	// metodo para llamar al jugador
	public NaveJugador getNaveJugador() {
		// retornamos el jugador
		return this.naveJugador;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public void cargarHistorialJuego() {
		
		//creamos un nuevo para nave jugador objetoHistorial
		ObjetoHistorial jugador = new ObjetoHistorial();
		jugador.setTipo("nave jugador");
		// cargamos el nombre
		jugador.setNombre("nave jugador");
		// cargamos la energia del jugador de entero a string
		jugador.setEnergia(Integer.toString(this.naveJugador.getEnergia()));
		// cargamos las vidas
		jugador.setVidas(Integer.toString(this.naveJugador.getVidas()));
		// cargamos la posicion
		jugador.setPosicion(Integer.toString(this.naveJugador.getPosicion().getX()));
		jugador.setPosicion(Integer.toString(this.naveJugador.getPosicion().getY()));
		// cargamos la velocidad
		jugador.setVelocidad(Integer.toString(this.naveJugador.getVelocidad().getX()));
		jugador.setVelocidad(Integer.toString(this.naveJugador.getVelocidad().getY()));
		// ancho y alto
		jugador.setTamanio(Integer.toString(this.naveJugador.getAncho()));
		jugador.setTamanio(Integer.toString(this.naveJugador.getAlto()));
		
		// lo agregamos al historial
		this.historial.setObjeto(jugador);
		//--------------------------------------------------------------------//
		
		// las naves enemigas
		// recorremos cada una de las naves enemigas
		for (NaveEnemiga naveEnemiga : navesEnemigas) {
			// creamos un objetoHoistorial para nave enemiga
			ObjetoHistorial enemigo = new ObjetoHistorial();
			// cargamos el tipo
			enemigo.setTipo("nave enemiga");
			// cargamos el nombre
			if (naveEnemiga.getClass().equals(Demoledor.class)) {				
				enemigo.setNombre("demoledor");
			}else if (naveEnemiga.getClass().equals(Destructor.class)) {
				enemigo.setNombre("destructor");				
			}else if (naveEnemiga.getClass().equals(Depredador.class)) {
				enemigo.setNombre("depredador");				
			}else if (naveEnemiga.getClass().equals(Exterminador.class)) {
				enemigo.setNombre("exterminador");				
			}
			// cargamos la energia del enemigo de entero a string
			enemigo.setEnergia(Integer.toString(naveEnemiga.getEnergia()));
			// cargamos la posicion
			enemigo.setPosicion(Integer.toString(naveEnemiga.getPosicion().getX()));
			enemigo.setPosicion(Integer.toString(naveEnemiga.getPosicion().getY()));
			// cargamos la velocidad
			enemigo.setVelocidad(Integer.toString(naveEnemiga.getVelocidad().getX()));
			enemigo.setVelocidad(Integer.toString(naveEnemiga.getVelocidad().getY()));
			// ancho y alto
			enemigo.setTamanio(Integer.toString(naveEnemiga.getAncho()));
			enemigo.setTamanio(Integer.toString(naveEnemiga.getAlto()));
			// estado
			enemigo.setEstado(naveEnemiga.getEstado().name());
			
			// lo agregamos al historial
			this.historial.setObjeto(enemigo);
		}
				
		// los objetos espaciales
		// recorremos todos los objetos espaciales
		for (ObjetoEspacial objetoEspacial : objetosEspaciales) {
			// creamos un objetoHoistorial para el objeto espacial
			ObjetoHistorial objetoEsp = new ObjetoHistorial();
			// cargamos el tipo
			objetoEsp.setTipo("objeto espacial");
			// cargamos el nombre
			if (objetoEspacial.getClass().equals(Meteorito.class)) {				
				objetoEsp.setNombre("meteorito");
			}else if (objetoEspacial.getClass().equals(Asteroide.class)) {
				objetoEsp.setNombre("asteroide");				
			}else if (objetoEspacial.getClass().equals(EstrellaFugaz.class)) {
				objetoEsp.setNombre("estrella fugaz");				
			}
			// cargamos la posicion
			objetoEsp.setPosicion(Integer.toString(objetoEspacial.getPosicion().getX()));
			objetoEsp.setPosicion(Integer.toString(objetoEspacial.getPosicion().getY()));
			// cargamos la velocidad
			objetoEsp.setVelocidad(Integer.toString(objetoEspacial.getVelocidad().getX()));
			objetoEsp.setVelocidad(Integer.toString(objetoEspacial.getVelocidad().getY()));
			// ancho y alto
			objetoEsp.setTamanio(Integer.toString(objetoEspacial.getAncho()));
			objetoEsp.setTamanio(Integer.toString(objetoEspacial.getAlto()));
			// danio
			objetoEsp.setDanio(Integer.toString(objetoEspacial.getDanio()));
			
			// lo agregamos al historial
			this.historial.setObjeto(objetoEsp);
		}

		
		// los disparos del jugador
		// recorremos todos los disparos del jugador
		for (Disparo disparoJugador : disparosJugador) {
			// creamos un objetoHoistorial para cada disparo
			ObjetoHistorial disparo = new ObjetoHistorial();
			// cargamos el tipo
			disparo.setTipo("disparo jugador");
			
			// cargamos el nombre
			if (disparoJugador.getClass().equals(Bomba.class)) {				
				disparo.setNombre("bomba");
			}else if (disparoJugador.getClass().equals(Laser.class)) {
				disparo.setNombre("laser");				
			}else if (disparoJugador.getClass().equals(Misil.class)) {
				disparo.setNombre("misil");				
			}
			// cargamos la posicion
			disparo.setPosicion(Integer.toString(disparoJugador.getPosicion().getX()));
			disparo.setPosicion(Integer.toString(disparoJugador.getPosicion().getY()));
			// cargamos la velocidad
			disparo.setVelocidad(Integer.toString(disparoJugador.getVelocidad().getX()));
			disparo.setVelocidad(Integer.toString(disparoJugador.getVelocidad().getY()));
			// ancho y alto
			disparo.setTamanio(Integer.toString(disparoJugador.getAncho()));
			disparo.setTamanio(Integer.toString(disparoJugador.getAlto()));
			// danio
			disparo.setDanio(Integer.toString(disparoJugador.getDanio()));
			
			// lo agregamos al historial
			this.historial.setObjeto(disparo);
		}

		// los disparos del enemigo
		// recorremos todos los disparos del enemigo
		for (Disparo disparoEnemigo : disparosEnemigos) {
			// creamos un objetoHoistorial para cada disparo
			ObjetoHistorial disparo = new ObjetoHistorial();
			// cargamos el tipo
			disparo.setTipo("disparo enemigo");
						
			// cargamos el nombre
			if (disparoEnemigo.getClass().equals(Bomba.class)) {				
				disparo.setNombre("bomba");
			}else if (disparoEnemigo.getClass().equals(Laser.class)) {
				disparo.setNombre("laser");				
			}else if (disparoEnemigo.getClass().equals(Misil.class)) {
				disparo.setNombre("misil");				
			}
			// cargamos la posicion
			disparo.setPosicion(Integer.toString(disparoEnemigo.getPosicion().getX()));
			disparo.setPosicion(Integer.toString(disparoEnemigo.getPosicion().getY()));
			// cargamos la velocidad
			disparo.setVelocidad(Integer.toString(disparoEnemigo.getVelocidad().getX()));
			disparo.setVelocidad(Integer.toString(disparoEnemigo.getVelocidad().getY()));
			// ancho y alto
			disparo.setTamanio(Integer.toString(disparoEnemigo.getAncho()));
			disparo.setTamanio(Integer.toString(disparoEnemigo.getAlto()));
			// danio
			disparo.setDanio(Integer.toString(disparoEnemigo.getDanio()));
			
			// lo agregamos al historial
			this.historial.setObjeto(disparo);
		}
	}

	public Historial getHistorial() {
		//
		this.historial.limpiar();
		this.cargarHistorialJuego();
		return this.historial;
	}


	@SuppressWarnings("unchecked")
	public LinkedList<NaveEnemiga> obtenerNavesNuevas() {
		// 
		LinkedList<NaveEnemiga> nuevas = (LinkedList<NaveEnemiga>) this.navesNuevas.clone();
		this.navesEnemigas.addAll(this.navesNuevas);
		this.navesNuevas.clear();
		return nuevas;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<Disparo> getDisparosJugadorNuevos() {
		LinkedList<Disparo> nuevos = (LinkedList<Disparo>) this.disparosJugadorNuevos.clone();
		this.disparosJugador.addAll(this.disparosJugadorNuevos);
		this.disparosJugadorNuevos.clear();
		return nuevos;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<Disparo> getDisparosEnemigosNuevos() {
		LinkedList<Disparo> nuevos = (LinkedList<Disparo>) this.disparosEnemigosNuevos.clone();
		this.disparosEnemigos.addAll(this.disparosEnemigosNuevos);
		this.disparosEnemigosNuevos.clear();
		return nuevos;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<ObjetoEspacial> getObjetosEspacialesNuevos() {
		LinkedList<ObjetoEspacial> nuevos = (LinkedList<ObjetoEspacial>) this.objetosEspacialesNuevos.clone();
		this.objetosEspaciales.addAll(this.objetosEspacialesNuevos);
		this.objetosEspacialesNuevos.clear();
		return nuevos;
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<ObjetoMovil> getObjetosMovilesMuertos() {
		int i = 0;
		for (ObjetoMovil objetoMovil : objetosMuertos) {
			i++;
			System.out.println(i+" "+objetoMovil.getClass().getSimpleName());
		}
		LinkedList<ObjetoMovil> nuevos = (LinkedList<ObjetoMovil>) this.objetosMuertos.clone();
		this.objetosMuertos.clear();
		return nuevos;
	}

	public void setPausa(boolean pausa) {
		// 
		this.pausa = pausa;
		
	}
	
	public boolean getPausa() {
		return this.pausa;
	}
	
	public void cargarNivelGenerico(Historial historial){
		// primero descargamos el nivel
		this.descargarNivel();
		
		ArrayList<ObjetoHistorial> objetoH = historial.getObjetos();
		for (ObjetoHistorial objetoHistorial : objetoH) {
			// todos tiene velocidad, posicion, alto y ancho
			int posX = Integer.parseInt(objetoHistorial.getPosicion().get(0));
			int posY = Integer.parseInt(objetoHistorial.getPosicion().get(1));
			
			int velX = Integer.parseInt(objetoHistorial.getVelocidad().get(0));
			int velY = Integer.parseInt(objetoHistorial.getVelocidad().get(1));
			
			int ancho = Integer.parseInt(objetoHistorial.getTamanio().get(0));
			int alto = Integer.parseInt(objetoHistorial.getTamanio().get(1));
			
			// la nave jugador
			if (objetoHistorial.getTipo().equals("nave jugador")) {

//				int vidas = Integer.parseInt(objetoHistorial.getVidas());
//		
//				int energia = Integer.parseInt(objetoHistorial.getEnergia());
//				
//				this.naveJugador = new NaveJugador(new Punto(posX, posY), ancho, alto,vidas,energia);
//				
			}else if (objetoHistorial.getTipo().equals("nave enemiga")) {
				
				int energia = Integer.parseInt(objetoHistorial.getEnergia());
				Estado estado = null;
				if (objetoHistorial.getEstado().equals("PASIVO")) {
					estado = Estado.PASIVO;
					System.out.println("cargo"+estado.name());
				}else if (objetoHistorial.getEstado().equals("KAMIKAZE")) {
					estado = Estado.KAMIKAZE;
					System.out.println("cargo"+estado.name());
				}else if (objetoHistorial.getEstado().equals("VOLVIENDO")) {
					estado = Estado.VOLVIENDO;
					System.out.println("cargo"+estado.name());
				}else if (objetoHistorial.getEstado().equals("ATACANDO")) {
					estado = Estado.ATACANDO;
					System.out.println("cargo"+estado.name());
				}
				if (objetoHistorial.getNombre().equals("demoledor")) {
					Demoledor dem = new Demoledor(new Punto(posX, posY), new Punto(velX, velY), ancho, alto);
					dem.setEstado(estado);
					dem.setEnergia(energia);
					this.navesNuevas.add(dem);
				}else if (objetoHistorial.getNombre().equals("depredador")) {
					Depredador dep = new Depredador(new Punto(posX, posY), new Punto(velX, velY), alto,ancho);
					dep.setEstado(estado);
					dep.setEnergia(energia);
					this.navesNuevas.add(dep);
					
				}else if (objetoHistorial.getNombre().equals("destructor")) {
					Destructor des = new Destructor(new Punto(posX, posY), new Punto(velX, velY), ancho, alto);
					des.setEstado(estado);
					des.setEnergia(energia);
					this.navesNuevas.add(des);
					
				}else if (objetoHistorial.getNombre().equals("exterminador")) {
					Exterminador ext = new Exterminador(new Punto(posX, posY), new Punto(velX, velY), ancho, alto);
					ext.setEstado(estado);
					ext.setEnergia(energia);
					this.navesNuevas.add(ext);
					
				}
			}else if (objetoHistorial.getTipo().equals("objeto espacial")) {
				int danio = Integer.parseInt(objetoHistorial.getDanio());
				if (objetoHistorial.getNombre().equals("estrella fugaz")) {
					EstrellaFugaz est = new EstrellaFugaz(new Punto(posX, posY), new Punto(velX, velY), ancho, alto);
					this.objetosEspacialesNuevos.add(est);
				}else if (objetoHistorial.getNombre().equals("meteorito")) {
					Meteorito met = new Meteorito(new Punto(posX, posY), new Punto(velX, velY), ancho, alto, danio);
					this.objetosEspacialesNuevos.add(met);
				}else if (objetoHistorial.getNombre().equals("asteroide")) {
					Asteroide ast = new Asteroide(new Punto(posX, posY), new Punto(velX, velY), ancho, alto, danio);
					this.objetosEspacialesNuevos.add(ast);
				}
			}else if (objetoHistorial.getTipo().equals("disparo enemigo")) {
				if (objetoHistorial.getNombre().equals("laser")) {
					Laser las = new Laser(new Punto(posX, posY), new Punto(velX, velY), ancho, alto); 
					this.disparosEnemigosNuevos.add(las);
				}else if (objetoHistorial.getNombre().equals("misil")) {
					Misil mis = new Misil(new Punto(posX, posY), new Punto(velX, velY), ancho, alto);
					this.disparosEnemigosNuevos.add(mis);
				}else if (objetoHistorial.getNombre().equals("bomba")) {
					Bomba bom = new Bomba(new Punto(posX, posY), new Punto(velX, velY), ancho, alto);
					this.disparosEnemigosNuevos.add(bom);
				}
			}else if (objetoHistorial.getTipo().equals("disparo jugador")) {
				if (objetoHistorial.getNombre().equals("laser")) {
					Laser las = new Laser(new Punto(posX, posY), new Punto(velX, velY), ancho, alto); 
					this.disparosEnemigosNuevos.add(las);
				}else if (objetoHistorial.getNombre().equals("misil")) {
					Misil mis = new Misil(new Punto(posX, posY), new Punto(velX, velY), ancho, alto);
					this.disparosEnemigosNuevos.add(mis);
				}else if (objetoHistorial.getNombre().equals("bomba")) {
					Bomba bom = new Bomba(new Punto(posX, posY), new Punto(velX, velY), ancho, alto);
					this.disparosEnemigosNuevos.add(bom);
				}
			}// fin if objetos
		}// fin for
		setChanged();
		notifyObservers();
	}

	public void descargarNivel() {
		// 
		for (NaveEnemiga naveEnemiga : navesEnemigas) {
			this.objetosMuertos.add(naveEnemiga);
			this.setChanged();
			this.notifyObservers();
		}
		this.navesEnemigas.clear();
		for (ObjetoEspacial objetoEspacial : objetosEspaciales) {
			this.objetosMuertos.add(objetoEspacial);
			this.setChanged();
			this.notifyObservers();
			
		}
		this.objetosEspaciales.clear();
		for (Disparo disparo : disparosEnemigos) {
			this.objetosMuertos.add(disparo);
			this.setChanged();
			this.notifyObservers();
			
		}
		this.disparosEnemigos.clear();
		for (Disparo disparo2 : disparosJugador) {
			this.objetosMuertos.add(disparo2);
			this.setChanged();
			this.notifyObservers();
			
		}
		this.disparosJugador.clear();
		
		for (Disparo disparo : disparosEnemigosNuevos) {
			this.objetosMuertos.add(disparo);
			this.setChanged();
			this.notifyObservers();
			
		}
		this.disparosEnemigosNuevos.clear();
		
		for (Disparo disparo : disparosJugadorNuevos) {
			this.objetosMuertos.add(disparo);
			this.setChanged();
			this.notifyObservers();
			
		}
		this.disparosJugadorNuevos.clear();
		
		for (ObjetoEspacial objetoEspacial : objetosEspacialesNuevos) {
			this.objetosMuertos.add(objetoEspacial);
			this.setChanged();
			this.notifyObservers();
			
		}
		this.disparosEnemigosNuevos.clear();
	}
	

	//retorno el estado del juego, naves enemigas destruidas.
	public boolean isGameWin() {
		return gameWin;
	}

	
}
