package proyectoDDSs;

import java.util.*;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonCliente {
		public static void main(String[] args)
		{
	        JSONParser parser = new JSONParser();
	 	        try {
	 	        	 JSONArray a = (JSONArray) parser.parse(new FileReader("C:\\Users\\PC\\Desktop\\mis cosas\\UTN\\2018\\Materias\\DISEÑO\\TP\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\Clientes.json"));
	 	        	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	 	        	 for (Object o : a)
	        	  {
	        	    JSONObject cliente = (JSONObject) o;
	        	    //Lo que está en Objeto.get("nombre") debe coincidir con el nombre "" del JSON!
	        	    String nombre = (String) cliente.get("nombre");
	        	    String apellido = (String) cliente.get("apellido");
	        	    String tipoDocumento = (String) cliente.get("tipoDocumento");
	        	    long numeroDocumento = (long) cliente.get("numeroDocumento");
	        	    long telefono = (long) cliente.get("telefono");
	        	    String domicilio = (String) cliente.get("domicilio");

	        	    //Imprimo los datos que acabo de agarrar del JSON, lo hago para ver que 
	        	    //los datos se levantaron correctamente.
	        	    System.out.println(nombre);
	        	    System.out.println(apellido);
	        	    System.out.println(tipoDocumento);
	        	    System.out.println(telefono);
	        	    System.out.println(domicilio);
	        	    
	        	    
	        	    
	        	    JSONArray dispositivos = (JSONArray) cliente.get("dispositivos");
	        	    
	        	    //Creo la list dónde guardaré todos los dispositivos que voy a levantar del JSON
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
	        	    		        	    
		        	            	    
		        	    listaDispositivos.add( new Dispositivo(nombreDispo,estado,kwhConsumeXHora) );
		        	    	        	    
		        	}
	        	  
	        	   clientes.add( new Cliente (nombre,apellido,tipoDocumento,numeroDocumento,telefono,domicilio, listaDispositivos));
	        	   }	        	    
	        } //Acá termina el try
	 	        catch (Exception e) { e.printStackTrace(); }
	 	        
        	    

	 	            
	 	        
		}//Acá termina el main
		
}

	