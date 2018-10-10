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
		
		List<Transformador> trafos = new ArrayList<>();
		
		List<ZonaGeografica> zonas = new ArrayList<>();
		
		ZonaGeografica zona = new ZonaGeografica(2, "zona2", 1.1321, 3.123);
		
		zonas.add(zona);
		
		trafos.add(new Transformador(1,-1.232,-2.312,2));
		trafos.add(new Transformador(2,-12.23,3.123,2));
		
		trafos.stream().forEach(trafo -> trafo.asignarZona(zonas));
		
		m.agregar(zona);
		
//		Administrador a=new Administrador("Rami","Perez",12,"Rami_kpo","1234");
//		
//		Cliente c=new Cliente("pepe","gonzales","dni",4012939,40239401,"Yrigoyen",
//				new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),
//				new Categoria("R1",18.56,0.86), -1.542, 7.1245, "pepe10", "pepe");
//		
//		m.agregar(c);
//		m.agregar(a);
		
		
	}

}
