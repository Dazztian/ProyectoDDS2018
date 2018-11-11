package spark.controller;

import java.util.*;
import java.util.stream.*;

import modelsPersistencia.AdministradorModel;
import modelsPersistencia.ClienteModel;
import modelsPersistencia.DispoPermitidoModel;
import modelsPersistencia.DispositivoModel;
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

		viewModel.put("actualAdmin", req.session().attribute("admin"));
		viewModel.put("clientes", clientes);
		viewModel.put("punto", "..");
		
		return new ModelAndView(viewModel,"hogares.hbs");
		
	}
	
	public static ModelAndView showTablaDispositivos(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		
		List<DispositivoPermitido> dispos = DispoPermitidoModel.getInstance().buscarTodosLosDispositivo();
		
		viewModel.put("dispositivos", dispos);
		viewModel.put("actualAdmin", req.session().attribute("admin"));

		return new ModelAndView(viewModel,"tablaDispositivos.hbs");
		
	}
	
	public static ModelAndView cambioEnLaTabla(Request req, Response res) {
		
		String accion = req.queryParams("accion");
		
		DispoPermitidoModel dispo_model = new DispoPermitidoModel();
		DispositivoModel d_model = new DispositivoModel();
		
		DispositivoPermitido dispo = dispo_model.buscarDispositivo(Long.parseLong(req.queryParams("id")));
		
		if(accion.equals("borrar")) {
					
			DispoPermitidoModel.getInstance().eliminar(dispo);
			
			res.redirect("/admin/tabladispositivos");
			
		}else {
				
			dispo.setKwhConsumeXHora(Double.parseDouble(req.queryParams("consumo")));
			System.out.println("Consumo: "+req.queryParams("consumo"));
			dispo.setConsumoMinimo(Double.parseDouble(req.queryParams("consumoMinimo")));
			dispo.setConsumoMaximo(Double.parseDouble(req.queryParams("consumoMaximo")));
			
			d_model.modificar(dispo);
			
			res.redirect("/admin/tabladispositivos");
		}
		
		return null;
	}
	
}
