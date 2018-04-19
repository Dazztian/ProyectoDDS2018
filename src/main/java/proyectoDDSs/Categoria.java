package proyectoDDSs;

public class Categoria {
	
	protected float cargoFijo;
	protected float cargoAdicional;
	
	public Categoria(float cargoFijo,float cargoAdicional) {
		this.cargoFijo=cargoFijo;
		this.cargoAdicional=cargoAdicional;
	}
	
	protected float estimativoFacturacion(Cliente unCliente){
		return cargoFijo + (cargoAdicional*unCliente.consumoMensual()/*lo que consumió cada dispositivo*/) ;	
	}
	
	
}

//Los demas casos de categorias van a hacer instancias de la original.

/*protected float estimativoFacturacion(Cliente unCliente)
{
	//A lo que consumio cada dispositivo x hora debo multiplicarle el cargoAdicional
return cargoFijo + (cargoAdicional *1/*lo que consumió cada dispositivo) ;	
}*/