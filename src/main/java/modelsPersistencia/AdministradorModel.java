package modelsPersistencia;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Administrador;
import proyectoDDSs.Cliente;

public class AdministradorModel extends ModelHelperPersistencia{
	
	public AdministradorModel() {
		super();
	}
	
	public Administrador buscarUsuario(int id) {
		return super.buscar(Administrador.class, new ImmutablePair<>("id", id));
	}
	
	public List<Administrador> buscarTodasLasUsuario(){
		return super.buscarTodos(Administrador.class);
	}
	
	public Administrador buscarAdmin(String username) {
		try {
		Administrador admin=entityManager().createQuery("from Administrador a where nombre_usuario='"+username+"'",Administrador.class).getSingleResult();
		return admin;
		}catch(NoResultException e) {
			return null;
		}
	}
	
}
