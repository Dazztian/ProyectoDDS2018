package modelsPersistencia;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;

import proyectoDDSs.Sensor;

public class SensorModel extends ModelHelperPersistencia{
	
	public SensorModel() {
		super();
	}
	
	public Sensor buscarSensor(int id) {
		return super.buscar(Sensor.class, new ImmutablePair<>("id", id));
	}
	
	public List<Sensor> buscarTodasLasSensor(){
		return super.buscarTodos(Sensor.class);
	}
	

}
