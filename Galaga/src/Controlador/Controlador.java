package Controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import Modelo.Juego;
import Vista.VentanaPrincipal;

public class Controlador implements KeyListener{

	private Juego juego;
	
	public Controlador(Juego juego) {
		//
		this.juego = juego;
	}
	
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		int numeroTecla = e.getKeyCode();		
		
		if (numeroTecla == KeyEvent.VK_SPACE) {
			
			this.juego.dispararJugador();
		}
		//comparamos
		if (numeroTecla == KeyEvent.VK_LEFT) {
			
			this.juego.getNaveJugador().moverIzquierda();
		}

		if (numeroTecla == KeyEvent.VK_RIGHT) {
			
			this.juego.getNaveJugador().moverDerecha();
		}
		
		if (numeroTecla == KeyEvent.VK_UP) {
			
			this.juego.getNaveJugador().moverArriba();
		}
		
		if (numeroTecla == KeyEvent.VK_DOWN) {
			
			this.juego.getNaveJugador().moverAbajo();
		}
		// pausa
		if (numeroTecla == KeyEvent.VK_P) {
			if (this.juego.getPausa()) {
				this.juego.setPausa(false);
			}else {
				this.juego.setPausa(true);				
			}
		}
	
		if (numeroTecla == KeyEvent.VK_C) {
			String mensaje = "GALAGA PROYECTO\n"
					+ "realizado por:\n"
					+ "Zalazar Gabriel\n"
					+ "Aguaysol Ernesto\n"
					+ "";
			this.juego.setPausa(true);				
			showMessageDialog(mensaje);
			this.juego.setPausa(false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 
		int numeroTecla = e.getKeyCode();
		
		if (numeroTecla == KeyEvent.VK_LEFT) {
			
			this.juego.getNaveJugador().detenerHorizontal();
		}

		if (numeroTecla == KeyEvent.VK_RIGHT) {
			
			this.juego.getNaveJugador().detenerHorizontal();
		}
		
		if (numeroTecla == KeyEvent.VK_UP) {
			
			this.juego.getNaveJugador().detenerVertical();
		}
		
		if (numeroTecla == KeyEvent.VK_DOWN) {
			
			this.juego.getNaveJugador().detenerVertical();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
	
	public static void showMessageDialog(String mensaje){
		
		URL path = VentanaPrincipal.class.getResource("Imagenes/NaveJugador2.png");
		JOptionPane.showMessageDialog(null, mensaje ,"Mensaje", JOptionPane.INFORMATION_MESSAGE,new ImageIcon(path));
		
	}
	
	public static int showConfirmDialog(String mensaje){
		URL path = VentanaPrincipal.class.getResource("Imagenes/NaveJugador2.png");		
		return JOptionPane.showConfirmDialog(null, mensaje, "Confirmar", 0, 0, new ImageIcon(path));
	}

}
