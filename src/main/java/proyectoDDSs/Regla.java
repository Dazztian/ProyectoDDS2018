package proyectoDDSs;

import proyectoDDSs.Actuador;

import java.util.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Regla {
	
	double condicionDeAccion;
	protected ArrayList<Actuador> ActuadoresAAccionar = new ArrayList<Actuador>();
	
	public void actualizarMedicion(double medicion){
		if (medicion<condicionDeAccion){
			this.accionarActuadores();
		}
	}
	
	public void accionarActuadores() {
		ActuadoresAAccionar.stream().map(actuador->actuador.execute());
	}
	
}

