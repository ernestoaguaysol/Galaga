package Vista;

import java.awt.BorderLayout;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelContenedor;
	

	/**
	 * ventana principal.
	 */
	public VentanaPrincipal() {
		setTitle("Galaga");// titulo
		//cerrar ventana al precionar la X
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 
		setBounds(100, 100, 900, 720);
		setLocationRelativeTo(null); // centrar ventana
		setResizable(false); // bloquear redimencion de ventana
		
		//----Imagen Favicon (Icono)
		URL pathGalaga = this.getClass().getResource("Imagenes/Galaga_ship.png");
		try{
			Image imagenIcono = ImageIO.read(pathGalaga);
			this.setIconImage(imagenIcono);
		}catch(Exception e){}
		
		// instancia de JPanel --
		panelContenedor = new JPanel();
		panelContenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelContenedor.setLayout(new BorderLayout(0, 0));
		// lo agregamos al JFrame
		setContentPane(panelContenedor);
		
		
		//----------------------------
//		
		
		
		
	}

}
