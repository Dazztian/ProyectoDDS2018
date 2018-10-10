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
import javax.persistence.Table;
import javax.persistence.Transient;

import java.time.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TipoDispositivo")
@DiscriminatorValue("Inteligente")
@Table(name="dispositivos_inteligentes")
public class DispositivoInteligente extends Dispositivo {
	
	//private Estado estado;
	@Transient
	private Sensor sensor;
	@Transient
	private Timer temporizador;
	@Column(name="magnitud")
	private Double magnitud;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
	@JoinColumn(name="id_Dispositivo",nullable=false)
	public List<Estado> estados = new LinkedList<Estado>();
	@Transient
	int intervalo=100;
	
	public DispositivoInteligente(String unNombre, double electricidadQConsume, Estado unEstado, double unConsumoMinimo, double unConsumoMaximo) {
		
		super(unNombre, electricidadQConsume, unConsumoMinimo, unConsumoMaximo);
		estados.add(unEstado);	
		temporizador=new Timer();
		
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
		return true;
	}
	
	public boolean estaEncendido(){
		return ((LinkedList<Estado>) estados).getFirst().estadoEncendido();
	}
	
	public Estado getEstadoActual () {
		return ((LinkedList<Estado>) this.estados).getFirst();
	}
	
	public void cambiarEstado(Estado unEstado) {
		Estado aux = ((LinkedList<Estado>) estados).removeFirst();
		aux.finalizarEstado();
		((LinkedList<Estado>) estados).addFirst(aux);
		((LinkedList<Estado>) estados).addFirst(unEstado);
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
		return kwhConsumeXHora * ((LinkedList<Estado>) estados).getFirst().coeficienteDeConsumo();
	}
	
/*	public void guardarConsumo() {
		Log nuevoLog = new Log(this.consumoPorHora());
		logDeConsumo.addFirst(nuevoLog);
	}*/
	
	public double consumoEnLasUltimasNHoras(int n) {
		return estados.stream().filter(estado -> estado.fechaDeInicio.isAfter(LocalDateTime.now().minusHours(n)))
				.mapToDouble(estado -> estado.coeficienteConHorasActivas())
				.sum() * this.kwhConsumeXHora();
	}
	
	public double consumoEnIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		return estados.stream().filter(estado -> estado.ocurreEntre(fechaLimiteMaxima, fechaLimiteMinima))
				.mapToDouble(estado -> estado.coeficienteConHorasActivas())
				.sum() * this.kwhConsumeXHora();
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
		estados.stream().filter(estado -> estado.ocurreEntre(fechaLimiteMaxima, fechaLimiteMinima))
		.filter(estado -> estado.estadoEncendido()).forEach(estado -> estado.mostrarPeriodoPorConsola());		
	}
	
	public void intervalosEncendidosEnElUltimoMes() {
		LocalDateTime aux = LocalDateTime.now().minusMonths(1);
		LocalDateTime fechaLimiteMaxima = LocalDateTime.of(aux.getYear(), aux.getMonthValue(), 1, 0, 0);
		LocalDateTime fechaLimiteMinima = LocalDateTime.of(aux.getYear(), aux.getMonthValue(), 30, 0, 0);
		this.intervalosEncendidoEnPeriodo(fechaLimiteMaxima, fechaLimiteMinima);
	}
	
}
