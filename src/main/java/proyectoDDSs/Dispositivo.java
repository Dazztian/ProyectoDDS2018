package proyectoDDSs;

public class Dispositivo {
	
	private String nombre;
	private boolean estado;
	protected long kwhConsumeXHora; 
	
	public Dispositivo(String unNombre, boolean unEstado,long electricidadQConsume)
	{
		estado= unEstado;
		nombre= unNombre;
		kwhConsumeXHora = electricidadQConsume;
	}
	protected boolean estaEncendido() {return this.estado;}
	protected int kwhConsumeXHora(){ if(estado) {return (int) this.kwhConsumeXHora; } else return 0;}

}
