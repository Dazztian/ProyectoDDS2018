package spark.controller;

import modelsPersistencia.AdministradorModel;
import modelsPersistencia.ClienteModel;
import modelsPersistencia.DispositivoModel;
import proyectoDDSs.Cliente;
import proyectoDDSs.Dispositivo;
import proyectoDDSs.DispositivoEstandar;
import proyectoDDSs.DispositivoInteligente;
import proyectoDDSs.DispositivoPermitido;
import proyectoDDSs.Administrador;
import proyectoDDSs.Apagado;
import spark.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ControllerUser {

	public static boolean autenticarCliente(String username, String password) {
		
		if(username.isEmpty()||password.isEmpty()) {
			return false;
		}
		
		Cliente cliente;
		if((cliente = new ClienteModel().buscarCliente(username))==null) {
			return false;
		}
		
		return cliente.getContrasenia().equals(password);
		
	}
		
	public static ModelAndView showUserHome(Request req, Response res) {
		
		Map<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("actualUser", req.session().attribute("user"));
		viewModel.put("punto", "..");
		
		return new ModelAndView(viewModel,"cliente/userHome.hbs");
		
	}
	
	public static ModelAndView showEstado(Request req, Response res) {
		String nombre = req.session().attribute("user");
		Map<String,Object> viewModel = new HashMap<>();
		
		Cliente cliente = new ClienteModel().buscarCliente(nombre);
		
		viewModel.put("consumo", cliente.consumoMensual());
		viewModel.put("dispositivos", cliente.getDispositivos());
		viewModel.put("reglas", DispositivoModel.getInstance().buscarReglas(cliente.getDispositivos()));
		
		return new ModelAndView(viewModel,"cliente/estadoHagar.hbs");
	}
	
	public static ModelAndView showUploadDispositivos(Request req, Response res) {
		
		Map<String,Object> viewModel = new HashMap<>();

		viewModel.put("actualUser", req.session().attribute("user"));
		viewModel.put("punto", "..");

		
		return new ModelAndView(viewModel,"cliente/upload.hbs");
		
	}
	
	public static ModelAndView uploadDispositivos(Request req, Response res) throws Exception{
		Map<String,Object> viewModel = new HashMap<>();
		
        File uploadDir = new File("upload");
        uploadDir.mkdir(); // create the upload directory if it doesn't exist
		Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");
        File archivitoJSON= new File("");
        
        req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp")); 
        
        try (InputStream input = req.raw().getPart("uploaded_file").getInputStream()) { // getPart needs to use same "name" as input field in form
            Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);

            //DispositivoXCliente prueba= new DispositivoXCliente();
            //ACA ESTA EL ERROR, no se xq mierdaaa!!!!
    		DispositivoModel dispoModel= new DispositivoModel();
            
            //Leo el json y se lo escribo al usuario                
            
    		Cliente cliente = ClienteModel.getInstance().buscarCliente((String)req.session().attribute("user"));
			Dispositivo dispoCliente;
			
			
    		JSONParser parser = new JSONParser();
            try{
	      		
            	JSONArray a = (JSONArray) parser.parse(new FileReader(tempFile.toString()));    	 
            		for (Object o : a){
            			
		    		 	    JSONObject dispo = (JSONObject) o;
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
		    					ClienteModel.getInstance().agregar(dispoCliente);							       	
		    				
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
		
		viewModel.put("actualUser", req.session().attribute("user"));
		viewModel.put("punto", "..");
		viewModel.put("uploadOk", true);
		
		return new ModelAndView(viewModel,"cliente/upload.hbs");
	}
	
	public static ModelAndView showSimplex (Request req, Response res) {
		
		Map<String,Object> viewModel = new HashMap<>();

		viewModel.put("actualUser", req.session().attribute("user"));
		viewModel.put("punto", "..");

		
		return new ModelAndView(viewModel,"cliente/simplex.hbs");
	}
	
	public static ModelAndView simplex (Request req, Response res) {
		
		Cliente cliente = ClienteModel.getInstance().buscarCliente((String)req.session().attribute("user"));
		
		Map<String,Object> viewModel = new HashMap<>();
		
		viewModel.put("consumos", cliente.mostrarConsumoOptimoWeb());
		viewModel.put("mostroConsumos", true);

		viewModel.put("actualUser", req.session().attribute("user"));
		viewModel.put("punto", "..");

		
		return new ModelAndView(viewModel,"cliente/simplex.hbs");
	}
	
	public static ModelAndView simplexAutomatico (Request req, Response res) {
		
		Cliente cliente = ClienteModel.getInstance().buscarCliente((String)req.session().attribute("user"));
		
		Map<String,Object> viewModel = new HashMap<>();
		
		cliente.suscribirseAlSimplexAutomatico();
		
		viewModel.put("suscripcionOk", true);

		viewModel.put("actualUser", req.session().attribute("user"));
//		viewModel.put("punto", "..");

		
		return new ModelAndView(viewModel,"cliente/simplex.hbs");
	}
	
	public static ModelAndView ejecutarSimplex (Request req, Response res) {
		Cliente cliente = ClienteModel.getInstance().buscarCliente((String)req.session().attribute("user"));
		
		Map<String,Object> viewModel = new HashMap<>();
		
		cliente.accionarSegunConsumoOptimo();

		viewModel.put("actualUser", req.session().attribute("user"));
//		viewModel.put("punto", "..");

		
		return new ModelAndView(viewModel,"cliente/simplex.hbs");
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
