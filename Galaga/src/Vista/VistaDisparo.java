package Vista;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Modelo.Disparo;

public class VistaDisparo implements Observer {

	JLabel lblLaserJugador;
	JLabel lblLaserEnemigo;
	
	public VistaDisparo() {
		// laser jugador
		lblLaserJugador = new JLabel();
		URL pathLaserJugador = this.getClass().getResource("Imagenes/LaserJugador.png");
		ImageIcon imagenLaserJugador = new ImageIcon(pathLaserJugador);
		lblLaserJugador.setIcon(imagenLaserJugador);
		
		// laser enemigo
		lblLaserEnemigo = new JLabel();
		URL pathLaserEnemigo = this.getClass().getResource("Imagenes/LaserEnemigo.png");
		ImageIcon imagenLaserEnemigo = new ImageIcon(pathLaserEnemigo);
		lblLaserEnemigo.setIcon(imagenLaserEnemigo);
		
		
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		// 
		Disparo n = (Disparo)o;
		lblLaserJugador.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
		lblLaserEnemigo.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
	}
	
	public JLabel getLblLaserJugador() {
		return lblLaserJugador;
	}

	public JLabel getLblLaserEnemigo() {
		// 
		return lblLaserEnemigo;
	}
	
}
