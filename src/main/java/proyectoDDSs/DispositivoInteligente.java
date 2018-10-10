package proyectoDDSs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.time.*;

public class DispositivoInteligente extends Dispositivo {
	
	//private Estado estado;
	private Sensor sensor;
	private Timer temporizador;
	public LinkedList<Estado> estados = new LinkedList<Estado>();
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
		return estados.getFirst().estadoEncendido();
	}
	
	public Estado getEstadoActual () {
		return this.estados.getFirst();
	}
	
	public void cambiarEstado(Estado unEstado) {
		Estado aux = estados.removeFirst();
		aux.finalizarEstado();
		estados.addFirst(aux);
		estados.addFirst(unEstado);
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
		return kwhConsumeXHora * estados.getFirst().coeficienteDeConsumo();
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
