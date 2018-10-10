package proyectoDDSs;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Encendido")
@DiscriminatorValue("Encendido")

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
