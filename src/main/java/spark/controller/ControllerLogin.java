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
			return new ModelAndView(viewModel,"login/isLogin.hbs");
		}
		
		return new ModelAndView(viewModel, "login/login.hbs");
		
	}
	
	public static ModelAndView manageLogin(Request req, Response res) {
		
		Map<String, Object> viewModel = new HashMap<>();
		
		if(ControllerUser.autenticarCliente(req.queryParams("username"), req.queryParams("password"))) {
			
			req.session().attribute("user", req.queryParams("username"));
			res.redirect("/user/home");
			return null;
			
		}else if(ControllerAdmin.autenticarAdministrador(req.queryParams("username"), req.queryParams("password"))) {
			
			req.session().attribute("admin", req.queryParams("username"));
			res.redirect("/admin/home");
			return null;
		
		}else {
			viewModel.put("AutenticacionFallida", true);
			return new ModelAndView(viewModel,"login/login.hbs");
		
		}
		
	}
	
	public static ModelAndView manageLogout(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		
		if(ControllerLogin.userIsLoggedIn(req, res)||ControllerLogin.adminIsLoggedIn(req, res)) {
			req.session().removeAttribute("user");
        	req.session().removeAttribute("admin");
        	viewModel.put("LogOut", true);
        	return new ModelAndView(viewModel,"home/home.hbs");
		}
		
		viewModel.put("InvalidLogOut", true);
		return new ModelAndView(viewModel,"home/home.hbs");
		
	}
	
	public static boolean userIsLoggedIn(Request req, Response res) {
        
		return req.session().attribute("user") == null?false:true;
			
    }
	
	public static boolean adminIsLoggedIn(Request req, Response res) {
		
		return req.session().attribute("admin") == null?false:true;
		
	}
	
}
