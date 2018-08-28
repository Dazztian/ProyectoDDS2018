package proyectoDDSs;
import java.util.*;

import com.google.gson.annotations.Expose;

import json.*;

public class Transformador extends BeanToJson<Transformador> {
	
	@Expose protected int id;
	@Expose protected double latitud;
	@Expose protected double longitud;
	@Expose protected int zona;
			private ArrayList<Cliente> clientes;
	
	//Constructor
		public Transformador(int unId,  double unaLatitud,double unaLongitud, int unaZona) {
			this.id=unId;
			this.latitud=unaLatitud;
			this.longitud=unaLongitud;
			this.zona = unaZona;
			
			this.clientes=new ArrayList<Cliente>();
			
		}
		@Override
		public Transformador getObj() {
			return this;
		}
		
		public void asignarZona(ArrayList <ZonaGeografica> zonas) {
			
			for(ZonaGeografica zona:zonas) {
				
				if(this.zona == zona.getId()) {
					zona.addTranformador(this);
				}
				
			}
			
		}
		public double getLat() {
			return this.latitud;
		}
		public double getLong() {
			return this.longitud;
		}
		public void addCliente(Cliente cliente) {
			
			clientes.add(cliente);
			
		}
		
		public int numeroClientes() {
			return clientes.size();
		}
		
		public ArrayList<Cliente> getClientes(){
			return this.clientes;
		}
		
		public double consumoMomentaneo() {
			
			return clientes.stream().mapToDouble(cliente -> cliente.consumoEnLaUltimaHora()).sum();
			
		}

}
