package Vista;


import java.awt.Image;
import java.net.URL;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import Modelo.*;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JPanel;


public class VentanaPrincipal extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	
	private Juego juego;
	private VistaEscenario espacio;
	private VistaJugador vistaJugador;

	/**
	 * ventana principal.
	 */
	public VentanaPrincipal(Juego juego) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//cerrar ventana al precionar la X
		setBounds(100, 100, 600, 600);
		setLocationRelativeTo(null); // centrar ventana
		setResizable(false); // bloquear redimencion de ventana
		setTitle("Galaga");// titulo
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		//----Imagen Favicon (Icono)
		URL pathGalaga = this.getClass().getResource("Imagenes/Galaga_ship.png");
		try{
			Image imagenIcono = ImageIO.read(pathGalaga);
			this.setIconImage(imagenIcono);
		}catch(Exception e){}
		
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar_1.add(mnArchivo);
		
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mnArchivo.add(mntmNuevo);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mnArchivo.add(mntmAbrir);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar_1.add(mnAyuda);
		
		JMenuItem mntmCreditos = new JMenuItem("Creditos");
		mnAyuda.add(mntmCreditos);
		
		JMenuItem mntmControles = new JMenuItem("Controles");
		mnAyuda.add(mntmControles);
		
				
		//----------------------------
		
		this.juego = juego;
		// instanciamos la vista jugador 
		this.vistaJugador = new VistaJugador();
		// al juego le pasamos un observador
		this.juego.getNaveJugador().addObserver(vistaJugador);
		// instanciamos un escenario (espacio)
		this.espacio = new VistaEscenario();
		// agregamos label al escenario
		this.espacio.getLblEspacio().add(this.vistaJugador.getLblNaveJugador());
		// agregamos espacio a ventana principal
		panel.add(this.espacio.getLblEspacio());
		
	}


	@Override
	public void update(Observable o, Object arg) {
		//
		Juego juego = (Juego)o;
		LinkedList<NaveEnemiga> navesEnemigas = juego.obtenerNavesNuevas();
		
		for (NaveEnemiga n : navesEnemigas) {
			
			if (n.getClass().equals(Demoledor.class)) {
				VistaNaveEnemiga vDem = new VistaNaveEnemiga();
				n.addObserver(vDem);
				this.espacio.getLblEspacio().add(vDem.getLblDemoledor());
			}
			if (n.getClass().equals(Destructor.class)) {
				VistaNaveEnemiga vDes = new VistaNaveEnemiga();
				n.addObserver(vDes);
				this.espacio.getLblEspacio().add(vDes.getLblDestructor());
			}
			if (n.getClass().equals(Depredador.class)) {
				VistaNaveEnemiga vDep = new VistaNaveEnemiga();
				n.addObserver(vDep);
				this.espacio.getLblEspacio().add(vDep.getLblDepredador());
			}
			if (n.getClass().equals(Exterminador.class)) {
				VistaNaveEnemiga vExt = new VistaNaveEnemiga();
				n.addObserver(vExt);
				this.espacio.getLblEspacio().add(vExt.getLblExterminador());
			}
			
		}
		
		LinkedList<Disparo> disparosJugador = juego.getDisparosJugadorNuevos();
		
		for (Disparo disparo : disparosJugador) {
			if (disparo.getClass().equals(Laser.class)){
				VistaDisparo vDis = new VistaDisparo();
				disparo.addObserver(vDis);
				this.espacio.getLblEspacio().add(vDis.getLblLaserJugador());
			}
		}
		
		
		
	}


	public void agregarJuego(Juego juego) {
		//
		this.juego = juego;
		
	}

}