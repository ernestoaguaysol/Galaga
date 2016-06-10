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
		VentanaPrincipal ventana = new VentanaPrincipal();
		ventana.setVisible(true);	
		Juego juego = new Juego();
		Controlador controlador = new Controlador(juego);
		ventana.addKeyListener(controlador);
	}

}
