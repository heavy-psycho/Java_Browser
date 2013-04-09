import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
    This program demonstrates how to use a socket to communicate
   with a web server. Supply the name of the host and the
    resource on the command-line, for example
    java WebGet java.sun.com index.html
 */
public class WebGet{

	public static String[] getHTML(String host,String resource) throws IOException{
		// Get command-line arguments

		Socket s = null;
		String[] retour=new String[0];

		
		if(host==null||resource==null){
			System.out.println("Getting / from java.sun.com");
			host = "java.sun.com";
			resource = "/";
		}

		// Open socket

		final int HTTP_PORT = 80;
		try{
			s = new Socket(host, HTTP_PORT);
		}catch(UnknownHostException err){
			System.err.println("ERROR HOST NOT FOUND. Re-routing to the default page");
			s = new Socket("java.sun.com", 80);
		}


		// Get streams

		InputStream instream = s.getInputStream();
		OutputStream outstream = s.getOutputStream();

		// Turn streams into scanners and writers

		Scanner in = new Scanner(instream);
		PrintWriter out = new PrintWriter(outstream);      

		// Send command

		String command = "GET " + resource + " HTTP/1.0\n\n";
		out.print(command);
		out.flush();

		// Read server response
		
		while (in.hasNextLine())
		{
			String input = in.nextLine();
			//System.out.println(input);
			retour=add(retour,input);
		}

		// Always close the socket at the end

		s.close(); 
		in.close();
		return retour;
	}
	
	private static String[] add (String[] a,String b){
		String[] temp = new String[a.length+1];
		for(int i=0;i<a.length;i++){
			temp[i]=a[i];
		}
		temp[temp.length-1]=b;
		return temp;
	}
}