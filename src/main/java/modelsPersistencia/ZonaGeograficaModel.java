package modelsPersistencia;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;

import json.JsonUtils;
import proyectoDDSs.ZonaGeografica;

public class ZonaGeograficaModel extends ModelHelperPersistencia{
	
	public ZonaGeograficaModel() {
		super();
	}
	
	public ZonaGeografica buscarZonaGeografica(int id) {
		return super.buscar(ZonaGeografica.class, new ImmutablePair<>("id", id));
	}
	
	public List<ZonaGeografica> buscarTodasLasZonaGeografica(){
		return super.buscarTodos(ZonaGeografica.class);
	}
	
	public String obtenerZonas() {
		List<ZonaGeografica> listaZonas = this.buscarTodasLasZonaGeografica();
		String zonasJson = JsonUtils.toJson(listaZonas);
		System.out.println(listaZonas);
		return zonasJson;
	}

}
