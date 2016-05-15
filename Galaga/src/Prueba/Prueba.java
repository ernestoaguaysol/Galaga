package Prueba;

import java.util.Scanner;

import Modelo.Juego;
import Modelo.NaveJugador;
import Modelo.Punto;

public class Prueba {

	public static void main(String[] args) {
		//
//		Juego juego = new Juego();
//		juego.cargar();
//		juego.decidirDisparo();
		
		NaveJugador naveJugador = new NaveJugador(new Punto(255, 100), 10);
		
		System.out.println("Que desea hacer?");
		System.out.println("1. Usar la Nave Jugador");
		System.out.println("2. Realizar mas iteraciones");
		System.out.println("3. Salir/Terminar");
		System.out.print("Ingrese su opcion: ");
		int opcionUsuario = Integer.parseInt(extracted().next());
		
		while (opcionUsuario != 3) {
			int iteraciones = 0;
			while (iteraciones < 10) {
				System.out.println("Que desea hacer con la Nave Jugador?");
				System.out.println("1. Mover a la izquierda");
				System.out.println("2. Mover a la derecha");
				System.out.println("3. Mover arriba");
				System.out.println("4. Mover abajo");
				System.out.println("5. Disparar");
				System.out.println("6. Detener la nave");
				System.out.print("Ingrese su opcion: ");
				int opcionJugar = Integer.parseInt(extracted().next());
				
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
					naveJugador.disparar();
					break;
				

				default:
					break;
				}
				
				//
				iteraciones++;
			}
			System.out.println("Que desea hacer?");
			System.out.println("1. Usar la Nave Jugador");
			System.out.println("2. Realizar mas iteraciones");
			System.out.println("3. Salir/Terminar");
			System.out.print("Ingrese opcion: ");
			opcionUsuario = Integer.parseInt(extracted().next());
		}
		
		System.out.println("FIN");

	}
	
	private static Scanner extracted() {
		return new Scanner(System.in);
	}

}
