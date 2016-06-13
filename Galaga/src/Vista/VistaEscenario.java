package Vista;

import java.awt.Image;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;



public class VistaEscenario implements Observer {
	public JLabel lblEspacio;
	
	public VistaEscenario()
	{
		lblEspacio = new JLabel();
		lblEspacio.setBounds(0,0, 512, 512);
		URL pathGalaga = this.getClass().getResource("Imagenes/fondoNivel1.jpg");
		ImageIcon imagenEspacio = new ImageIcon(pathGalaga);
		Icon iconoEspacio = new ImageIcon(imagenEspacio.getImage().getScaledInstance(lblEspacio.getWidth(), lblEspacio.getHeight(), Image.SCALE_DEFAULT));
		lblEspacio.setIcon(iconoEspacio);
		lblEspacio.repaint();
	}

	@Override
	public void update(Observable obs, Object arg1) 
	{

		
	}

	public JLabel getLblEspacio() {
		//
		return this.lblEspacio;
	}
}