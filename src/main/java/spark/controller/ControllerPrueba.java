package spark.server;

import spark.*;

public class ControllerPrueba {
	
	public static ModelAndView showInputFile(Request req, Response res) {
		
		return new ModelAndView(null,"subirarchivo.hbs");
		
	}
	
	
}
