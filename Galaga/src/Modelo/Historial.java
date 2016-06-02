package Modelo;

import java.util.ArrayList;


public class Historial {
	
	private ArrayList<ObjetoHistorial> objetos = new ArrayList<>();

	public ObjetoHistorial getObjeto(int n) {
		return objetos.get(n);
	}

	public void setObjeto(ObjetoHistorial objeto) {
		objetos.add(objeto);
	}
	
	public void removerObjeto(){
		objetos.remove(objetos.size()-1);
	}
	
	public int tamanio(){
		return objetos.size();
	}

}
