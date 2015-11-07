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

import GuiElements.*;

class Main {

	
	public static void main(String [ ] args) {
		
		System.out.println("Hello World");
		
		// send a quick ack, why dont you
		try {
			sendAck();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		// create and show gui
//		LaunchGameGui gui = new LaunchGameGui();
//		YourGrid battleGrid = new YourGrid();
		SetupShipsGui setup = new SetupShipsGui();
		
		
		
	} // end of main
	
	
	 
	// acknowledge that you are connected to the server
	private static void sendAck() throws Exception {

			String url = "http://9ff96ab3.ngrok.io/test";
			
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			con.setRequestProperty("User-Agent", "");

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