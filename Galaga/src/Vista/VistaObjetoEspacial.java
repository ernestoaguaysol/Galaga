package Vista;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Modelo.ObjetoEspacial;

public class VistaObjetoEspacial implements Observer{

	private JLabel lblAsteroide;
	private JLabel lblEstrellaFugaz;
	private JLabel lblMeteorito;
	private ObjetoEspacial objetoEspacial;
	
	public VistaObjetoEspacial() {
		// asteroide
		lblAsteroide = new JLabel();
		URL pathAsteroide = this.getClass().getResource("Imagenes/Asteroide.png");
		ImageIcon imagenAsteroide = new ImageIcon(pathAsteroide);
		lblAsteroide.setIcon(imagenAsteroide);
		
		// estrella fugaz
		lblEstrellaFugaz = new JLabel();
		URL pathEstrellaFugaz = this.getClass().getResource("Imagenes/EstrellaFugaz.png");
		ImageIcon imagenEstrellaFugaz = new ImageIcon(pathEstrellaFugaz);
		lblEstrellaFugaz.setIcon(imagenEstrellaFugaz);
		
		// meteorito
		lblMeteorito = new JLabel();
		URL pathMeteorito = this.getClass().getResource("Imagenes/Meteorito.png");
		ImageIcon imagenMeteorito = new ImageIcon(pathMeteorito);
		lblMeteorito.setIcon(imagenMeteorito);
		
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		// 
		ObjetoEspacial n = (ObjetoEspacial)o;
		lblAsteroide.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
		lblEstrellaFugaz.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
		lblMeteorito.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
	}
	
	public JLabel getLblAsteroide() {
		return lblAsteroide;
	}
	
	public ObjetoEspacial getObjetoEspacial() {
		return objetoEspacial;
	}
	
	public void setObjetoEspacial(ObjetoEspacial objetoEspacial) {
		this.objetoEspacial = objetoEspacial;
	}

	public JLabel getLblEstrellaFugaz() {
		// 
		return lblEstrellaFugaz;
	}
	
	public JLabel getLblMeteorito() {
		return lblMeteorito;
	}
	
}
