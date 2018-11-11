package modelsPersistencia;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;
import proyectoDDSs.*;

import proyectoDDSs.Dispositivo;


	public class DispositivoModel extends ModelHelperPersistencia{

		private static DispositivoModel instance= new DispositivoModel();
		
		public DispositivoModel() {
			super();
		}
		
		public static DispositivoModel getInstance() {
			return instance;
		}
		
		public Dispositivo buscarDispositivo(Long id) {
			return super.buscar(Dispositivo.class, new ImmutablePair<>("id", id));
		}
		
		public List<Dispositivo> buscarTodosLosDispositivo(){
			return super.buscarTodos(Dispositivo.class);
		}
		
		public List<Regla> buscarReglas(List<Dispositivo> dispos){
			
			return (List<Regla>)dispos.stream().filter(dispo -> dispo.esInteligente()).map
				(dispo -> ((DispositivoInteligente) dispo).getSensor()).map(sensor -> sensor.getRegla());
			
		}
		
}
 