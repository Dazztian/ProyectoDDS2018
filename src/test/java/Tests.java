import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.*;
import java.util.*;

import org.apache.commons.math3.optim.PointValuePair;
import org.junit.Test;

import com.google.gson.Gson;

import junit.framework.Assert;
import proyectoDDSs.Actuador;
import proyectoDDSs.Administrador;
import proyectoDDSs.Apagado;
import proyectoDDSs.Apagar;
import proyectoDDSs.Categoria;
import proyectoDDSs.Cliente;
import proyectoDDSs.Dispositivo;
import proyectoDDSs.DispositivoEstandar;
import proyectoDDSs.DispositivoInteligente;
import proyectoDDSs.Encendido;
import proyectoDDSs.ISO8601;
import proyectoDDSs.ParserCategoria;
import proyectoDDSs.ParserTransformador;
import proyectoDDSs.ParserZonasGeograficas;
import proyectoDDSs.Regla;
import proyectoDDSs.Traductor;
import proyectoDDSs.TraductorDeMensajesAJSON;
import proyectoDDSs.Transformador;
import proyectoDDSs.ZonaGeografica;
import simplex.facade.*;


public class Tests {
	//Creo un par de instancias
	private Cliente pepe;
	private Cliente rosa;
	private Cliente pedro;
	private Cliente jose;
	
	List<ZonaGeografica> listaZonas;
	List<Transformador> listaTransformadores;
	
	
	public Tests() {
		try {
			pepe=new Cliente("pepe","gonzales","dni",4012939,40239401,"Yrigoyen",
					new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),
							new Categoria("R1",18.56,0.86), -1.542, 7.1245, "pepe10", "pepe");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rosa=new Cliente("rosa","Perez","dni",4012339,40239401,"Yrigoyen",
					new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-02-01T12:00:00+01:00"),
							new Categoria("R2",21.36,1.2),-5.653, 10.5020, "rosa20", "rosa");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		try {
			pedro = new Cliente("Pedro", "Perez","DNI", 40740740, 011450450,
				"casa",new ArrayList<Dispositivo>(),
				ISO8601.toCalendar("2010-01-01T12:00:00+01:00"), new Categoria("R1",18.56,0.86), 7.1235, -5.4820, "rosa20", "rosa");
		} catch(ParseException e1) {
			e1.printStackTrace();
		}
		try {
			jose = new Cliente("Jose","Lopez","dni",10598212,1134913412,"25 de mayo",new ArrayList<Dispositivo>(),
								ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),new Categoria("R1",18.56,0.86),
								-34.704966, -58.412315, "rosa20", "rosa");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	//Dispositivos para el Simplex
	DispositivoInteligente disp1 = new DispositivoInteligente("Dispo1", 0.06, new Encendido(), 120.0, 360.0);
	DispositivoInteligente disp2 = new DispositivoInteligente("Dispo2", 0.875, new Encendido(), 6.0, 30.0);
	DispositivoInteligente disp3 = new DispositivoInteligente("Dispo3", 0.18, new Encendido(), 90.0, 370.0);
	DispositivoInteligente disp4 = new DispositivoInteligente("Dispo1", 4.5, new Encendido(), 120.0, 360.0);
	DispositivoInteligente disp5 = new DispositivoInteligente("Dispo1", 400.0, new Encendido(), 120.0, 360.0);
	DispositivoInteligente disp6 = new DispositivoInteligente("Dispo6", 50.0, new Encendido(), 6.0, 30.0);
	DispositivoInteligente disp7 = new DispositivoInteligente("Dispo7", 50.0, new Encendido(), 6.0, 30.0);
	
	private DispositivoInteligente dispositivo1 = new DispositivoInteligente("Heladera",50, new Encendido(), 0.0, 0.0);
	private DispositivoInteligente dispositivo2 = new DispositivoInteligente("TV",50, new Encendido(), 30.0, 360.0);
	private DispositivoInteligente dispositivo3 = new DispositivoInteligente("Aire",100, new Encendido(), 30.0, 360.0);
	private DispositivoInteligente dispositivo4 = new DispositivoInteligente("Microondas",100, new Encendido(), 30.0, 360.0);
	private DispositivoEstandar dispositivo5 = new DispositivoEstandar("Ventilador", 50, 8, 30.0, 360.0);
	private	Administrador juan = new Administrador("Juan","Lopez",123231,"Juancito","asd123");
	Transformador trafo1 = new Transformador(5, -1.3414, -2.657, 3);
	Transformador trafo2 = new Transformador(6, -1.3414, -2.657, 2);
	Transformador trafo3 = new Transformador(7, -1.3414, -2.657, 1);
	Transformador trafo4 = new Transformador(8, -1.3414, -2.657, 2);
	
	


	@Test
	public void consumoMensualDispo1() {
		dispositivo1.cambiarEstado(new Encendido(LocalDateTime.now(), LocalDateTime.now().plusHours(4)));
		
		assertEquals(200, dispositivo1.consumoMensual(), 0);
	}

	@Test
	public void adaptarUnDispositivo() {
		//Cuando Rosa adapta su ventilador, este deberia salir de su lista de dispositivos y en su lugar estar el moduloAdaptador
		//Tambien se deberian poder entender todos los metodos de un DI
		rosa.addDispositivo(dispositivo5);
		assertEquals(12000, rosa.consumoMensual(), 0);
		rosa.adaptarDispositivo(dispositivo5);
		rosa.dispositivos().forEach(dispositivo -> ((DispositivoInteligente) dispositivo).prender());
		rosa.dispositivos().forEach(dispositivo -> ((DispositivoInteligente) dispositivo).getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(1)));
		assertEquals(50, rosa.consumoMensual(), 0);
		
	}
	
	@Test
		public void consumoMensualDeRosa() {
		//Le agrego los dispositivos y testeo el consumo mensual que tiene Rosa
		//Por motivos de testeo se asume que Rosa solo utilizo sus dispositivos por 1 hora este mes
			dispositivo1.getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(1));
			dispositivo2.getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(1));
			dispositivo3.getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(1));
			dispositivo4.getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(1));
			rosa.addDispositivo(dispositivo1);
			rosa.addDispositivo(dispositivo2);
			rosa.addDispositivo(dispositivo3);
			rosa.addDispositivo(dispositivo4);
			assertEquals(300, rosa.consumoMensual(), 0);		
	}
	
	
	@Test
		public void estimativoFacturacionDeRosa() {
		//Le agrego los dispositivos y testeo el precio estimativo de la factura de Rosa
		//Por motivos de testeo se asume que Rosa solo utilizo sus dispositivos por 1 hora este mes
			dispositivo1.getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(1));
			dispositivo2.getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(1));
			dispositivo3.getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(1));
			rosa.addDispositivo(dispositivo1);
			rosa.addDispositivo(dispositivo2);
			rosa.addDispositivo(dispositivo3);
			assertEquals(261.36, rosa.estimativoFacturacion(), 0);	
}
		@Test
	 	public void testCantDispositivos()	{
		 //Le agrego los dispositivos y testeo la cantidad de dispositivos que tiene Rosa
		 	rosa.addDispositivo(dispositivo1);
		 	rosa.addDispositivo(dispositivo2);
		 	rosa.addDispositivo(dispositivo3);
		 	assertEquals(3,rosa.cantDispositivos());		 
	 }
	
	@Test
	
		public void testCantDispositivosEncendidos(){
		//Se agregan dispositivos a Rosa y se comprueba cuantos de ellos estan encendidos
			dispositivo4.apagar();
			rosa.addDispositivo(dispositivo1);
			rosa.addDispositivo(dispositivo2);
			rosa.addDispositivo(dispositivo3);
			rosa.addDispositivo(dispositivo4);
			assertEquals(3, rosa.cantDispositivosEncendidos());
	}
	
	@Test
		public void testCantDispositivosApagados() {
		//Se agregan dispositivos a Rosa y se comprueba cuantos de ellos estan apagados
			dispositivo4.apagar();
			rosa.addDispositivo(dispositivo1);
			rosa.addDispositivo(dispositivo2);
			rosa.addDispositivo(dispositivo3);
			rosa.addDispositivo(dispositivo4);
			assertEquals(1, rosa.cantDispositivosApagados());
	}
	
	@Test
		public void tieneRosaAlgunDispositivoEncendido() {
		//Se agregan dispositivos a Rosa y se comprueba si alguno de ellos esta encendido
			rosa.addDispositivo(dispositivo1);
			rosa.addDispositivo(dispositivo2);
			rosa.addDispositivo(dispositivo3);
			rosa.addDispositivo(dispositivo4);
			assert(rosa.algunDispositivoEncendido());
		
	}
	
	@Test
		public void rosaDaDeBajaUnDispositivo() {
		//Se agrega un dispositivo a Rosa y se lo da de baja, luego se comprueba que ya no este mas en sus dispositivos
			rosa.addDispositivo(dispositivo1);
			rosa.bajaDispositivo(dispositivo1);
			assert(!rosa.dispositivos().contains(dispositivo1));
	}
	
	@Test
	    public void testCantMesesAdmin(){	
		//Se crea una fecha futura a terminos de prueba y se comprueba hace cuantos meses Juan es administrador
		 	LocalDateTime now = LocalDateTime.now();
	    	    
		 		//LocalDateTime sixDaysBehind = now.minusDays(6);
	    	 
	    	    //Duration duration = Duration.between(now, sixDaysBehind);
	    	    //long diff = Math.abs(duration.toDays());
	    	 
	    	    //assertEquals(diff, 6);
	    	    
	    	    
	    	assertEquals(2,juan.cantMesesAdmin(now.plusDays(60)));
	    	    
	    }
	 
	@Test
		public void testDispositivoApagado() {
		 //Cuando un dispositivo esta apagado, este no consume energia una fraccion minima de energia
		 	dispositivo4.apagar();
		 	assertEquals(1, dispositivo4.consumoPorHora(), 0);
	 }
	 
	@Test
		public void testDispositivoEncendido() {
		 //Cuando un dispositivo esta encendido, este consume energia completamente
		 	dispositivo4.prender();
			assertEquals(100, dispositivo4.consumoPorHora(), 0);
	 }
	@Test
	public void testDispositivoAhorroDeEnergia() {
		//Cuando un dispositivo esta en modo ahorro de energia, este consume la mitad de energia
			dispositivo4.ahorrarEnergia();
			assertEquals(50, dispositivo4.consumoPorHora(), 0);
	}
	
	//-------------------------------------------TESTS--------DE--------ENTREGA 1--------------------------------------------------------------------	
	@Test
	public void tesTraductorDeMensajesAJSON() 
	{	Traductor probandoTraductor = new TraductorDeMensajesAJSON();
		Apagar probandoActuador = new Apagar( dispositivo1,"mensaje:apagar",probandoTraductor);  
		assertEquals(probandoActuador.traducir(),new Gson().toJson("mensaje:apagar"));
	}
	@Test
	public void JSONAcodigo() 
	{
		File archivoPrueba = new File ("..\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\categorias.json");
     	try {
			List<Categoria> listaCategory =  new ParserCategoria().load(archivoPrueba);
			assertEquals(listaCategory.size(),9);
			//Aca se corrobora que se cargaron las 9 categorias
     		} 
     	catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	//-------------------------------------------TESTS--------DE--------ENTREGA 2--------------------------------------------------------------------
	@Test //Test para verificar que se logro agregar un dispo desde el json tablaDispositivos
	public void agregarDispo()
	{
		rosa.agregarDispositivo("tubo_21");
		assertEquals(0,rosa.dispositivos.size(),1);
		
	}
	
	
	@Test //Test para verificar que se logro levantar el JSON de  ZonasGeograficas
	public void JSONZonasAcodigo() 
	{
		File archivoPruebaZonas = new File ("..\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\ZonasGeograficas.json");
     	try {
			List<ZonaGeografica> listaZonas =  new ParserZonasGeograficas().load(archivoPruebaZonas);
			assertEquals(listaZonas.size(),2);
			//Aca se corrobora que se cargaron las 2 zonas
     		} 
     	catch (IOException e) {
			e.printStackTrace();
		}
	}
     	
    @Test //Test para verificar que se logro levantar el JSON de Transformadores
    public void JSONTransformadoresACodigo()
    {
    	File archivoTransformadores = new File ("..\\ProyectoDDS2018\\src\\main\\java\\proyectoDDSs\\Transformadores.json");
     	try {
			List<Transformador> listaTransformadores =  new ParserTransformador().load(archivoTransformadores);
			assertEquals(listaTransformadores.size(),3);
			//Aca se corrobora que se cargaron las 2 zonas
     		} 
     	catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@Test //Test de Simplex, es posible obtener los usos maximos de los dispositivos
	public void seTienenLosUsosMaximos()
	{
		pedro.addDispositivo(disp1);
		pedro.addDispositivo(disp2);
		pedro.addDispositivo(disp3);
		
		PointValuePair consumosOptimos = pedro.consumoOptimo();
		
		assertEquals(360.0, consumosOptimos.getPoint()[0], 0.01);
		assertEquals(30.0, consumosOptimos.getPoint()[1], 0.01);
		assertEquals(370.0, consumosOptimos.getPoint()[2], 0.01);
	}
	
	@Test //Test de Simplex, al aumentar el consumo, cambia las horas maximas recomendadas
	public void seAlteraronLasHorasMaximas()
	{
		pedro.addDispositivo(disp4);
		pedro.addDispositivo(disp2);
		pedro.addDispositivo(disp3);
		
		PointValuePair consumosOptimos = pedro.consumoOptimo();
		
		assertEquals(131.0, consumosOptimos.getPoint()[0], 0.5);
		assertEquals(6.0, consumosOptimos.getPoint()[1], 0.01);
		assertEquals(90.0, consumosOptimos.getPoint()[2], 0.01);
	}
	
	@Test //Test de Simplex, al tener un consumo excesivo, el hogar no es optimizable
	public void elHogarNoEsOptimizable()
	{
		pedro.addDispositivo(disp5);
		pedro.addDispositivo(disp2);
		pedro.addDispositivo(disp3);
		
		System.out.format("TEST HOGAR NO OPTIMIZABLE \n");
		
		pedro.mostrarConsumoOptimo();
		
		//try {
		//PointValuePair consumosOptimos = pedro.consumoOptimo();
		//}
		//catch (Exception e) {
		//	System.out.format("Su hogar no es compatible");
		//}
	}
	
	@Test //Test de Simplex, al excederse de los consumos recomendados, el disp6 se apaga. Tambien se ignora al dispositivo que es una heladera.
	public void seCambiaElEstadoSegunElConsumo()
	{
		pedro.addDispositivo(disp6);
		pedro.addDispositivo(disp7);
		pedro.agregarDispositivo("conFreezer");
		
		disp6.getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(9));
		
		disp7.getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(3));
		
		System.out.format("TEST ACCIONAR SEGUN CONSUMOS \n");
		
		pedro.mostrarConsumoOptimo();
		
		pedro.accionarSegunConsumoOptimo();
		
		
		assertEquals(0.5, disp6.consumoPorHora(), 0.0);
		assertEquals(50.0, disp7.consumoPorHora(), 0.0);
		
	}
	
	
	@Test
	public void testAsignacionDeTrafos() {
		
		ArrayList<ZonaGeografica> zonas = new ArrayList<ZonaGeografica>();
		zonas.add(new ZonaGeografica(1, "zona1", 1.31, 2.13));
		zonas.add(new ZonaGeografica(2, "zona2", 4.31, 3.25));
		zonas.add(new ZonaGeografica(3, "zona3", 7.70, 1.01));
		
		trafo1.asignarZona(zonas);
		trafo2.asignarZona(zonas);
		trafo3.asignarZona(zonas);
		trafo4.asignarZona(zonas);
		
		assertEquals(1, zonas.get(2).cantidadTrafos());
		
		assertEquals(2, zonas.get(1).cantidadTrafos());
		
		assertEquals(1, zonas.get(0).cantidadTrafos());
		
	}
	
	@Test
	public void testAsignacionDeClientes() {
		
		ArrayList<Transformador> trafos=new ArrayList<Transformador>();
		
		trafos.add(new Transformador(5, -1.3414, -2.657, 3));
		trafos.add(new Transformador(6, -5.3314, 6.657, 2));
		trafos.add(new Transformador(7, 23.551, -3.657, 1));
		trafos.add(new Transformador(8, -1.412, 7.657, 2));
		
		rosa.asignarTrafoMasCercano(trafos);
		pepe.asignarTrafoMasCercano(trafos);
		pedro.asignarTrafoMasCercano(trafos);
		
		assertEquals(2, trafos.get(3).numeroClientes());
			
	}
	
	@Test
	public void testAsisgnacionDelTrafoMasCercano() {
		
		ArrayList<Transformador> trafos=new ArrayList<Transformador>();
		
		trafos.add(new Transformador(5, -34.705797, -58.404736, 3)); //Lanus Oeste - mas cercano a jose
		trafos.add(new Transformador(6, -34.721041, -58.396010, 2)); //Escalada
		trafos.add(new Transformador(7, -34.707652, -58.438311, 1)); //Fiorito
		trafos.add(new Transformador(8, -34.689638, -58.402254, 2)); //Alsina
		
		jose.asignarTrafoMasCercano(trafos);
		
		//Me fijo si al trafo de Lanus se le asigno a jose como cliente ya que es el mas cercano
		assertEquals(jose, trafos.get(0).getClientes().get(0));
	}
	
	@Test
	public void testConsumoTotalDeUnaZona() {
		
		ArrayList<ZonaGeografica> zonas = new ArrayList<ZonaGeografica>();
		zonas.add(new ZonaGeografica(1, "zona1", 1.31, 2.13));
		
		ArrayList<Transformador> trafos=new ArrayList<Transformador>();
		
		trafos.add(new Transformador(5, -34.705797, -58.404736, 1)); //Lanus Oeste - mas cercano a jose
		trafos.add(new Transformador(6, -34.721041, -58.396010, 1)); //Escalada
		trafos.add(new Transformador(7, -34.707652, -58.438311, 1)); //Fiorito
		trafos.add(new Transformador(8, -34.689638, -58.402254, 1)); //Alsina
		
		trafos.stream().forEach(trafo -> trafo.asignarZona(zonas));
		
		rosa.asignarTrafoMasCercano(trafos);
		pepe.asignarTrafoMasCercano(trafos);
		pedro.asignarTrafoMasCercano(trafos);
		jose.asignarTrafoMasCercano(trafos);
		
		rosa.addDispositivo(new DispositivoEstandar("heladera", 30, 12, 100, 220));
		rosa.addDispositivo(dispositivo1);
		pepe.addDispositivo(new DispositivoEstandar("lavarropa", 100, 2, 100, 300));
		pepe.addDispositivo(dispositivo2);
		pedro.addDispositivo(new DispositivoEstandar("notebook", 20, 5, 50, 180));
		jose.addDispositivo(new DispositivoEstandar("TV", 60.5, 10, 80, 240));
		jose.addDispositivo(dispositivo4);
		jose.addDispositivo(dispositivo5);
		
		dispositivo1.getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(1));
		dispositivo2.getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(1));
		dispositivo4.getEstadoActual().finalizarEstado(LocalDateTime.now().plusHours(1));
		
		//Calculo del consumo total de una zona
		
		assertEquals(460.5, zonas.get(0).consumoMomentaneo(), 1e-15);

		
	}
	
	//-------------------------------------------TESTS--------DE--------ENTREGA PERSISTENCIA--------------------------------------------------------------------
	
	@Test //Crear 1 usuario nuevo. Persistirlo. Recuperarlo, modificar la geolocalización y
		  //grabarlo. Recuperarlo y evaluar que el cambio se haya realizado.
	public void casoDePrueba1() {
		Cliente roberto = new Cliente("Roberto","Gomez","dni",4012939,40239401,"Yrigoyen",
				new ArrayList<Dispositivo>(),Calendar.getInstance(),
				new Categoria("R1",18.56,0.86), -1.542, 7.1245, "Roberto11", "robertito");
		//PERSISTIR CLIENTE
		//RECUPERAR CLIENTE
		roberto.cambiarGeolocalizacion(-20.05, 25.01);
		//GRABAR CLIENTE
		//RECUPERAR CLIENTE
		assertEquals(-20.05, roberto.latitud(), 0);
		assertEquals(25.01, roberto.longitud(), 0);
		//ROLLBACK A LA DB
		
	}
	@Test //Recuperar un dispositivo. Mostrar por consola todos los intervalos que estuvo
		  //encendido durante el último mes. Modificar su nombre (o cualquier otro atributo
		  //editable) y grabarlo. Recuperarlo y evaluar que el nombre coincida con el
		  //esperado.
	public void casoDePrueba2() {
		//RECUPERAR DISPOSITIVO
		disp1.intervalosEncendidosEnElUltimoMes();
		disp1.cambiarConsumo(5.0);
		//PERSISTIR
		//RECUPERAR
		assertEquals(5.0, disp1.kwhConsumeXHora(), 0);
		//ROLLBACK DE LA DB
	}
	@Test //Crear una nueva regla. Asociarla a un dispositivo. Agregar condiciones y
		  //acciones. Persistirla. Recuperarla y ejecutarla. Modificar alguna condición y
		  //persistirla. Recuperarla y evaluar que la condición modificada posea la última
		  //modificación.
	public void casoDePrueba3() {
		Regla regla = new Regla (30.0);
		//ASOCIAR REGLA
		//AGREGAR CONDICIONES Y ACCIONES
		//PERSISTIR
		//RECUPERAR Y EJECUTAR
		//MODIFICAR Y PERSISTIR
		//RECUPERAR
		//assertThat([MODIFICACION] == regla.condicion());
		//ROLLBACK DE LA DB
	}
	@Test //Recuperar todos los transformadores persistidos. Registrar la cantidad.
		  //Agregar una instancia de Transformador al JSON de entradas. Ejecutar el
		  //método de lectura y persistencia. Evaluar que la cantidad actual sea la anterior
		  //+ 1.
	public void casoDePrueba4() {
		//RECUPERAR TRANSFORMADORES
		// int i = transformadores.size();
		//AGREGAR TRANSFO AL JSON
		//HACER LECTURA Y PERSISTENCIA DEL JSON DE NUEVO
		//assertThat(transformadores.size() == i + 1);
		//ROLLBACK DE LA DB
	}
	@Test //Dado un hogar y un período, mostrar por consola (interfaz de comandos) el
		  //consumo total. Dado un dispositivo y un período, mostrar por consola su
		  //consumo promedio. Dado un transformador y un período, mostrar su consumo
		  //promedio. Recuperar un dispositivo asociado a un hogar de ese transformador e
		  //incrementar un 1000 % el consumo para ese período. Persistir el dispositivo.
		  //Nuevamente mostrar el consumo para ese transformador.
	public void casoDePrueba5() {
		LocalDateTime fechaLimiteMinima = LocalDateTime.now();
		LocalDateTime fechaLimiteMaxima = fechaLimiteMinima.plusDays(10);
		System.out.format("El consumo del hogar en el periodo fue de %f \n", (float) pedro.consumoEnIntervalo(fechaLimiteMaxima, fechaLimiteMinima));
		//HACER UN PAR DE CONSUMOS
		System.out.format("El consumo del dispositivo fue de %f \n", (float) disp1.consumoPromedioEnIntervalo(fechaLimiteMaxima, fechaLimiteMinima));
		System.out.format("El consumo del transformador fue de %f \n", (float) trafo1.consumoEnIntervalo(fechaLimiteMaxima, fechaLimiteMinima));
		//RECUPERAR UN DISPO QUE ESTE EN ESTE TRANSFORMADOR
		disp1.cambiarConsumo( disp1.kwhConsumeXHora() * 1000);
		//PERSISTIR DISPOSITIVO
		System.out.format("El consumo del transformador fue de %f \n", (float) trafo1.consumoEnIntervalo(fechaLimiteMaxima, fechaLimiteMinima));
		//ROLLBACK DE LA DB
	}
	
}