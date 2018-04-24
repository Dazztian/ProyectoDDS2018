package proyectoDDSs;

public class Categoria {
	
	protected String tipo;
	protected double cargoFijo;
	protected double cargoAdicional;
		
	protected double CalcularMonto( double cargoFijo, double CargoVariable, double consumoMensual)
	{ return  (cargoFijo + (CargoVariable * consumoMensual)) ;}
		
	//Constructor
	public Categoria(String unTipo,double cargoFijo,double cargoAdicional) {
		this.tipo=unTipo;
		this.cargoFijo=cargoFijo;
		this.cargoAdicional=cargoAdicional;
	}
}
