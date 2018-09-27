package proyectoDDSs;

import java.time.LocalDateTime;

public abstract class Dispositivo {
	
	public String nombre;
	protected double kwhConsumeXHora;
	public Boolean inteligente;
	public Boolean bajoConsumo;
	public String equipo;
	public double consumoMinimo;
	public double consumoMaximo;
	
	
	public Dispositivo(String unNombre, double electricidadQConsume, double unConsumoMinimo, double unConsumoMaximo)
	{
		nombre= unNombre;
		kwhConsumeXHora = electricidadQConsume;
		consumoMinimo = unConsumoMinimo;
		consumoMaximo = unConsumoMaximo;
	}
	public abstract boolean esInteligente();
	public abstract double consumoMensual();
	public abstract boolean estaEncendido();
	public abstract double consumoEnLasUltimasNHoras(int n);
	public abstract double consumoEnIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima);
	public abstract double consumoPromedioEnIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima);
	
	public double kwhConsumeXHora() {
		return kwhConsumeXHora;
	}
	
	public boolean noEsHeladera() {
		return (!this.nombre.contains("heladera"));
	}
}
