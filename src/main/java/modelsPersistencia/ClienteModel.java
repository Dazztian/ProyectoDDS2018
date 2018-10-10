package modelsPersistencia;

import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Cliente;


public class ClienteModel extends ModelHelperPersistencia{

	public ClienteModel() {
		super();
	}
	
	public Cliente buscarCliente(int id) {
		return super.buscar(Cliente.class, new ImmutablePair<>("id", id));
	}
	
	public List<Cliente> buscarTodosLosCliente(){
		return super.buscarTodos(Cliente.class);
	}
}


