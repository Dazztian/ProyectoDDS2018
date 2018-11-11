package spark.controller;
import spark.*;
import java.util.*;

import com.google.protobuf.TextFormat.ParseException;

import modelsPersistencia.ClienteModel;
import proyectoDDSs.Cliente;
import proyectoDDSs.ISO8601;

public class ControllerRegister {

	public static ModelAndView showRegisterPage(Request req, Response res) {
		
		Map<String,Object> viewModel = getDatosRegister(req);
		
		return new ModelAndView(viewModel,"register.hbs");
		
	}
	
	public static ModelAndView manageRegister(Request req, Response res) throws Exception {
		
		Map<String, Object> viewModel = new HashMap<>();
		
		if(datosCompletos(req)) {
			if(req.queryParams("password1").equals((String)req.queryParams("password2"))) {
				if(ClienteModel.getInstance().buscarCliente(req.queryParams("username"))==null) {
				
				Cliente cliente = new Cliente(req.queryParams("nombre"),req.queryParams("apellido"),"DNI",Long.parseLong(req.queryParams("dni")),
				Long.parseLong(req.queryParams("telefono")), req.queryParams("domicilio"),new ArrayList<>(),
				new ISO8601().toCalendar("2010-01-01T12:00:00+01:00"), Integer.parseInt(req.queryParams("categoria")), 
				Double.parseDouble(req.queryParams("latitud")),	Double.parseDouble(req.queryParams("longitud")), 
				req.queryParams("username"),req.queryParams("password1"));
				
				ClienteModel.getInstance().agregar(cliente);
				
				viewModel.put("RegistroOK", true);
				return new ModelAndView(viewModel,"home.hbs");
				}else {
					viewModel=getDatosRegister(req);
					viewModel.put("ClienteExistente", true);
					return new ModelAndView(viewModel,"register.hbs");
				}
			}else {
				viewModel=getDatosRegister(req);
				viewModel.put("ContraseniasDistintas", true);
				return new ModelAndView(viewModel,"register.hbs");
			}
		}else {
			viewModel = CamposVacios(req);
			return new ModelAndView(viewModel,"register.hbs");
		}
		
		
	}
	
	private static boolean datosCompletos(Request req) {
		
		if(req.queryParams("nombre").isEmpty()) {
			return false;
		}
		if(req.queryParams("apellido").isEmpty()) {
			return false;
		}
		if(req.queryParams("domicilio").isEmpty()) {
			return false;
		}
		if(req.queryParams("dni").isEmpty()) {
			return false;
		}
		if(req.queryParams("telefono").isEmpty()) {
			return false;
		}
		if(req.queryParams("latitud").isEmpty()) {
			return false;
		}
		if(req.queryParams("longitud").isEmpty()) {
			return false;
		}
		if(req.queryParams("username").isEmpty()) {
			return false;
		}
		if(req.queryParams("password1").isEmpty()) {
			return false;
		}
		if(req.queryParams("password2").isEmpty()) {
			return false;
		}
		if(req.queryParams("categoria").isEmpty()) {
			return false;
		}
		return true;
	}
	
	private static Map<String, Object> getDatosRegister(Request req){
		Map<String,Object> viewModel = new HashMap<>();
		viewModel.put("Notnombre", false);
		viewModel.put("Notapellido", false);
		viewModel.put("Notdomicilio", false);
		viewModel.put("Notdni", false);
		viewModel.put("Nottelefono", false);
		viewModel.put("Notlatitud", false);
		viewModel.put("Notlongitud", false);
		viewModel.put("Notusuario", false);
		viewModel.put("Notpass1", false);
		viewModel.put("Notpass2", false);
		viewModel.put("Notcategoria", false);
		return viewModel;
	}
	
	private static Map<String,Object> CamposVacios(Request req){
		Map<String,Object> viewModel = new HashMap<>();
		viewModel.put("Notnombre", true);
		viewModel.put("Notapellido", true);
		viewModel.put("Notdomicilio", true);
		viewModel.put("Notdni", true);
		viewModel.put("Nottelefono", true);
		viewModel.put("Notlatitud", true);
		viewModel.put("Notlongitud", true);
		viewModel.put("Notusuario", true);
		viewModel.put("Notpass1", true);
		viewModel.put("Notpass2", true);
		viewModel.put("Notcategoria", true);
		viewModel.put("RegistroFailed", true);
		
		return viewModel;
		
		
	}
	
}
