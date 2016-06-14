package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.Juego;
import Vista.VentanaPrincipal;

public class ControladorMenu implements ActionListener{

	private Juego juego; // 
    private VentanaPrincipal ventana;
    
    public ControladorMenu(Juego juego, VentanaPrincipal ventana) 
    {
        this.juego = juego;
        this.ventana = ventana;
        this.ventana.setActionListenerForMenu(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//
		if (e.getSource() == this.ventana.getMenuItemJuegoNuevo())
		{
            System.out.println("Juego Nuevo Apretado");    
            // lo lanzo en un hilo aparte
            // el hilo que ejecuta este metodo actionPerformed es el mismo
            // que actualiza la pantalla y escucha eventos sobre la misma
            // si llamo el while infinito de jugar en este metodo
            // el actionPerformed este nunca termine hasta que muera
            // la nave por atras... y recien ahi actualiza
            // por eso aca hago que el juego se ejecute en un hilo nuevo
            
            Thread t = new Thread(new Runnable() {
                
                @Override
                public void run() {
                    juego.cargarNivel1();
                    juego.jugar();
                    
                }
            });
            t.start();
		}
		//etc....
	}

	
}
