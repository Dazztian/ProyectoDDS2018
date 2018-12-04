package modelsPersistencia;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.tuple.ImmutablePair;

import json.JsonUtils;
import proyectoDDSs.Transformador;

public class TransformadorModel extends ModelHelperPersistencia{
	
	private static TransformadorModel instance = new TransformadorModel();
	
	public TransformadorModel() {
		super();
	}
	
	public Transformador buscarTransformador(int id) {
		return super.buscar(Transformador.class, new ImmutablePair<>("id", id));
	}
	
	public List<Transformador> buscarTodasLasTransformador(){
		return super.buscarTodos(Transformador.class);
	}
	
	public String obtenerTrafos() {
		
		List<Transformador> trafos = this.buscarTodasLasTransformador();
		ModelHelperPersistencia mh = new ModelHelperPersistencia();
		EntityManager em = mh.entityManager();
		
		for(Transformador trafoIndividual : trafos) {
			double consumoActual = 0;
			consumoActual = (Double) em.createNativeQuery("select sum(consumo)\r\n" + 
					"	from transformadores t join usuarios u on (u.id_Transformador = t.id)\r\n" + 
					"		join dispositivos_cliente dc on (dc.id_cliente = u.id) \r\n" + 
					"        join consumos c on (c.id_Dispositivo = dc.id)\r\n" + 
					"	where t.id = " + trafoIndividual.getId()+ " and c.Fecha = " + LocalDate.now()+
					" 	group by t.id").getSingleResult();
			
			trafoIndividual.setConsumoActual(consumoActual);
		}
		String trafosJson = JsonUtils.toJson(trafos);
		System.out.println(trafos);
		return trafosJson;
	}
	
	public static TransformadorModel getInstance() {
		return instance;
	}

}
