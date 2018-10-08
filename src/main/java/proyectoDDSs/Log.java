package proyectoDDSs;

import java.time.*;

import javax.swing.Spring;

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
	
	public void mostrarLogPorConsola(double consumoDisp) {
		System.out.format("En el momento %s el dispositivo estuvo %s", horaDeLaOperacion.toString(), this.conseguirEstado(consumoDisp));
	}
	
	public String conseguirEstado(double consumoDisp) {
		if(consumoDisp == consumo) {
			return "ENCENDIDO";
		}else {
			if(consumoDisp / 2 == consumo) {
				return "AHORRO DE ENERGIA";
			}else {
				if(consumoDisp / 100 == consumo) {
					return "APAGADO";
				}else {
					return "OTRO";
				}
			}
		}
		
	}
}
