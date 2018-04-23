package proyectoDDSs;

public class Dispositivo {
	
	private String nombre;
	private EstadoDispositivo estado; 
	protected int kwhConsumeXHora; //cheuqear que onda si es un valor fijo
	
	public Dispositivo(String unNombre, EstadoDispositivo unEstado,int electricidadQConsume)
	{
		//Le seteamos los datos del JSON
		estado= unEstado;
		nombre= unNombre;
		kwhConsumeXHora = electricidadQConsume;
	}
	
	protected boolean estaEncendido() {  return this.estado.estaEncendido(); }
	
	protected int kwhConsumeXHora()
	{ return this.estado.kwhConsumeXHora();	}
	
	


}
