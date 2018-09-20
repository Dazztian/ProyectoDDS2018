package proyectoDDSs;

import java.util.ArrayList;

import org.junit.Test;

public abstract class Actuador{
	
	protected DispositivoInteligente dispositivoSobreElQActuo; 
	protected String accion;
	protected Traductor traductor;
	
	public abstract void disparar();

	public  Object traducir() { return traductor.traduccion(accion); }

	public Actuador( String unaAccion) {accion = unaAccion;	}
	public Actuador(DispositivoInteligente unDispo, String unaAccion, Traductor unTraductor) 
	{
		dispositivoSobreElQActuo = unDispo;
		accion = unaAccion;		
		traductor = unTraductor;
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
 