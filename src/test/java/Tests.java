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
	
	private Cliente pepe;
	private Cliente rosa;
	//Creo un par de instancias
	public Tests() {
		pepe=new Cliente("pepe","gonzales","dni",4012939,40239401,"Yrigoyen",
				new ArrayList<Dispositivo>(),new Categoria("R1",18.56,0.86));
		rosa=new Cliente("rosa","Perez","dni",4012339,40239401,"Yrigoyen",
				new ArrayList<Dispositivo>(),new Categoria("R2",21.36,1.2));
		pepe.addDispositivo(new Dispositivo("microondas",true,120));
		
		rosa.addDispositivo(new Dispositivo("heladera",true,50));
		rosa.addDispositivo(new Dispositivo("tv",true,50));
		rosa.addDispositivo(new Dispositivo("Aire",true,100));
		
	}
	
	
	 @Test
	    public void testCantMesesAdmin() 
	    {	
		 	Administrador juan = new Administrador("Juan","Lopez",123231,"Juancito","asd123");
		 		
		 		LocalDateTime now = LocalDateTime.now();
	    	    
		 		//LocalDateTime sixDaysBehind = now.minusDays(6);
	    	 
	    	    //Duration duration = Duration.between(now, sixDaysBehind);
	    	    //long diff = Math.abs(duration.toDays());
	    	 
	    	    //assertEquals(diff, 6);
	    	    
	    	    long difer=juan.cantMesesAdmin(now.plusDays(60));
	    	    
	    	    assertEquals(2,difer);
	    	    
	    }
	 
	 @Test
	 	public void testCantDispositivos()	{
		 //Cantidad de dispositivos de Rosa
		 assertEquals(3,rosa.cantDispositivos());
		 
	 }
	 
	
}
