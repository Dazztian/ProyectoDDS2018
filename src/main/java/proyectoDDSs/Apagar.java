package proyectoDDSs;

public class Apagar extends Actuador {
	
	public Apagar( String unaAccion){ super( unaAccion);}

	public Apagar(DispositivoInteligente dispositivo1, String accion, Traductor probandoTraductor) {
		super(dispositivo1,accion,probandoTraductor);
		}

	public void disparar() {this.apagar();}
	
	public void apagar() {	dispositivoSobreElQActuo.apagar();	}
	
}
