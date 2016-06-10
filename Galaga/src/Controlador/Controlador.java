package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Modelo.Juego;

public class Controlador implements KeyListener {

	private Juego juego;
	
	public Controlador(Juego juego) {
		//
		this.juego = juego;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("----------");
		int numeroTecla = e.getKeyCode();
		
		if (numeroTecla == KeyEvent.VK_I) {
			
			this.juego.cargar();
			this.juego.imprimir();
		}
		
		if (numeroTecla == KeyEvent.VK_A) {
			this.juego.jugar();
			this.juego.imprimir();
			
			System.out.println("cargado");
		}
		
		
		if (numeroTecla == KeyEvent.VK_SPACE) {
			
			this.juego.dispararJugador();
		}
		//comparamos
		if (numeroTecla == KeyEvent.VK_LEFT) {
			
			this.juego.getNaveJugador().moverIzquierda();
			System.out.println("mover izquierda");
		}

		if (numeroTecla == KeyEvent.VK_RIGHT) {
			
			this.juego.getNaveJugador().moverDerecha();
			System.out.println("mover derecha");
		}
		
		if (numeroTecla == KeyEvent.VK_UP) {
			
			this.juego.getNaveJugador().moverArriba();
			System.out.println("mover arriba");
		}
		
		if (numeroTecla == KeyEvent.VK_DOWN) {
			
			this.juego.getNaveJugador().moverAbajo();
			System.out.println("mover abajo");
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
//		int numeroTecla = e.getKeyCode();
		
		
		
		
		//pasamos el valor numerico de la tecla
//				int numeroTecla = e.getKeyCode();
				
				//
				//
				
		
	}

	// 
	
	
}
