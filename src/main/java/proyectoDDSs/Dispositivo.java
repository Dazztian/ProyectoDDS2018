package proyectoDDSs;

public class Dispositivo {
	
	private String nombre;
	//El estado debería ser una interfaz que según el mismo puede responder ciertos msj.
	private boolean estado;
	protected int kwhConsumeXHora;
	
	protected boolean estaEncendido()
	{
		return estado;
	}
	
	//¿Como determinamos esto?
	protected int  kwhConsumeXHora()
	{
		return kwhConsumeXHora;
	}
	
	
	//Constructor	
	public   Dispositivo(String unNombre, boolean unEstado,int UnkwhConsumeXHora)
	{
		estado= unEstado;
		nombre= unNombre;
		kwhConsumeXHora = kwhConsumeXHora;
	}

}
