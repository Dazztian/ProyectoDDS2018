package proyectoDDSs;

public class DispositivoEstandar extends Dispositivo {
	
	int usoDiarioEnHoras;

	public DispositivoEstandar(String unNombre, long electricidadQConsume, int unaCantidadDeHoras) {
		super(unNombre ,electricidadQConsume);
		usoDiarioEnHoras = unaCantidadDeHoras;
	}

	public boolean esInteligente() {
		return false;
	}
	
	public ModuloAdaptador adaptar() {
		return new ModuloAdaptador(nombre, kwhConsumeXHora, new Apagado(), this);
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

}
