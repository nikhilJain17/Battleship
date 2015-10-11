// The main class, the launch-point of the program
// Contains main method

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class Main {

	public static void main(String [ ] args) {
		
		System.out.println("Hello World");
		
		try {
			connectToServer();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
	} // end of main
	
	
	private static void connectToServer() throws IOException {
		final int portNumber = 3000;
		System.out.println("Connecting to server on port " + portNumber );
		ServerSocket mServerSocket = new ServerSocket(portNumber);
		
		while (true) {
			Socket mSocket = mServerSocket.accept();
			OutputStream os = mSocket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);
			pw.println("Whats your name?");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
			String str = br.readLine();
			
			pw.println("Hello, " + str);
			pw.close();
			mSocket.close();
			
			System.out.println("Just said hello to " + str);
			
		} // end of while
		
	} // end of connectToServer()
	
	
} // end of Main