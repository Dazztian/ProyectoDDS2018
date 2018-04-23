package proyectoDDSs;

public class EstadoApagado implements EstadoDispositivo {

	@Override
	public boolean estaEncendido() {return false;}
	@Override
	public int kwhConsumeXHora() {return 0;}

}
