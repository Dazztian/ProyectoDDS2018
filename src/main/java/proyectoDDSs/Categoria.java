package proyectoDDSs;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import json.*;

@Entity
@Table(name = "Categoria")
public class Categoria extends BeanToJson<Categoria>{
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name = "tipo")
	@Expose protected String tipo;
	
	@Column(name = "cargoFijo")
	@Expose protected double cargoFijo;
	
	@Column(name = "cargoAdicional")
	@Expose protected double cargoAdicional;
		
	//getters
	public double getCargoFijo() {
		return cargoFijo;
	}

	public double getCargoAdicional() {
		return cargoAdicional;
	}

	//Constructor
	public Categoria(String unTipo,double cargoFijo,double cargoAdicional) {
		this.tipo=unTipo;
		this.cargoFijo=cargoFijo;
		this.cargoAdicional=cargoAdicional;
	}
	@Override
	public Categoria getObj() {
		return this;
	}

}
