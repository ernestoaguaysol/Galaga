package Prueba;

import java.util.Scanner;

import Modelo.Juego;

public class Prueba {

	public static void main(String[] args) {
		//
		Juego juego = new Juego();
		
		boolean entrar = true;
		while (entrar) {
			System.out.println("Que desea hacer?");
			System.out.println("1. Usar la Nave Jugador");
			System.out.println("2. Realizar mas iteraciones");
			System.out.println("3. Salir/Terminar");
			System.out.print("Ingrese su opcion: ");
			int opcionUsuario = Integer.parseInt(extraer().next());
			if (opcionUsuario == 1) {
				juego = new Juego();
				juego.cargar();
				juego.menu();
			}
			if (opcionUsuario == 2) {
				juego.menu();
			}
			if (opcionUsuario == 3) {
				entrar = false;
			}
		}
		
	}
	
	private static Scanner extraer() {
		return new Scanner(System.in);
	}
}
