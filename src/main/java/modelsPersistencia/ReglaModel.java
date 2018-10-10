package modelsPersistencia;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Regla;

public class ReglaModel extends ModelHelperPersistencia{
	
	public ReglaModel() {
		super();
	}
	
	public Regla buscarRegla(int id) {
		return super.buscar(Regla.class, new ImmutablePair<>("id", id));
	}
	
	public List<Regla> buscarTodasLasRegla(){
		return super.buscarTodos(Regla.class);
	}
	

}
