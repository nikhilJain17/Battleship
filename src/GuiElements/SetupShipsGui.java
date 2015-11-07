package GuiElements;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.net.*;
import org.apache.commons.*;//httpclient.*;//http.client.*;
//import org.apache.commons.httpclient.*;
//import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.HttpClient;

import javax.swing.*;


// To place your ships on the board

public class SetupShipsGui extends JFrame {

	JPanel theMainFrame; // the frame that holds everything
	
	JButton[] setShips; // array to hold where you want to place the ships
	GridLayout gridLayout;
	
	JButton submitBtn; // to confirm the ships location, post that shit, and progress
	
	final int SHIPS = 10;
	
	public SetupShipsGui() {
		
		theMainFrame = new JPanel();
//		theMainFrame.setTitle("Place your ships");
		
		setShips = new JButton[100];
		gridLayout = new GridLayout(11, 10);
		theMainFrame.setLayout(gridLayout);
		
		// init the frame
		for (int i = 0; i < 100; i++) {
			
			setShips[i] = new JButton("No Ship");
			setShips[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// Changes text on the Button
					if (((AbstractButton) e.getSource()).getText().equals("No Ship"))
						((AbstractButton) e.getSource()).setText("<html><FONT COLOR=RED>SET</html>");
					
					else {
						((AbstractButton) e.getSource()).setText("No Ship");
//						((AbstractButton) e.getSource()).set(Color.RED);
					}
				}
				
			}); // end of action Listener
			
			theMainFrame.add(setShips[i]);
			
		} // end of for loop
		
		
		submitBtn = new JButton("<html><FONT COLOR=BLUE><b>Submit ships</b></html>");
		submitBtn.setPreferredSize(new Dimension(100, 50));
		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int actualConfirmed = 0;
				for (int i = 0; i < 100; i++) {
				// iterate through the buttons, check how many ships are set		
					if (setShips[i].getText().equals("<html><FONT COLOR=RED>SET</html>"))
						actualConfirmed++;
				} // end of for
				
				if (actualConfirmed >= SHIPS) {
					// enough ships were placed
					
					//2. Open the YourGrid cheese ON A NEW THREAD
				
					SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

						@Override
						protected Void doInBackground() throws Exception {
							
							YourGrid gui = new YourGrid();
							return null;

						}
					};
					
				
					//1. Send that info to the server 
					Runnable r = new Runnable() {
						// This is a critical point in the program
//						sendShips();

						@Override
						public void run() {
							try {
								System.out.println("Sending ships");
								sendPost();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					};
					r.run();
					
					worker.execute();
					deleteMe();
					
					
				}// end of if
				
				else {
					// show dialog saying user is an idiot
					JOptionPane.showMessageDialog(theMainFrame, "You need at least 10 ships!", 
							"Come on", 2, null);
				} // end of else
				
			}
			
		});
		
		
		theMainFrame.add(submitBtn);
		JLabel dontreadthis = new JLabel("<html><b>\tPlace " + SHIPS + " ships</b></html>");
		theMainFrame.setPreferredSize(new Dimension(1280, 720));
		theMainFrame.add(dontreadthis);
		theMainFrame.setBounds(0, 0, 1280, 700);
		theMainFrame.setVisible(true);
		
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 1280, 720);
//		this.setBounds(getMaximizedBounds());
		this.setVisible(true);
		this.add(theMainFrame, BorderLayout.CENTER);
		
	} // end of constructor
	
	private void deleteMe() {
		this.setVisible(false);
//		this.
	}
	
//	private void sendShips() {
//		
//		 HttpClient httpClient = HttpClientBuilder.create().build(); //Use this instead 
//
//		    try {
//		        HttpPost request = new HttpPost("http://yoururl");
//		        StringEntity params =new StringEntity("details={\"name\":\"myname\",\"age\":\"20\"} ");
//		        request.addHeader("content-type", "application/x-www-form-urlencoded");
//		        request.setEntity(params);
//		        HttpResponse response = httpClient.execute(request);
//
//		        // handle response here...
//		    }catch (Exception ex) {
//		        // handle exception here
//		    } finally {
//		        httpClient.getHttpConnectionManager().shutdown(); //Deprecated
//		    }
//		
//	}
	
	
	private void sendPost() throws Exception {
		
		String sendShips = "";
		
		
		System.out.println("Params: " + sendShips);
		
		String url = "http://9ff96ab3.ngrok.io/user1_ships";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "User 1");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//		wr.writeBytes(sendShips);
		
		// check which ships are selected
		// send the data as well
				for (int i = 0; i < 100; i++) {
					
					if (setShips[i].getText().equals("<html><FONT COLOR=RED>SET</html>")) {
						
						// append to the params to be passed
						sendShips += ("," + i);
						
//						// WRITE THE DATA HERE
//						String tag = "ship" + i;
//						String data = Integer.toString(i);
//						String send = tag + ":" + data + "\n";
//						wr.writeBytes(send);
						
						
					} // end of if
							
				} // end of for
		
		System.out.println(sendShips);
		wr.writeBytes(sendShips);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
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
		
		
//		// open a HTTP connection to the server
//		URL url = new URL("http://www.10917bc9.ngrok.io/user1_ships");
//		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//		connection.setRequestMethod("POST");
//		connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//		connection.setDoOutput(true);
//		
//		//.
//		
//		// send the ship data
//		OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
//		// TODO Get the actual ships and send them
//		writer.write("ignore=-1");
//		writer.write(sendShips);
//		writer.flush();
//		
//		System.out.println("Sent post to " + url);
//		
////		// Get the response from the server
////		String line;
////		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
////		while ((line = reader.readLine()) != null) {
////			System.out.println(line);
////		}
//		
//		// clean up resources
//		writer.close();
////		reader.close();
		

		

	}
	
	// TODO Paint that background!
//	
//	@Override
//	protected void paint(Graphics g) {
//		// draw some cheese
//		g.setColor(Color.GREEN);
//		
//	}
	
	
	// once you are done, start YourGridGui
	
}
