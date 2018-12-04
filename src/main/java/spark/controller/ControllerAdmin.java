package spark.controller;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.*;

import javax.swing.text.DateFormatter;

import modelsPersistencia.AdministradorModel;
import modelsPersistencia.ClienteModel;
import modelsPersistencia.DispoPermitidoModel;
import modelsPersistencia.DispositivoModel;
import modelsPersistencia.TransformadorModel;
import proyectoDDSs.*;
import spark.*;

public class ControllerAdmin {

	public static boolean autenticarAdministrador(String username, String password) {
		
		if(username.isEmpty()||password.isEmpty()) {
			return false;
		}
		
		Administrador admin;
		if((admin = AdministradorModel.getInstance().buscarAdmin(username))==null) {
			return false;
		}
		
		return admin.getContrasenia().equals(password);
		
	}

	public static ModelAndView showAdminHome(Request req, Response res) {
	
		Map<String, Object> viewModel = new HashMap<>();
		
		viewModel.put("actualAdmin", req.session().attribute("admin"));
		viewModel.put("punto", "..");
		
		return new ModelAndView(viewModel,"admin/adminHome.hbs");
		
	}
	
	public static ModelAndView showHogares(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		List<Cliente> clientes = ClienteModel.getInstance().buscarTodosLosCliente();

		Object[] consumos = clientes.stream().map(cliente -> cliente.consumoMensual()).toArray();

		viewModel.put("actualAdmin", req.session().attribute("admin"));
		viewModel.put("clientes", clientes);
		viewModel.put("consumos", consumos);
		viewModel.put("punto", "..");
		
		return new ModelAndView(viewModel,"admin/hogares.hbs");
		
	}
	
	public static ModelAndView showTablaDispositivos(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		
		List<DispositivoPermitido> dispos = DispoPermitidoModel.getInstance().buscarTodosLosDispositivo();
		
		viewModel.put("dispositivos", dispos);
		viewModel.put("actualAdmin", req.session().attribute("admin"));

		return new ModelAndView(viewModel,"admin/tablaDispositivos.hbs");
		
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
	
	public static ModelAndView showAddDispositivo(Request req, Response res) {		
		Map<String, Object> viewModel = new HashMap<>();

		viewModel.put("actualAdmin", req.session().attribute("admin"));

		
		return new ModelAndView(viewModel,"admin/agregardispositivo.hbs");

	}
	
	public static ModelAndView addNuevoDispositivo(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		DispositivoPermitido dispo = new DispositivoPermitido();
		
		viewModel.put("actualAdmin", req.session().attribute("admin"));
		
		if(!camposVacios(req)) {
			dispo.setNombre(req.queryParams("nombre"));
			dispo.setEquipo(req.queryParams("equipo"));
		
			if(req.queryParams("inteligente")!=null)
				dispo.setInteligente(true);
			else
				dispo.setInteligente(false);
			if(req.queryParams("bajoConsumo")!=null)
				dispo.setBajoConsumo(true);
			else
				dispo.setBajoConsumo(false);
		
			dispo.setKwhConsumeXHora(Double.parseDouble(req.queryParams("consumo")));
			dispo.setConsumoMinimo(Double.parseDouble(req.queryParams("consumoMinimo")));
			dispo.setConsumoMaximo(Double.parseDouble(req.queryParams("consumoMaximo")));
		
			DispoPermitidoModel.getInstance().agregar(dispo);
		
			res.redirect("/admin/tabladispositivos");
			
			return null;
		}else {
			
			viewModel.put("Notnombre", true);
			viewModel.put("Notequipo", true);
			viewModel.put("Notconsumo", true);
			viewModel.put("Notminimo", true);
			viewModel.put("Notmaximo", true);
			
			return new ModelAndView(viewModel,"admin/agregardispositivo.hbs");
		}
	}
	
	public static ModelAndView showReportes(Request req, Response res) {
		
		Map<String, Object> viewModel = new HashMap<>();
		LocalDateTime fecha = LocalDateTime.now();
		String fechaActual = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(fecha);
		
		List<Cliente> clientes = ClienteModel.getInstance().buscarTodosLosCliente();
		List<Transformador> trafos = TransformadorModel.getInstance().buscarTodasLasTransformador();
		
		viewModel.put("actualAdmin", req.session().attribute("admin"));	
		viewModel.put("trafos", trafos);
		viewModel.put("clientes", clientes);
		viewModel.put("punto", "..");
		viewModel.put("fecha", fecha);
		viewModel.put("fechaActual", fechaActual);
		viewModel.put("actualAdmin", req.session().attribute("admin"));

		return new ModelAndView(viewModel,"admin/reportes.hbs");
		
	}
	
	public static ModelAndView getReporteHogar(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		
		Administrador admin = AdministradorModel.getInstance().buscarAdmin(req.session().attribute("admin"));
		
		LocalDateTime fechaInicio = LocalDateTime.parse(req.queryParams("start"), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		LocalDateTime fechaFin = LocalDateTime.parse(req.queryParams("end"), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

		String inicio = fechaInicio.toString();
		String fin = fechaFin.toString();
		
		if(req.queryParams("clientes").equals("todos")) {
			
			List<Cliente> clientes = ClienteModel.getInstance().buscarTodosLosCliente();
			
			Object[] consumos = clientes.stream().map(cliente -> 
				admin.generarReportHogarXPeriodo(cliente, fechaInicio, fechaFin)).toArray();
			
			viewModel.put("clientes", clientes);
			viewModel.put("consumos", consumos);
			viewModel.put("todos", true);
		}else {
			
			Cliente cliente = ClienteModel.getInstance().buscarCliente(req.queryParams("clientes"));
			
			Double consumo = admin.generarReportHogarXPeriodo(cliente, fechaInicio, fechaFin);
			
			viewModel.put("cliente", cliente);
			viewModel.put("consumo", consumo);
			
		}
		
		viewModel.put("inicio", inicio);
		viewModel.put("fin", fin);
		viewModel.put("reportehogar", true);
		viewModel.put("actualAdmin", req.session().attribute("admin"));
		viewModel.put("header", "reporte de consumo por hogar");
		
		return new ModelAndView(viewModel,"admin/showreporte.hbs");
	}
	
	public static ModelAndView getReporteDispositivo(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		
		Administrador admin = AdministradorModel.getInstance().buscarAdmin(req.session().attribute("admin"));
		LocalDateTime fechaInicio = LocalDateTime.parse(req.queryParams("start"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDateTime fechaFin = LocalDateTime.parse(req.queryParams("end"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String inicio = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH).format(fechaInicio);
		String fin = DateTimeFormatter.ofPattern("dd/MM/yyyy",Locale.ENGLISH).format(fechaFin);
		
		List<Object[]> o = admin.generarReportePromedioXPeriodo(inicio.replace('-', '/'), fin.replace('-', '/'));
		
		List<String> tipos = new ArrayList<>();
		List<Double> promedios = new ArrayList<>();
				
		for(Object[] lista : o){
			tipos.add((String)lista[0]);
			promedios.add((Double)lista[1]);
		}
		
		viewModel.put("tipos", tipos);
		viewModel.put("promedios", promedios);
		viewModel.put("inicio", inicio);
		viewModel.put("fin", fin);
		viewModel.put("actualAdmin", req.session().attribute("admin"));
		viewModel.put("header", "reporte promedio por tipo de dispositivo");
		viewModel.put("reportedispo", true);
		return new ModelAndView(viewModel,"admin/showreporte.hbs");
		
	}
	
	public static ModelAndView getReporteTrafo(Request req, Response res) {
		Map<String, Object> viewModel = new HashMap<>();
		
		
		viewModel.put("actualAdmin", req.session().attribute("admin"));
		viewModel.put("header", "reporte de consumo por transformador");
		viewModel.put("reportetrafo", true);
		return new ModelAndView(viewModel,"admin/showreporte.hbs");
	}
	
	
	
	public static boolean camposVacios(Request req) {
		
		if(req.queryParams("nombre").isEmpty()) {
			return true;
		}
		if(req.queryParams("equipo").isEmpty()) {
			return true;
		}
		if(req.queryParams("consumo").isEmpty()) {
			return true;
		}
		if(req.queryParams("consumoMinimo").isEmpty()) {
			return true;
		}
		if(req.queryParams("consumoMaximo").isEmpty()) {
			return true;
		}
		return false;
		
	}
	
}
