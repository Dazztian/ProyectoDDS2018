package proyectoDDSs;

import java.util.ArrayList;
import javax.persistence.*;
import org.junit.Test;

@Entity
@Table(name = "Actuador")
public class Actuador{
	
	@Id 
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	//@Column(name = "dispositivoSobreElQueActuo")
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="dispositivo_asignado")
	protected DispositivoInteligente dispositivoSobreElQActuo;
	
	@Column(name ="accion")
	protected String accion;
	
	//@Column(name = "traductor")
	@Transient
	protected Traductor adaptadorDeMsjsSegunFabricante;
	
	public void disparar(Estado unEstado) {
		dispositivoSobreElQActuo.cambiarEstado(unEstado);
	}

	public  Object traducir() { return adaptadorDeMsjsSegunFabricante.traduccion(accion); }

	public Actuador( String unaAccion) {accion = unaAccion;	}
	
	public Actuador(DispositivoInteligente unDispo, String unaAccion) 
	{
		dispositivoSobreElQActuo = unDispo;
		accion = unaAccion;		
		//adaptadorDeMsjsSegunFabricante = null;
	}
	
	public Actuador(DispositivoInteligente unDispo, String unaAccion, Traductor unTraductor) 
	{
		dispositivoSobreElQActuo = unDispo;
		accion = unaAccion;		
		adaptadorDeMsjsSegunFabricante = unTraductor;
	}
	
}
/*ejemplo de test
*@Test
*	public void tesTraductorDeMensajesAJSON() 
*	{
*		Traductor probandoTraductor = new TraductorDeMensajesAJSON();
*		Apagar probandoActuador = new Apagar( "apagar");
*		probandoActuador.traducir();
*	}
*/	
 