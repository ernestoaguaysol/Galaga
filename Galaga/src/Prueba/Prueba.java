package Prueba;

import java.util.Scanner;

//import java.util.LinkedList;
//import Modelo.Demoledor;
//import Modelo.Disparo;
//import Modelo.NaveEnemiga;
import Modelo.Juego;
import Modelo.NaveJugador;

public class Prueba {

	public static void main(String[] args) {
		
//		LinkedList<Disparo> disparosJugador = new LinkedList<>();
//		LinkedList<NaveEnemiga> navesEnemigas = new LinkedList<>();
//		
//		Demoledor d1 = new Demoledor(new Po, velocidad, ancho, alto)
//		
//		
//		
//		// recorremos los disparos del jugador
//				for (int j = 0; j < disparosJugador.size(); j++) {
//					// recorremos todos los enemigos
//					for (int i = 0; i < navesEnemigas.size(); i++) {
//						// si la nave enemiga coliciona con el el disparo jugador
//						if (navesEnemigas.get(i).getSuperficie().colisiona(disparosJugador.get(j).getSuperficie())) {
//							// disminuimos energia del enemigo
//							navesEnemigas.get(i).disminuirEnergia(disparosJugador.get(j).getDanio());
//							// si la energia es menor o igual a cero
//							if (navesEnemigas.get(i).getEnergia() <= 0) {
//								// eliminamos la nave enemiga
//								navesEnemigas.remove(i);
//								// retrocedemos un paso en i
//								i--;
//							}
//							// eliminamos el disparo jugador que impactó
//							disparosJugador.remove(j);
//							// retrocedemos un paso en j
//							j--;
//							// salimos del for i
//							break;
//						}
//					}
//				}
		
//		boolean entra = true;
//		if (entra) {
//			return;
//		}
		
		Juego juego = new Juego();
		juego.cargar();
		
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
