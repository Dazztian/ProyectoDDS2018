package proyectoDDSs;

import java.util.*;
import junit.framework.Assert;
import proyectoDDSs.Actuador;
import proyectoDDSs.Apagar;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileReader;
import java.lang.ProcessBuilder.Redirect.Type;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonCliente {
		public static void main(String[] args)
		{
	        JSONParser parser = new JSONParser();
	 	        try {

	 	        	JSONArray a = (JSONArray) parser.parse(new FileReader("..\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\Clientes.json"));

	 	        	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	 	        	 for (Object o : a)
	        	  {
	        	    JSONObject cliente = (JSONObject) o;
	        	    //Lo que est� en Objeto.get("nombre") debe coincidir con el nombre "" del JSON!
	        	    String nombre = (String) cliente.get("nombre");
	        	    String apellido = (String) cliente.get("apellido");
	        	    String tipoDocumento = (String) cliente.get("tipoDocumento");
	        	    long numeroDocumento = (long) cliente.get("numeroDocumento");
	        	    long telefono = (long) cliente.get("telefono");
	        	    String domicilio = (String) cliente.get("domicilio");
	        	    String fechaAlta =(String) cliente.get("fechaAlta");
	        	    Calendar fechaObtenida= ISO8601.toCalendar(fechaAlta);

	        	    //Imprimo los datos que acabo de agarrar del JSON, lo hago para ver que 
	        	    //los datos se levantaron correctamente.
	        	    System.out.println(nombre);
	        	    System.out.println(apellido);
	        	    System.out.println(tipoDocumento);
	        	    System.out.println(telefono);
	        	    System.out.println(domicilio);
	        	    
	        	    
	        	    
	        	    JSONArray dispositivos = (JSONArray) cliente.get("dispositivos");
	        	    
	        	    //Creo la list d�nde guardar� todos los dispositivos que voy a levantar del JSON
	        	    ArrayList<Dispositivo> listaDispositivos = new ArrayList<Dispositivo>();	
	        	    
	        	    for (Object d :dispositivos)
	        	    {
	        	    	 JSONObject dispo = (JSONObject) d;
	        	    	 
	        	    	String nombreDispo = (String) dispo.get("nombreDispo");
		        	    boolean estado = (boolean) dispo.get("estado");
		        	    long kwhConsumeXHora = (long) dispo.get("kwhConsumeXHora");
		        	    
		        	    System.out.println(nombreDispo);
		        	    System.out.println(estado);
		        	    System.out.println(kwhConsumeXHora);        	    
		        	            	    
		        	    listaDispositivos.add( new DispositivoEstandar(nombreDispo/*,estado*/,kwhConsumeXHora,1) );
		        	    	        	    
		        	}
	        	    //Resuelvo la categoria asociada al cliente
	        	    
	        	    JSONObject cat = (JSONObject) cliente.get("categoria");;
	        	    String categoriaTipo = (String) cat.get("tipo");
	        	    double cargoFijo = (double) cat.get("cargoFijo");
	        	    double cargoVariable = (double) cat.get("cargoVariable");
	        	    	 
	        	    System.out.println(categoriaTipo);
			        System.out.println(cargoFijo);
			        System.out.println(cargoVariable);
			        	  
			        Categoria categoriaCliente=(new Categoria(categoriaTipo,cargoFijo,cargoVariable));
	        
		        	  clientes.add( new Cliente (nombre,apellido,tipoDocumento,numeroDocumento,telefono,domicilio, listaDispositivos,fechaObtenida, categoriaCliente));
	        	   }	        	    
	 	        	 

	 	        	
	        } //Ac� termina el try
	 	        catch (Exception e) { e.printStackTrace(); }  
	 	            
	 	        
		}//Ac� termina el main
		
}

	