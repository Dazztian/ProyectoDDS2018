package modelsPersistencia;

import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Cliente;


public class ClienteModel extends ModelHelperPersistencia{

	private static ClienteModel instance = new ClienteModel();
	
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
		Cliente cliente= super.buscar(Cliente.class, new ImmutablePair<>("nombre_usuario", username));
		return cliente;
	}
	
	public static ClienteModel getInstance() {
		return instance;
	}
	
}


