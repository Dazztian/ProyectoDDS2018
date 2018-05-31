package proyectoDDSs;

public class Prender extends Actuador {
	
	public void disparar() {
		this.prender();
	}
	
	public void prender() {
		dispositivoSobreElQActuo.prender();
	}
}
