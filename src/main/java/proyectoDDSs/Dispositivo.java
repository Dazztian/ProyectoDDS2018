package proyectoDDSs;

public class Dispositivo {
	
	private String nombre;
	//El estado debería ser una interfaz que según el mismo puede responder ciertos msj.
	private boolean estado; 
	protected int kwhConsumeXHora;
	
	//Constructor	
	public   Dispositivo(String unNombre, boolean unEstado,int UnkwhConsumeXHora)
	{
		estado= unEstado;
		nombre= unNombre;
		kwhConsumeXHora = UnkwhConsumeXHora;
	}
	
	protected boolean estaEncendido()
	{
		return estado;
	}
	
	
	//¿Como determinamos esto?
	protected int kwhConsumeXHora()
	{
		//Le agregue que si esta encendido que consuma lo que le pasamos por constructor y que si no esta encendido
		// que el consumo sea 0
		return this.estaEncendido()? kwhConsumeXHora : 0;
	}
	
	


}
