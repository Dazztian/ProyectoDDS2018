package proyectoDDSs;

import proyectoDDSs.Actuador;
import java.util.ArrayList;

public class Regla {
	
	private double condicionDeAccion;
	private ArrayList<Actuador> ActuadoresAAccionar = new ArrayList<Actuador>();
	
	public void actualizarMedicion(double valor) {
		if (valor>condicionDeAccion){
			accionarActuadores();
		}
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

