

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
import proyectoDDSs.Transformador;
import proyectoDDSs.ZonaGeografica;

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
1-Recuperar todos los transformadores persistidos
2-Registrar la cantidad.
 
3-Agregar una instancia de Transformador al JSON de entradas.
4-Ejecutar el m�todo de lectura y persistencia. 
5-Evaluar que la cantidad actual sea la anterior + 1.

 */
public class CasoDePrueba4 {
		public static void main(String[] args)
		{			
			
			ModelHelperPersistencia model = new ModelHelperPersistencia();
			Transformador trafoJSONEntrada;
			
			String json;
			JSONParser parser = new JSONParser();
			int cantTrafos = 0;
			ZonaGeograficaModel zona_model = new ZonaGeograficaModel();
			TransformadorModel trafo_model = new TransformadorModel();
			List<ZonaGeografica> zonas = new ArrayList<ZonaGeografica>();			
			List<Transformador> trafos = new ArrayList<Transformador>();//Va a tener los trafos que levante de la BD
			List<Transformador> trafosJSON = new ArrayList<Transformador>();//Va a tener los trafos que levante del JSON de entrada
			
			//Logica para levantar los trafos de la BD
			trafos=trafo_model.buscarTodasLasTransformador();
			System.out.println("Cantidad de trafos actuales: "+trafos.size());
			
			//Si la zona ya Existe en la bd, comentar esto xq va a tirar error. AUN ASI el codigo seguiria funcionando
			zonas.add(new ZonaGeografica(1, "zona1", 1.1, 2.2));
			zonas.add(new ZonaGeografica(2, "zona2", 45.3, 2.56));
			
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
			    
			    //Muestro lo que leo solo para ver el contenido que ser� escrito
			    System.out.println("Trafo" +id);
			    System.out.println("latitud: " +latitud);
			    System.out.println("longitud:" +longitud);			    
			    System.out.println("Zona " +zona + "\n");
			    

			    /* ESTO ES UNA FORMA DE CARGAR LOS TRAFOS DEL JSON A LA BD, RECOMENDABLE USAR UNICAMENTE LA  1ERA VEZ QUE SE CARGA LA BD
			    trafoJSONEntrada = new Transformador(id.intValue(), latitud, longitud,  zona.intValue());
			    trafosJSON.add(trafoJSONEntrada);
			    trafoJSONEntrada.asignarZona(zonas);//Esto es lo que persiste al trafo
			    */
											
		 	  }
		      	//Aca agrego un TRAFO al json de entrada y el mismo se persistira en la BD 		      	
		      	trafoJSONEntrada = new Transformador(300, 34.5, 65.4, 2);
			    trafos.add(trafoJSONEntrada);
			    trafoJSONEntrada.asignarZona(zonas);//Esto es lo que persiste al trafo
				
		      	zonas.stream().forEach(zona -> zona_model.agregar(zona));
		      	
		      	//Esta es otra forma de checkear que correctamente haya cant+1 de trafos, haci�ndolo desde la BD
				//trafos=trafo_model.buscarTodasLasTransformador();
				
		     	Gson gson = new Gson();
				json = gson.toJson(trafos);
				//Con esto Previsualizo el resultado del JSON de Entradas
				System.out.println(json);

//////////////////////////////////////////   LOGICA PARA ESCRIBIR  UN ARCHIVO BIEN ATR  ////////////////////////////////////////////////////////////////
		    // Saco esto SOLO UN TOQUE para probar el resto del codigo
				try {
 	        	File file = new File("..\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\Transformadores.json");
 	        	FileWriter fr = new FileWriter(file, false);//False es sobreescritura, true es append
 	        	fr.write(json);
 	        	fr.close();
 	        	}catch (IOException e) {
	        	    System.out.println("ERROR escrbiendo el archivo");
	        	}
	        
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 	        	 
				System.out.println("Cantidad de trafos actuales: "+trafos.size());	
				
				
		     }//Aca termina el try
		     catch (Exception e) { e.printStackTrace(); } 
		     
		
		}
}
