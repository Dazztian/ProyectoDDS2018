package proyectoDDSs;

public abstract class Dispositivo {
	
	public String nombre;
	//private boolean estado;
	protected double kwhConsumeXHora; 
	
	public Dispositivo(String unNombre, double electricidadQConsume)
	{
		//estado= unEstado;
		nombre= unNombre;
		kwhConsumeXHora = electricidadQConsume;
	}
	public abstract boolean esInteligente();
	public abstract double consumoMensual();
	public abstract boolean estaEncendido();
	
	public double kwhConsumeXHora() {
		return kwhConsumeXHora;
	}
	//public void setEstado(boolean unEstado) {estado = unEstado;};
	//protected boolean estaEncendido() {return this.estado;}
	//public int kwhConsumeXHora(){ if(estado) {return (int) this.kwhConsumeXHora; } else return 0;}

}
