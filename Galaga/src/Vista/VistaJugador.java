package Vista;

import java.awt.Image;
import java.net.URL;
//import java.util.ArrayList;
//import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Modelo.NaveJugador;;

public class VistaJugador implements Observer{
	//
	private JLabel lblNave;
	private ImageIcon imagenNave;
	private ImageIcon imagenVidas;
	
	
	public VistaJugador(){
		// nave
		lblNave = new JLabel();
//		lblNave.setBounds(n.getPosicion().getX(),n.getPosicion().getY()-512, 32, 32);
		URL pathGalaga = this.getClass().getResource("Imagenes/Galaga_ship32.png");
		imagenNave = new ImageIcon(pathGalaga);
//		Icon iconoNave = new ImageIcon(imagenNave.getImage().getScaledInstance(lblNave.getWidth(), lblNave.getHeight(), Image.SCALE_DEFAULT));
		lblNave.setIcon(imagenNave);
//		lblNave.repaint();
		
		// vidas
		JLabel lblVida = new JLabel();
		lblVida.setBounds(5, 512, 16, 16);
		imagenVidas = new ImageIcon(pathGalaga);//
		Icon iconoVidas = new ImageIcon(imagenVidas.getImage().getScaledInstance(lblVida.getWidth(), lblVida.getHeight(), Image.SCALE_DEFAULT));
		lblVida.setIcon(iconoVidas);
		lblVida.repaint();
		
	}

	@Override
	public void update(Observable obs, Object arg1) 
	{
		NaveJugador n = (NaveJugador) obs;
		lblNave.setBounds(n.getPosicion().getX(),512- n.getPosicion().getY()-n.getAlto(), n.getAlto(), n.getAncho());		
		
	}

	public JLabel getLblNaveJugador() 
	{
		return this.lblNave;
	}
	
//	public JLabel getLblCantVidas(int i) {
//		return lblCantVidas.get(i);
//	}
}