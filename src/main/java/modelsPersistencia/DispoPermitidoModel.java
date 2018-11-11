package modelsPersistencia;

import java.util.List;
import proyectoDDSs.*;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class DispoPermitidoModel extends ModelHelperPersistencia{

	public DispoPermitidoModel() {
		super();
	}
	
	public DispositivoPermitido buscarDispositivo(Long id) {
		return super.buscar(DispositivoPermitido.class, new ImmutablePair<>("id", id));
	}
	
	public List<DispositivoPermitido> buscarTodosLosDispositivo(){
		return super.buscarTodos(DispositivoPermitido.class);
	}
	
	
}
