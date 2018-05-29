package proyectoDDSs;

import java.util.*;
import java.time.*;

public class DispositivoInteligente extends Dispositivo {
	
	private Estado estado;
	public LinkedList<Log> logDeConsumo;

	public DispositivoInteligente(String unNombre, double electricidadQConsume, Estado unEstado) {
		super(unNombre, electricidadQConsume);
		estado = unEstado;
	}

	public boolean esInteligente() {
		return true;
	}
	
	public boolean estaEncendido(){
		return estado.estadoEncendido();
	}
	
	public void apagar() {
		estado = new Apagado();
	}
	public void prender() {
		estado = new Encendido();
	}
	
	public void ahorrarEnergia() {
		estado = new AhorroDeEnergia();
	}
	
	public double consumoPorHora() {
		return kwhConsumeXHora * estado.coeficienteDeConsumo();
	}
	
	public void guardarConsumo() {
		Log nuevoLog = new Log(this.consumoPorHora());
		logDeConsumo.addFirst(nuevoLog);
	}
	
	public double consumoEnLasUltimasNHoras(int n) {
		if(logDeConsumo.size() < n) {
			n = logDeConsumo.size();
		}
		return logDeConsumo.subList(0, n).stream().map(log -> log.consumo).count();
	}
	
	public double consumoEnElSiguenteIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		return logDeConsumo.stream().filter(log -> log.ocurreEntre(fechaLimiteMaxima, fechaLimiteMinima)).map(log -> log.consumo).count();
	}
	
	public double consumoMensual() {
		return this.consumoEnLasUltimasNHoras(720);
	}

}
