package proyectoDDSs;

import java.util.ArrayList;

public class Regla {

	public Regla() {
		
		actuadores=new ArrayList<Actuador>();
		
	}
	
	public void actualizarMedicion(double valor) {
		
		valorMedido=valor;
		
		accionarActuadores();
		
	}
	
	private void accionarActuadores() {
		for(Actuador a:actuadores) {
			a.disparar();
		}
	}
	
	public void agregarActuador(Actuador a) {
		actuadores.add(a);
	}
	
	public void removerActuador(Actuador a) {
		actuadores.remove(a);
	}
	
	private double valorMedido;
	private ArrayList<Actuador> actuadores;
	
}
