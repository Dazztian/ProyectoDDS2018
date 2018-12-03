package proyectoDDSs;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import modelsPersistencia.*;

public class Contexto {
	public static void main(String[] args) {
		AdministradorModel modelAdmin = new AdministradorModel();
		ZonaGeograficaModel modelZona = new ZonaGeograficaModel();
		
		//-----------------CARGAR CATEGORIAS-----------------------------
		
		//Cargo las categorias, igual tiene un error porque el campo 'tipo' lo sube en null y el campo 'cargoVariable' en 0
		
		File archivoCategorias = new File("../ProyectoDDS2018/src/main/java/proyectoDDSs/categorias.json"); 
		try {
		List<Categoria> listCategorias = new ParserCategoria().load(archivoCategorias);
		
		CategoriaModel m_cat = new CategoriaModel();
		
		listCategorias.forEach(categoria -> m_cat.agregar(categoria));
		
		}catch(IOException e) {
			e.printStackTrace();
		}
		//-----------------CARGAR CATEGORIAS-----------------------------
		
		//-----------------CARGAR ZONAS----------------------------------
		List<ZonaGeografica> zonas = new ArrayList<ZonaGeografica>();	
		
		zonas.add(new ZonaGeografica(1, "zona1", -34.6609, -58.4584));
		zonas.add(new ZonaGeografica(2, "zona2", -34.6372, -58.4788));
		//-----------------CARGAR ZONAS----------------------------------
		//-----------------CARGAR TRANSFORMADORES------------------------
		JSONParser parser = new JSONParser();
		List<Transformador> trafosJSON = new ArrayList<Transformador>();
		try {

	      	JSONArray a = (JSONArray) parser.parse(new FileReader("../ProyectoDDS2018/src/main/java/proyectoDDSs/transformadores.json"));		      	
	      	
	      	 for (Object o : a){
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
		    

	      		 // ESTO ES UNA FORMA DE CARGAR LOS TRAFOS DEL JSON A LA BD, RECOMENDABLE USAR UNICAMENTE LA  1ERA VEZ QUE SE CARGA LA BD
		    
	      		Transformador trafoJSONEntrada = new Transformador(id.intValue(), latitud, longitud,  zona.intValue());
	      		trafosJSON.add(trafoJSONEntrada);
	      		trafoJSONEntrada.asignarZona(zonas);//Esto es lo que persiste al trafo
	      	 	}
	      	 
		}
		catch (Exception e) { e.printStackTrace(); } 
		//-----------------CARGAR TRANSFORMADORES------------------------
		//-----------------CARGAR CLIENTES Y ADMINISTRADORES-------------
		Cliente pepe = null;
		Cliente rosa = null;
		Administrador roberto;
		Administrador pedro;
		try {
		 pepe=new Cliente("Pepe","Gonzales","dni",4012939,40239401,"Yrigoyen",
				new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),
						1, -34.6538, -58.4810, "pepe", "unapass");
		} catch (ParseException e1) {e1.printStackTrace();}
		try {
		rosa=new Cliente("Rosa","Perez","dni",4012339,40239401,"Yrigoyen",
				new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-02-01T12:00:00+01:00"),
						5,-34.6372, -58.4788, "rosa", "unapass");
		} catch (ParseException e) {e.printStackTrace();}
		roberto = new Administrador("Roberto", "Lopez", "roberto", "unapass");
		pedro = new Administrador("Pedro", "Rodriguez", "pedro", "unapass");
		modelAdmin.agregar(roberto);
		modelAdmin.agregar(pedro);
		pepe.asignarTrafoMasCercano(trafosJSON);
		rosa.asignarTrafoMasCercano(trafosJSON);
		//-----------------CARGAR CLIENTES Y ADMINISTRADORES-------------
		//-----------------CARGAR DISPOSITIVOS PERMITIDOS----------------
		final List<DispositivoPermitido> dispositivos = new ArrayList<DispositivoPermitido>();
		DispositivoModel modelDispo = new DispositivoModel();
        try 
        {
        	JSONArray tablaDispositivos = (JSONArray) parser.parse(new FileReader("../ProyectoDDS2018/src/main/java/proyectoDDSs/dispositivosPermitidos.json"));
        
        	//Todo lo que "cargue" del JSON lo guardo en o
        	for (Object o : tablaDispositivos)
          	  {
		       	    JSONObject unDispo = (JSONObject) o;
		       	    
		       	    	String dispoTipo = (String) unDispo.get("nombre");       	   
		       	    	String dispoNombre = (String) unDispo.get("equipo");
		       	    	Boolean dispoInteligente = (Boolean) unDispo.get("inteligente");
				       	Boolean dispoBajo_Consumo = (Boolean) unDispo.get("bajo_consumo");
				       	double dispoConsumo = (double) unDispo.get("kwhConsumeXHora");
				       	double consumoMin = (double) unDispo.get("consumoMinimo");
				       	double consumoMax = (double) unDispo.get("consumoMaximo");

				       	
				       	DispositivoPermitido dispoEncontrado = new DispositivoPermitido();
				       	dispoEncontrado.setNombre(dispoNombre);
				       	dispoEncontrado.setEquipo(dispoTipo);
				       	dispoEncontrado.setInteligente(dispoInteligente);
				       	dispoEncontrado.setKwhConsumeXHora(dispoConsumo);
				       	dispoEncontrado.setConsumoMinimo(consumoMin);
				       	dispoEncontrado.setConsumoMaximo(consumoMax);
				       	dispoEncontrado.setBajoConsumo(dispoBajo_Consumo);
				       	
				       	modelDispo.agregar(dispoEncontrado);
          	  }
        }catch (Exception e) { e.printStackTrace(); }
      //-----------------CARGAR DISPOSITIVOS PERMITIDOS----------------
      //-----------------CARGAR DISPOSITIVOS DE CLIENTES---------------
        pepe.agregarDispositivo("vapor");
        pepe.agregarDispositivo("3500");
        pepe.agregarDispositivo("led_24");
        pepe.getDispositivos().stream().filter(dispositivo -> dispositivo.esInteligente())
        .forEach(dispositivo -> ((DispositivoInteligente) dispositivo).prender());
        
        rosa.agregarDispositivo("automatico");
        rosa.agregarDispositivo("techo");
        rosa.getDispositivos().stream().filter(dispositivo -> dispositivo.esInteligente())
        .forEach(dispositivo -> ((DispositivoInteligente) dispositivo).prender());
      //-----------------CARGAR DISPOSITIVOS DE CLIENTES---------------
      //-----------------CARGAR CONSUMOS-------------------------------
        pepe.getDispositivos().stream().filter(dispositivo -> dispositivo.esInteligente())
        .forEach(dispositivo ->{ for(int i = 0; i <= 30; i++) {
        	for(int j = 0; j <= 5; j++) {
        		((DispositivoInteligente) dispositivo).guardarConsumo(LocalDateTime.now().minusHours(5-j).minusDays(30-i));
        	}
        }});
        rosa.getDispositivos().stream().filter(dispositivo -> dispositivo.esInteligente())
        .forEach(dispositivo ->{ for(int i = 0; i <= 30; i++) {
        	for(int j = 0; j <= 3; j++) {
        		((DispositivoInteligente) dispositivo).guardarConsumo(LocalDateTime.now().minusHours(3-j).minusDays(30-i));
        	}
        }});
      //-----------------CARGAR CONSUMOS-------------------------------
      //-----------------PERSISTO--------------------------------------
        zonas.stream().forEach(zona -> modelZona.agregar(zona));
      //-----------------PERSISTO--------------------------------------
      
        
	}
}
