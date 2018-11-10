package proyectoDDSs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.time.*;

@Entity
@DiscriminatorValue("Inteligente")
public class DispositivoInteligente extends Dispositivo {
	
	@Transient
	private Estado estado;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sensor_asignado")
	private Sensor sensor;
	
	@Transient
	private Timer temporizador;
	@Transient
	private Double magnitud;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
	@JoinColumn(name="id_Dispositivo",nullable=false)
	public List<Log> logDeConsumo = new LinkedList<Log>();
	
	@Transient
	int intervalo=100;
	
	public DispositivoInteligente() {
		
	}
	
	public DispositivoInteligente(String unNombre,String equipo, double electricidadQConsume, Estado unEstado, double unConsumoMinimo, double unConsumoMaximo) {
		
		super(unNombre,equipo ,electricidadQConsume, unConsumoMinimo, unConsumoMaximo);
		estado = unEstado;	
		temporizador=new Timer();
		this.inteligente=true;
		
		temporizador.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				/*
				sensor.medirMagnitud(0);//aca no sabria bien que poner porque la magnitud que mide podria ser algo fisico del cual no se de donde sacarlo
				sensor.notificarMedicion();
				*/
				
			}
		}, 0, intervalo*1000);
	}

	public boolean esInteligente() {
		return this.inteligente;
	}
	
	public boolean estaEncendido(){
		return estado.estadoEncendido();
	}
	
	public Estado getEstadoActual () {
		return this.estado;
	}
	
	public void cambiarEstado(Estado unEstado) {
		estado = unEstado;
	}
	
	public void apagar() {
		this.cambiarEstado(new Apagado());
	}
	public void prender() {
		this.cambiarEstado(new Encendido());
	}
	
	public void ahorrarEnergia() {
		this.cambiarEstado(new AhorroDeEnergia());
	}
	
	public double consumoPorHora() {
		return kwhConsumeXHora * estado.coeficienteDeConsumo();
	}
	
	public void guardarConsumo() {
		LinkedList<Log> logCopy = new LinkedList<>(); 
		this.logDeConsumo.addAll(logCopy);
		Log nuevoLog = new Log(this.consumoPorHora(), this.getEstadoActual());
		logCopy.addFirst(nuevoLog);
		logDeConsumo.add(logCopy.getFirst());
	}
	
	public double consumoEnLasUltimasNHoras(int n) {
		return logDeConsumo.stream().filter(log -> log.horaDeLaOperacion.isAfter(LocalDateTime.now().minusHours(n)))
				.mapToDouble(log -> log.consumo())
				.sum();
	}
	
	public double consumoEnIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		return logDeConsumo.stream().filter(log -> log.ocurreEntre(fechaLimiteMaxima, fechaLimiteMinima))
				.mapToDouble(log -> log.consumo())
				.sum();
	}
	
	public double consumoPromedioEnIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		 Apagado aux = new Apagado();
		 int cantidadDeHoras = aux.horasDeDiferencia(fechaLimiteMaxima, fechaLimiteMinima);
		 return this.consumoEnIntervalo(fechaLimiteMaxima, fechaLimiteMinima) / cantidadDeHoras;
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
	
	public void intervalosEncendidoEnPeriodo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		System.out.format("El Dispositivo estuvo encendido en los siguientes Intervalos: \n");
		logDeConsumo.stream().filter(log -> log.ocurreEntre(fechaLimiteMaxima, fechaLimiteMinima))
		.filter(log -> log.estabaEncendido()).forEach(log -> log.mostrarLogPorConsola());		
	}
	
	public void intervalosEncendidosEnElUltimoMes() {
		LocalDateTime aux = LocalDateTime.now().minusMonths(1);
		LocalDateTime fechaLimiteMaxima = LocalDateTime.of(aux.getYear(), aux.getMonthValue(), 1, 0, 0);
		LocalDateTime fechaLimiteMinima = LocalDateTime.of(aux.getYear(), aux.getMonthValue(), 30, 0, 0);
		this.intervalosEncendidoEnPeriodo(fechaLimiteMaxima, fechaLimiteMinima);
	}
	
	public List<Log> getLogs(){
		return this.logDeConsumo;
	}
	
	public Sensor getSensor() {
		return this.sensor;
	}
	
}
