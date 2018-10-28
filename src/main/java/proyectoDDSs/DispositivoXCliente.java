package proyectoDDSs;

import java.util.Objects;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Tipo_Dispositivo")
@Table(name="Dispositivo_Cliente")
public abstract class DispositivoXCliente implements java.io.Serializable{
	
	@Id
	@GeneratedValue
	private Long Id_DispoCliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_cliente")
    private Cliente cliente;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_dispositivo")
    protected Dispositivo dispositivo;
    
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sensor_asignado")
	private Sensor sensor;
	
    public DispositivoXCliente() {}
    
    public DispositivoXCliente(Cliente cliente) {
        this.cliente=cliente;
    }
	
    public void setDispositivo(Dispositivo dispo) {
    	this.dispositivo=dispo;
    }
    
    public abstract Dispositivo getDispositivo();
    
    public void setCliente(Cliente cliente) {
    	this.cliente=cliente;
    }
    
    public Cliente getCliente() {
    	return this.cliente;
    }
    
	public Sensor getSensor() {
		return this.sensor;
	}
	
	public void setSensor(Sensor sensor) {
		this.sensor=sensor;
	}
	
	public Long getId() {
		return this.Id_DispoCliente;
	}
	
	public int hashCode() {
		return Objects.hash(cliente,dispositivo);
	}
	
}
