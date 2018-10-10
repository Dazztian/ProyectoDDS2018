package proyectoDDSs;

import proyectoDDSs.Actuador;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "Regla")
public class Regla {
	
	@Id 
	@Column(name = "id")
	private int id;
	
	@Expose @Column(name = "condicionDeAccion")
	private double condicionDeAccion;
	
	//@Column(name = "estadoAAccionar")
	@Transient
	public Estado estadoAAccionar;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="id_Regla",nullable=false)
	private List<Actuador> actuadoresAAccionar = new ArrayList<Actuador>();
	
	public Regla(double unaCondicion) {
		condicionDeAccion = unaCondicion;
	}
	
	public Regla (double unaCondicion, List<Actuador> unosActuadores) {
		condicionDeAccion = unaCondicion;
		actuadoresAAccionar = unosActuadores;
		//estadoAAccionar = new Apagado();
	}
	
	public void actualizarMedicion(double valor) {
		if (valor>condicionDeAccion){
			accionarActuadores();
		}
	}
	
	public double condicionDeAccion() {
		return condicionDeAccion;
	}
	
	public void modificarCondicion (double unaCondicion) {
		condicionDeAccion = unaCondicion;
	}
	
	private void accionarActuadores() {
		for(Actuador a:actuadoresAAccionar) {
			a.disparar(new Apagado());
		}
	}
	
	public void agregarActuador(Actuador a) {
		actuadoresAAccionar.add(a);
	}
	
	public void removerActuador(Actuador a) {
		actuadoresAAccionar.remove(a);
	}
	
}

