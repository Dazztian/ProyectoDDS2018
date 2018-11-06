package proyectoDDSs;

import java.time.*;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

import modelsPersistencia.ModelHelperPersistencia;

@Entity
@DiscriminatorValue("A")
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
			return (long)Math.abs(duration.toDays()/30); //Hago un casteo para que no me de n� con coma
		   
		}
		
		//Metodo auxiliar para poder realizar el testeo
		public long cantMesesAdmin(LocalDateTime fechaActual) {
			Duration duracion=Duration.between(fechaActual, fechaCreacion);
			return (long)Math.abs(duracion.toDays()/30);
		}
		
		public double generarReportHogarXPeriodo(Cliente cliente, LocalDateTime fechaInicial, LocalDateTime fechaFinal) {

			double resultado=0;
			ModelHelperPersistencia mh = new ModelHelperPersistencia();
			EntityManager em = mh.entityManager();
			
			List<Object[]> resultadoQuery = 
					em.createNativeQuery("select sum(consumo) suma " +
									"from usuarios u " +
										"join dispositivo_cliente dc on (u.id = dc.id_cliente) " +
										"join consumos c on (c.id_dispositivo = dc.Id_DispoCliente) " +	
									"where u.id = " + cliente.getId() + "And c.fecha between " + fechaInicial + " and " + fechaFinal + 
									" group by u.id"
									).getResultList();
			
			for(Object[] resultadoSingular : resultadoQuery) {
				resultado = (double) resultadoSingular[0];
			}
			return resultado;
		}
		
		public double generarReportePromedioXPeriodo(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
			
			double resultado=0;
			ModelHelperPersistencia mh = new ModelHelperPersistencia();
			EntityManager em = mh.entityManager();
			
			List<Object[]> resultadoQuery = 
					em.createNativeQuery("select dc.Tipo_Dispositivo, avg(consumo) PromedioDeConsumo\r\n" + 
							"	from dispositivo_cliente dc \r\n" + 
							"		join consumos c on (dc.Id_DispoCliente = c.id_dispositivo)\r\n" + 
							"    where c.fecha between " + fechaInicial + 
							" and " + fechaFinal + 
							"    group by dc.Tipo_Dispositivo").getResultList();
			
			for(Object[] resultadoSingular : resultadoQuery) {
				resultado = (double) resultadoSingular[0];
			}
			return resultado;
		}
}
				
	

