package Modelo;

//import java.util.LinkedList;

public class KamikaseAlgoritmo {
	private static int diferencia;
	
	//esta clase sera utilizada para calcular los distintos patrones de movimientos...
		//por el momento tiene el kamikaze que hace que la nave apunte directamente al jugador...
		//se implmentara luego el zigzag, elipse.etc....
//		private LinkedList<Punto> movimientos;
//		
//		public LinkedList<Punto> getMovimientos() {
//			
//			
//			return movimientos;
//		}
//			public KamikaseAlgoritmo(ObjetoMovil enemigo_0, Punto jugador_1) {
//				this.movimientos=new LinkedList<>();
//				int x, y, dx, dy, p, incE, incNE, stepx, stepy;
//				  dx = (enemigo_0.posicion.getX()-jugador_1.getX());
//				  dy = (enemigo_0.posicion.getY()-1-jugador_1.getY());
//				  
//				 /* determinar que punto usar para empezar, cual para terminar */
//				  if (dy < 0) { 
//				    dy = -dy; 
//				    stepy = -1; 
//				  } 
//				  else {
//				    stepy = 1;
//				  }
//
//				  if (dx < 0) {  
//				    dx = -dx;  
//				    stepx = -1; 
//				  } 
//				  else {
//				    stepx = 1;
//				  }
//
//				 // x = enemigo_0.posicion.getX();
//				 // y = enemigo_0.posicion.getY();
//				  x=jugador_1.getX();
//				  y=jugador_1.getY();
//				 // System.out.println("posiciones kamikase11111111111 "+ x+"..."+y);
//				
//				 //this.velocidad=new Punto(x,y);
//				 /* se cicla hasta llegar al extremo de la línea */
//				  if(dx>dy){
//				    p = 2*dy - dx;
//				    incE = 2*dy;
//				    incNE = 2*(dy-dx);
//				   // while (x != jugador_1.getX()){
//				    while (x != enemigo_0.posicion.getX()){
//				      x = x + stepx;
//				      if (p < 0){
//				        p = p + incE;
//				      }
//				      else {
//				        y = y + stepy;
//				        p = p + incNE;
//				      }
//				      
//				     System.out.println("posiciones kamikase22222222222222 "+ x+"..."+y);
//				    
//				      movimientos.add(new Punto(x,y));
//				     
//				    }
//				  }
//				  else{
//				    p = 2*dx - dy;
//				    incE = 2*dx;
//				    incNE = 2*(dx-dy);
//				   // while (y != jugador_1.getY()){
//				    	
//				    while (y != enemigo_0.posicion.getY()){
//				      y = y + stepy;
//				      if (p < 0){
//				        p = p + incE;
//				      }
//				      else {
//				        x = x + stepx;
//				        p = p + incNE;
//				      }
//				      
//				      movimientos.add(new Punto(x,y));
//				     System.out.println("posiciones kamikase "+ x+"..."+y);
//				      
//				    }
//				    
//				  }
//				 }
			public static void moverKamikaze(Punto posicionJugador, NaveEnemiga enemigo) {
				//
				
				if (enemigo.getPosicion().getY() > 60) {
										
					if (posicionJugador.getX() < enemigo.getPosicion().getX()) {
						
						enemigo.getVelocidad().setX(-1);
						enemigo.getVelocidad().setY(-1);
					}else {
//						System.out.println("entre else");
						enemigo.getVelocidad().setX(1);
						enemigo.getVelocidad().setY(-1);
					}
				}else {
					enemigo.setEstado(Estado.VOLVIENDO);
				}
				
			}

			public static void moverVolviendo(NaveEnemiga n) {
				// 
//				System.out.println("entre en volviendo");
				if (!n.getPosicioInicial().equals(n.getPosicion())) {
					
					if (n.getPosicioInicial().getY() > n.getPosicion().getY()) {
						n.getVelocidad().setX(0);
						n.getVelocidad().setY(-1);
					}else {
						if (n.getPosicioInicial().getX() == n.getPosicion().getX()) {					
							n.getVelocidad().setX(0);
							n.getVelocidad().setY(1);
						}else if (n.getPosicioInicial().getX() < n.getPosicion().getX()) {
							n.getVelocidad().setX(-1);
							n.getVelocidad().setY(-1);
						}else {
							
							n.getVelocidad().setX(1);
							n.getVelocidad().setY(-1);
						}
					}
				}else {
					n.setEstado(Estado.PASIVO);
				}
				
			}
	
			public static void moverPasivo(NaveEnemiga n) {
				
				int lejania=n.getPosicion().getX()-n.posicioInicial.getX();
				
				if(lejania < 50 ){
					n.getVelocidad().setX(1);
//					System.out.println("sumo  1     " + diferencia);
				}else{	diferencia=1;}

				if (diferencia==1){
						if(n.getPosicion().getX()>0){
							n.getVelocidad().setX(-1);
						}else{diferencia=0;}
				}
			}
				
				
							
				
					
				
				
			
}
