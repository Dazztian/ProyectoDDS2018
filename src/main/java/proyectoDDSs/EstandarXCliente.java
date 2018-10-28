package proyectoDDSs;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="DispoEstandar_Cliente")
public class EstandarXCliente extends DispositivoXCliente{

	@Column(name="Uso_Diario")
	private int usoDiario;
	
	public EstandarXCliente() {}
	
	public EstandarXCliente(Cliente cliente, DispositivoEstandar dispo) {
		
		super(cliente);
		this.setDispositivo(dispo);
		usoDiario=dispo.getUsoDiario();
		
	}

	@Override
	public DispositivoEstandar getDispositivo() {
		if(this.dispositivo instanceof DispositivoEstandar) {
			DispositivoEstandar dispo_estandar = (DispositivoEstandar)this.dispositivo;
			return dispo_estandar;
		}
		else 
			return null;
	}
	
}
