package proyectoDDSs;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

public class Apagado extends Estado {
	

	public boolean estadoEncendido() {
		return false;
	}
	
	public String nombreEstado() {
		return "APAGADO";
	}

	public double coeficienteDeConsumo() {
		return 0.01;
	}

}
