package spark.server;

import spark.*;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {

	public static void main(String[] args) {
		
		Spark.port(9000);
	
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		Spark.staticFileLocation("/public");
		
		Spark.get("/login", Controller::showLogin, engine);
		
		Spark.init(); 
		
		DebugScreen.enableDebugScreen();
		
	}

}
