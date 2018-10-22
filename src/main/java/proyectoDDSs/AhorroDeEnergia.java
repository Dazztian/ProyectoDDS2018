package proyectoDDSs;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "AhorroDeEnergia")
@DiscriminatorValue("AhorroDeEnergia")

public class AhorroDeEnergia extends Estado {
	
	public boolean estadoEncendido() {
		return true;
	}
	
	public String nombreEstado() {
		return "AHORRO DE ENERGIA";
	}

	public double coeficienteDeConsumo() {
		return 0.5;
	}

}
