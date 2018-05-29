package proyectoDDSs;

public class ModuloAdaptador extends DispositivoInteligente {
	
	public DispositivoEstandar dispositivoQueAdapta;

	public ModuloAdaptador(String unNombre, double electricidadQConsume, Estado unEstado, DispositivoEstandar unDispositivo) {
		super(unNombre, electricidadQConsume, unEstado);
		dispositivoQueAdapta = unDispositivo;
	}

}
