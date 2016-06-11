package Vista;

//import java.awt.Image;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
//import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Modelo.NaveEnemiga;

public class VistaNaveEnemiga implements Observer {

	JLabel lblNaveEnemiga;
	
	
	public VistaNaveEnemiga() {
		// nave
		lblNaveEnemiga = new JLabel();
//		lblNaveEnemiga.setBounds(x,y-512, 32, 32);
		URL pathGalaga = this.getClass().getResource("Imagenes/Galaga_Flagship32.png");
		ImageIcon imagenNave = new ImageIcon(pathGalaga);
//		Icon iconoNave = new ImageIcon(imagenNave.getImage().getScaledInstance(lblNaveEnemiga.getWidth(), lblNaveEnemiga.getHeight(), Image.SCALE_DEFAULT));
		lblNaveEnemiga.setIcon(imagenNave);
//		lblNaveEnemiga.repaint();
		
		
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		// 
		NaveEnemiga n = (NaveEnemiga)o;
		lblNaveEnemiga.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
		
	}
	
	public JLabel getLblNaveEnemiga() {
		return lblNaveEnemiga;
	}
	
}