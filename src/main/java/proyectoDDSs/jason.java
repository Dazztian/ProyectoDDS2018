package proyectoDDSs;

import java.io.FileReader;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class jason {
	
	//@SuppressWarnings("unchecked")
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
 
        try {
 
        	 JSONArray arrayJSON = (JSONArray) parser.parse(new FileReader("C:\\Users\\FedeHazama\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\categorias.json"));
 
        	 for (Object object : arrayJSON)
        	  {
        	    JSONObject categoria = (JSONObject) object;

        	    String nombreCategoria = (String) categoria.get("categoria");
        	    System.out.println(nombreCategoria);

        	    Double cargoFijo = (Double) categoria.get("cargoFijo");
        	    System.out.println(cargoFijo);

        	    Double cargoVariable = (Double) categoria.get("cargoVariable");
        	    System.out.println(cargoVariable + "\n");
        	    
        	    
        	  }
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}