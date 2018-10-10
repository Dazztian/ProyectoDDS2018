package proyectoDDSs;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "AhorroDeEnergia")
@DiscriminatorValue("AhorroDeEnergia")

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
