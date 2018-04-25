import static org.junit.Assert.*;

import java.time.*;
import java.util.*;

import org.junit.Test;

import junit.framework.Assert;
import proyectoDDSs.Administrador;

public class Tests {

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
	
}
