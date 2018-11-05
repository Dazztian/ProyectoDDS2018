package proyectoDDSs;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.commons.math3.distribution.GeometricDistribution;

@Entity
@DiscriminatorValue("Modulo_Adaptador")
public class AdaptadoXCliente extends InteligenteXCliente{
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinColumn(name="dispositivo_adaptado")
	private DispositivoEstandar dispoAdaptado;

	public AdaptadoXCliente() {}
	
	public AdaptadoXCliente(Cliente cliente, ModuloAdaptador dispo, DispositivoEstandar estandar) {
		
		super(cliente,dispo);
		this.dispoAdaptado=estandar;
	
	}
	
	public DispositivoEstandar getDispoAdaptado() {
		return this.dispoAdaptado;
	}
	
	@Override
	public ModuloAdaptador getDispositivo() {		
		if(this.dispositivo instanceof ModuloAdaptador) {
			ModuloAdaptador dispo_inteligente = (ModuloAdaptador)this.dispositivo;
			return dispo_inteligente;
		}else
			return null;

	}
	
	
}
