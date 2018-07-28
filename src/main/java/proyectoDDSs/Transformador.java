package proyectoDDSs;
import com.google.gson.annotations.Expose;

import json.*;

public class Transformador extends BeanToJson<Transformador> {
	
	@Expose protected int id;
	@Expose protected double latitud;
	@Expose protected double longitud;
	@Expose protected int zona;
	
	
	//Constructor
		public Transformador(int unId,  double unaLatitud,double unaLongitud, int unaZona) {
			this.id=unId;
			this.latitud=unaLatitud;
			this.longitud=unaLongitud;
			this.zona = unaZona;
		}
		@Override
		public Transformador getObj() {
			return this;
		}

}
