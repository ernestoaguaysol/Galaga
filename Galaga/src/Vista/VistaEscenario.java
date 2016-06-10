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
	private ImageIcon imagenEspacio;
	
	public VistaEscenario()
	{
		this.lblEspacio = new JLabel();
		this.lblEspacio.setBounds(100, 100, 512, 512);
		URL pathGalaga = this.getClass().getResource("Imagenes/fondoEstrellas.jpg");
		imagenEspacio = new ImageIcon(pathGalaga);
		Icon iconoEspacio = new ImageIcon(imagenEspacio.getImage().getScaledInstance(lblEspacio.getWidth(), lblEspacio.getHeight(), Image.SCALE_DEFAULT));
		lblEspacio.setIcon(iconoEspacio);
		lblEspacio.repaint();
	}

	@Override
	public void update(Observable obs, Object arg1) 
	{
//		Juego e = (Juego)obs;
		
//		List<NaveEnemiga> naves = e.obtenerNavesNuevas();
//		
//		for (NaveEnemiga n : naves)
//		{
//			VistaNave vista = new VistaNave(n);
//			n.addObserver(vista);
//			espacio.add(vista.getJLabel());
//		}
		
	}
	
	public void agregarLabel(JLabel label) {
		this.lblEspacio.add(label);
	}

	public JLabel getLebel() {
		//
		return this.lblEspacio;
	}
}
