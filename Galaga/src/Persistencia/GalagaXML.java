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
		// leemos el xml
		Element historial = document.getDocumentElement();
		//creamos la lista de nodosHijos de nodoHistorial
		NodeList comando = historial.getChildNodes();
		//recorremos la lista de comandos (nodosHijos)
		for (int i = 0; i < comando.getLength(); i++) {
			Node n = comando.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				// casteamos de node a element
				Element e = (Element) n;
				//nombre del tag <nombre>
				NodeList nombreComandoNodo = e.getElementsByTagName("nombre");
				// creamos un objeto comando nuevo
				ObjetoHistorial comandologo = new ObjetoHistorial();
				// insertamos el nombre del nodelist a comando nombre
				comandologo.setNombre(nombreComandoNodo.item(0).getTextContent());
				// tag parametro a nodelist
				NodeList parametrosComandoNodo = e.getElementsByTagName("parametro");
				for (int j = 0; j < parametrosComandoNodo.getLength(); j++) {	
					Node nodoParametro = parametrosComandoNodo.item(j);
					if (nodoParametro.getNodeType() == Node.ELEMENT_NODE) {
						// cargamos parametros al comando
						comandologo.setDanio(parametrosComandoNodo.item(j).getTextContent());
					}
				}
				// insertamos el comando al historial
				historialList.setObjeto(comandologo);
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
                	//
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
