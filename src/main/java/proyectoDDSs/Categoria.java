package proyectoDDSs;

public class Categoria {
	
	protected String tipo;
	protected double cargoFijo;
	protected double cargoAdicional;
		
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
}
