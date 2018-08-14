package proyectoDDSs;

public abstract class Dispositivo {
	
	public String nombre;
	protected double kwhConsumeXHora;
	public Boolean inteligente;
	public Boolean bajoConsumo;
	public String equipo;
	
	
	public Dispositivo(String unNombre, double electricidadQConsume)
	{
		nombre= unNombre;
		kwhConsumeXHora = electricidadQConsume;
	}
	public abstract boolean esInteligente();
	public abstract double consumoMensual();
	public abstract boolean estaEncendido();
	
	public double kwhConsumeXHora() {
		return kwhConsumeXHora;
	}

}
