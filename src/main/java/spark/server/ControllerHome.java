package spark.server;

import spark.*;
import java.util.*;

public class ControllerHome {

	public static ModelAndView showHome(Request req, Response res) {
		
		Map<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "home.hbs");
		
		
	}
	
}
