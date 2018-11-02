package modelsPersistencia;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Actuador;

public class ActuadorModel extends ModelHelperPersistencia{
	
	public ActuadorModel() {
		super();
	}
	
	public Actuador buscarActuador(int id) {
		return super.buscar(Actuador.class, new ImmutablePair<>("id", id));
	}
	
	public List<Actuador> buscarTodasLasActuador(){
		return super.buscarTodos(Actuador.class);
	}
	

}
