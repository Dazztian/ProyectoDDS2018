package modelsPersistencia;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Dispositivo;
import proyectoDDSs.DispositivoXCliente;

public class DispoXClienteModel extends ModelHelperPersistencia {

	public DispoXClienteModel() {
		super();
	}
	
	public DispositivoXCliente buscarDispositivo(Long id) {
		return super.buscar(DispositivoXCliente.class, new ImmutablePair<>("id", id));
	}
	
	public List<DispositivoXCliente> buscarTodosLosDispositivo(){
		return super.buscarTodos(DispositivoXCliente.class);
	}

	
}
