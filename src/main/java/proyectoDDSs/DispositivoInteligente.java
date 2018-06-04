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
	
	public DispositivoInteligente(String unNombre, double electricidadQConsume, Estado unEstado) {
		
		super(unNombre, electricidadQConsume);
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
		
		//List<Log> logDePrueba = logDeConsumo.subList(0, n);
		
		//Stream<Object> listaDePrueba = logDeConsumo.subList(0, n).stream().map(log -> log.consumo());
		
		//double numeroDePrueba = logDeConsumo.subList(0, n).stream().map(log -> log.consumo()).count();
		return logDeConsumo.subList(0, n).stream().mapToDouble(log -> log.consumo()).sum();
	}
	
	public double consumoEnElSiguenteIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		return logDeConsumo.stream().filter(log -> log.ocurreEntre(fechaLimiteMaxima, fechaLimiteMinima)).map(log -> log.consumo).count();
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
	
}
