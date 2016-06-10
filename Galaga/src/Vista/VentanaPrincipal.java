package Vista;

import java.awt.BorderLayout;
import java.awt.Image;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Juego;


public class VentanaPrincipal extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JPanel panelContenedor;
	private Juego juego;
	private VistaEscenario espacio;
	private VistaJugador vistaJugador;

	/**
	 * ventana principal.
	 */
	public VentanaPrincipal(Juego juego) {
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
		this.juego = juego;
		// instanciamos la vista jugador 
		this.vistaJugador = new VistaJugador(this.juego.getNaveJugador());
		// al juego le pasamos un observador
		this.juego.getNaveJugador().addObserver(vistaJugador);
		// instanciamos un escenario (espacio)
		this.espacio = new VistaEscenario();
		// agregamos label al escenario
		this.espacio.agregarLabel(this.vistaJugador.getLblNaveJugador());
		this.espacio.agregarLabel(this.vistaJugador.getLblCantVidas());
		// agregamos espacio a ventana principal
		add(this.espacio.getLebel());
		
	}


	@Override
	public void update(Observable o, Object arg) {
		//
		
		
	}


	public void agregarJuego(Juego juego) {
		//
		this.juego = juego;
		
	}

}
