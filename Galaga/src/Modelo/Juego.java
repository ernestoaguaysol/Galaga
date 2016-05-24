package Modelo;

import java.util.LinkedList;
import java.util.Random;
import Modelo.NaveJugador;

public class Juego {
	// contiene un espacio, un jugador, y listas de naves enemigas
	//objetos espaciales, disparos enemigos, disparos jugador
	private Espacio espacio;
	private NaveJugador naveJugador;
	private LinkedList<NaveEnemiga> navesEnemigas;
	private LinkedList<ObjetoEspacial> objetosEspaciales;
	private LinkedList<Disparo> disparosEnemigos;
	private LinkedList<Disparo> disparosJugador;
	
	//aleatorio para usar en varios
	private Random aleatorio = new Random();
	
	// constructor Juego
	public Juego() {
		this.espacio = new Espacio(512,512);
		this.naveJugador = new NaveJugador(new Punto(255, 16),32,32);
		this.objetosEspaciales = new LinkedList<>();
		this.disparosEnemigos = new LinkedList<>();
		this.disparosJugador = new LinkedList<>();
		this.navesEnemigas = new LinkedList<>();
	}
	
	public void cargar() {
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
		//los añadimos a la lista de naves enemigas
		this.navesEnemigas.add(dem1);
		this.navesEnemigas.add(dem2);
		this.navesEnemigas.add(dem3);
		this.navesEnemigas.add(dem4);
		this.navesEnemigas.add(dem5);
		this.navesEnemigas.add(dem6);
		this.navesEnemigas.add(dem7);
		///
		this.navesEnemigas.add(des1);
		this.navesEnemigas.add(des2);
		this.navesEnemigas.add(des3);
		this.navesEnemigas.add(des4);
		this.navesEnemigas.add(des5);
		this.navesEnemigas.add(des6);
		this.navesEnemigas.add(des7);
		//		
		
	}
	
	
	//el bucle principal del juego
	public void jugar() {
		
		//diez iteraciones
		for (int i = 0; i < 16; i++) {			
			// movemos todos los objetos moviles
			this.moverTodo();
				
			//chequeamos si hay colicionas
			this.chequearColisiones();
			
			// si hay menos de 3 disparos 
			if (this.disparosEnemigos.size() < 3) {
				// recorremos cada nave enemiga
				for (NaveEnemiga naveEnemiga : navesEnemigas) {				
					// y vamos decidiendo la nave dispara o no
					if (this.decidirDisparo()) {
						this.disparosEnemigos.add(naveEnemiga.disparar());
					}
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
			
//			System.out.println("Iteracion: "+i);
//			this.imprimir();
		}//FIN FOR
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
				EstrellaFugaz nuevaEstrellaFugaz = new EstrellaFugaz(new Punto(0, 300), new Punto(0, 0), 16,16);
				// agregamos la nueva estrella a la lista de objetos espaciales
				this.objetosEspaciales.add(nuevaEstrellaFugaz);
			}
		}
		if (cantMeteorito < 3) {
			// aleatorio de 0 a 4 ¿si es 0?
			if (this.aleatorio.nextInt(5) == 0) {
				// creamos un nuevo meteorito
				Meteorito nuevoMeteorito = new Meteorito(new Punto(0, 303), new Punto(1, -1),16,16,25);
				// agregamos el nuevo meteorito a la lista de objetos espaciales
				this.objetosEspaciales.add(nuevoMeteorito);
			}
		}
		if (cantAsteriode < 2) {
			// aleatorio de 0 a 8 ¿si es 0?
			if (this.aleatorio.nextInt(8) == 0) {
				// creamos un nuevo asteroide
				Asteroide nuevoAsteroide = new Asteroide(new Punto(479, 271),new Punto(-1, -1),16,16,40);
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
		if (!this.espacio.estaDentroDeEspacio(this.naveJugador.getPosicion())) {
			this.reubicarPosicion(this.naveJugador);
		}
		
		//chequeamos todas las naves enemigas
		for (NaveEnemiga naveEnemiga : navesEnemigas) {
			if (!this.espacio.estaDentroDeEspacio(naveEnemiga.getPosicion())) {
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
		if (objeto.getPosicion().getX() < 0) {
			objeto.getPosicion().setX(511);
		}
		if (objeto.getPosicion().getX() >= 512) {
			objeto.getPosicion().setX(0);
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

	// metodo decidir disparo, si hay mas de tres disparos
	// en el espacio ninguna nave hace disparo
	// si hay menos de 3, hacemos un ramdom
	private boolean decidirDisparo() {
		//cantidad de disparos en disparos enemigos
		int cantDisparos = this.disparosEnemigos.size();
		// cantidad de disparos
		if (cantDisparos <= 3) {
			//si aleatorio es cero
			if (aleatorio.nextInt(8) == 0) {
				return true;
			}
		}
		return false;
	}

	/*las coliciones son entre:
	 * naveEnemiga y naveJugador
	 * 
	 * */
	private void chequearColisiones() {
		// recorremos todas las naves enemigas
		for (int i = 0; i < navesEnemigas.size(); i++) {
			//si las superficies colicionan
			if (navesEnemigas.get(i).getSuperficie().colisiona(this.naveJugador.getSuperficie())) {
				// descontamos 50% de energia de jugador
				this.naveJugador.disminuirEnergia(50);
				//eliminamos el enemigo
				navesEnemigas.remove(i);
				//retrocedemos un paso en i (ya que al remover
				// la lista se modifica el tamaño e indices tambien)
				i--;
			}
		}
		
		
		//recorremos los disparos enemigos "actuales"
		for (int i = 0; i < disparosEnemigos.size(); i++) {
			//si el disparo enemigo coliciona con jugador
			if (disparosEnemigos.get(i).getSuperficie().colisiona(this.naveJugador.getSuperficie())) {
				//descontamos energia segun el daño que cause el disparo
				this.naveJugador.disminuirEnergia(disparosEnemigos.get(i).getDanio());
				//eliminamos el disparo que impactó en la nave
				disparosEnemigos.get(i);
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
					// eliminamos la estrella fugaz
					this.objetosEspaciales.remove(i);
					//retrocedemos un paso en i
					i--;
					//----------------------------------------
					//faltaria sumar puntaje
				}else {
					//si no es una estrella fugaz
					// diminuir energia segun el daño que cause el objeto espacial 
					this.naveJugador.disminuirEnergia(this.objetosEspaciales.get(i).getDanio());
					// eliminemos el objeto espacial
					this.navesEnemigas.remove(i);
					//retrocedemos un paso en i
					i--;
				}
			}
		
			
		
		}
		
		
		
	}
	
	
	public void imprimir() {
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
			System.out.println("Disparo Jugador; Daño="+disparoJ.danio+"; PosX="+disparoJ.getPosicion().getX()+"; PosY"+disparoJ.getPosicion().getY());
		}
		
		//disparo enemigo
		for (Disparo disparoE : disparosEnemigos) {
			System.out.println("Disparo Enemigo; Daño="+disparoE.danio+"; PosX="+disparoE.getPosicion().getX()+"; PosY"+disparoE.getPosicion().getY());
		}
		
		//objetos espaciales
		for (ObjetoEspacial objetoEspacial : objetosEspaciales) {
			System.out.println("Objetos Espaciales; PosX="+objetoEspacial.getPosicion().getX()+
					"; PosY="+objetoEspacial.getPosicion().getY());
		}
		
	}

	// metodo para disparar 
	public void dispararJugador()
	{
		// agregamos un nuevo disparo a la lista de disparo jugador
		this.disparosJugador.add(this.naveJugador.disparar());
	}

	// metodo para llamar al jugador
	public NaveJugador getNaveJugador() {
		// retornamos el jugador
		return this.naveJugador;
	}
}
