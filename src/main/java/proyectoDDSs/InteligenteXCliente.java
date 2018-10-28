package proyectoDDSs;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name="DispoInteligente_Cliente")
public class InteligenteXCliente extends DispositivoXCliente {
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
	@JoinColumn(name="id_Dispositivo",nullable=false)
	private List<Log> logs=new ArrayList<Log>();
	
	public InteligenteXCliente() {}
	
	public InteligenteXCliente(Cliente cliente, DispositivoInteligente dispo) {
		super(cliente);
		this.setDispositivo(dispo);
		dispo.getLogs().addAll(logs);
	}

	@Override
	public DispositivoInteligente getDispositivo() {
		if(this.dispositivo instanceof DispositivoInteligente) {
			DispositivoInteligente dispo_inteligente = (DispositivoInteligente)this.dispositivo;
			return dispo_inteligente;
		}else
			return null;
	}

	
	
}
