package modelsPersistencia;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Estado;


public class EstadoModel extends ModelHelperPersistencia{
	
	public EstadoModel() {
		super();
	}
	
	public Estado buscarEstado(int id) {
		return super.buscar(Estado.class, new ImmutablePair<>("id", id));
	}
	
	public List<Estado> buscarTodosLosEstado(){
		return super.buscarTodos(Estado.class);
	}
	

}