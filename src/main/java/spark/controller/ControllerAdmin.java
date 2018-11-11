package spark.controller;

import java.util.*;
import java.util.stream.*;

import modelsPersistencia.AdministradorModel;
import modelsPersistencia.ClienteModel;
import proyectoDDSs.*;
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
		
		return admin.getContrasenia().equals(password);
		
	}

	public static ModelAndView showAdminHome(Request req, Response res) {
	
		Map<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("actualAdmin", req.session().attribute("admin"));
		viewModel.put("punto", "..");
		
		return new ModelAndView(viewModel,"adminHome.hbs");
		
	}
	
	public static ModelAndView showHogares(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		List<Cliente> clientes = ClienteModel.getInstance().buscarTodosLosCliente();

		clientes.stream().forEach(cliente -> cliente.consumoMensual());
		
		viewModel.put("clientes", clientes);
		viewModel.put("punto", "..");
		
		return new ModelAndView(viewModel,"hogares.hbs");
		
	}
	
}
