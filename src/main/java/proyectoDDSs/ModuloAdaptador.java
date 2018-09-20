package proyectoDDSs;
import proyectoDDSs.DispositivoInteligente;

public class ModuloAdaptador extends DispositivoInteligente {
	
	public DispositivoEstandar dispositivoQueAdapta;

	public ModuloAdaptador(String unNombre, double electricidadQConsume, Estado unEstado, DispositivoEstandar unDispositivo, double unConsumoMinimo, double unConsumoMaximo) {
		super(unNombre, electricidadQConsume, unEstado, unConsumoMinimo, unConsumoMaximo);
		dispositivoQueAdapta = unDispositivo;
	}

}
