package proyectoDDSs;

public class Encendido implements Estado {

	public boolean estadoEncendido() {
		return true;
	}

	public double coeficienteDeConsumo() {
		return 1;
	}

}
