import org.apache.log4j.Logger;
import org.apache.log4j.BasicConfigurator;
 
public class TestSpark {
	static Logger log = Logger.getLogger(TestSpark.class);
 
	public static void main(String[] args) {
		BasicConfigurator.configure();
		log.info("This is Logger Info");
	}
}
