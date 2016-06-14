package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Modelo.Juego;
import Vista.VentanaPrincipal;

public class ControladorMenu implements ActionListener{

	private Juego juego; // 
    private VentanaPrincipal ventana;
    private boolean bandera;
    
    public ControladorMenu(Juego juego, VentanaPrincipal ventana) 
    {
        this.juego = juego;
        this.ventana = ventana;
        this.ventana.setActionListenerForMenu(this);
        this.bandera = false;
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
            
           
            this.juego.descargarNivel();
            this.juego.setPausa(false);
            if (!bandera) {
				
            	Thread t = new Thread(new Runnable() {
            		
            		@Override
            		public void run() {
            			juego.cargarNivel1();
            			juego.jugar();
            			
            		}
            	});
            	
            	t.start();
            	bandera = true;
			}else{
				juego.cargarNivel1();
			}
            
            
            
		}
		//
		if (e.getSource() == this.ventana.getMenuItemAbrir()) {
			//abrimos el juego guardado
			
			
		}
		
		if (e.getSource() == this.ventana.getMenuItemGuardar()) {
			//cuardamos el juego
			
		}
		
		if (e.getSource() == this.ventana.getMenuItemPausa()) {
        	if (juego.getPausa()) {
        		juego.setPausa(false);
        	}else {
        		juego.setPausa(true);				
        	}
		}
		
		if (e.getSource() == this.ventana.getMenuItemSalir()) {
			this.juego.setPausa(true);
			String mensaje = "Se perderan todos los datos\n"
					+ "¿Desea salir?";
			int respuesta = Controlador.showConfirmDialog(mensaje);
			if (JOptionPane.OK_OPTION == respuesta) {
				System.exit(0);
			}else{
				this.juego.setPausa(false);
			}
				
		}
		
		if (e.getSource() == this.ventana.getMenuItemCreditos()) {
			String mensaje = "GALAGA PROYECTO\n"
					+ "Realizado por:\n"
					+ "Zalazar Gabriel\n"
					+ "Aguaysol Ernesto\n"
					+ "";
			this.juego.setPausa(true);				
			Controlador.showMessageDialog(mensaje);
			this.juego.setPausa(false);
		}
		
		if (e.getSource() == this.ventana.getMenuItemControles()) {
			String mensaje = "CONTROLES GALAGA\n"
					+ "MOVER IZQUIERDA = TECLA FLECHA IZQUIERDA\n"
					+ "MOVER DERECHA = TECLA FLECHA DERECHA\n"
					+ "MOVER ARRIBA = TECLA FLECHA ARRIBA\n"
					+ "MOVER ABAJO = TECLA FLECHA ABAJO\n"
					+ "DISPARAR = TECLA ESPACIO\n"
					+ "PAUSA = P"
					+ "";
			this.juego.setPausa(true);				
			Controlador.showMessageDialog(mensaje);
			this.juego.setPausa(false);
		}
	}
	
	

	
}
