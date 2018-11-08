package proyectoDDSs;

public class ModificarIntensidad extends Actuador {
	
	public ModificarIntensidad( String unaAccion){ super( unaAccion);}
	
	public ModificarIntensidad(DispositivoInteligente dispositivo1, String accion, Traductor probandoTraductor) {
		super(dispositivo1,accion,probandoTraductor);
		}


	public void disparar() {
		this.ModificarIntensidad();
	}
	
	public void ModificarIntensidad() {
		
	}
}
