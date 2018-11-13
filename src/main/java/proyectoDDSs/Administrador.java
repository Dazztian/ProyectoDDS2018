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
		public Double generarReportHogarXPeriodo(Cliente cliente, String fechaInicio, String fechaFinal) {

			double resultado=0;
			ModelHelperPersistencia mh = new ModelHelperPersistencia();
			EntityManager em = mh.entityManager();
			
			Object resultadoQuery = 			
					em.createNativeQuery("select sum(consumo) suma " +
									"from usuarios u " +
										"join dispositivo_cliente dc on (u.id = dc.id_cliente) " +
										"join consumos c on (c.id_dispositivo = dc.id_dispositivo) " +	
									"where u.id = " + cliente.getId() + " and c.fecha between " + fechaInicio + " and " + fechaFinal + 
									" group by u.id"
									).getSingleResult();
			
			//System.out.println("Consumo total del hogar dado el periodo es de "+ resultadoQuery + " kw");
			
			return (Double) resultadoQuery;
	
		}
		
		public List<Object[]> generarReportePromedioXPeriodo(String fechaInicial, String fechaFinal) {
			
			double resultado=0;
			String tipo;
			ModelHelperPersistencia mh = new ModelHelperPersistencia();
			EntityManager em = mh.entityManager();
			
			List<Object[]> resultadoQuery = 
					em.createNativeQuery("select dc.tipo, avg(consumo) PromedioDeConsumo\r\n" + 
							"	from dispositivo_cliente dc \r\n" + 
							"		join consumos c on (dc.id_dispositivo = c.id_dispositivo)\r\n" + 
							"    where c.fecha between " + fechaInicial + 
							" and " + fechaFinal + 
							"    group by dc.tipo").getResultList();
			
			for(Object[] resultadoSingular : resultadoQuery) {
				tipo = (String) resultadoSingular[0];
				resultado = (double) resultadoSingular[1];
				System.out.println("Promedio para " +tipo +" "+ resultado + " kw");
			}
			
			return resultadoQuery;
		}
}
				
	

