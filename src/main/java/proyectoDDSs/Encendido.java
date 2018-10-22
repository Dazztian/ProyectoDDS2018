package proyectoDDSs;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Encendido")
@DiscriminatorValue("Encendido")

public class Encendido extends Estado {
	
	public boolean estadoEncendido() {
		return true;
	}
	
	public String nombreEstado() {
		return "ENCENDIDO";
	}

	public double coeficienteDeConsumo() {
		return 1;
	}

}
