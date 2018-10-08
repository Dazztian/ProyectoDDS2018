package proyectoDDSs;

import java.time.LocalDateTime;

public class Encendido extends Estado {
	
	public Encendido() {
		super();
	}
	public Encendido (LocalDateTime inicio, LocalDateTime fin) {
		super(inicio, fin);
	}
	
	public boolean estadoEncendido() {
		return true;
	}

	public double coeficienteDeConsumo() {
		return 1;
	}

}
