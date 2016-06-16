package Vista;


import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import Controlador.Controlador;
import Modelo.*;

import javax.swing.JMenuItem;

import java.awt.BorderLayout;

import javax.swing.JPanel;











import sonido.Sonido;




public class VentanaPrincipal extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	
	private JMenuItem mntmNuevo;
	private JMenuItem mntmAbrir;
	private JMenuItem mntmGuardar;
	private JMenuItem mntmPausa;
	private JMenuItem mntmSalir;
	private JMenu mnAyuda;
	private JMenuItem mntmCreditos;
	private JMenuItem mntmControles;
	
	private Juego juego;
	private VistaEscenario espacio;
	private VistaJugador vistaJugador;
	private LinkedList<VistaObjetoEspacial> vistaObjetosEspaciales;
	private LinkedList<VistaNaveEnemiga> vistaNavesEnemigas;
	private CopyOnWriteArrayList<VistaDisparo> vistaDisparos;
	
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
		URL pathGalaga = this.getClass().getResource("Imagenes/NaveJugador2.png");
		try{
			Image imagenIcono = ImageIO.read(pathGalaga);
			this.setIconImage(imagenIcono);
		}catch(Exception e){}
		
		JMenuBar menuBar_1 = new JMenuBar();
		setJMenuBar(menuBar_1);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar_1.add(mnArchivo);
		
		this.mntmNuevo = new JMenuItem("Nuevo Juego");
		mnArchivo.add(mntmNuevo);
		
		this.mntmAbrir = new JMenuItem("Abrir Juego");
		mnArchivo.add(mntmAbrir);

		this.mntmGuardar = new JMenuItem("Guardar Juego");
		mnArchivo.add(mntmGuardar);
		
		this.mntmPausa = new JMenuItem("Pausa");
		mnArchivo.add(mntmPausa);
		
		this.mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		this.mnAyuda = new JMenu("Ayuda");
		menuBar_1.add(mnAyuda);
		
		this.mntmCreditos = new JMenuItem("Creditos");
		mnAyuda.add(mntmCreditos);
		
		this.mntmControles = new JMenuItem("Controles");
		mnAyuda.add(mntmControles);
		
				
		//----------------------------
		
		
		this.juego = juego;
		this.vistaObjetosEspaciales = new LinkedList<>();
		this.vistaNavesEnemigas = new LinkedList<>();
		this.vistaDisparos = new CopyOnWriteArrayList<>();
		
		// instanciamos la vista jugador 
		this.vistaJugador = new VistaJugador();
		// al juego le pasamos un observador
		this.juego.getNaveJugador().addObserver(vistaJugador);
		// instanciamos un escenario (espacio)
		this.espacio = new VistaEscenario();
		// agregamos label al escenario
		this.espacio.getLblEspacio().add(this.vistaJugador.getLblNaveJugador());
		this.espacio.getLblEspacio().add(this.vistaJugador.getLblInmortal());
		this.espacio.getLblEspacio().add(this.vistaJugador.getEnergia());
		this.espacio.getLblEspacio().add(this.vistaJugador.getLblVidas().get(0));
		this.espacio.getLblEspacio().add(this.vistaJugador.getLblVidas().get(1));
		this.espacio.getLblEspacio().add(this.vistaJugador.getLblVidas().get(2));
		
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
		
		//si la cantidad de vidas es igual a 0, mostramos mensaje de game over y limpiamos la pantalla.
		if(juego.getNaveJugador().getVidas()==0){
			Controlador.showMessageDialog("GAME OVER\n"+"te has quedado sin vidas...");
			Galaga.InicioGalaga.main(null);
		}
		//si el jugador muere matando la ultima nave es juego perdido
		if(juego.getNaveJugador().getVidas()==0 && juego.isGameWin()){
			Controlador.showMessageDialog("GAME OVER\n"+"destruiste todas las naves pero...\n"+"tambien has sido destruido");
			Galaga.InicioGalaga.main(null);
		}
		//si el juego termina con la destruccion de todas las naves enemigas nos muestra la informacion
		//y queda preparado para un nuevo juego.
		if(juego.isGameWin()){
			Controlador.showMessageDialog("FELICITACIONES!!!!!......\n"+"DESTRUISTE A TODAS LAS NAVES ENEMIGAS..\n"+"Proximamente Tendremos mas Niveles\n"+"si es que aprobamos.................");
			Galaga.InicioGalaga.main(null);
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
				//cargo sonido
				try {
					Sonido.disparoEnemigo1();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//
				vLas.setDisparo(disparo);
				disparo.addObserver(vLas);
				this.vistaDisparos.add(vLas);
				this.espacio.getLblEspacio().add(vLas.getLblLaserEnemigo());
			}
			if (disparo.getClass().equals(Misil.class)){
				VistaDisparo vMis = new VistaDisparo();
				//cargo sonido
				try {
					Sonido.disparoEnemigo();
				} catch (Exception e) {
					e.printStackTrace();
				}
				//
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
						//cargo sonido
						try {
							Sonido.vidaExtra();
						} catch (Exception e) {
							e.printStackTrace();
						}
						//
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Meteorito.class)) {
				for (VistaObjetoEspacial vistaObjetoEspacial : vistaObjetosEspaciales) {
					if (objetoMovilMuerto.equals(vistaObjetoEspacial.getObjetoEspacial())) {
						objetoMovilMuerto.deleteObserver(vistaObjetoEspacial);
						this.espacio.getLblEspacio().remove(vistaObjetoEspacial.getLblMeteorito());
						//cargo sonido
						try {
							Sonido.muerteObjeto1();
						} catch (Exception e) {
							e.printStackTrace();
						}
						//
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Asteroide.class)) {
				for (VistaObjetoEspacial vistaObjetoEspacial : vistaObjetosEspaciales) {
					if (objetoMovilMuerto.equals(vistaObjetoEspacial.getObjetoEspacial())) {
						objetoMovilMuerto.deleteObserver(vistaObjetoEspacial);
						this.espacio.getLblEspacio().remove(vistaObjetoEspacial.getLblAsteroide());
						//cargo sonido
						try {
							Sonido.muerteObjeto();
						} catch (Exception e) {
							e.printStackTrace();
						}
						//
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Demoledor.class)) {
				for (VistaNaveEnemiga vistaNaveEnemiga : vistaNavesEnemigas) {
					if (objetoMovilMuerto.equals(vistaNaveEnemiga.getNaveEnemiga())) {
						objetoMovilMuerto.deleteObserver(vistaNaveEnemiga);
						this.espacio.getLblEspacio().remove(vistaNaveEnemiga.getLblDemoledor());
						//cargo sonido
						try {
							Sonido.explosion();
						} catch (Exception e) {
							e.printStackTrace();
						}
						//
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Depredador.class)) {
				for (VistaNaveEnemiga vistaNaveEnemiga : vistaNavesEnemigas) {
					if (objetoMovilMuerto.equals(vistaNaveEnemiga.getNaveEnemiga())) {
						objetoMovilMuerto.deleteObserver(vistaNaveEnemiga);
						this.espacio.getLblEspacio().remove(vistaNaveEnemiga.getLblDepredador());
						//cargo sonido
						try {
							Sonido.explosion1();
						} catch (Exception e) {
							e.printStackTrace();
						}
						//
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Destructor.class)) {
				for (VistaNaveEnemiga vistaNaveEnemiga : vistaNavesEnemigas) {
					if (objetoMovilMuerto.equals(vistaNaveEnemiga.getNaveEnemiga())) {
						objetoMovilMuerto.deleteObserver(vistaNaveEnemiga);
						this.espacio.getLblEspacio().remove(vistaNaveEnemiga.getLblDestructor());
						//cargo sonido
						try {
							Sonido.explosion();
						} catch (Exception e) {
							e.printStackTrace();
						}
						//
					}
				}
			}else if (objetoMovilMuerto.getClass().equals(Exterminador.class)) {
				for (VistaNaveEnemiga vistaNaveEnemiga : vistaNavesEnemigas) {
					if (objetoMovilMuerto.equals(vistaNaveEnemiga.getNaveEnemiga())) {
						objetoMovilMuerto.deleteObserver(vistaNaveEnemiga);
						this.espacio.getLblEspacio().remove(vistaNaveEnemiga.getLblExterminador());
						//cargo sonido
						try {
							Sonido.explosion();
						} catch (Exception e) {
							e.printStackTrace();
						}
						//
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


	public void setActionListenerForMenu(ActionListener a) {
		//
		this.mntmNuevo.addActionListener(a);
		this.mntmAbrir.addActionListener(a);
		this.mntmGuardar.addActionListener(a);
		this.mntmPausa.addActionListener(a);
		this.mntmSalir.addActionListener(a);
		this.mntmCreditos.addActionListener(a);
		this.mntmControles.addActionListener(a);
		
	}


	public Object getMenuItemJuegoNuevo() {// aca hay que hacer para cada item para que el controlador menu sepa cual item se apretó
		//
		return this.mntmNuevo;
	}
	
	public Object getMenuItemAbrir() {
		//
		return this.mntmAbrir;
	}

	public Object getMenuItemGuardar() {
		//
		return this.mntmGuardar;
	}
	
	public Object getMenuItemPausa() {
		//
		return this.mntmPausa;
	}
	
	public Object getMenuItemSalir() {
		//
		return this.mntmSalir;
	}
	
	public Object getMenuItemCreditos() {
		//
		return this.mntmCreditos;
	}
	
	public Object getMenuItemControles() {
		//
		return this.mntmControles;
	}
	
}
