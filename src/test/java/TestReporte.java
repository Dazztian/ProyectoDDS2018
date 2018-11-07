import modelsPersistencia.CategoriaModel;
import modelsPersistencia.ClienteModel;
import proyectoDDSs.Administrador;
import proyectoDDSs.Cliente;

public class TestReporte {

	public static void main(String[] args) {

		ClienteModel clienteHelper = new ClienteModel();
		
		Administrador admin = new Administrador("Alfredo","Alfredez",1,"usuario","ycontrasenia");
		
		Cliente roberto = clienteHelper.buscarCliente(new Long (1));
	
		admin.generarReportHogarXPeriodo(roberto, "1/1/2018" , "4/1/2018");
		admin.generarReportePromedioXPeriodo("1/1/2018" , "4/1/2018");

	}

}
