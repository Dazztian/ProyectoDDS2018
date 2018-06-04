package proyectoDDSs;

public class Prender extends Actuador {
	
	public Prender( String unaAccion){ super( unaAccion);}

	public void disparar() {
		this.prender();
	}
	
	public void prender() {
		dispositivoSobreElQActuo.prender();
	}
}
