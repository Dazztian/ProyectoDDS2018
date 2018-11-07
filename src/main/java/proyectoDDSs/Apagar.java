package proyectoDDSs;

public class Apagar extends Actuador {
	
	public Apagar( String unaAccion){ super( unaAccion);}

	public Apagar(InteligenteXCliente dispositivo1, String accion, Traductor probandoTraductor) {
		super(dispositivo1,accion,probandoTraductor);
	}
	public Apagar (DispositivoInteligente unDispo, String unaAccion, Traductor unTraductor) {
		super(unDispo, unaAccion, unTraductor);
	}

	public void disparar() {this.apagar();}
	
	public void apagar() {	((DispositivoInteligente)dispositivoSobreElQActuo.getDispositivo()).apagar();	}
	
}
