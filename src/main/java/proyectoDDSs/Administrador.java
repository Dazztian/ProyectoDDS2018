package proyectoDDSs;

import java.time.*;

public class Administrador {
	
	private String nombre;
	private String apellido;
	private int id;
	private String nombreUsuario;
	private String contrasenia;
	
	//A la fecha de creacion en el constructor le "resto" lo de abajo.
	private LocalDateTime fechaActual = LocalDateTime.now( );
	
		private int cantMesesAdmin()
		{
			return 1;
		}
}
