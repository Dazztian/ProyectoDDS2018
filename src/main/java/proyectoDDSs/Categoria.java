package proyectoDDSs;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import json.*;

@Entity
@Table(name = "categoria")
public class Categoria extends BeanToJson<Categoria>{
	
	@Id
	@GeneratedValue
	private int id;

	@Column(name = "categoria")
	@Expose protected String categoria;
	
	@Column(name = "cargoFijo")
	@Expose protected double cargoFijo;
	
	@Column(name = "cargoAdicional")
	@Expose protected double cargoAdicional;
		
	//getters
	public String getcategoria() {
		return this.categoria;
	}
	
	public double getCargoFijo() {
		return cargoFijo;
	}

	public double getCargoAdicional() {
		return cargoAdicional;
	}

	//Constructor
	public Categoria() {
		
	}
	
	public Categoria(String uncategoria,double cargoFijo,double cargoAdicional) {
		this.categoria=uncategoria;
		this.cargoFijo=cargoFijo;
		this.cargoAdicional=cargoAdicional;
	}

	@Override
	public Categoria getObj() {
		return this;
	}

}
