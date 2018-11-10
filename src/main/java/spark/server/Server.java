package spark.server;

import spark.*;
import spark.controller.ControllerHome;
import spark.controller.ControllerLogin;
import spark.controller.ControllerMapa;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {

	public static void main(String[] args) {
		
		Spark.port(9000);
	
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		
		Spark.staticFileLocation("/public");
		
//		Spark.before("/", filter);
//		
		
		
		Spark.get("/", ControllerHome::showHome, engine);
		Spark.get("/home", ControllerHome::showHome, engine);
		Spark.get("/mapa", ControllerMapa::showMap,engine);
		
		Spark.get("/login", ControllerLogin::showLogin, engine);	
		Spark.post("/login", ControllerLogin::manageLogin, engine);
		Spark.post("logout", ControllerLogin::manageLogout, engine);
		
		Spark.init(); 
		
		DebugScreen.enableDebugScreen();
		
	}

}
