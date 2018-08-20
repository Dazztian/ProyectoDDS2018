package proyectoDDSs;

public abstract class Dispositivo {
	
	public String nombre;
	protected double kwhConsumeXHora;
	public Boolean inteligente;
	public Boolean bajoConsumo;
	public String equipo;
	public double consumoMinimo;
	public double consumoMaximo;
	
	
	public Dispositivo(String unNombre, double electricidadQConsume, double unConsumoMinimo, double unConsumoMaximo)
	{
		nombre= unNombre;
		kwhConsumeXHora = electricidadQConsume;
		consumoMinimo = unConsumoMinimo;
		consumoMaximo = unConsumoMaximo;
	}
	public abstract boolean esInteligente();
	public abstract double consumoMensual();
	public abstract boolean estaEncendido();
	
	public double kwhConsumeXHora() {
		return kwhConsumeXHora;
	}

}
