package proyectoDDSs;

import java.time.*;

import javax.swing.Spring;

public class Log {
	public double consumo;
	public LocalDateTime horaDeLaOperacion;
	public Estado estado;
	
	public Log (double unConsumo, Estado unEstado) {
		consumo = unConsumo;
		horaDeLaOperacion = LocalDateTime.now();
		estado = unEstado;
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
	
	public void mostrarLogPorConsola() {
		System.out.format("En el momento %s el dispositivo estuvo %s \n", horaDeLaOperacion.toString(), this.conseguirEstado());
	}
	
	public boolean estabaEncendido() {
		return estado.estadoEncendido();
	}
	
	public String conseguirEstado() {
		return estado.nombreEstado();
		
	}
}
