import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import modelsPersistencia.DispositivoModel;
import proyectoDDSs.Dispositivo;
import proyectoDDSs.DispositivoEstandar;
import proyectoDDSs.DispositivoInteligente;
import proyectoDDSs.Encendido;

//Esto deberia ser la carga de dispositivos Permitidos
public class cargaDispositivosPermitidos {
	

	public static void main(String[] args) {
		
		final List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
		DispositivoModel modelDispo = new DispositivoModel();
		
		JSONParser parser = new JSONParser();
        try 
        {
        	JSONArray tablaDispositivos = (JSONArray) parser.parse(new FileReader("..\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\dispositivosPermitidos.json"));
        
        	//Todo lo que "cargue" del JSON lo guardo en o
        	for (Object o : tablaDispositivos)
          	  {
		       	    JSONObject unDispo = (JSONObject) o;
		       	    
		       	    	String dispoTipo = (String) unDispo.get("tipo");       	   
		       	    	String dispoNombre = (String) unDispo.get("equipo");
		       	    	Boolean dispoInteligente = (Boolean) unDispo.get("inteligente");
				       	Boolean dispoBajo_Consumo = (Boolean) unDispo.get("bajo_consumo");
				       	double dispoConsumo = (double) unDispo.get("kwhConsumeXHora");
				       	double consumoMin = (double) unDispo.get("consumoMinimo");
				       	double consumoMax = (double) unDispo.get("consumoMaximo");
				       			       	    	
		       	    	if(dispoInteligente)
		       	    	{			       	    		
					       	DispositivoInteligente dispositivoEncontrado = new DispositivoInteligente(dispoNombre, dispoTipo, dispoConsumo, new Encendido(), consumoMin, consumoMax);
					       	dispositivos.add(dispositivoEncontrado);
					       	modelDispo.agregar(dispositivoEncontrado);
					       	//Aca estaba la logica que le agregaba ptos al cliente
		       	    	}
		       	    	else
		       	    	{
		       	    		DispositivoEstandar dispositivoEncontrado = new DispositivoEstandar(dispoNombre, dispoTipo, dispoConsumo , 10, consumoMin, consumoMax);
					       	dispositivos.add(dispositivoEncontrado);
					       	modelDispo.agregar(dispositivoEncontrado);

		       	    	}
		       	    
		      }
   	    }
        	catch (Exception e) { e.printStackTrace(); }


	}

}