package modelsPersistencia;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Dispositivo;


	public class DispositivoModel extends ModelHelperPersistencia{

		public DispositivoModel() {
			super();
		}
		
		public Dispositivo buscarDispositivo(int id) {
			return super.buscar(Dispositivo.class, new ImmutablePair<>("id", id));
		}
		
		public List<Dispositivo> buscarTodosLosDispositivo(){
			return super.buscarTodos(Dispositivo.class);
		}
}
 