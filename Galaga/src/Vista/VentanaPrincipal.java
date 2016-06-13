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

	private LinkedList<VistaObjetoEspacial> vistaObjetosEspaciales;
	private LinkedList<VistaNaveEnemiga> vistaNavesEnemigas;
	private LinkedList<VistaDisparo> vistaDisparos;
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
		
		this.vistaObjetosEspaciales = new LinkedList<>();
		this.vistaNavesEnemigas = new LinkedList<>();
		this.vistaDisparos = new LinkedList<>();
		
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
				vDem.setNaveEnemiga(n);
				n.addObserver(vDem);
				this.vistaNavesEnemigas.add(vDem);
				this.espacio.getLblEspacio().add(vDem.getLblDemoledor());
			}
			if (n.getClass().equals(Destructor.class)) {
				VistaNaveEnemiga vDes = new VistaNaveEnemiga();
				vDes.setNaveEnemiga(n);
				n.addObserver(vDes);
				this.vistaNavesEnemigas.add(vDes);
				this.espacio.getLblEspacio().add(vDes.getLblDestructor());
			}
			if (n.getClass().equals(Depredador.class)) {
				VistaNaveEnemiga vDep = new VistaNaveEnemiga();
				vDep.setNaveEnemiga(n);
				n.addObserver(vDep);
				this.vistaNavesEnemigas.add(vDep);
				this.espacio.getLblEspacio().add(vDep.getLblDepredador());
			}
			if (n.getClass().equals(Exterminador.class)) {
				VistaNaveEnemiga vExt = new VistaNaveEnemiga();
				vExt.setNaveEnemiga(n);
				n.addObserver(vExt);
				this.vistaNavesEnemigas.add(vExt);
				this.espacio.getLblEspacio().add(vExt.getLblExterminador());
			}
			
		}
		
		LinkedList<Disparo> disparosJugador = juego.getDisparosJugadorNuevos();
		
		for (Disparo disparo : disparosJugador) {
			if (disparo.getClass().equals(Laser.class)){
				VistaDisparo vLas = new VistaDisparo();
				vLas.setDisparo(disparo);
				disparo.addObserver(vLas);
				this.vistaDisparos.add(vLas);
				this.espacio.getLblEspacio().add(vLas.getLblLaserJugador());
			}
			if (disparo.getClass().equals(Misil.class)){
				VistaDisparo vMis = new VistaDisparo();
				vMis.setDisparo(disparo);
				disparo.addObserver(vMis);
				this.vistaDisparos.add(vMis);
				this.espacio.getLblEspacio().add(vMis.getLblMisilJugador());
			}
		}
		
		LinkedList<Disparo> disparosEnemigos = juego.getDisparosEnemigosNuevos();
		
		for (Disparo disparo : disparosEnemigos) {
			if (disparo.getClass().equals(Laser.class)){
				VistaDisparo vLas = new VistaDisparo();
				vLas.setDisparo(disparo);
				disparo.addObserver(vLas);
				this.vistaDisparos.add(vLas);
				this.espacio.getLblEspacio().add(vLas.getLblLaserEnemigo());
			}
			if (disparo.getClass().equals(Misil.class)){
				VistaDisparo vMis = new VistaDisparo();
				vMis.setDisparo(disparo);
				disparo.addObserver(vMis);
				this.vistaDisparos.add(vMis);
				this.espacio.getLblEspacio().add(vMis.getLblMisilEnemigo());
			}
		}
		
		LinkedList<ObjetoEspacial> objetosEspaciales = juego.getObjetosEspacialesNuevos();
		
		for (ObjetoEspacial objetoEspacial : objetosEspaciales) {
			if (objetoEspacial.getClass().equals(EstrellaFugaz.class)){
				VistaObjetoEspacial oEst = new VistaObjetoEspacial();
				// agregamos el objeto a la clase
				oEst.setObjetoEspacial(objetoEspacial);
				objetoEspacial.addObserver(oEst);
				this.vistaObjetosEspaciales.add(oEst);
				this.espacio.getLblEspacio().add(oEst.getLblEstrellaFugaz(), objetoEspacial);
			}
			if (objetoEspacial.getClass().equals(Meteorito.class)){
				VistaObjetoEspacial oMet = new VistaObjetoEspacial();
				oMet.setObjetoEspacial(objetoEspacial);
				objetoEspacial.addObserver(oMet);
				this.vistaObjetosEspaciales.add(oMet);
				this.espacio.getLblEspacio().add(oMet.getLblMeteorito());
			}
			if (objetoEspacial.getClass().equals(Asteroide.class)){
				VistaObjetoEspacial oAst = new VistaObjetoEspacial();
				oAst.setObjetoEspacial(objetoEspacial);
				objetoEspacial.addObserver(oAst);
				this.vistaObjetosEspaciales.add(oAst);
				this.espacio.getLblEspacio().add(oAst.getLblAsteroide());
			}
		}
		
		LinkedList<ObjetoMovil> objetosMovilesMuertos = juego.getObjetosMovilesMuertos();
		for (ObjetoMovil objetoMovilMuerto : objetosMovilesMuertos) {
			if (objetoMovilMuerto.getClass().equals(EstrellaFugaz.class)) {
				for (VistaObjetoEspacial vistaObjetoEspacial : vistaObjetosEspaciales) {
					if (objetoMovilMuerto.equals(vistaObjetoEspacial.getObjetoEspacial())) {
						objetoMovilMuerto.deleteObserver(vistaObjetoEspacial);
						this.espacio.getLblEspacio().remove(vistaObjetoEspacial.getLblEstrellaFugaz());
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Meteorito.class)) {
				for (VistaObjetoEspacial vistaObjetoEspacial : vistaObjetosEspaciales) {
					if (objetoMovilMuerto.equals(vistaObjetoEspacial.getObjetoEspacial())) {
						objetoMovilMuerto.deleteObserver(vistaObjetoEspacial);
						this.espacio.getLblEspacio().remove(vistaObjetoEspacial.getLblMeteorito());
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Asteroide.class)) {
				for (VistaObjetoEspacial vistaObjetoEspacial : vistaObjetosEspaciales) {
					if (objetoMovilMuerto.equals(vistaObjetoEspacial.getObjetoEspacial())) {
						objetoMovilMuerto.deleteObserver(vistaObjetoEspacial);
						this.espacio.getLblEspacio().remove(vistaObjetoEspacial.getLblAsteroide());
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Demoledor.class)) {
				for (VistaNaveEnemiga vistaNaveEnemiga : vistaNavesEnemigas) {
					if (objetoMovilMuerto.equals(vistaNaveEnemiga.getNaveEnemiga())) {
						objetoMovilMuerto.deleteObserver(vistaNaveEnemiga);
						this.espacio.getLblEspacio().remove(vistaNaveEnemiga.getLblDemoledor());
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Depredador.class)) {
				for (VistaNaveEnemiga vistaNaveEnemiga : vistaNavesEnemigas) {
					if (objetoMovilMuerto.equals(vistaNaveEnemiga.getNaveEnemiga())) {
						objetoMovilMuerto.deleteObserver(vistaNaveEnemiga);
						this.espacio.getLblEspacio().remove(vistaNaveEnemiga.getLblDepredador());
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Destructor.class)) {
				for (VistaNaveEnemiga vistaNaveEnemiga : vistaNavesEnemigas) {
					if (objetoMovilMuerto.equals(vistaNaveEnemiga.getNaveEnemiga())) {
						objetoMovilMuerto.deleteObserver(vistaNaveEnemiga);
						this.espacio.getLblEspacio().remove(vistaNaveEnemiga.getLblDestructor());
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Exterminador.class)) {
				for (VistaNaveEnemiga vistaNaveEnemiga : vistaNavesEnemigas) {
					if (objetoMovilMuerto.equals(vistaNaveEnemiga.getNaveEnemiga())) {
						objetoMovilMuerto.deleteObserver(vistaNaveEnemiga);
						this.espacio.getLblEspacio().remove(vistaNaveEnemiga.getLblExterminador());
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Laser.class)) {
				for (VistaDisparo vistaDisparo : vistaDisparos) {
					if (objetoMovilMuerto.equals(vistaDisparo.getDisparo())) {
						objetoMovilMuerto.deleteObserver(vistaDisparo);
						this.espacio.getLblEspacio().remove(vistaDisparo.getLblLaserEnemigo());
						this.espacio.getLblEspacio().remove(vistaDisparo.getLblLaserJugador());
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Misil.class)) {
				for (VistaDisparo vistaDisparo : vistaDisparos) {
					if (objetoMovilMuerto.equals(vistaDisparo.getDisparo())) {
						objetoMovilMuerto.deleteObserver(vistaDisparo);
						this.espacio.getLblEspacio().remove(vistaDisparo.getLblMisilEnemigo());
						this.espacio.getLblEspacio().remove(vistaDisparo.getLblMisilJugador());
					}
				}
			}
		}
		
		
	}


	public void agregarJuego(Juego juego) {
		//
		this.juego = juego;
		
	}

}