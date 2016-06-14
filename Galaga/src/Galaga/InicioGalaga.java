package Galaga;

import javax.swing.UIManager;

import Controlador.*;
import Modelo.Juego;
import Vista.VentanaPrincipal;

public class InicioGalaga {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception e) {}
		
		
		Juego juego = new Juego();
		VentanaPrincipal vista = new VentanaPrincipal(juego);
		vista.setVisible(true);	
		juego.addObserver(vista);

		Controlador controlador = new Controlador(juego);
		vista.addKeyListener(controlador);
		
		ControladorMenu controlador_menu = new ControladorMenu(juego, vista);
		
//		juego.cargarNivel1();
//		juego.jugar();
		
//		
	}

}
