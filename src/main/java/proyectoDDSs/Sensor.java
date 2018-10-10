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
	
	@Column(name= "valor")
	private double valor; //aca se almacena lo que mide el sensor
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="id_Sensor",nullable=false)
	private List<Regla> reglas;
	
	public Sensor() {
		this.reglas=new ArrayList<>();
	}
	
	public void setMedicion(double m) {
		this.valor=m;
	}
	
	public void medirMagnitud(double magnitud) {
		valor=magnitud;
	}
	
	public void notificarMedicion() {
		for(Regla r:reglas) {
	//Metodo en la regla para modificar valores
			r.actualizarMedicion(valor);
		}
	}
	
	public void agregarRegla(Regla r) {
		reglas.add(r);
	}
	
	public void removerRegla(Regla r) {
		reglas.remove(r);
	}
	
}
