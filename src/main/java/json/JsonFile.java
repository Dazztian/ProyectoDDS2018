package json;

	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileReader;
	import java.io.IOException;

	public class JsonFile extends File {

	    public JsonFile(String pathname) {
	        super(pathname);
	    }

	    /**
	     * read line from text plain
	     * @throws IOException
	     */
	    public String read() throws IOException {
	        final StringBuffer sb = new StringBuffer();
	        try (BufferedReader br = new BufferedReader(new FileReader(getAbsolutePath()))) {
	            String line = br.readLine();
	            while (line != null) {
//	                System.out.println("line to process=>"+ line);
	                sb.append(line);
	                line = br.readLine();
	            }
	        }
	        return sb.toString();
	    }

}
