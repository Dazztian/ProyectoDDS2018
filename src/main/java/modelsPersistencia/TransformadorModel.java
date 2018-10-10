package modelsPersistencia;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Transformador;

public class TransformadorModel extends ModelHelperPersistencia{
	
	public TransformadorModel() {
		super();
	}
	
	public Transformador buscarTransformador(int id) {
		return super.buscar(Transformador.class, new ImmutablePair<>("id", id));
	}
	
	public List<Transformador> buscarTodasLasTransformador(){
		return super.buscarTodos(Transformador.class);
	}
	

}
