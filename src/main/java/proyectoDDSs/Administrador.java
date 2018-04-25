package proyectoDDSs;

import java.time.*;
import java.util.*;

public class Administrador {
	
	private String nombre;
	private String apellido;
	private int id;
	private String nombreUsuario;
	private String contrasenia;
	private LocalDateTime fechaCreacion;
	
	public Administrador(String unNombre, String unApellido, int unId, String unNombreUsuario, String unaContrasenia) 
	{
		nombre = unNombre;
		apellido=unApellido;
		id=unId;
		nombreUsuario=unNombreUsuario;
		contrasenia=unaContrasenia;
		fechaCreacion=LocalDateTime.now();
		
	}
		private long cantMesesAdmin()
		{
			LocalDateTime fechaActual=LocalDateTime.now();
			Duration duration = Duration.between(fechaActual, fechaCreacion);
		    //Diferencia en cantDeDias -- Lo divido por 30 para que tire los meses
			return Math.abs(duration.toDays()/30);
		   
		}
				
	
}
