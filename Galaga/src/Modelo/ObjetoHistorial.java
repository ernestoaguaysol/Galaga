package Modelo;

import java.util.ArrayList;

public class ObjetoHistorial {
	private String tipo;
	private String nombre;
	private ArrayList<String> posicion;
	private ArrayList<String> tamanio;
	private ArrayList<String> velocidad;
	private String estado;
	private String vidas;
	private String energia;
	private String danio;
	
	public ObjetoHistorial() {
		//
		this.posicion = new ArrayList<>();
		this.tamanio = new ArrayList<>();
		this.velocidad = new ArrayList<>();
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<String> getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion.add(posicion);
	}

	public ArrayList<String> getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio.add(tamanio);
	}

	public ArrayList<String> getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(String velocidad) {
		this.velocidad.add(velocidad);
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getVidas() {
		return vidas;
	}

	public void setVidas(String vidas) {
		this.vidas = vidas;
	}

	public String getEnergia() {
		return energia;
	}

	public void setEnergia(String energia) {
		this.energia = energia;
	}

	public void setDanio(String danio) {
		// 
		this.danio = danio;
	}
	
	public String getDanio() {
		return danio;
	}

}
