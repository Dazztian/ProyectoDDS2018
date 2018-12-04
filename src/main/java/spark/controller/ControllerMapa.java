package spark.controller;

import java.util.*;

import json.JsonUtils;
import modelsPersistencia.TransformadorModel;
import modelsPersistencia.ZonaGeograficaModel;
import proyectoDDSs.Transformador;
import spark.*;

public class ControllerMapa {

	public static ModelAndView showMap(Request req, Response res) {
		
		Map<String, Object> viewModel = new HashMap<>();
		
		if(ControllerLogin.userIsLoggedIn(req, res))
			viewModel.put("actualUser", req.session().attribute("user"));
		else if(ControllerLogin.adminIsLoggedIn(req, res))
			viewModel.put("actualAdmin", req.session().attribute("admin"));
		
		TransformadorModel modelTrafos = new TransformadorModel();
		String trafosJson = modelTrafos.obtenerTrafos();
		ZonaGeograficaModel modelZona = new ZonaGeograficaModel();
		String zonasJson = modelZona.obtenerZonas();
		
		viewModel.put("jsonTrafos", trafosJson);
		viewModel.put("jsonZonas", zonasJson);
		
		return new ModelAndView(viewModel,"mapa.hbs");
		
		
	}
	
	
	
}
