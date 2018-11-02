package proyectoDDSs;

import java.time.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.Spring;

@Entity
@Table(name="Consumos")
public class Log {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="Consumo")
	public double consumo;
	@Column(name="Fecha")
	public LocalDateTime horaDeLaOperacion;
	@Column(name="Estado_Dispositivo")
	public String nombreEstado;
	
	public Log() {}
	
	public Log (double unConsumo, Estado unEstado) {
		consumo = unConsumo;
		horaDeLaOperacion = LocalDateTime.now();
		nombreEstado = unEstado.nombreEstado();
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
		if(nombreEstado == "ENCENDIDO" || nombreEstado == "AHORRO DE ENERGIA") {
			return true;
		}else {
			return false;
		}
	}
	
	public String conseguirEstado() {
		return nombreEstado;
		
	}
}
