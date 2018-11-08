package modelsPersistencia;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Cliente;


public class ClienteModel extends ModelHelperPersistencia{

	public ClienteModel() {
		super();
	}
	
	public Cliente buscarCliente(Long id) {
		return super.buscar(Cliente.class, new ImmutablePair<>("id", id));
	}
	
	public List<Cliente> buscarTodosLosCliente(){
		return super.buscarTodos(Cliente.class);
	}
	
	public Cliente buscarCliente(String username) {
		try {
		Cliente cliente=entityManager().createQuery("from Cliente c where nombre_usuario='"+username+"'",Cliente.class).getSingleResult();
		return cliente;
		}catch(NoResultException e) {
			return null;
		}
	}
	
}


