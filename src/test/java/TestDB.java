import java.text.ParseException;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import modelsPersistencia.*;
import proyectoDDSs.*;
import java.util.*;

public class TestDB {

	public static void main(String[] args) throws ParseException {
		
		EntityManager e = ModelHelperPersistencia.getEntityManager();
		
		ModelHelperPersistencia m = new ModelHelperPersistencia();
		
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
		ZonaGeograficaModel modelZona = new ZonaGeograficaModel();
		
		List<Transformador> trafos = new ArrayList<>();
		
		List<ZonaGeografica> zonas;
		
//		ZonaGeografica zona = new ZonaGeografica(2, "zona2", 1.1321, 3.123);
		
//		zonas.add(zona);
		
//		zonas =  modelZona.buscarTodasLasZonaGeografica();
		
		trafos.add(new Transformador(5, -34.705797, -58.404736, 1)); //Lanus Oeste - mas cercano a jose
		trafos.add(new Transformador(2, -34.721041, -58.396010, 2)); //Escalada
		trafos.add(new Transformador(7, -34.707652, -58.438311, 2)); //Fiorito
		trafos.add(new Transformador(11, -34.689638, -58.402254, 3)); //Alsina
		trafos.add(new Transformador(9, -34.689638, -58.402254, 1)); //Alsina
		trafos.add(new Transformador(10, -34.689638, -58.402254, 3)); //Alsina
		
//		System.out.println("Reseteando trafos...");
//		
		
//		m.agregar(zona);
//		
//		Administrador a=new Administrador("Rami","Perez",12,"Rami_kpo","1234");
//		
//		m.agregar(a);
		
		
	}

}
