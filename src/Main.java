// The main class, the launch-point of the program
// Contains main method

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

class Main {

	private static final String USER_AGENT = "Mozilla/5.0";

	
	public static void main(String [ ] args) {
		
		System.out.println("Hello World");
//		
		try {
//			connectToServer();
			sendGet();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		

		
		
	} // end of main
	
	 
	private static void connectToServer() throws IOException {
		final int portNumber = 3000; 
		System.out.println("Connecting to server on port " + portNumber );
//		ServerSocket mServerSocket = new ServerSocket(portNumber);
		
		while (true) {
			Socket mSocket = new Socket("http://54b7b6a3.ngrok.io", 3000);
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
	
	
	// HTTP GET request
		private static void sendGet() throws Exception {

//			String url = "http://www.google.com/search?q=mkyong";
			String url = "http://54b7b6a3.ngrok.io/test";
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//print result
			System.out.println(response.toString());

		}
	
	
} // end of Main