package spark.server;

import spark.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import modelsPersistencia.ClienteModel;
import modelsPersistencia.DispositivoModel;
import modelsPersistencia.ModelHelperPersistencia;

import proyectoDDSs.*;

import java.io.*;
import java.nio.file.*;


import static spark.Spark.*;
//import static spark.debug.DebugScreen.*;

public class ClienteAgregaDispositivos {
	
    public static void main(String[] args) {

    	//ESTO ES EL CODIGO DE PRUEBA QUE YA SE FUNCIONA EN EL MAIN PERO ACA NO ANDA FUNCANNDO
    	/*
    	try {
    	ModelHelperPersistencia m = new ModelHelperPersistencia();
		DispositivoXCliente prueba= new DispositivoXCliente();
		DispositivoModel dispoModel= new DispositivoModel();
		DispositivoPermitido dispo1=dispoModel.existeDispoEnBDEquipo("40w");
		Long idDispoEncontrado = dispo1.getId();
		prueba.setId_cliente(1);
		prueba.setId_dispositivo(idDispoEncontrado.intValue());
		m.agregar(prueba);
		}
       	catch (Exception e) { e.printStackTrace(); }
		*/
		
    	ModelHelperPersistencia modelHelPersistencia = new ModelHelperPersistencia();
        File uploadDir = new File("upload");
        uploadDir.mkdir(); // create the upload directory if it doesn't exist

        staticFiles.externalLocation("upload");

        get("/", (req, res) ->
                  "<form method='post' enctype='multipart/form-data'>" // note the enctype
                + "    <input type='file' name='uploaded_file' accept='.json'>" // make sure to call getPart using the same "name" in the post
                + "    <button>Upload JSON</button>"
                + "</form>"
        );


				
        post("/", (req, res) -> {

            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
            File archivitoJSON= new File("");
            
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp")); 
            
            try (InputStream input = req.raw().getPart("uploaded_file").getInputStream()) { // getPart needs to use same "name" as input field in form
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);

                //DispositivoXCliente prueba= new DispositivoXCliente();
                //ACA ESTA EL ERROR, no se xq mierdaaa!!!!
        		DispositivoModel dispoModel= new DispositivoModel();
                
                //Leo el json y se lo escribo al usuario                
                
        		Cliente cliente = ClienteModel.getInstance().buscarCliente(new Long(6));
    			Dispositivo dispoCliente;
    			
    			
        		JSONParser parser = new JSONParser();
                try
                {
		      		
                	JSONArray a = (JSONArray) parser.parse(new FileReader(tempFile.toString()));    	 
                		for (Object o : a)
	    		      	 	{
			    		 	    JSONObject dispo = (JSONObject) o;
			    			    String nombre = (String) dispo.get("nombre");//Lo que esta entre " " debe coincididr con el atributo del JSON
			    			    String equipo = (String) dispo.get("equipo");
		    			    	
			    			    DispositivoPermitido dispo1=dispoModel.existeDispoEnBDEquipo(equipo);
			    			    
			    			    if(dispo1!=null)//Agrego SOLO los dispositivos que estan permitidos
			    			    {
				    			    System.out.println("esta es la id del dipoEncontrado " +dispo1.getId());

			    			    	Long idDispoEncontrado = dispo1.getId();
			    			    	
			    			    	if(dispo1.getInteligente()) {
			    			    	
			    			    		dispoCliente = new DispositivoInteligente(dispo1.getNombre(), dispo1.getEquipo(), dispo1.getKwhConsumeXHora(),new Apagado(), dispo1.getConsumoMinimo(), dispo1.getConsumoMaximo());

			    			    	}else {
			    			    		
			    			    		dispoCliente = new DispositivoEstandar(dispo1.getNombre(), dispo1.getEquipo(), dispo1.getKwhConsumeXHora(),5, dispo1.getConsumoMinimo(), dispo1.getConsumoMaximo());
			    			    		
			    			    	}
			    			    	
			    			    	dispoCliente.setCliente(cliente);
			    			    	dispoCliente.setDispositivo(dispo1);
			    			    	
			    					System.out.println("El dispositivo esta permitido, se puede persistir");
			    					modelHelPersistencia.agregar(dispoCliente);							       	
			    				
			    			    }
			    			    else//Aca debería haber un mensajito que indique el error
			    			    {
			    			    	System.out.println("ERROR: DISPOSITIVO NO ADMITIDO POR EL SISTEMA");
			    			    }
	    		 	  }
    		      	 
                } 
   		    	catch (Exception e) { e.printStackTrace(); }
			
    }   	    	
        	
            logInfo(req, tempFile);
            return "<h1>You uploaded this JSON, lucas KITTEN:<h1>";           

        });

   }

    
    
    // methods used for logging
    private static void logInfo(Request req, Path tempFile) throws IOException, ServletException {
        System.out.println("Uploaded file '" + getFileName(req.raw().getPart("uploaded_file")) + "' saved as '" + tempFile.toAbsolutePath() + "'");
    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

}