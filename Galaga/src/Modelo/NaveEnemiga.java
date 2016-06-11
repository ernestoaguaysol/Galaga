package Modelo;

import java.util.LinkedList;

public abstract class NaveEnemiga extends Nave{
	//solo las naves enemigas tienen estados
	//pueden ser...
	Estado estado;
	Punto posicioInicial;
	LinkedList<Punto> kamikaze;
	
	public Estado getEstado() {
		return estado;
	}

	 //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	//sobrecargo el metodo para hacer funcionar los patrones de movimiento
	public void mover(Punto jugador) {
		
//		System.out.println("entre en mover sobrecargado");
//		//si la lista no esta creada...la creo....sino no
//		if (kamikaze==null){
//			kamikaze=new LinkedList<Punto>();
//			System.out.println("entre###################");
//			KamikaseAlgoritmo k= new KamikaseAlgoritmo(this,jugador);
//			//lleno la lista del objeto con los calculos
//			kamikaze=k.getMovimientos();
//			
//		}
		
		
		if (this.estado.equals(Estado.KAMIKAZE)) {
			KamikaseAlgoritmo.mover(jugador,this);
		}
		
		
//		//si la lista no esta vacia
//		if(!kamikaze.isEmpty()){
//			//y la posicion de la nave no exede a la del jugador
//			if (this.posicion.getY() >= jugador.getY()){
//				//creamos un punto con la posiscion
//				Punto posicionAtake=kamikaze.getLast();
//				//asignamos los valores a la nave enemiga
//				this.posicion.setX(posicionAtake.getX());
//				this.posicion.setY(posicionAtake.getY());
//				System.out.println("entre en mover sobrecargado"+posicion.getX()+"...."+ posicion.getY());
//				//removemos el elemento utilizado de la lista
//				kamikaze.removeLast();
//			}
//			//si exede la posicion cambio estado
//			else{
//				this.estado=Estado.VOLVIENDO;
//				//this.posicion.setX(this.posicion.getX()+this.velocidad.getX());
//				//this.posicion.setY(this.posicion.getY()+1);
//				System.out.println("@1");
//
//			}
//			//si la lista esta vacia...llegue a tope de movimientos cambio estado...
//		}else{
//			this.estado=Estado.VOLVIENDO;
//			//this.posicion.setY(this.posicion.getY()+1);
//			System.out.println("@2");
//			//this.mover();
//		}
//		
	}
//	//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//	
//	
//	public void volviendo(){
//		
//	}
	
	
}
