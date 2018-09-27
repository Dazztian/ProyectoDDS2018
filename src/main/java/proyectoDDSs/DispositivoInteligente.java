package proyectoDDSs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.*;

public class DispositivoInteligente extends Dispositivo {
	
	private Estado estado;
	private Sensor sensor;
	private Timer temporizador;
	private double magnitud;
	public LinkedList<Log> logDeConsumo = new LinkedList<Log>();
	int intervalo=100;
	
	public DispositivoInteligente(String unNombre, double electricidadQConsume, Estado unEstado, double unConsumoMinimo, double unConsumoMaximo) {
		
		super(unNombre, electricidadQConsume, unConsumoMinimo, unConsumoMaximo);
		estado = unEstado;	
		temporizador=new Timer();
		
		temporizador.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				
				//notificarSensor();
				
			}
		}, 0, intervalo*1000);
	}

	public boolean esInteligente() {
		return true;
	}
	
	public boolean estaEncendido(){
		return estado.estadoEncendido();
	}
	
	public Estado getEstado () {
		return this.estado;
	}
	
	public void cambiarEstado(Estado unEstado) {
		estado = unEstado;
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
		return logDeConsumo.subList(0, n).stream().mapToDouble(log -> log.consumo()).sum();

	}
	
	public double consumoEnIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		return logDeConsumo.stream().filter(log -> log.ocurreEntre(fechaLimiteMaxima, fechaLimiteMinima)).mapToDouble(log -> log.consumo).sum();
	}
	
	public double consumoPromedioEnIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		long size = logDeConsumo.stream().filter(log -> log.ocurreEntre(fechaLimiteMaxima, fechaLimiteMinima)).count();
		return (logDeConsumo.stream().filter(log -> log.ocurreEntre(fechaLimiteMaxima, fechaLimiteMinima)).mapToDouble(log -> log.consumo).sum()) / size;
	}
	
	public double consumoMensual() {
		return this.consumoEnLasUltimasNHoras(720);
	}
	public void enlazarSensor(Sensor unSensor){
		this.sensor=unSensor;
	}
	/*public void notificarSensor() {
		sensor.medirMagnitud(this.magnitud);
	}*/
	
	public void setMagnitud(double m) {
		this.magnitud=m;
	}
	
	public double horasDeUsoMensuales() {
		return (this.consumoEnLasUltimasNHoras(720)/this.kwhConsumeXHora());
	}
	
}
