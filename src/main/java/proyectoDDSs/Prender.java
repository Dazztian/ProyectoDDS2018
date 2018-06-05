package proyectoDDSs;

public class Prender extends Actuador {
	
	public Prender( String unaAccion){ super( unaAccion);}
	
	public Prender(DispositivoInteligente dispositivo1, String accion, Traductor probandoTraductor) {
		super(dispositivo1,accion,probandoTraductor);
		}


	public void disparar() {
		this.prender();
	}
	
	public void prender() {
		dispositivoSobreElQActuo.prender();
	}
}
