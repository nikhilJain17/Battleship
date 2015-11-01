package GuiElements;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.*;
import org.apache.commons.*;//httpclient.*;//http.client.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.PostMethod;

import javax.swing.*;


// To place your ships on the board

public class SetupShipsGui extends JFrame {

	JFrame theMainFrame; // the frame that holds everything
	
	JButton[] setShips; // array to hold where you want to place the ships
	GridLayout gridLayout;
	
	JButton submitBtn; // to confirm the ships location, post that shit, and progress
	
	final int SHIPS = 10;
	
	public SetupShipsGui() {
		
		theMainFrame = new JFrame();
		theMainFrame.setTitle("Place your ships");
		
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
					//1. Send that info to the server 
					try {
						// This is a critical point in the program
//						sendShips();
						sendPost();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
					//2. Open the YourGrid cheese
					YourGrid gui = new YourGrid();
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
		theMainFrame.setPreferredSize(new Dimension());
		theMainFrame.add(dontreadthis);
		theMainFrame.setBounds(0, 0, 1280, 750);
		theMainFrame.setVisible(true);
		
		this.setLayout(new BorderLayout());
		this.add(theMainFrame, BorderLayout.CENTER);
		
	} // end of constructor
	
	private void deleteMe() {
		theMainFrame.setVisible(false);
//		this.
	}
	
	private void sendShips() {
		
//		HttpClient client = ;
		String url = "http://b3510ffa.ngrok.io/user1_ships";
//		PostMethod post = new PostMethod(url); 
//		
//		NameValuePair[] data = {
//				new NameValuePair("ship1", "1")
//		};
//		
//		post.setRequestBody(data);
		
		
	}
	
	
	private void sendPost() throws Exception {

		String url = "http://b3510ffa.ngrok.io/user1_ships";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//PostMethod post = new PostMethod(url); 
//		
//		NameValuePair[] data = {
//				new NameValuePair("ship1", "1")
//		};
//		
//		post.setRequestBody(data);

//		String urlParameters = "shiplocations=12345";
		
		// the datum to send
//		NameValuePair[] data = {
//				new NameValuePair("ship1", "1")
//		};
		String data = "ship1: " + "1";
		
		// write data
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(data.getBytes());

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : ");
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
