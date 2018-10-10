package proyectoDDSs;
import com.google.gson.annotations.Expose;
import javax.persistence.*;
import java.util.*;
import json.*;


@Entity
@Table(name = "ZonaGeografica")
public class ZonaGeografica extends BeanToJson<ZonaGeografica> {
	
	@Id 
	@Column(name = "id")
	private int id;
	
	@Expose @Column(name = "nombre")
	protected String nombre;
	@Expose @Column(name = "latitud")
	protected Double latitud;
	@Expose @Column(name = "longitud")
	protected Double longitud;
	//Esto también hay que ver como resolver
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_Zona",nullable=false)
	private List<Transformador> trafos;
		
	
			
	//Constructor
		public ZonaGeografica() {
			this.trafos=new ArrayList<>();
		};
		
		
		
		public ZonaGeografica(String nombre, Double latitud, Double longitud, ArrayList<Transformador> trafos, int id) {
		super();
		this.nombre = nombre;
		this.latitud = latitud;
		this.longitud = longitud;
		this.trafos = trafos;
		this.id = id;
		this.trafos=new ArrayList<>();
	}



		public ZonaGeografica(int unId,String unNombre, Double unaLatitud,Double unaLongitud) {
			this.id=unId;
			this.nombre = unNombre;
			this.latitud=unaLatitud;
			this.longitud=unaLongitud;
		}
		@Override
		public ZonaGeografica getObj() {
			return this;
		}
		public int getId() {
			return this.id;
		}
		
		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public Double getLatitud() {
			return latitud;
		}

		public void setLatitud(Double latitud) {
			this.latitud = latitud;
		}

		public Double getLongitud() {
			return longitud;
		}

		public void setLongitud(Double longitud) {
			this.longitud = longitud;
		}

		/*
		 * public ArrayList<Transformador> getTrafos() {
			return trafos;
		}

		public void setTrafos(ArrayList<Transformador> trafos) {
			this.trafos = trafos;
		}
		 */
		public void setId(int id) {
			this.id = id;
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
