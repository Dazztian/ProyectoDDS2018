package spark.server;

import static spark.Spark.staticFiles;

import java.io.File;

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
		
		//Esto es para el tema del json de dispositivos
        staticFiles.externalLocation("upload");

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

		Spark.before("/register",(req,res)->{
			if(ControllerLogin.userIsLoggedIn(req, res)||ControllerLogin.adminIsLoggedIn(req, res)) {
				Spark.halt(401, "You have to LogOut");
			}
		});
		
		Spark.get("/", ControllerHome::showHome, engine);
		Spark.get("/home", ControllerHome::showHome, engine);
		Spark.get("/mapa", ControllerMapa::showMap,engine);
		
		//Vistas del usuario, pueden cambiar
		Spark.get("/user/home", ControllerUser::showUserHome, engine);
		Spark.get("/user/estado", ControllerUser::showEstado, engine);
		Spark.get("/user/uploadDispositivos", ControllerUser::showUploadDispositivos, engine);
		Spark.post("/user/uploadDispositivos", ControllerUser::uploadDispositivos, engine);
//		Spark.get("/user/simplex", route);
//		Spark.get("/user/reglas-dispositivos", route);
//		Spark.get("/user/consumos", route);
		
		Spark.get("/admin/home", ControllerAdmin::showAdminHome, engine);
		Spark.get("/admin/hogares", ControllerAdmin::showHogares, engine);
		Spark.get("/admin/tabladispositivos", ControllerAdmin::showTablaDispositivos, engine);
		Spark.post("/admin/tabladispositivos", ControllerAdmin::cambioEnLaTabla, engine);
		Spark.get("/admin/agregardispositivo", ControllerAdmin::showAddDispositivo, engine);
		Spark.post("/admin/agregardispositivo", ControllerAdmin::addNuevoDispositivo, engine);
		Spark.get("/admin/reportes", ControllerAdmin::showReportes, engine);
		Spark.post("/admin/reportes/reportehogar", ControllerAdmin::getReporteHogar, engine);
		Spark.post("/admin/reportes/reportepromedio", ControllerAdmin::getReporteDispositivo, engine);
		Spark.post("/admin/reportes/reportetrafo", ControllerAdmin::getReporteTrafo, engine);
		
		Spark.get("/login", ControllerLogin::showLogin, engine);	
		Spark.post("/login", ControllerLogin::manageLogin, engine);
		Spark.post("/logout", ControllerLogin::manageLogout, engine);
		
		Spark.get("/register", ControllerRegister::showRegisterPage, engine);
		Spark.post("/register", ControllerRegister::manageRegister, engine);
		
		Spark.init(); 
		
		DebugScreen.enableDebugScreen();
		
	}

}
