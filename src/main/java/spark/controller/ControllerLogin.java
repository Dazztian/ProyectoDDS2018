package spark.controller;

import spark.*;

import java.util.*;

import javax.persistence.EntityManager;

import modelsPersistencia.ModelHelperPersistencia;

public class ControllerLogin {

	public static ModelAndView showLogin(Request req, Response res) {
		
		Map<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "login.hbs");
		
	}
	
	public static ModelAndView manageLogin(Request req, Response res) {
		
		Map<String, Object> viewModel = new HashMap<>();
		
		if(ControllerUser.autenticarCliente(req.queryParams("username"), req.queryParams("password"))) {
			
			req.session().attribute("user", req.queryParams("username"));
			viewModel.put("UserAutentication", true);
			return new ModelAndView(viewModel,"login.hbs");
			
		}else if(ControllerUser.autenticarAdministrador(req.queryParams("username"), req.queryParams("password"))) {
			
			req.session().attribute("admin", req.queryParams("username"));
			viewModel.put("UserAutentication", true);
			return new ModelAndView(null,"login.hbs");
		
		}else {
			
			viewModel.put("AutenticacionFallida", true);
			return new ModelAndView(viewModel,"login.hbs");
		
		}
		
	}
	
	public static ModelAndView manageLogout(Request req, Response res) {
		
        req.session().removeAttribute("usuarioActual");
        res.redirect("/login");
        return null;
		
	}
	
}
