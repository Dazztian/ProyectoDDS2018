package proyectoDDSs;

public class Apagar extends Actuador {
	
	public Apagar( String unaAccion){ super( unaAccion);}

	public Apagar (DispositivoInteligente unDispo, String unaAccion, Traductor unTraductor) {
		super(unDispo, unaAccion, unTraductor);
	}

	public void disparar() {this.apagar();}
	
	public void apagar() {	dispositivoSobreElQActuo.apagar();	}
	
}
