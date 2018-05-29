package proyectoDDSs;

import java.time.*;

public class Log {
	public double consumo;
	public LocalDateTime horaDeLaOperacion;
	
	public Log (double unConsumo) {
		consumo = unConsumo;
		horaDeLaOperacion = LocalDateTime.now();
	}
	
	public double consumo() {
		return consumo;
	}
	
	public LocalDateTime horaDeLaOperacion() {
		return horaDeLaOperacion;
	}
	
	public boolean ocurreEntre(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		return horaDeLaOperacion.isBefore(fechaLimiteMaxima) && horaDeLaOperacion.isAfter(fechaLimiteMinima);
	}
}
