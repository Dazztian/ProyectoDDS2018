package proyectoDDSs;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import java.util.*;

@Entity(name="Dispositivos_Permitidos")
public class DispositivoPermitido {

	@Id
	@GeneratedValue
	@Column(name="id",nullable=false)
	private Long id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="equipo")
	private String equipo;
	@Column(name="inteligente")
	private Boolean inteligente;
	@Column(name="bajoConsumo")
	private Boolean bajoConsumo;
	@Column(name="kwhConsumeXHora")
	private double kwhConsumeXHora;
	@Column(name="consumoMinimo")
	private double consumoMinimo;
	@Column(name="consumoMaximo")
	private double consumoMaximo;

	@OneToMany(mappedBy="dispositivo_permitido",cascade=CascadeType.ALL)
	private List<Dispositivo> dispositivos = new ArrayList<Dispositivo>();
	
	//Getters
	public Long getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public String getEquipo() {
		return equipo;
	}
	public Boolean getInteligente() {
		return inteligente;
	}
	public Boolean getBajoConsumo() {
		return bajoConsumo;
	}
	public double getKwhConsumeXHora() {
		return kwhConsumeXHora;
	}
	public double getConsumoMinimo() {
		return consumoMinimo;
	}
	public double getConsumoMaximo() {
		return consumoMaximo;
	}
	public List<Dispositivo> getDispositivos() {
		return dispositivos;
	}
	
	//Setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public void setInteligente(Boolean inteligente) {
		this.inteligente = inteligente;
	}
	public void setBajoConsumo(Boolean bajoConsumo) {
		this.bajoConsumo = bajoConsumo;
	}
	public void setKwhConsumeXHora(double kwhConsumeXHora) {
		this.kwhConsumeXHora = kwhConsumeXHora;
	}
	public void setConsumoMinimo(double consumoMinimo) {
		this.consumoMinimo = consumoMinimo;
	}
	public void setConsumoMaximo(double consumoMaximo) {
		this.consumoMaximo = consumoMaximo;
	}
	
	public String toString() {
		return this.nombre + " " + this.equipo + " " + this.inteligente + " " + this.bajoConsumo + 
				" " + this.kwhConsumeXHora + " " + this.consumoMinimo + " " + this.consumoMaximo;
	}
}
