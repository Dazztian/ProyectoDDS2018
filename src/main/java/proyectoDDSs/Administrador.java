package proyectoDDSs;

import java.time.*;
import java.util.*;

public class Administrador {
	
	private String nombre;
	private String apellido;
	private int id;
	private String nombreUsuario;
	private String contrasenia;
	private LocalDate fechaCreacion;
	
	public Administrador(String unNombre, String unApellido, int unId, String unNombreUsuario, String unaContrasenia,
			LocalDate unaFechaCreacion) 
	{
		nombre = unNombre;
		apellido=unApellido;
		id=unId;
		nombreUsuario=unNombreUsuario;
		contrasenia=unaContrasenia;
		fechaCreacion=unaFechaCreacion;
		
	}
		private long cantMesesAdmin()
		{
			LocalDateTime fechaActual=LocalDateTime.now();
			Duration duration = Duration.between(fechaActual, fechaCreacion);
		    //Diferencia en cantDeDias
			return Math.abs(duration.toDays());
		   
		}
				
	
}
