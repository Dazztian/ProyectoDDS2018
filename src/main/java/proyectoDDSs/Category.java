package proyectoDDSs;

import com.google.gson.annotations.Expose;

import json.*;

public class Category extends BeanToJson {


	@Expose protected String tipo;
	@Expose protected double cargoFijo;
	@Expose protected double cargoAdicional;
	
	@Override
	public Category getObj() {
		return this;
	}

}
