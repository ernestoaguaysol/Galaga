package Galaga;

import javax.swing.UIManager;
import Controlador.Controlador;
import Modelo.Juego;
import Vista.VentanaPrincipal;

public class InicioGalaga {
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} catch (Exception e) {
			
		}
		Juego juego = new Juego();
		VentanaPrincipal ventana = new VentanaPrincipal(juego);
		ventana.setVisible(true);	
		Controlador controlador = new Controlador(juego);
		ventana.addKeyListener(controlador);
		
		juego.cargar();
		juego.jugar();
	}

}
