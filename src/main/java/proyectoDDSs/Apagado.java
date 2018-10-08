package proyectoDDSs;

import java.time.LocalDateTime;

public class Apagado extends Estado {
	
	public Apagado() {
		super();
	}
	public Apagado (LocalDateTime inicio, LocalDateTime fin) {
		super(inicio, fin);
	}

	public boolean estadoEncendido() {
		return false;
	}

	public double coeficienteDeConsumo() {
		return 0.01;
	}

}
