package proyectoDDSs;

public class AhorroDeEnergia implements Estado {

	public boolean estadoEncendido() {
		return true;
	}

	public double coeficienteDeConsumo() {
		return 0.5;
	}

}
