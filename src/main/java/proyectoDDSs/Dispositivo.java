package proyectoDDSs;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Tipo_Dispositivo")
public abstract class Dispositivo {
	
	@Id
	@GeneratedValue
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="Dispositivo")
	public String nombre;
	@Column(name="equipo_concreto")
	public String equipo;
	@Column(name="es_inteligente")
	public Boolean inteligente;
	@Column(name="es_bajoConsumo")
	public Boolean bajoConsumo;
	@Column(name="kw_hora")
	protected double kwhConsumeXHora;
	@Column(name="consumo_minimo")
	public double consumoMinimo;
	@Column(name="consumo_maximo")
	public double consumoMaximo;
	
	public Dispositivo() {
	}
	
	
	public Dispositivo(String unNombre,String equipo ,double electricidadQConsume, double unConsumoMinimo, double unConsumoMaximo)
	{
		this.equipo=equipo;
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
	
	public void cambiarConsumo(double unConsumo) {
		kwhConsumeXHora = unConsumo;
	}
	
	public int getId() {
		return this.id;
	}
}
