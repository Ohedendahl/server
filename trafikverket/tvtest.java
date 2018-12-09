import java.util.*;
import java.io.*;
import org.json.*;
import java.net.*;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Paths;

public class tvtest {

	public static void main(String[] args) {


		try
		{
			String requestXml = new String(Files.readAllBytes(Paths.get("request.xml")));

			URL url = new URL("http://api.trafikinfo.trafikverket.se/v1.3/data.json");
			URLConnection con = url.openConnection();
			// specify that we will send output and accept input
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setConnectTimeout( 20000 );  // long timeout, but not infinite
			con.setReadTimeout( 20000 );
			con.setUseCaches (false);
			con.setDefaultUseCaches (false);
			// tell the web server what we are sending
			con.setRequestProperty ( "Content-Type", "text/xml" );
			OutputStreamWriter writer = new OutputStreamWriter( con.getOutputStream() );
			writer.write(requestXml);
			writer.flush();
			writer.close();
			// reading the response
			InputStreamReader reader = new InputStreamReader( con.getInputStream() );
			StringBuilder buf = new StringBuilder();
			char[] cbuf = new char[ 2048 ];
			int num;
			while ( -1 != (num=reader.read( cbuf )))
			{
				buf.append( cbuf, 0, num );
			}
			String result = buf.toString();
			System.err.println( "\nResponse from server after POST:\n" + result );
		}
		catch( Throwable t )
		{
			t.printStackTrace( System.out );
		}

	}
}
