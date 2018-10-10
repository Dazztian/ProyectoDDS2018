package proyectoDDSs;
import java.time.LocalDateTime;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.gson.annotations.Expose;

import json.*;


@Entity
@Table(name="Transformadores")
public class Transformador extends BeanToJson<Transformador> {
			
			@Id
			@Column(name="id")
	@Expose protected int id;
	@Expose protected double latitud;
	@Expose protected double longitud;
	@Expose protected int zona;
			
			@OneToMany(cascade=CascadeType.ALL)
			@JoinColumn(name="id_Transformador")
			private List<Cliente> clientes;
	
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
		
		public void asignarZona(List <ZonaGeografica> zonas) {
			
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
		
		public List<Cliente> getClientes(){
			return this.clientes;
		}
		
		public double consumoMomentaneo() {
			
			return clientes.stream().mapToDouble(cliente -> cliente.consumoEnLaUltimaHora()).sum();
			
		}
		
		public double consumoEnIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
			return clientes.stream().mapToDouble(cliente -> cliente.consumoEnIntervalo(fechaLimiteMaxima, fechaLimiteMinima)).sum();
		}

}
