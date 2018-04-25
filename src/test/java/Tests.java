import static org.junit.Assert.*;

import java.time.*;
import java.util.*;

import org.junit.Test;

import junit.framework.Assert;
import proyectoDDSs.Administrador;
import proyectoDDSs.Categoria;
import proyectoDDSs.Cliente;
import proyectoDDSs.Dispositivo;

public class Tests {
	//Creo un par de instancias
	private Cliente pepe = new Cliente("pepe","gonzales","dni",4012939,40239401,"Yrigoyen",
			new ArrayList<Dispositivo>(),new Categoria("R1",18.56,0.86));
	private Cliente rosa = new Cliente("rosa","Perez","dni",4012339,40239401,"Yrigoyen",
			new ArrayList<Dispositivo>(),new Categoria("R2",21.36,1.2));
	private Dispositivo dispositivo1 = new Dispositivo("Heladera",true,50);
	private Dispositivo dispositivo2 = new Dispositivo("TV",true,50);
	private Dispositivo dispositivo3 = new Dispositivo("Aire",true,100);
	private Dispositivo dispositivo4 = new Dispositivo("Microondas", false, 100);
	private	Administrador juan = new Administrador("Juan","Lopez",123231,"Juancito","asd123");
	
	
	@Test
		public void consumoMensualDeRosa() {
		 //Le agrego los dispositivos y testeo el consumo mensual que tiene Rosa
			rosa.addDispositivo(dispositivo1);
			rosa.addDispositivo(dispositivo2);
			rosa.addDispositivo(dispositivo3);
			rosa.addDispositivo(dispositivo4);
			assertEquals(200, rosa.consumoMensual(), 0);
		
	}
	
	@Test
		public void estimativoFacturacionDeRosa() {
		//Le agrego los dispositivos y testeo el consumo mensual que tiene Rosa
			rosa.addDispositivo(dispositivo1);
			rosa.addDispositivo(dispositivo2);
			rosa.addDispositivo(dispositivo3);
			rosa.addDispositivo(dispositivo4);
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
			rosa.addDispositivo(dispositivo1);
			rosa.addDispositivo(dispositivo2);
			rosa.addDispositivo(dispositivo3);
			rosa.addDispositivo(dispositivo4);
			assertEquals(3, rosa.cantDispositivosEncendidos());
	}
	
	@Test
		public void testCantDispositivosApagados() {
		//Se agregan dispositivos a Rosa y se comprueba cuantos de ellos estan apagados
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
		 //Cuando un dispositivo esta apagado, este no consume energia
		 	
		 	assertEquals(0, dispositivo4.kwhConsumeXHora());
	 }
	 
	@Test
		public void testDispositivoEncendido() {
		 //Cuando un dispositivo esta encendido, este si consume energia
		 
			assertEquals(50, dispositivo1.kwhConsumeXHora());
	 }
	 
	
}
