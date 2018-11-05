package spark.server;

import spark.*;
import java.util.*;

public class Controller {

	public static ModelAndView showLogin(Request req, Response res) {
		
		Map<String, Object> viewModel = new HashMap<>();
		
		return new ModelAndView(viewModel, "login.hbs");
		
		
	}
	
}
