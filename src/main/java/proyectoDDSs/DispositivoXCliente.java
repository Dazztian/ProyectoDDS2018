package proyectoDDSs;

import java.util.Objects;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
@Table(name="dispositivos_cliente")
public  class DispositivoXCliente implements java.io.Serializable{
	
	public DispositivoXCliente(int id, int id_cliente, int id_dispositivo) {
		super();
		this.id = id;
		this.id_cliente = id_cliente;
		this.id_dispositivo = id_dispositivo;
	}

	@Id
	@GeneratedValue
	@Column(name="id",nullable=false)
	private int id;
	@Column(name="id_cliente")
	private int id_cliente;
	@Column(name="id_dispositivo")
	private int id_dispositivo;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sensor_asignado")
	private Sensor sensor;
	
    public DispositivoXCliente() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_dispositivo() {
		return id_dispositivo;
	}

	public void setId_dispositivo(int id_dispositivo) {
		this.id_dispositivo = id_dispositivo;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	
}