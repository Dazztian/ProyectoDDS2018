package proyectoDDSs;

import java.util.*;
import javax.persistence.*;

@Entity
@Table(name= "Sensor")
public class Sensor {
	
	@Id 
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@ElementCollection(fetch=FetchType.LAZY)
	private List<Double> valor; //aca se almacena lo que mide el sensor
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="id_Sensor",nullable=false)
	private List<Regla> reglas;
	
	public Sensor() {
		this.reglas=new ArrayList<>();
	}

	public void medirMagnitud(Double magnitud) {
		valor.add(magnitud);
	}
	
	public void notificarMedicion() {
		for(Regla r:reglas) {
	//Metodo en la regla para modificar valores
			r.actualizarMedicion(valor.get(valor.size()-1));
		}
	}
	
	public List<Double> getMediciones(){
		return this.valor;
	}
	
	public void agregarRegla(Regla r) {
		reglas.add(r);
	}
	
	public void removerRegla(Regla r) {
		reglas.remove(r);
	}
	
	public Regla getRegla() {
		return this.reglas.get(0);
	}
	
}
