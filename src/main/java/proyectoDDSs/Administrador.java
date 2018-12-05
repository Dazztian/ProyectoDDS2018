package proyectoDDSs;

import java.io.Console;
import java.time.*;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

import modelsPersistencia.DispositivoModel;
import modelsPersistencia.ModelHelperPersistencia;

@Entity
@DiscriminatorValue("A")
public class Administrador extends Usuario {
	
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido")
	private String apellido;
	@Column(name="fecha_alta")
	private LocalDateTime fechaCreacion;
	
	public Administrador() {
		
	}
	
	public Administrador(String unNombre, String unApellido, String usuario, String contrasenia) 
	{
		super(usuario, contrasenia);
		nombre = unNombre;
		apellido=unApellido;

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
		
		//Abm dispositivos
		public void altaDispositivo(Dispositivo unDispo) { 
			DispositivoModel dispoMod = new DispositivoModel();
			dispoMod.agregar(unDispo);
			dispoMod.cerrarEntityManager();
		}
		
		public void bajaDispositivo(Dispositivo unDispo) { 
			DispositivoModel dispoMod = new DispositivoModel();
			dispoMod.eliminar(unDispo);
			dispoMod.cerrarEntityManager();
		}
		public void modificacionDispositivo(Dispositivo unDispo) { 
			DispositivoModel dispoMod = new DispositivoModel();
			dispoMod.modificar(unDispo);
			dispoMod.cerrarEntityManager();
		}
		
		//Reportes
		public Double generarReportHogarXPeriodo(Cliente cliente, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {

			double resultado=0;
			ModelHelperPersistencia mh = new ModelHelperPersistencia();
			EntityManager em = mh.entityManager();
			
			Object resultadoQuery = 			
					em.createNativeQuery("select COALESCE(sum(consumo), 0)" +
									"from Usuarios u " +
										"join Dispositivos_Cliente dc on (u.id = dc.id_cliente) " +
										"join Consumos c on (c.id = dc.id_dispositivo) " +	
									"where u.id = " + cliente.getId() + " and c.fecha between '" + fechaInicio + "' and '" + fechaFinal + 
									"'"
									).getSingleResult();
			
			//System.out.println("Consumo total del hogar dado el periodo es de "+ resultadoQuery + " kw");
			
			return (Double) resultadoQuery;
	
		}
		
		public Double generarReportePromedioXPeriodo(LocalDateTime fechaInicial, LocalDateTime fechaFinal) {
			
			double resultado=0;
			String tipo;
			ModelHelperPersistencia mh = new ModelHelperPersistencia();
			EntityManager em = mh.entityManager();
			
			resultado = (Double)
					em.createNativeQuery("select COALESCE(avg(consumo), 0) PromedioDeConsumo\r\n" + 
							"	from Dispositivos_Cliente dc \r\n" + 
							"		join Consumos c on (dc.id = c.id_dispositivo)\r\n" + 
							"    where c.fecha between '" + fechaInicial + 
							"' and '" + fechaFinal + 
							"'").getSingleResult();
			
			/*for(Object[] resultadoSingular : resultadoQuery) {
				tipo = (String) resultadoSingular[0];
				resultado = (double) resultadoSingular[1];
				System.out.println("Promedio para " +tipo +" "+ resultado + " kw");
			}*/
			
			return resultado;
		}
		
		public Double generarReportTrafoXPeriodo(Transformador trafo, LocalDateTime fechaInicio, LocalDateTime fechaFinal) {

			double resultado=0;
			ModelHelperPersistencia mh = new ModelHelperPersistencia();
			EntityManager em = mh.entityManager();
			
			resultado=(Double) em.createNativeQuery("select COALESCE(sum(consumo), 0) \r\n" + 
					"	from Transformadores t join Usuarios u on (u.id_Transformador = t.id)\r\n" + 
					"		join Dispositivos_Cliente dc on (dc.id_cliente = u.id) \r\n" + 
					"        join Consumos c on (c.id_Dispositivo = dc.id)\r\n" + 
					"	where t.id = " + trafo.getId() + 
					" 			and c.Fecha between '" + fechaInicio + "' and '"+ 
								fechaFinal + "'").getSingleResult();
			//System.out.println("Consumo total del hogar dado el periodo es de "+ resultadoQuery + " kw");
			
			return resultado;
	
		}
		
}
				
	

