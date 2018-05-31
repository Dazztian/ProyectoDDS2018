package proyectoDDSs;

public class Apagar extends Actuador {
	
	public void disparar() {
		this.apagar();
	}
	
	public void apagar() {
		dispositivoSobreElQActuo.apagar();
	}
}
