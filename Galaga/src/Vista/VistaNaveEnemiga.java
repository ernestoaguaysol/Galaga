package Vista;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import Modelo.NaveEnemiga;

public class VistaNaveEnemiga implements Observer {

	JLabel lblDestructor;
	JLabel lblDemoledor;
	JLabel lblDepredador;
	JLabel lblExterminador;
	NaveEnemiga naveEnemiga;
	
	public VistaNaveEnemiga() {
		
		// DESTRUCTOR
		lblDestructor = new JLabel();
		URL pathDestructor = this.getClass().getResource("Imagenes/Destructor.png");
		ImageIcon imagenDestructor = new ImageIcon(pathDestructor);
		lblDestructor.setIcon(imagenDestructor);
		
		// DEMOLEDOR
		lblDemoledor = new JLabel();
		URL pathDemoledor = this.getClass().getResource("Imagenes/Demoledor.png");
		ImageIcon imagenDemoledor = new ImageIcon(pathDemoledor);
		lblDemoledor.setIcon(imagenDemoledor);
		
		// DEPREDADOR
		lblDepredador = new JLabel();
		URL pathDepredador = this.getClass().getResource("Imagenes/Depredador.png");
		ImageIcon imagenDepredador = new ImageIcon(pathDepredador);
		lblDepredador.setIcon(imagenDepredador);
		
		// EXTERMINADOR
		lblExterminador = new JLabel();
		URL pathExterminador = this.getClass().getResource("Imagenes/Exterminador.png");
		ImageIcon imagenExterminador = new ImageIcon(pathExterminador);
		lblExterminador.setIcon(imagenExterminador);
		
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		// 
		NaveEnemiga n = (NaveEnemiga)o;
		lblDestructor.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
		lblDemoledor.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
		lblDepredador.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
		lblExterminador.setBounds(n.getPosicion().getX(), 512-n.getPosicion().getY()-n.getAlto(), n.getAncho(), n.getAlto());
	}
	
	public JLabel getLblDestructor() {
		return lblDestructor;
	}

	public JLabel getLblDemoledor() {
		// 
		return lblDemoledor;
	}
	
	public JLabel getLblDepredador() {
		// 
		return lblDepredador;
	}
	
	public JLabel getLblExterminador() {
		// 
		return lblExterminador;
	}
	
	public NaveEnemiga getNaveEnemiga() {
		return naveEnemiga;
	}
	
	public void setNaveEnemiga(NaveEnemiga naveEnemiga) {
		this.naveEnemiga = naveEnemiga;
	}
	
}
