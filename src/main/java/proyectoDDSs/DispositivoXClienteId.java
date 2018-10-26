package proyectoDDSs;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import proyectoDDSs.*;

@Embeddable
public class DispositivoXClienteId implements java.io.Serializable {

	private Dispositivo dispositivo;
	private Cliente cliente;
	
	@ManyToOne
	public Dispositivo getDispositivo() {
		return this.dispositivo;
	}

	public void setDispositivo(Dispositivo dispo) {
		this.dispositivo=dispo;
	}
	
	@ManyToOne
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente=cliente;
	}

	public int hashCode() {
        int result;
        result = (cliente != null ? cliente.hashCode() : 0);
        result = 31 * result + (dispositivo != null ? dispositivo.hashCode() : 0);
        return result;
    }
	
}
