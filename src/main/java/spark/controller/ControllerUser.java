package spark.controller;

import modelsPersistencia.AdministradorModel;
import modelsPersistencia.ClienteModel;
import modelsPersistencia.DispositivoModel;
import proyectoDDSs.Cliente;
import proyectoDDSs.Administrador;
import spark.*;
import java.util.*;

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
	
	
	
}
