
package proyectoDDSs;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import json.BeanToJson;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Tipo")
@Table(name = "Dispositivos")
public abstract class Dispositivo extends BeanToJson<Dispositivo> implements Serializable{
	
	@Id
	@GeneratedValue
	@Column(name="id",nullable=false)
	private Long id;
	@Column(name="nombre")
	public String nombre;
	@Column(name="equipo")
	public String equipo;
	@Column(name="inteligente")
	public Boolean inteligente;
	@Column(name="bajoConsumo")
	public Boolean bajoConsumo;
	@Column(name="kwhConsumeXHora")
	protected double kwhConsumeXHora;
	@Column(name="consumoMinimo")
	public double consumoMinimo;
	@Column(name="consumoMaximo")
	public double consumoMaximo;
	
	@ManyToOne
    @JoinColumn(name="id_cliente")
    protected Cliente cliente;
 
    @ManyToOne
    @JoinColumn(name="id_dispositivo")
    protected DispositivoPermitido dispositivo_permitido;
	
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
	
	public Long getId() {
		return this.id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEquipo() {
		return equipo;
	}


	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}


	public Boolean getInteligente() {
		return inteligente;
	}


	public void setInteligente(Boolean inteligente) {
		this.inteligente = inteligente;
	}


	public Boolean getBajoConsumo() {
		return bajoConsumo;
	}


	public void setBajoConsumo(Boolean bajoConsumo) {
		this.bajoConsumo = bajoConsumo;
	}


	public double getKwhConsumeXHora() {
		return kwhConsumeXHora;
	}


	public void setKwhConsumeXHora(double kwhConsumeXHora) {
		this.kwhConsumeXHora = kwhConsumeXHora;
	}


	public double getConsumoMinimo() {
		return consumoMinimo;
	}


	public void setConsumoMinimo(double consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}


	public double getConsumoMaximo() {
		return consumoMaximo;
	}


	public void setConsumoMaximo(double consumoMaximo) {
		this.consumoMaximo = consumoMaximo;
	}


	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public Dispositivo getObj() {
		return this;
	}
	
	public void setCliente(Cliente c) {
		this.cliente=c;
	}
	
	public void setDispositivo(DispositivoPermitido d) {
		this.dispositivo_permitido=d;
	}
	
	public int hashCode() {
		return Objects.hash(cliente,dispositivo_permitido);
	}
}