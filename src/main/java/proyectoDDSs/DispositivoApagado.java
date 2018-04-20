package proyectoDDSs;

public class DispositivoApagado implements DispositivoEstado {

	@Override
	public boolean estaEncendido() {return false;}
	@Override
	public int kwhConsumeXHora() {return 0;}

}
