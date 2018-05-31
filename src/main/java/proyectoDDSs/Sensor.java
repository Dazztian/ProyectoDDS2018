package proyectoDDSs;

import java.util.*;

public class Sensor {
	private double valor; //aca se almacena lo que mide el sensor
	private ArrayList<Regla> reglas;
	
	public Sensor() {
		reglas = new ArrayList<Regla>();
		
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
