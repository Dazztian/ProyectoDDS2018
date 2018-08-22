package proyectoDDSs;
import com.google.gson.annotations.Expose;
import java.util.*;
import json.*;
public class ZonaGeografica extends BeanToJson<ZonaGeografica> {
	
	@Expose private int id;
	@Expose protected String nombre;
	@Expose protected double latitud;
	@Expose protected double longitud;
			private ArrayList<Transformador> trafos;
	
	//Constructor
		public ZonaGeografica(int unId,String unNombre, double unaLatitud,double unaLongitud) {
			this.id=unId;
			this.nombre = unNombre;
			this.latitud=unaLatitud;
			this.longitud=unaLongitud;
			trafos=new ArrayList<Transformador>();
		}
		@Override
		public ZonaGeografica getObj() {
			return this;
		}
		public int getId() {
			return this.id;
		}
		
		public void addTranformador(Transformador trafo) {
			
			trafos.add(trafo);
			
		}
		
		public double consumoMomentaneo() {
			return trafos.stream().mapToDouble(trafo -> trafo.consumoMomentaneo()).sum();
		}
		public int cantidadTrafos() {
			// TODO Auto-generated method stub
			return this.trafos.size();
		}
		

}
