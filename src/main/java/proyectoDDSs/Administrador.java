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
		public long cantMesesAdmin()
		{
			LocalDateTime fechaActual=LocalDateTime.now();
			Duration duration = Duration.between(fechaActual, fechaCreacion);
		    //Diferencia en cantDeDias -- Lo divido por 30 para que tire los meses
			return (long)Math.abs(duration.toDays()/30); //Hago un casteo para que no me de nº con coma
		   
		}
		
		//Metodo auxiliar para poder realizar el testeo
		public long cantMesesAdmin(LocalDateTime fechaActual) {
			Duration duracion=Duration.between(fechaActual, fechaCreacion);
			return (long)Math.abs(duracion.toDays()/30);
		}
		
	
}
