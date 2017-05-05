package serverSystem;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import Data.DataLoader;
import Data.DataSaver;


public class mainServer extends Thread{

	private ServerSocket serverSocket;

	public mainServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(100000);
	}

	public void run() {
		while(true) {
			try {
				System.out.println("Waiting for client on port " + 
						serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				//System.out.println("Incoming connection from : " + server.getRemoteSocketAddress());
				BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
				PrintWriter out = new PrintWriter(server.getOutputStream(), true);
				String line;
				DataLoader.LOAD();
				inputProcessing ip = new inputProcessing(server);
				
				while ((line = in.readLine()) != null) {
					//out.println("Server got your message :" +line);
					ip.processInput(line);
				}
				
				DataSaver.SAVE();
				server.close();

			}catch(SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			}catch(IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public static void main(String [] args) {
		int port = Integer.parseInt("5");
		try {
			Thread t = new mainServer(port);
			t.start();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
