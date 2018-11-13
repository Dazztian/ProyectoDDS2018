package modelsPersistencia;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Administrador;
import proyectoDDSs.Cliente;

public class AdministradorModel extends ModelHelperPersistencia{
	
	private static AdministradorModel instance = new AdministradorModel();
	
	public AdministradorModel() {
		super();
	}
	
	public Administrador buscarUsuario(Long id) {
		return super.buscar(Administrador.class, new ImmutablePair<>("id", id));
	}
	
	public List<Administrador> buscarTodasLasUsuario(){
		return super.buscarTodos(Administrador.class);
	}
	
	public Administrador buscarAdmin(String username) {
		Administrador admin=super.buscar(Administrador.class, new ImmutablePair<>("nombre_usuario", username));
		return admin;
	}
	
	public static AdministradorModel getInstance() {
		return instance;
	}
	
}
