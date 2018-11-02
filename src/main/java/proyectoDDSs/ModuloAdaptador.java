package proyectoDDSs;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import proyectoDDSs.DispositivoInteligente;

@Entity
@DiscriminatorValue("Adaptado")
public class ModuloAdaptador extends DispositivoInteligente {
	@Transient
	public DispositivoEstandar dispositivoQueAdapta;

	public ModuloAdaptador() {}
	
	public ModuloAdaptador(String unNombre,String equipo, double electricidadQConsume, Estado unEstado,
				DispositivoEstandar unDispositivo, double unConsumoMinimo, double unConsumoMaximo) {
		super(unNombre, equipo, electricidadQConsume, unEstado, unConsumoMinimo, unConsumoMaximo);
		dispositivoQueAdapta = unDispositivo;
	}

	public DispositivoEstandar getDispoQueAdapta() {
		return this.dispositivoQueAdapta;
	}

}
