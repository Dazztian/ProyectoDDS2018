import static org.junit.Assert.*;

import java.time.*;
import java.util.*;

import junit.framework.Assert;

public class Tests {

	 //@Test
	    public void testCantMesesAdmin() 
	    { 
	    		LocalDate now = LocalDate.now();
	    	    LocalDate sixDaysBehind = now.minusDays(6);
	    	 
	    	    Duration duration = Duration.between(now, sixDaysBehind);
	    	    long diff = Math.abs(duration.toDays());
	    	 
	    	    assertEquals(diff, 76);
	    }
	
}
