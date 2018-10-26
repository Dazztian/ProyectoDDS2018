import java.text.ParseException;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import modelsPersistencia.*;
import proyectoDDSs.*;
import java.util.*;

public class TestDB {

	public static void main(String[] args) throws ParseException {
		
		EntityManager e = ModelHelperPersistencia.getEntityManager();
		
		ClienteModel m = new ClienteModel();
		
		DispositivoModel d = new DispositivoModel();
		
//		Cliente lucas=new Cliente("Lucas","Resa","dni",40190642,1140256921,"Yrigoyen",
//				new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),
//				new Categoria("R1",18.56,0.86), -1.542, 7.1245, "pepe10", "pepe");
//
//		lucas.addDispositivo(new DispositivoInteligente("Heladera",2,new Apagado(),1,3));
//		lucas.addDispositivo(new DispositivoInteligente("Smart TV",1,new Apagado(),1,3));
//		lucas.addDispositivo(new DispositivoEstandar("Plancha",5,1,2,8));
//
//		m.agregar(lucas);
//		
//		
		
		DispositivoEstandar copiaDispo = (DispositivoEstandar) d.buscarDispositivo(3);
		
		Cliente lucasCopy = m.buscarCliente(1);
		
		lucasCopy.adaptarDispositivo(copiaDispo);
		
		m.modificar(lucasCopy);
		
//		List<ZonaGeografica> zonas = new ArrayList<>();
//		
//		zonas.add(new ZonaGeografica(3,"zona1",-3.11,-2.21));
//		
//		Transformador trafo1 = new Transformador(1, 1.1, 2.2, 3);
//		
//		trafo1.asignarZona(zonas);
//		
//		//Persisto la zona junto con su trafo
//		m.agregar(zonas.get(0));
//		
//		ZonaGeografica zona1=m.buscarZonaGeografica(3);
//		
//		System.out.println(zona1.getNombre()+" id: "+zona1.getId()+" lat: "+zona1.getLatitud()+" longitud: "+zona1.getLongitud()+
//				" trafos: "+zona1.getTrafos().size());
		
//		zonas.add(zona1);
//		
//		trafo1.asignarZona(zonas);
//		
//		zonas.forEach(zona->m.agregar(zona));
//		
//		zona1 = m.buscarZonaGeografica(3);
//		
//		
		
		//System.out.println(zona1.getTrafos().size());
		
//		DispositivoInteligente dispositivo1 = new DispositivoInteligente("Heladera",50, new Encendido(), 0.0, 0.0);
//		
//		m.agregar(dispositivo1);
//		
//		Actuador actuador = new Actuador(dispositivo1, "mensaje:apagar");
//		Regla regla = new Regla(30.0);
//		regla.agregarActuador(actuador);
//		Sensor sensor = new Sensor();
//		sensor.agregarRegla(regla);
//		
//		m.agregar(sensor);
		
//		Dispositivo d = new DispositivoEstandar("heladera", 312, 1, 10, 20);
//		
//		m.agregar(d);
		
//		Categoria cat1 = new Categoria("R1",18.56,0.86);
//
//		Cliente c=new Cliente("pepe","gonzales","dni",4012939,40239401,"Yrigoyen",
//				new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),
//				cat1, -1.542, 7.1245, "pepe10", "pepe");
//		
//		m.agregar(c);

		
//		Apagado estado1 = new Apagado();
//		m.agregar(cat1);
//		m.agregar(estado1);
//		
//		ZonaGeograficaModel modelZona = new ZonaGeograficaModel();
//		
//		List<Transformador> trafos = new ArrayList<>();
//		
//		List<ZonaGeografica> zonas;
//		
//		ZonaGeografica zona = new ZonaGeografica(2, "zona2", 1.1321, 3.123);
		
//		zonas.add(zona);
		
//		zonas =  modelZona.buscarTodasLasZonaGeografica();
		
//		trafos.add(new Transformador(5, -34.705797, -58.404736, 1)); //Lanus Oeste - mas cercano a jose
//		trafos.add(new Transformador(2, -34.721041, -58.396010, 2)); //Escalada
//		trafos.add(new Transformador(7, -34.707652, -58.438311, 2)); //Fiorito
//		trafos.add(new Transformador(11, -34.689638, -58.402254, 3)); //Alsina
//		trafos.add(new Transformador(9, -34.689638, -58.402254, 1)); //Alsina
//		trafos.add(new Transformador(10, -34.689638, -58.402254, 3)); //Alsina
		
//		System.out.println("Reseteando trafos...");
//		
		
//		m.agregar(zona);
//		
//		Administrador a=new Administrador("Rami","Perez",12,"Rami_kpo","1234");
//		
//		m.agregar(a);
		
		
	}

}
