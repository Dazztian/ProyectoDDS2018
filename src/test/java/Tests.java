import static org.junit.Assert.*;

import java.time.*;
import java.util.*;

import org.junit.Test;

import junit.framework.Assert;


public class Tests {

	 @Test
	    public void testCantMesesAdmin() 
	    { 
	    	LocalDateTime now = LocalDateTime.now();
	    	LocalDateTime sixDaysBehind = now.minusDays(6);
	    	
	    	Duration duration = Duration.between(now, sixDaysBehind);
	    	long diff = Math.abs(duration.toDays());
	    	 
	    	assertEquals(diff, 6);
	    
	    }	 
	
	
}
