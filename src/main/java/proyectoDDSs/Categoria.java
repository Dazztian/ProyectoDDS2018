package proyectoDDSs;

public class Categoria {
	
	protected double cargoFijo;
	protected double cargoAdicional;
		
	protected double CalcularMonto( double cargoFijo, double CargoVariable, double consumoMensual)
	{ return  (cargoFijo + (CargoVariable * consumoMensual)) ;}
		
	//Constructor
	public Categoria(double cargoFijo,double cargoAdicional) {
		this.cargoFijo=cargoFijo;
		this.cargoAdicional=cargoAdicional;
	}
}
