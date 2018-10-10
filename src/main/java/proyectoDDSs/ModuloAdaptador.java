package proyectoDDSs;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import proyectoDDSs.DispositivoInteligente;


@Entity(name="modulos_adaptadores")
public class ModuloAdaptador extends DispositivoInteligente {
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="dispositivo_adaptado")
	public DispositivoEstandar dispositivoQueAdapta;

	public ModuloAdaptador(String unNombre, double electricidadQConsume, Estado unEstado, DispositivoEstandar unDispositivo, double unConsumoMinimo, double unConsumoMaximo) {
		super(unNombre, electricidadQConsume, unEstado, unConsumoMinimo, unConsumoMaximo);
		dispositivoQueAdapta = unDispositivo;
	}

}
