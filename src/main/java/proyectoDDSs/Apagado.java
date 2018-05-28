package proyectoDDSs;

public class Apagado implements Estado {

	public boolean estadoEncendido() {
		return false;
	}

	public double coeficienteDeConsumo() {
		return 0.01;
	}

}
