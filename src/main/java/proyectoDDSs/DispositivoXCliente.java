package proyectoDDSs;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Dispositivo_Cliente")
@AssociationOverrides({
		@AssociationOverride(name = "pk.cliente", 
			joinColumns = @JoinColumn(name = "CLIENTE_ID")),
		@AssociationOverride(name = "pk.dispositivo", 
			joinColumns = @JoinColumn(name = "DISPOSITIVO_ID")) })
public class DispositivoXCliente implements java.io.Serializable{
	
	private DispositivoXClienteId pk=new DispositivoXClienteId();
	private Sensor sensor;
	
	@EmbeddedId
	public DispositivoXClienteId getPk() {
		return this.pk;
	}
	
	public void setPk(DispositivoXClienteId pk) {
		this.pk=pk;
	}
	
	@Transient
	public Dispositivo getDispositivo() {
		return getPk().getDispositivo();
	}

	public void setDispositivo(Dispositivo dispo) {
		getPk().setDispositivo(dispo);
	}

	@Transient
	public Cliente getCliente() {
		return getPk().getCliente();
	}

	public void setCliente(Cliente cliente) {
		getPk().setCliente(cliente);
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sensor_asignado")
	public Sensor getSensor() {
		return this.sensor;
	}
	
	public void setSensor(Sensor sensor) {
		this.sensor=sensor;
	}
	
	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}
	
}
