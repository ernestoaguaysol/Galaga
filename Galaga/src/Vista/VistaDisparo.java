package Vista;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Modelo.Disparo;

public class VistaDisparo implements Observer {

	JLabel lblLaserJugador;
	JLabel lblMisilJugador;
	
	JLabel lblLaserEnemigo;
	JLabel lblMisilEnemigo;
	
	public VistaDisparo() {
		// laser jugador
		lblLaserJugador = new JLabel();
		URL pathLaserJugador = this.getClass().getResource("Imagenes/LaserJugador.png");
		ImageIcon imagenLaserJugador = new ImageIcon(pathLaserJugador);
		lblLaserJugador.setIcon(imagenLaserJugador);
		
		// misil jugador
		lblMisilJugador = new JLabel();
		URL pathMisilJugador = this.getClass().getResource("Imagenes/MisilJugador.png");
		ImageIcon imagenMisilJugador = new ImageIcon(pathMisilJugador);
		lblMisilJugador.setIcon(imagenMisilJugador);
		
		// laser enemigo
		lblLaserEnemigo = new JLabel();
		URL pathLaserEnemigo = this.getClass().getResource("Imagenes/LaserEnemigo.png");
		ImageIcon imagenLaserEnemigo = new ImageIcon(pathLaserEnemigo);
		lblLaserEnemigo.setIcon(imagenLaserEnemigo);
		
		// misil enemigo
		lblMisilEnemigo = new JLabel();
		URL pathMisilEnemigo = this.getClass().getResource("Imagenes/MisilEnemigo.png");
		ImageIcon imagenMisilEnemigo = new ImageIcon(pathMisilEnemigo);
		lblMisilEnemigo.setIcon(imagenMisilEnemigo);
		
		
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		Disparo n = (Disparo)o;
		// jugador
		lblLaserJugador.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
		lblMisilJugador.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
		//enemigo
		lblLaserEnemigo.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
		lblMisilEnemigo.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
	}
	
	public JLabel getLblLaserJugador() {
		return lblLaserJugador;
	}
	
	public JLabel getLblMisilJugador() {
		return lblMisilJugador;
	}

	public JLabel getLblLaserEnemigo() {
		// 
		return lblLaserEnemigo;
	}
	
	public JLabel getLblMisilEnemigo() {
		return lblMisilEnemigo;
	}
	
}
