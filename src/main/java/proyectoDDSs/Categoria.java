package proyectoDDSs;

import proyectoDDSs.Cliente;

public abstract class Categoria {
	
	protected float cargoFijo;
	protected float cargoAdicional;
	
	
	protected float estimativoFacturacion(Cliente unCliente)
	{
	return cargoFijo + (cargoAdicional *1/*lo que consumió cada dispositivo*/) ;	
	}
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}
}

//Los demas casos de categorias van a hacer instancias de la original.

/*protected float estimativoFacturacion(Cliente unCliente)
{
	//A lo que consumio cada dispositivo x hora debo multiplicarle el cargoAdicional
return cargoFijo + (cargoAdicional *1/*lo que consumió cada dispositivo*/) ;	
}