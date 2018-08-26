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

	public Tests() {
		try {
			pepe=new Cliente("pepe","gonzales","dni",4012939,40239401,"Yrigoyen",
					new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),new Categoria("R1",18.56,0.86), 8.0, 8.0);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rosa=new Cliente("rosa","Perez","dni",4012339,40239401,"Yrigoyen",
					new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-02-01T12:00:00+01:00"),new Categoria("R2",21.36,1.2),8.0, 8.0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		try {
			pedro = new Cliente("Pedro", "Perez","DNI", 40740740, 011450450,
				"casa",new ArrayList<Dispositivo>(),
				ISO8601.toCalendar("2010-01-01T12:00:00+01:00"), new Categoria("R1",18.56,0.86), 30.0, 30.0);
		} catch(ParseException e1) {
			e1.printStackTrace();
	}
	}
	
	//Dispositivos para el Simplex
	DispositivoInteligente disp1 = new DispositivoInteligente("Dispo1", 0.06, new Encendido(), 120.0, 360.0);
	DispositivoInteligente disp2 = new DispositivoInteligente("Dispo2", 0.875, new Encendido(), 6.0, 30.0);
	DispositivoInteligente disp3 = new DispositivoInteligente("Dispo3", 0.18, new Encendido(), 90.0, 370.0);
	DispositivoInteligente disp4 = new DispositivoInteligente("Dispo1", 4.5, new Encendido(), 120.0, 360.0);
	
	private DispositivoInteligente dispositivo1 = new DispositivoInteligente("Heladera",50, new Encendido(), 0.0, 0.0);
	private DispositivoInteligente dispositivo2 = new DispositivoInteligente("TV",50, new Encendido(), 30.0, 360.0);
	private DispositivoInteligente dispositivo3 = new DispositivoInteligente("Aire",100, new Encendido(), 30.0, 360.0);
	private DispositivoInteligente dispositivo4 = new DispositivoInteligente("Microondas",100, new Encendido(), 30.0, 360.0);
	private DispositivoEstandar dispositivo5 = new DispositivoEstandar("Ventilador", 50, 8, 30.0, 360.0);
	private	Administrador juan = new Administrador("Juan","Lopez",123231,"Juancito","asd123");
	
	


	@Test
	public void consumoMensualDispo1() {
		dispositivo1.guardarConsumo();
		dispositivo1.guardarConsumo();
		dispositivo1.guardarConsumo();
		dispositivo1.guardarConsumo();
		
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
		rosa.dispositivos().forEach(dispositivo -> ((DispositivoInteligente) dispositivo).guardarConsumo());
		assertEquals(50, rosa.consumoMensual(), 0);
		
	}
	
	@Test
		public void consumoMensualDeRosa() {
		//Le agrego los dispositivos y testeo el consumo mensual que tiene Rosa
		//Por motivos de testeo se asume que Rosa solo utilizo sus dispositivos por 1 hora este mes
			dispositivo1.guardarConsumo();
			dispositivo2.guardarConsumo();
			dispositivo3.guardarConsumo();
			dispositivo4.guardarConsumo();
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
			dispositivo1.guardarConsumo();
			dispositivo2.guardarConsumo();
			dispositivo3.guardarConsumo();
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
}