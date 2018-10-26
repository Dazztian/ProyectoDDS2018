package proyectoDDSs;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("Estandar")
public class DispositivoEstandar extends Dispositivo {
	@Column(name="uso_diario")
	int usoDiarioEnHoras;

	public DispositivoEstandar() {}
	
	public DispositivoEstandar(String unNombre,String equipo, double electricidadQConsume, int unaCantidadDeHoras, double unConsumoMinimo, double unConsumoMaximo) {
		super(unNombre,equipo ,electricidadQConsume, unConsumoMinimo, unConsumoMaximo);
		usoDiarioEnHoras = unaCantidadDeHoras;
		this.inteligente=false;
	}

	public boolean esInteligente() {
		return this.inteligente;
	}
	
	public ModuloAdaptador adaptar() {
		return new ModuloAdaptador(nombre, kwhConsumeXHora, new Apagado(), this, this.consumoMinimo, this.consumoMaximo);
	}
	
	public double consumoDiario() {
		return kwhConsumeXHora * usoDiarioEnHoras;
	}

	public double consumoMensual() {
		return this.consumoDiario() * 30;
	}

	public boolean estaEncendido() {
		throw new Error();
	}
	
	public double consumoEnLasUltimasNHoras(int n) {
		if (n < usoDiarioEnHoras) {
			return kwhConsumeXHora * n;
		}
		else {
			return this.consumoDiario();
		}
	}
	
	public double consumoEnIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		int cantidadDeDias = ((fechaLimiteMaxima.getYear() - fechaLimiteMinima.getYear()) * 365) +
							 ((fechaLimiteMaxima.getMonthValue() - fechaLimiteMinima.getMonthValue()) * 30) +
							 (fechaLimiteMaxima.getDayOfMonth() - fechaLimiteMinima.getDayOfMonth());
		return this.consumoDiario() * cantidadDeDias;
				
	}
	
	public double consumoPromedioEnIntervalo(LocalDateTime fechaLimiteMaxima, LocalDateTime fechaLimiteMinima) {
		return kwhConsumeXHora;
	}
	
}