package proyectoDDSs;

public class DispositivoEncendido implements DispositivoEstado {

	@Override
	public boolean estaEncendido() {return true;}

	@Override
	//Acá le cargamos lo que diga el JSON
	public int kwhConsumeXHora() {
		return 0;
	}

}
