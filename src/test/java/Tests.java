import static org.junit.Assert.*;

import java.text.ParseException;
import java.time.*;
import java.util.*;

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
import proyectoDDSs.Traductor;
import proyectoDDSs.TraductorDeMensajesAJSON;


public class Tests {
	//Creo un par de instancias
	private Cliente pepe;
	private Cliente rosa;

	public Tests() {
		try {
			pepe=new Cliente("pepe","gonzales","dni",4012939,40239401,"Yrigoyen",
					new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-01-01T12:00:00+01:00"),new Categoria("R1",18.56,0.86));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rosa=new Cliente("rosa","Perez","dni",4012339,40239401,"Yrigoyen",
					new ArrayList<Dispositivo>(),ISO8601.toCalendar("2010-02-01T12:00:00+01:00"),new Categoria("R2",21.36,1.2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private DispositivoInteligente dispositivo1 = new DispositivoInteligente("Heladera",50, new Encendido());
	private DispositivoInteligente dispositivo2 = new DispositivoInteligente("TV",50, new Encendido());
	private DispositivoInteligente dispositivo3 = new DispositivoInteligente("Aire",100, new Encendido());
	private DispositivoInteligente dispositivo4 = new DispositivoInteligente("Microondas",100, new Encendido());
	private DispositivoEstandar dispositivo5 = new DispositivoEstandar("Ventilador", 50, 8);
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
	
	/*											 TESTS		 DE			 ENTREGA 1											 */
	@Test
	public void tesTraductorDeMensajesAJSON() 
	{	//Como el nombre bien lo indica, con este mecanismo es posible traducir cualquier mensaje a JSON, 
		//el 2 argumento de la accion será lo que traduzca a JSON.
		Traductor probandoTraductor = new TraductorDeMensajesAJSON();
		Apagar probandoActuador = new Apagar( dispositivo1,"mensaje:apagar",probandoTraductor);
		//OBS: NO podemos comparar directamente un JSON con un STRING, SON 2 COSAS DIFERENTES
		//X eso al usar equals debemos laburar si o si con 2 elementos del mismo tipo  
		assertEquals(probandoActuador.traducir(),new Gson().toJson("mensaje:apagar"));
	}
	
}