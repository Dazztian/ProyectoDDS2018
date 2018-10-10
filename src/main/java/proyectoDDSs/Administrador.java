package proyectoDDSs;

import java.time.*;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Administradores")
public class Administrador extends Usuario {
	
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	@Column(name="id_admin")
	private int id_admin;
	@Column(name="fecha_alta")
	private LocalDateTime fechaCreacion;
	
	
	public Administrador(String unNombre, String unApellido, int unId, String usuario, String contrasenia) 
	{
		super(usuario, contrasenia);
		nombre = unNombre;
		apellido=unApellido;
		id_admin=unId;
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
