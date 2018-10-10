package modelsPersistencia;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Categoria;

public class CategoriaModel extends ModelHelperPersistencia{
	
	public CategoriaModel() {
		super();
	}
	
	public Categoria buscarCategoria(int id) {
		return super.buscar(Categoria.class, new ImmutablePair<>("id", id));
	}
	
	public List<Categoria> buscarTodasLasCategoria(){
		return super.buscarTodos(Categoria.class);
	}
	

}
