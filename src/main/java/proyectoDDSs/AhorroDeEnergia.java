package proyectoDDSs;

import java.time.LocalDateTime;

public class AhorroDeEnergia extends Estado {
	
	public AhorroDeEnergia() {
		super();
	}
	public AhorroDeEnergia (LocalDateTime inicio, LocalDateTime fin) {
		super(inicio, fin);
	}
	
	public boolean estadoEncendido() {
		return true;
	}

	public double coeficienteDeConsumo() {
		return 0.5;
	}

}
