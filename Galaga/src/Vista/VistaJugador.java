package Vista;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.util.LinkedList;
//import java.util.ArrayList;
//import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import Modelo.NaveJugador;;

public class VistaJugador implements Observer{
	//
	private JLabel lblNave;
	private LinkedList<JLabel> lblVidas;
	private ImageIcon imagenNave;
	private JProgressBar energia;
	
	public VistaJugador(){
		// nave
		lblNave = new JLabel();
//		lblNave.setBounds(n.getPosicion().getX(),n.getPosicion().getY()-512, 32, 32);
		URL pathGalaga = this.getClass().getResource("Imagenes/NaveJugador.png");
		imagenNave = new ImageIcon(pathGalaga);
//		Icon iconoNave = new ImageIcon(imagenNave.getImage().getScaledInstance(lblNave.getWidth(), lblNave.getHeight(), Image.SCALE_DEFAULT));
		lblNave.setIcon(imagenNave);
//		lblNave.repaint();
		
		// vidas
		this.lblVidas = new LinkedList<>();
		
		JLabel lblVida1 = new JLabel();
		lblVida1.setBounds(450, 2, 16, 16);
		ImageIcon imagenVidas = new ImageIcon(pathGalaga);//
		Icon iconoVidas = new ImageIcon(imagenVidas.getImage().getScaledInstance(lblVida1.getWidth(), lblVida1.getHeight(), Image.SCALE_DEFAULT));
		lblVida1.setIcon(iconoVidas);
		lblVida1.repaint();

		JLabel lblVida2 = new JLabel();
		lblVida2.setBounds(470, 2, 16, 16);
		lblVida2.setIcon(iconoVidas);
		lblVida2.repaint();
		
		JLabel lblVida3 = new JLabel();
		lblVida3.setBounds(490, 2, 16, 16);
		lblVida3.setIcon(iconoVidas);
		lblVida3.repaint();
		
		this.lblVidas.add(lblVida1);
		this.lblVidas.add(lblVida2);
		this.lblVidas.add(lblVida3);
		
		//energia
		this.energia = new JProgressBar(0,100);
		this.energia.setBounds(2, 2, 100, 20);
		this.energia.setForeground(Color.GREEN);
	}

	@Override
	public void update(Observable obs, Object arg1) 
	{
		NaveJugador n = (NaveJugador) obs;
		lblNave.setBounds(n.getPosicion().getX(),512- n.getPosicion().getY()-n.getAlto(), n.getAlto(), n.getAncho());		
		this.energia.setValue(n.getEnergia());
		if (n.getVidas() == 3) {
			this.lblVidas.get(0).setVisible(true);
			this.lblVidas.get(1).setVisible(true);
			this.lblVidas.get(2).setVisible(true);
		}else if (n.getVidas() == 2) {
			
			this.lblVidas.get(0).setVisible(true);
			this.lblVidas.get(1).setVisible(true);
			this.lblVidas.get(2).setVisible(false);
			
		}else if (n.getVidas() == 1) {
			this.lblVidas.get(0).setVisible(true);
			this.lblVidas.get(1).setVisible(false);
			this.lblVidas.get(2).setVisible(false);
			
		}else {
			this.lblVidas.get(0).setVisible(false);
			this.lblVidas.get(1).setVisible(false);
			this.lblVidas.get(2).setVisible(false);
			
		}
	}

	public JLabel getLblNaveJugador(){
		return this.lblNave;
	}
	
	public JProgressBar getEnergia() {
		return energia;
	}
	
	public LinkedList<JLabel> getLblVidas() {
		return lblVidas;
	}
	
}