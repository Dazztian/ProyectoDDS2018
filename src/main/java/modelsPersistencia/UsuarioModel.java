package modelsPersistencia;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Usuario;

public class UsuarioModel extends ModelHelperPersistencia{
	
	public UsuarioModel() {
		super();
	}
	
	public Usuario buscarUsuario(int id) {
		return super.buscar(Usuario.class, new ImmutablePair<>("id", id));
	}
	
	public List<Usuario> buscarTodasLasUsuario(){
		return super.buscarTodos(Usuario.class);
	}
	

}
