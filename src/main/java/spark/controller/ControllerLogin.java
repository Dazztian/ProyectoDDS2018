package spark.controller;

import spark.*;

import java.util.*;

import javax.persistence.EntityManager;

import modelsPersistencia.ModelHelperPersistencia;

public class ControllerLogin {

	public static ModelAndView showLogin(Request req, Response res) {
		
		Map<String, Object> viewModel = new HashMap<>();
		
		if(ControllerLogin.userIsLoggedIn(req, res)||ControllerLogin.adminIsLoggedIn(req, res)) {
			viewModel.put("actualUser", req.session().attribute("user"));
			viewModel.put("actualAdmin", req.session().attribute("admin"));
			return new ModelAndView(viewModel,"isLogin.hbs");
		}
		
		return new ModelAndView(viewModel, "login.hbs");
		
	}
	
	public static ModelAndView manageLogin(Request req, Response res) {
		
		Map<String, Object> viewModel = new HashMap<>();
		
		if(ControllerUser.autenticarCliente(req.queryParams("username"), req.queryParams("password"))) {
			
			req.session().attribute("user", req.queryParams("username"));
			viewModel.put("actualUser", req.session().attribute("user"));
			return new ModelAndView(viewModel,"home.hbs");
			
		}else if(ControllerUser.autenticarAdministrador(req.queryParams("username"), req.queryParams("password"))) {
			
			req.session().attribute("admin", req.queryParams("username"));
			viewModel.put("actualAdmin", req.session().attribute("admin"));
			return new ModelAndView(viewModel,"home.hbs");
		
		}else {
			viewModel.put("AutenticacionFallida", true);
			return new ModelAndView(viewModel,"login.hbs");
		
		}
		
	}
	
	public static ModelAndView manageLogout(Request req, Response res) {
		
		if(ControllerLogin.userIsLoggedIn(req, res)||ControllerLogin.adminIsLoggedIn(req, res)) {
			req.session().removeAttribute("user");
        	req.session().removeAttribute("admin");
		}
    	res.redirect("/home");
    	return null;

	}
	
	public static boolean userIsLoggedIn(Request req, Response res) {
        
		return req.session().attribute("user") == null?false:true;
			
    }
	
	public static boolean adminIsLoggedIn(Request req, Response res) {
		
		return req.session().attribute("admin") == null?false:true;
		
	}
	
}
