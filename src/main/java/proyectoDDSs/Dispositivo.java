package proyectoDDSs;

public class Dispositivo {
	
	private String nombre;
	private DispositivoEstado estado; 
	protected int kwhConsumeXHora;
	
	public   Dispositivo(String unNombre, DispositivoEstado unEstado,int UnkwhConsumeXHora)
	{
		//Le seteamos los datos del JSON
		estado= unEstado;
		nombre= unNombre;
		kwhConsumeXHora = UnkwhConsumeXHora;
	}
	
	protected boolean estaEncendido() {  return this.estado.estaEncendido(); }
	
	protected int kwhConsumeXHora()
	{ return this.estado.kwhConsumeXHora();	}
	
	


}
