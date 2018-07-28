package proyectoDDSs;
import com.google.gson.annotations.Expose;

import json.*;
public class ZonaGeografica extends BeanToJson<ZonaGeografica> {
	
	@Expose protected int id;
	@Expose protected String nombre;
	@Expose protected double latitud;
	@Expose protected double longitud;
	
	
	//Constructor
		public ZonaGeografica(int unId,String unNombre, double unaLatitud,double unaLongitud) {
			this.id=unId;
			this.nombre = unNombre;
			this.latitud=unaLatitud;
			this.longitud=unaLongitud;
		}
		@Override
		public ZonaGeografica getObj() {
			return this;
		}
	

}
