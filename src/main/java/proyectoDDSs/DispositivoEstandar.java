package proyectoDDSs;

public class DispositivoEstandar extends Dispositivo {
	
	int usoDiarioEnHoras;

	public DispositivoEstandar(String unNombre, double electricidadQConsume, int unaCantidadDeHoras, double unConsumoMinimo, double unConsumoMaximo) {
		super(unNombre ,electricidadQConsume, unConsumoMinimo, unConsumoMaximo);
		usoDiarioEnHoras = unaCantidadDeHoras;
	}

	public boolean esInteligente() {
		return false;
	}
	
	public ModuloAdaptador adaptar() {
		return new ModuloAdaptador(nombre, kwhConsumeXHora, new Apagado(), this, this.consumoMinimo, this.consumoMaximo);
	}
	
	public double consumoDiario() {
		return kwhConsumeXHora * usoDiarioEnHoras;
	}

	public double consumoMensual() {
		return this.consumoDiario() * 30;
	}

	public boolean estaEncendido() {
		throw new Error();
	}
	
	public double consumoEnLasUltimasNHoras(int n) {return kwhConsumeXHora * n;}

}
