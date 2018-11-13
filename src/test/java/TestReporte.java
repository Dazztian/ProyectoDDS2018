import modelsPersistencia.CategoriaModel;
import modelsPersistencia.ClienteModel;
import proyectoDDSs.Administrador;
import proyectoDDSs.Cliente;

public class TestReporte {
	// para poder correr esto primero hay que correr el scriptContextoReportes.txt desde mySql
	public static void main(String[] args) {
		
		ClienteModel clienteHelper = new ClienteModel();
		
		Administrador admin = new Administrador("Alfredo","Alfredez","usuario","ycontrasenia");
		
		Cliente roberto = clienteHelper.buscarCliente(new Long (1));
		Cliente roberto2 = clienteHelper.buscarCliente(new Long(2));
		
		System.out.println("Consumo de "+roberto.getNombre()+" es "+ 
								admin.generarReportHogarXPeriodo(roberto, "1/1/2018" , "4/1/2018"));
		
		System.out.println("Consumo de "+roberto2.getNombre()+" es "+ 
				admin.generarReportHogarXPeriodo(roberto2, "1/1/2018" , "4/1/2018"));

		
		admin.generarReportePromedioXPeriodo("1/1/2018" , "4/1/2018");

	}

}
