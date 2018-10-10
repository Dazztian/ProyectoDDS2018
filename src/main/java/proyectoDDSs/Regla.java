package proyectoDDSs;

import proyectoDDSs.Actuador;
import java.util.ArrayList;

public class Regla {
	
	private double condicionDeAccion;
	private ArrayList<Actuador> ActuadoresAAccionar = new ArrayList<Actuador>();
	
	public Regla(double unaCondicion) {
		condicionDeAccion = unaCondicion;
	}
	
	public Regla (double unaCondicion, ArrayList<Actuador> unosActuadores) {
		condicionDeAccion = unaCondicion;
		ActuadoresAAccionar = unosActuadores;
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
		for(Actuador a:ActuadoresAAccionar) {
			a.disparar();
		}
	}
	
	public void agregarActuador(Actuador a) {
		ActuadoresAAccionar.add(a);
	}
	
	public void removerActuador(Actuador a) {
		ActuadoresAAccionar.remove(a);
	}
	
}

