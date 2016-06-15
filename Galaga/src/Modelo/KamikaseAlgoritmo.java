package Modelo;

//import java.util.LinkedList;

public class KamikaseAlgoritmo {
	private static int diferencia;
	
	
	//esta clase sera utilizada para calcular los distintos patrones de movimientos...
		//por el momento tiene el kamikaze que hace que la nave apunte directamente al jugador...
		//se implmentara luego el zigzag, elipse.etc....

			public static void moverKamikaze(Punto posicionJugador, NaveEnemiga enemigo) {
				//
				
				if (enemigo.getPosicion().getY() > 40) { //60
										
					if (posicionJugador.getX() < enemigo.getPosicion().getX()) {
						enemigo.getVelocidad().setX(-1);
						enemigo.getVelocidad().setY(-1);
					}else {
//						
						enemigo.getVelocidad().setX(1);
						enemigo.getVelocidad().setY(-1);
					}
				}else {
					enemigo.setEstado(Estado.VOLVIENDO);
				}
				
			}

			public static void moverVolviendo(NaveEnemiga n) {
				// 					
					if (n.getPosicioInicial().getY() > n.getPosicion().getY()) {
						n.getVelocidad().setX(0);
						n.getVelocidad().setY(-1);
					 
					}							
					if(n.getPosicioInicial().getX()>n.posicion.getX()){
						n.getVelocidad().setX(2);
						n.getVelocidad().setY(-1);
					}else{ 
						n.getVelocidad().setX(-2);
						n.getVelocidad().setY(-1);
					}
					
					if (n.getPosicioInicial().getX() == n.getPosicion().getX()) {					
						n.getVelocidad().setX(0);
						n.getVelocidad().setY(-1);
					} 
										
					if(n.getPosicioInicial().getY() == n.getPosicion().getY()) {
						n.getVelocidad().setY(0);
						n.setEstado(Estado.PASIVO);
						n.posicion.setX(n.posicioInicial.getX());
						n.posicion.setY(n.posicioInicial.getY());
						
					
					}
			}
			
			
			public static void moverPasivo(NaveEnemiga n) {
				
				int lejania=n.getPosicion().getX()-n.posicioInicial.getX();
				
				if(lejania < 50 ){
					n.getVelocidad().setX(1);

				}else{	diferencia=1;}

				if (diferencia==1){
						if(n.getPosicion().getX()>0){
							n.getVelocidad().setX(-1);
							
						}else{diferencia=0;}
				}
			}
				
				
				
				
				
			
}

