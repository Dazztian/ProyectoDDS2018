import java.text.ParseException;

import javax.persistence.EntityManager;

import modelsPersistencia.*;
import proyectoDDSs.*;
import java.util.*;

public class TestDB {

	public static void main(String[] args) throws ParseException {
				
		EntityManager em = ModelHelperPersistencia.getEntityManager();
		ClienteModel cliente_model = new ClienteModel();
		DispoPermitidoModel permitido_model = new DispoPermitidoModel();
		DispositivoModel dispo_model = new DispositivoModel();

		
//		DispositivoInteligente dispo = (DispositivoInteligente) dispo_model.buscarDispositivo(new Long(3));
//		
//		dispo_model.eliminar(dispo);
		
		//Agregar Dispositivo nuevo a un cliente
		Cliente copy = cliente_model.buscarCliente(new Long(3));		
		Cliente copy2 = cliente_model.buscarCliente(new Long(1));
		DispositivoPermitido dispo = permitido_model.buscarDispositivo(new Long(3));
//		
		DispositivoEstandar dispoNuevo = new DispositivoEstandar(dispo.getNombre(),dispo.getEquipo(),
				dispo.getKwhConsumeXHora(),5,dispo.getConsumoMinimo(),dispo.getConsumoMaximo());
		dispoNuevo.setCliente(copy);
		dispoNuevo.setDispositivo(dispo);
//		dispo_model.agregar(dispoNuevo);
				
		DispositivoEstandar dispoNuevo2 = new DispositivoEstandar(dispo.getNombre(),dispo.getEquipo(),
				dispo.getKwhConsumeXHora(),6,dispo.getConsumoMinimo(),dispo.getConsumoMaximo());

		dispoNuevo2.setCliente(copy2);
		dispoNuevo2.setDispositivo(dispo);
//		dispo_model.agregar(dispoNuevo2);
		
//		dispo_model.eliminar(dispo);
		
//		DispositivoInteligente dispo_cliente = new DispositivoInteligente(dispo.getNombre(),dispo.getEquipo(),
//				dispo.getKwhConsumeXHora(),new Apagado(),dispo.getConsumoMinimo(),dispo.getConsumoMaximo());

//		dispo_cliente.setCliente(copy);
//		dispo_cliente.setDispositivo(dispo);
		
//		DispositivoInteligente dispo_cliente = (DispositivoInteligente) dispo_model.buscarDispositivo(new Long(5));
//		
//		dispo_model.eliminar(dispo_cliente);
//		
//		DispositivoInteligente dispoCopy = (DispositivoInteligente) dispo_model.buscarDispositivo(new Long(8));
//		
//		dispoCopy.cambiarEstado(new Encendido());
//		dispoCopy.guardarConsumo();
//		dispoCopy.cambiarEstado(new AhorroDeEnergia());
//		dispoCopy.guardarConsumo();
//
//		dispo_model.modificar(dispoCopy);
//		dispo_model.agregar(dispoCopy);
//		
//		Cliente lucas=new Cliente("Lucas","Resa","dni",40190642,1140256921,"Yrigoyen",
//		new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),
//		2, -1.542, 7.1245, "Luquitas", "asd123");
//
//		Cliente roberto=new Cliente("Roberto","Perez","dni",40190643,1139201381,"25 de Mayo",
//		new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),
//		2, -1.542, 7.1245, "Robertito", "RPerez");

		
		
//		Administrador roberto= new Administrador("roberto","Lopez",2,"robertito","asd123");
//		
//		cliente_model.agregar(lucas);
//		cliente_model.agregar(roberto);
//		
//		
		
//		Cliente admin=new ClienteModel().buscarCliente("robertito");
//		
//		if(admin==null) {
//			System.out.println("no se encontro al cliente robertito");
//		}
//		
//		DispositivoEstandar dispoCopy3 = (DispositivoEstandar)dispo_model1.buscarDispositivo(new Long(1));
//		
//		dispoCopy3.setUsoDiario(10);
//		
//		lucasCopy.addDispositivo(dispoCopy3);
//		
		
//		dispo_model.modificar(clienteCopy);
		
//		AdaptadoXCliente adaptadoCopy = (AdaptadoXCliente)dispo_model.buscarDispositivo(new Long(5));
//		
//		ModuloAdaptador modulo = adaptadoCopy.getDispositivo();
//		
//		EstandarXCliente estandar = adaptadoCopy.getDispoAdaptado();
//		
//		ArrayList<EstandarXCliente> lista = new ArrayList<>();
//		
//		lista.add(estandar);
//		
//		System.out.println("Id Modulo: "+modulo.getId());
//		System.out.println("Id Estandar: "+estandar.getId());
//		EstandarXCliente dispoCopy = (EstandarXCliente)dispo_model.buscarDispositivo(new Long(1));
//				
//		Cliente pepeCopy = new ClienteModel().buscarCliente(new Long(1));
//		
//		pepeCopy.adaptarDispositivo(dispoCopy);
//		
//		dispo_model.modificar(pepeCopy);
		
//		Cliente lucas=new Cliente("Lucas","Resa","dni",40190642,1140256921,"Yrigoyen",
//				new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),
//				1, -1.542, 7.1245, "pepe10", "pepe");
//		
//		DispositivoEstandar dispo_estandar=new DispositivoEstandar("lalaland", "peli", 5, 10, 3, 15);
//		
//		EstandarXCliente estandar_cliente = new EstandarXCliente(lucas, dispo_estandar);
//		
//		DispositivoEstandar dispo_estandar_copy = estandar_cliente.getDispositivo();
//		
//		System.out.println(dispo_estandar_copy.getId()+" "+dispo_estandar_copy.getUsoDiario());
		
//		Dispositivo d1 = new DispositivoEstandar("Heladera","150 litros",25,6,20,50);
//		
//		zona_model.agregar(d1);
	
//		Cliente lucas=new Cliente("Lucas","Resa","dni",40190642,1140256921,"Yrigoyen",
//				new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),
//				1, -1.542, 7.1245, "pepe10", "pepe");
//		
//		zona_model.agregar(lucas);
		
//		DispositivoModel dispo_model = new DispositivoModel();
//		
//		Dispositivo dispo = dispo_model.buscarDispositivo(new Long(1));
//		
//		Cliente pepeCopy = new ClienteModel().buscarCliente(new Long(1));
//		
//		pepeCopy.addDispositivo(dispo);
//		
//		dispo_model.agregar(pepeCopy);
		
//		
		TransformadorModel trafo_model = new TransformadorModel();
//		
//		List<ZonaGeografica> zonas = new ArrayList<ZonaGeografica>();
//		
//		List<Transformador> trafos = new ArrayList<Transformador>();
//		
//		zonas.add(new ZonaGeografica(1, "zona1", 1.1, 2.2));
//	
//
//		Transformador trafo1 = new Transformador(2, -1.3414, -2.657, 1);
//		Transformador trafo2 = new Transformador(6, -1.3414, -2.657, 1);
//		Transformador trafo3 = new Transformador(7, -1.3414, -2.657, 1);
//		Transformador trafo4 = new Transformador(8, -1.3414, -2.657, 1);
//		Transformador trafo5= new Transformador(10,-1.123,4.132,2);
//		trafo1.asignarZona(zonas);
//		trafo2.asignarZona(zonas);
//		trafo3.asignarZona(zonas);
//		trafo4.asignarZona(zonas);
//		
//		zonas.stream().forEach(zona -> zona_model.agregar(zona));
		
		
		/*ClienteModel m = new ClienteModel();
		
		DispositivoModel d = new DispositivoModel();
		
		Cliente lucas=new Cliente("Lucas","Resa","dni",40190642,1140256921,"Yrigoyen",
				new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),
				new Categoria("R1",18.56,0.86), -1.542, 7.1245, "pepe10", "pepe");
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
		
		*/
	}

}