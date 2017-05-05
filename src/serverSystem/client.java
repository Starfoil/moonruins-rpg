package serverSystem;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class client {
	public static void main(String [] args) {
		String serverName = "localhost";
		int port = Integer.parseInt("5");
		
		try {
			//System.out.println("Connecting to " + serverName + " on port " + port);
			Socket client = new Socket(serverName, port);
			Scanner x = new Scanner(System.in);
			System.out.println("Connected to " + client.getRemoteSocketAddress());

			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			
			String line;
			System.out.println("Say something to the server:");
			String s = x.nextLine();
			out.println(s);
			
			
			while(true){
				StringBuffer sb = new StringBuffer();
				int value;
				while((value = in.read()) != -1) {
		            char c = (char)value;
		            if(c == '@'){
		            	break;
		            }
		            sb.append(c);
		        }
				System.out.println(sb.toString());
				s = x.nextLine();
				out.println(s);	
			}
		
			//InputStream inFromServer = client.getInputStream();
			//DataInputStream in = new DataInputStream(inFromServer);

			//System.out.println("Server says " + in.readUTF());
			//client.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
