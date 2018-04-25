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
	public void setEstado(boolean unEstado) {estado = unEstado;};
	protected boolean estaEncendido() {return this.estado;}
	public int kwhConsumeXHora(){ if(estado) {return (int) this.kwhConsumeXHora; } else return 0;}

}
