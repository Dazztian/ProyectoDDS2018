package proyectoDDSs;

public class EstadoEncendido implements EstadoDispositivo {

	@Override
	public boolean estaEncendido() {return true;}

	@Override
	//Ac� le cargamos lo que diga el JSON
	public int kwhConsumeXHora() {
		return 0;
	}

}
