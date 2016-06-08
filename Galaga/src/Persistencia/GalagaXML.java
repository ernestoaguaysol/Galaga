package Persistencia;

import javax.xml.parsers.*;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

import Modelo.Historial;
import Modelo.ObjetoHistorial;


public class GalagaXML {
	
	public static Historial leerXML(String ruta){
		//
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(ruta);
		} catch (Exception e) {
			// algun mensaje----------------
			return null;
		}
		// creamos un historial nuevo
		Historial historialList = new Historial();
		// leermos el document NODO RAIZ <historial>
		Element historial = document.getDocumentElement();
		//leemos la lista de nodos Hijos(objeto) de Historial nodo
		NodeList objeto = historial.getChildNodes();
		//recorremos la lista de objeto (nodosHijos)
		for (int i = 0; i < objeto.getLength(); i++) {
			// nodo i
			Node n = objeto.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				// creamos un objeto historial nuevo
				ObjetoHistorial objetoHistorial = new ObjetoHistorial();
				// casteamos de node a element
				Element e = (Element) n;
				//tag <tipo>
				NodeList tipoObjetoNodo = e.getElementsByTagName("tipo");
				// insertamos texto tipo
				objetoHistorial.setTipo(tipoObjetoNodo.item(0).getTextContent());
				//tag <nombre>
				NodeList nombreObjetoNodo = e.getElementsByTagName("nombre");
				// insertamos texto
				objetoHistorial.setNombre(nombreObjetoNodo.item(0).getTextContent());
				// tag <posicion>
				NodeList posicionNodo = e.getElementsByTagName("posicion");
				for (int j = 0; j < posicionNodo.getLength(); j++) {	
					Node nodo = posicionNodo.item(j);
					if (nodo.getNodeType() == Node.ELEMENT_NODE) {
						// cargamos texto
						objetoHistorial.setPosicion(posicionNodo.item(j).getTextContent());
					}
				}
				// tag <velocidad>
				NodeList velocidadNodo = e.getElementsByTagName("velocidad");
				for (int j = 0; j < velocidadNodo.getLength(); j++) {	
					Node nodo = velocidadNodo.item(j);
					if (nodo.getNodeType() == Node.ELEMENT_NODE) {
						// cargamos texto
						objetoHistorial.setVelocidad(velocidadNodo.item(j).getTextContent());
					}
				}
				// tag <tamanio>
				NodeList tamanioNodo = e.getElementsByTagName("tamanio");
				for (int j = 0; j < tamanioNodo.getLength(); j++) {	
					Node nodo = tamanioNodo.item(j);
					if (nodo.getNodeType() == Node.ELEMENT_NODE) {
						// cargamos texto
						objetoHistorial.setTamanio(tamanioNodo.item(j).getTextContent());
					}
				}
				//------------------------------------------
				//objetos individuales

				// 	NAVE JUGADOR
				if (objetoHistorial.getTipo().equals("nave jugador")) {
					//tag <vida>
					NodeList vidasNodo = e.getElementsByTagName("vidas");
					// insertamos texto
					objetoHistorial.setVidas(vidasNodo.item(0).getTextContent());					
					//tag <energia>
					NodeList energiaNodo = e.getElementsByTagName("energia");
					// insertamos texto
					objetoHistorial.setEnergia(energiaNodo.item(0).getTextContent());					
				
				}else if (objetoHistorial.getTipo().equals("nave enemiga")) {
					//tag <estado>
					NodeList estadoNodo = e.getElementsByTagName("estado");
					// insertamos texto
					objetoHistorial.setEstado(estadoNodo.item(0).getTextContent());					
					//tag <energia>
					NodeList energiaNodo = e.getElementsByTagName("energia");
					// insertamos texto
					objetoHistorial.setEnergia(energiaNodo.item(0).getTextContent());					
					
				}else if(objetoHistorial.getTipo().equals("objeto espacial")){
					//tag <danio>
					NodeList danioNodo = e.getElementsByTagName("danio");
					// insertamos texto
					objetoHistorial.setDanio(danioNodo.item(0).getTextContent());
					
				}else if(objetoHistorial.getTipo().equals("disparo jugador")){
					//tag <danio>
//					NodeList danioNodo = e.getElementsByTagName("danio");
//					// insertamos texto
//					objetoHistorial.setDanio(danioNodo.item(0).getTextContent());										
				}else if(objetoHistorial.getTipo().equals("disparo enemigo")){
					//tag <danio>
					NodeList danioNodo = e.getElementsByTagName("danio");
					// insertamos texto
					objetoHistorial.setDanio(danioNodo.item(0).getTextContent());										
				}
				
				// insertamos el objeto al historial
				historialList.setObjeto(objetoHistorial);
			}
		}
		return historialList;
	}
	
	public static void escribirXML(String nombre_archivo, Historial historial) throws Exception{
		 
		// si no hay historial
        if(historial.tamanio() < 1){
        	// --completar-------mensaje de excepcion----------//
        	return;
        }else{
        	
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document document = implementation.createDocument(null,"historial", null);
            document.setXmlVersion("1.0");
            
            Element historialNodo = document.getDocumentElement();
            //Por cada objeto creamos un item que contendrá el nombre y sus atributos
            for(int i=0; i<historial.tamanio();i++){
            	//Nodo objeto
            	Element objetoNodo = document.createElement("objeto"); 
                //-------------------//
            	// creamos tipoNodo
            	Element tipoNodo = document.createElement("tipo");             	
            	// creamos el texto que contiene el nodo tipo
            	Text tipoTexto = document.createTextNode(historial.getObjeto(i).getTipo());
            	// insertamos texto 
            	tipoNodo.appendChild(tipoTexto); 
            	// ligamos el nodo nombre al nodo objeto <objeto><nombre>..texto..</nombre><objeto>
            	objetoNodo.appendChild(tipoNodo);
            	//creamos nombreNodo
                Element nombreNodo = document.createElement("nombre"); 
                // creamos el texto que contiene el Nodo nombre
                Text nombreTexto = document.createTextNode(historial.getObjeto(i).getNombre());
                // insertamos el texto que contiene el nodo al Nodo <nombre> ...texto....</nombre> 
                nombreNodo.appendChild(nombreTexto); 
                // ligamos el nodo nombre al nodo objeto <objeto><nombre>..texto..</nombre><objeto>
                objetoNodo.appendChild(nombreNodo);
                //-----------------------------------------------//
                
                // POSICION
                // rrecorremos la lista de Nodo Posicion X Y
                for (int j = 0; j < historial.getObjeto(i).getPosicion().size(); j++) {
                	// creamos un elemento para cada posicion;
                	Element posicionNodo = document.createElement("posicion");
                	// creamos texto
                	Text posicionTexto = document.createTextNode(historial.getObjeto(i).getPosicion().get(j));                
                	// insertamos texto
                	posicionNodo.appendChild(posicionTexto);
                	// insertamos en <posicion> al nodo <objeto>
                	objetoNodo.appendChild(posicionNodo);
				}
                
                // VELOCIDAD
                for (int j = 0; j < historial.getObjeto(i).getVelocidad().size(); j++) {
                	// creamos un elemento para cada velocidad;
                	Element velocidadNodo = document.createElement("velocidad");
                	// creamos texto
                	Text velocidadTexto = document.createTextNode(historial.getObjeto(i).getVelocidad().get(j));                
                	// insertamos texto
                	velocidadNodo.appendChild(velocidadTexto);
                	// insertamos <velocidad> al nodo <objeto>
                	objetoNodo.appendChild(velocidadNodo);
                }
                
                // TAMAÑO (ANCHO, ALTO)
                for (int j = 0; j < historial.getObjeto(i).getTamanio().size(); j++) {
                	// creamos un elemento para cada tamaño;
                	Element tamanioNodo = document.createElement("tamanio");
                	// creamos texto
                	Text tamanioTexto = document.createTextNode(historial.getObjeto(i).getTamanio().get(j));                
                	// insertamos texto
                	tamanioNodo.appendChild(tamanioTexto);
                	// insertamos <tamanio> al nodo <objeto>
                	objetoNodo.appendChild(tamanioNodo);
                }
                
                //---------------------------------------------------//
                // objetos individual
                // NAVE JUGADOR
                if (historial.getObjeto(i).getTipo().equals("nave jugador")) {
                	//VIDAS-----------------------
                	// creamos un elemento para las vidas
                	Element vidasNodo = document.createElement("vidas");
                	// creamos texto
                	Text vidasTexto = document.createTextNode(historial.getObjeto(i).getVidas());                
                	// insertamos texto
                	vidasNodo.appendChild(vidasTexto);
                	// insertamos <vidas> al nodo <objeto>
                	objetoNodo.appendChild(vidasNodo);
                	
                	//ENERGIA
                	// creamos un elemento para la energía
                	Element energiaNodo = document.createElement("energia");
                	// creamos texto
                	Text energiaTexto = document.createTextNode(historial.getObjeto(i).getEnergia());                
                	// insertamos texto
                	energiaNodo.appendChild(energiaTexto);
                	// insertamos <energia> al nodo <objeto>
                	objetoNodo.appendChild(energiaNodo);
                	//
				
                	//NAVE ENEMIGA                
                }else if (historial.getObjeto(i).getTipo().equals("nave enemiga")) {
                	//ESTADO-----------------------
                	// creamos un elemento para el estado
                	Element estadoNodo = document.createElement("estado");
                	// creamos texto
                	Text estadoTexto = document.createTextNode(historial.getObjeto(i).getEstado());                
                	// insertamos texto
                	estadoNodo.appendChild(estadoTexto);
                	// insertamos <estado> al nodo <objeto>
                	objetoNodo.appendChild(estadoNodo);
                	
                	//ENERGIA
                	// creamos un elemento para la energía
                	Element energiaNodo = document.createElement("energia");
                	// creamos texto
                	Text energiaTexto = document.createTextNode(historial.getObjeto(i).getEnergia());                
                	// insertamos texto
                	energiaNodo.appendChild(energiaTexto);
                	// insertamos <energia> al nodo <objeto>
                	objetoNodo.appendChild(energiaNodo);
                	
                	// OBJETO ESPACIAL
                }else if (historial.getObjeto(i).getTipo().equals("objeto espacial")) {
                	
                	//DAÑO
                	// creamos un elemento para danio
                	Element danioNodo = document.createElement("danio");
                	// creamos texto
                	Text danioTexto = document.createTextNode(historial.getObjeto(i).getDanio());                
                	// insertamos texto
                	danioNodo.appendChild(danioTexto);
                	// insertamos <danio> al nodo <objeto>
                	objetoNodo.appendChild(danioNodo);
                	
                	
                	// DISPARO JUGADOR
                }else if (historial.getObjeto(i).getTipo().equals("disparo jugador")) {
                	
                	//DAÑO
                	// creamos un elemento para danio
                	Element danioNodo = document.createElement("danio");
                	// creamos texto
                	Text danioTexto = document.createTextNode(historial.getObjeto(i).getDanio());                
                	// insertamos texto
                	danioNodo.appendChild(danioTexto);
                	// insertamos <danio> al nodo <objeto>
                	objetoNodo.appendChild(danioNodo);
                
                // DISPARO ENEMIGO
                }else if (historial.getObjeto(i).getTipo().equals("disparo enemigo")) {
                	
                	//DAÑO
                	// creamos un elemento para danio
                	Element danioNodo = document.createElement("danio");
                	// creamos texto
                	Text danioTexto = document.createTextNode(historial.getObjeto(i).getDanio());                
                	// insertamos texto
                	danioNodo.appendChild(danioTexto);
                	// insertamos <danio> al nodo <objeto>
                	objetoNodo.appendChild(danioNodo);
                	//
                }
                
                
                //agrego objetoNodo al historialNodo(raiz)
                historialNodo.appendChild(objetoNodo);
            }                
            //Genera XML
            Source source = new DOMSource(document);
            //Indicamos donde lo queremos almacenar
            Result result = new StreamResult(new java.io.File(nombre_archivo+".galaga")); //nombre del archivo
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
        }
    }
	

}
