package GuiElements;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.*;

import com.sun.net.ssl.HttpsURLConnection;

// To place your ships on the board

public class SetupShipsGui extends JFrame {

	JButton[] setShips; // array to hold where you want to place the ships
	GridLayout gridLayout;
	
	JButton submitBtn; // to confirm the ships location, post that shit, and progress
	
	final int SHIPS = 10;
	
	public SetupShipsGui() {
		
		this.setTitle("Place your ships");
		
		setShips = new JButton[100];
		gridLayout = new GridLayout(11, 10);
		this.setLayout(gridLayout);
		
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
			
			this.add(setShips[i]);
			
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
					JOptionPane.showMessageDialog(SetupShipsGui.this, "You need at least 10 ships!", 
							"Come on", 2, null);
				} // end of else
				
			}
			
		});
		
		
		this.add(submitBtn);
		JLabel dontreadthis = new JLabel("<html><b>\tPlace " + SHIPS + " ships</b></html>");
		dontreadthis.setPreferredSize(new Dimension());
		this.add(dontreadthis);
		this.setBounds(0, 0, 1280, 750);
		this.setVisible(true);
		
	} // end of constructor
	
	private void deleteMe() {
		this.setVisible(false);
	}
	
	private void sendPost() throws Exception {

		String url = "http://54b7b6a3.ngrok.io/user1_ships";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

		String urlParameters = "shiplocations=12345";
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
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
	
	
	// once you are done, start YourGridGui
	
}
