package spark.server;

import modelsPersistencia.AdministradorModel;
import modelsPersistencia.ClienteModel;
import proyectoDDSs.Cliente;
import proyectoDDSs.Administrador;

public class UserController {

	public static boolean autenticarCliente(String username, String password) {
		
		if(username.isEmpty()||password.isEmpty()) {
			return false;
		}
		
		Cliente cliente;
		if((cliente = new ClienteModel().buscarCliente(username))==null) {
			return false;
		}
		
		return cliente.getPassword().equals(password);
		
	}
	
	public static boolean autenticarAdministrador(String username, String password) {
		
		if(username.isEmpty()||password.isEmpty()) {
			return false;
		}
		
		Administrador admin;
		if((admin = new AdministradorModel().buscarAdmin(username))==null) {
			return false;
		}
		
		return admin.getPassword().equals(password);
		
	}
	
	
}
