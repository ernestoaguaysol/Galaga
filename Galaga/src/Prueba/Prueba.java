package Prueba;

import java.util.Scanner;
import Modelo.Juego;
import Modelo.NaveJugador;

public class Prueba {

	public static void main(String[] args) {
		
		// creo un nuevo juego
		Juego juego = new Juego();
		// cargo el juego
		juego.cargar();
		
		// boolean para entrar en verdadero
		boolean entrar = true;
		while (entrar) {
			System.out.println("Que desea hacer?");
			System.out.println("1. Usar la Nave Jugador");
			System.out.println("2. Realizar mas iteraciones");
			System.out.println("3. Salir/Terminar");
			System.out.print("Ingrese su opcion: ");
			int opcionUsuario = Integer.parseInt(extraer().next());
			if (opcionUsuario == 1) {
				menu(juego);
				
			}
			if (opcionUsuario == 2) {
				juego.jugar();
				
			}
			if (opcionUsuario == 3) {
				entrar = false;
			}
			juego.imprimir();
		}
		System.out.println("Fin del juego");
		
	}
	
	private static Scanner extraer() {
		return new Scanner(System.in);
	}
	
	public static void menu(Juego juego){ 
		//
		System.out.println("Que desea hacer con la Nave Jugador?");
		System.out.println("1. Mover a la izquierda");
		System.out.println("2. Mover a la derecha");
		System.out.println("3. Mover arriba");
		System.out.println("4. Mover abajo");
		System.out.println("5. Disparar");
		System.out.println("6. Detener la nave");
		System.out.print("Ingrese su opcion: ");
		int opcionJugar = Integer.parseInt(extraer().next());
		NaveJugador naveJugador = juego.getNaveJugador();
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
			juego.dispararJugador();
			
			break;
		case 6:
			// detener la nave
			naveJugador.detener();
			break;
		default:
			break;
		}
	}
}
