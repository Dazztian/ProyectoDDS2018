package proyectoDDSs;
import com.google.gson.annotations.Expose;

import json.*;


public class Categoria extends BeanToJson<Categoria>{
	
	@Expose protected String tipo;
	@Expose protected double cargoFijo;
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
