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
		
		public List<DispositivoEstandar> buscarTodosLosEstandar(){
			return super.buscarTodos(DispositivoEstandar.class);
		}
		
		public List<Regla> buscarReglas(List<Dispositivo> dispos){
			
			return (List<Regla>)dispos.stream().filter(dispo -> dispo.esInteligente()).map
				(dispo -> ((DispositivoInteligente) dispo).getSensor()).map(sensor -> sensor.getRegla());	
		}
		
		public DispositivoPermitido existeDispoEnBD(String nombre, String equipo){
			//Intento obtener el dispositivo, si existe voy a poder compararlo x el nombre, sino no y me va a tirar error.
			return super.buscar(DispositivoPermitido.class, new ImmutablePair<>("nombre", nombre),new ImmutablePair<>("equipo", equipo)); 				 			 			
			
		}
		
		public Long obtenerIDDispo(String nombre, String equipo){
			return  (super.buscar(DispositivoPermitido.class, new ImmutablePair<>("nombre", nombre),new ImmutablePair<>("equipo", equipo))).getId();
		}

		public DispositivoPermitido existeDispoEnBDEquipo(String equipo) {
			DispositivoPermitido dispo;
			try {
				dispo=super.buscar(DispositivoPermitido.class, new ImmutablePair<>("equipo", equipo)); 				 			 			
			
			}catch(Exception e) {		
				dispo=null;	
			}
			return dispo;
			
		}
		
}
 