package modelsPersistencia;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Administrador;

public class AdministradorModel extends ModelHelperPersistencia{
	
	public AdministradorModel() {
		super();
	}
	
	public Administrador buscarUsuario(int id) {
		return super.buscar(Administrador.class, new ImmutablePair<>("id", id));
	}
	
	public List<Administrador> buscarTodasLasUsuario(){
		return super.buscarTodos(Administrador.class);
	}
	

}
