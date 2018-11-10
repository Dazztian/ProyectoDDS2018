package spark.controller;

import java.util.HashMap;
import java.util.Map;

import modelsPersistencia.AdministradorModel;
import proyectoDDSs.Administrador;
import spark.*;

public class ControllerAdmin {

	public static boolean autenticarAdministrador(String username, String password) {
		
		if(username.isEmpty()||password.isEmpty()) {
			return false;
		}
		
		Administrador admin;
		if((admin = new AdministradorModel().buscarAdmin(username))==null) {
			return false;
		}
		
		return admin.getPassword().equals(password);
		
	}

	public static ModelAndView showAdminHome(Request req, Response res) {
	
		Map<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("actualAdmin", req.session().attribute("admin"));
		viewModel.put("punto", "..");
		
		return new ModelAndView(viewModel,"adminHome.hbs");
		
	}
	
	
}
