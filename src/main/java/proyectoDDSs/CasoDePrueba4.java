package proyectoDDSs;

import java.util.*;

import javax.persistence.EntityManager;

import junit.framework.Assert;
import modelsPersistencia.ClienteModel;
import modelsPersistencia.DispositivoModel;
import modelsPersistencia.ModelHelperPersistencia;
import modelsPersistencia.TransformadorModel;
import modelsPersistencia.ZonaGeograficaModel;
import proyectoDDSs.Actuador;
import proyectoDDSs.Apagar;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/*
ESTO ES DE HIBERNATE
Recuperar todos los transformadores persistidos
Registrar la cantidad.
 
 */
//Agregar una instancia de Transformador al JSON de entradas.
//Ejecutar el método de lectura y persistencia. 
//Evaluar que la cantidad actual sea la anterior + 1.

//CONCLUSION: El metodo de persistencia implica persistir a partir del JSON de entrada y checkeand si hay o no algun dispo nuevo!!!

//Codigo para toda la logica del test3

		//Leo uno x uno los elementos del JSON 
		//Checkeo si no existen en la BD
		//Si existen no los persisto y paso al siguiente
		//Si no existen los persisto
/**/

public class CasoDePrueba4 {
		public static void main(String[] args)
		{			
			
			ModelHelperPersistencia model = new ModelHelperPersistencia();
			Transformador trafoAPersistir;
			
			String json;
			ArrayList<Transformador> transformadores = new ArrayList<Transformador>(); 
			JSONParser parser = new JSONParser();
			int cantTrafos = 0;
			
			//Logica para levantar los trafos de la BD
			ZonaGeograficaModel zona_model = new ZonaGeograficaModel();
			
			TransformadorModel trafo_model = new TransformadorModel();
			
			List<ZonaGeografica> zonas = new ArrayList<ZonaGeografica>();
			
			List<Transformador> trafos = new ArrayList<Transformador>();
			
			zonas.add(new ZonaGeografica(1, "zona1", 1.1, 2.2));

			
			
			// trafos=trafo_model.buscarTodasLasTransformador();
			
			System.out.println("Cantidad de trafos actuales: "+trafos.size());
			
			//Recupero la zona con los trafos
			//ZonaGeografica zona1 = zona_model.buscarZonaGeografica(1);
			
			
			//Logica para agregar un Trafo al JSON de entradas
			
		     try {

		      	JSONArray a = (JSONArray) parser.parse(new FileReader("..\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\transformadores.json"));
		      	
		      	
		      	
		      	 for (Object o : a)
		 	  {
		 	    JSONObject transformador = (JSONObject) o;
		 	   //Lo que esta en Objeto.get("nombre") debe coincidir con el nombre "" del JSON!
			    Long id = (Long) transformador.get("id");
			    Double latitud = (Double) transformador.get("latitud");
			    Double longitud = (Double) transformador.get("longitud");
			    Long zona = (Long) transformador.get("zona");
			    
			    //Muestro lo que leo solo para ver el contenido que será escrito
			    System.out.println("Trafo" +id);
			    System.out.println("latitud: " +latitud);
			    System.out.println("longitud:" +longitud);			    
			    System.out.println("Zona " +zona + "\n");
			    
			    //A cada uno los persisto en la BD, uno por uno 
			    
			    trafoAPersistir = new Transformador(id.intValue(), latitud, longitud,  zona.intValue());
			    	
			    trafos.add(trafoAPersistir);
			    //LABURANDING
			    trafoAPersistir.asignarZona(zonas);//Esto es lo que persiste al trafo

				//transformadores.add( trafoAPersistir);
				//trafo4.asignarZona(zonas);
				//zona_model.agregar(zona);
				//model.agregar(new Transformador(cantTrafos+1, latitud, longitud,  zona.intValue()));
				
							
				cantTrafos++;
				
		 	  }
		      	 	//LABURANDING
					zonas.stream().forEach(zona -> zona_model.agregar(zona));

		      	//Aca agrego al nuevo trafo que dice el enunciado 
		      	//transformadores.add( new Transformador(cantTrafos+1, latitud, longitud,  zona.intValue())); 
		      	 
		     	Gson gson = new Gson();
				json = gson.toJson(trafos);  
				//Esto lo hago si quiero ver el resultado de los trafos actualizados
				System.out.println(json);

//////////////////////////////////////////   LOGICA PARA ESCRIBIR (APPEND) UN ARCHIVO BIEN ATR  ////////////////////////////////////////////////////////////////
		    /* Saco esto SOLO UN TOQUE para probar el resto del codigo
				try {
 	        	File file = new File("..\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\transformadores.json");
 	        	FileWriter fr = new FileWriter(file, true);
 	        	fr.write(json);
 	        	fr.close();
 	        	}catch (IOException e) {
	        	    System.out.println("ERROR escrbiendo el archivo");
	        	}
	        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 	        	 
		      	System.out.println("La cant de trafos es:" + cantTrafos); 
		      	*/
		     }//Aca termina el try
		     catch (Exception e) { e.printStackTrace(); } 
		
		}
}
