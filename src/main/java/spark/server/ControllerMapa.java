package spark.server;

import spark.*;

public class ControllerMapa {

	public static ModelAndView showMap(Request req, Response res) {
		
		return new ModelAndView(null,"mapa.hbs");
		
		
	}
	
}
