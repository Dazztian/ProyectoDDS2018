package spark.server;

import javax.persistence.EntityManager;

import modelsPersistencia.ModelHelperPersistencia;
import spark.*;
import spark.controller.*;
import spark.debug.DebugScreen;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class Server {

	public static void main(String[] args) {
		
		Spark.port(9000);
	
		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();
		
		EntityManager em = ModelHelperPersistencia.getEntityManager();
		
		Spark.staticFileLocation("/public");
		
		//Filtros para que no se pueda acceder a las paginas de usuario y admin sin loguearse
		Spark.before("/user/*", (req, res)->{
			if(req.session().attribute("user")==null) {
				Spark.halt(401, "Go Away!");
			}
		});
	
		Spark.before("/admin/*", (req, res)->{
			if(req.session().attribute("admin")==null) {
				Spark.halt(401, "Go Away!");
			}
		});

		
		Spark.get("/", ControllerHome::showHome, engine);
		Spark.get("/home", ControllerHome::showHome, engine);
		Spark.get("/mapa", ControllerMapa::showMap,engine);
		
		//Vistas del usuario, pueden cambiar
		Spark.get("/user/home", ControllerUser::showUserHome, engine);
		Spark.get("user/estado", ControllerUser::showEstado, engine);
//		Spark.get("/user/uploadDispositivos", ControllerUser::);
//		Spark.get("/user/simplex", route);
//		Spark.get("/user/reglas-dispositivos", route);
//		Spark.get("/user/consumos", route);
		
		Spark.get("/admin/home", ControllerAdmin::showAdminHome, engine);
		
		Spark.get("/login", ControllerLogin::showLogin, engine);	
		Spark.post("/login", ControllerLogin::manageLogin, engine);
		Spark.post("/logout", ControllerLogin::manageLogout, engine);
		
		Spark.init(); 
		
		DebugScreen.enableDebugScreen();
		
	}

}
