package GuiElements;
// main game gui
// (rhyming accidentally)
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.*;

public class YourGrid extends JFrame {

	boolean isPlayerOne;
	
	JPanel actionPanel; // holds buttons, menu, etc
	
	JButton[] shipArray;	// array for YOUR alwhe;iwahtships (bruh)
	GridLayout battleGrid; // layout for battleFrame
	
	JPanel battlePanel; // frame to hold your battleships
	JPanel opponentPanel; // opponent's ships(?) where u send ur attacks
	
	// for sending attacks u mormon
	JToggleButton[][] attackButtonArray = new JToggleButton[10][10];
	
	JTextArea yourAttackStatus; // If your attack was a hit or miss
	
	JButton confirmAttack; // to send out the attack fo sho
	
	
	// so far this variable has proved to be as useful as [redacted] 
	int turnNumber = 1;
	
	
	
	
	// Construction tools
	public YourGrid(int[][] nadgir, boolean dor) {
		
		isPlayerOne = dor;
		
		// ishan was here
		for (int z = 0; z < 14; z++) {
		    for (int y = 0; y < 2; y++) {
		    	System.out.println("nadgir values" + nadgir[z][y]);
		   }
		}
		
		System.out.println("we are here in yourgrid");
		
		yourAttackStatus = new JTextArea("");
		
		// actionpanel stuff
		actionPanel = new JPanel();
		actionPanel.setBounds(0, 0, 1000, 200);
		actionPanel.add(new JLabel("\n\nTurn #" + turnNumber));
		
		
		
		// battlepanel stuff (your grid = battlepanel)
		battleGrid = new GridLayout(10, 10);
		
		battlePanel = new JPanel();
		battlePanel.setBounds(10, 20, 300, 300);
		battlePanel.setAlignmentX(25);
		battlePanel.setAlignmentY(100);
		battlePanel.setPreferredSize(new Dimension(500, 500));
		battlePanel.setLayout(battleGrid);
		battlePanel.setVisible(true);
		battlePanel.setBackground(Color.BLUE);
		
		shipArray = new JButton[100];
		
		for (int i = 0; i < 100; i++) {
				
			shipArray[i] = new JButton("X");		
			battlePanel.add(shipArray[i]);
		
		}
		
		
		// opponent panel stuff to send attacks
		opponentPanel = new JPanel();
		opponentPanel.setBounds(350, 20, 300, 300);
		opponentPanel.setPreferredSize(new Dimension(550, 550));
		opponentPanel.setAlignmentX(25);
		opponentPanel.setAlignmentY(400);
		opponentPanel.setLayout(new GridLayout(11,10));
		opponentPanel.setVisible(true);
		opponentPanel.setBackground(Color.red);


		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
			
			
			attackButtonArray[i][j] = new JToggleButton("O");
			opponentPanel.add(attackButtonArray[i][j]);
			
			attackButtonArray[i][j].addActionListener(new ActionListener() {


				// send an attack to the serber
				@Override
				public void actionPerformed(ActionEvent e) {
					
					JToggleButton deezNadgirs = (JToggleButton) e.getSource();
					deezNadgirs.setSelected(false);
					deezNadgirs.setText("BOOM!");
					
					// set all the other buttons to be unselected
					for (int i = 0; i < 10; i++)
						for (int j = 0; j < 10; j++) {
							
							// not the button we want
							if (attackButtonArray[i][j].equals(deezNadgirs))
									;
							
							else {
								attackButtonArray[i][j].setText("O");
								attackButtonArray[i][j].setSelected(true);
							}
									// ayy lmao
									
						}
					
//					String sampleText = e.getSource().toString();
//					
//					// send attack to the server
//					try {
//						acks(sampleText);
//					}
//					catch (IOException r) {
//						r.printStackTrace();
//					}
//					
					
					
				} // end of actionPerformed
				
			}); // end of attackButtonArray
			
			} // end of inner for loop
		} // end of for
		
		
		confirmAttack = new JButton("Confirm");
		confirmAttack.setPreferredSize(new Dimension(100, 25));
		
		confirmAttack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// send da attack
				String whichVon = "";
				
				for (int i = 0; i < 10; i++)
					for (int j = 0; j < 10; j++) {
						
						if (attackButtonArray[i][j].getText().equals("BOOM!")) 
							whichVon = i + " " + j;
						
						
					} // end of inner for
			
				try {
					sendAttacks(whichVon);
				} 
				catch (IOException e1) {
					e1.printStackTrace();
				} // end of tryCatch
				
			} // end of actionPerformed
			
		}); // end of actionlistener 
		
		// TODO ADD A JSCROLLPANE
//		previousAttacks = new JTextArea("Sample text", 20, 1);
//		previousAttacks.setEditable(false);
//		previousAttacks.
		
		
		this.setTitle("Your $hips!");
		this.setBackground(Color.BLUE);
		this.setVisible(true);
		this.setBounds(0, 0, 1280, 1080);
		
		// add the stuff 
		this.setLayout(new BorderLayout());
		this.add(battlePanel, BorderLayout.WEST);
		this.add(opponentPanel, BorderLayout.EAST);
		this.add(confirmAttack, BorderLayout.NORTH);
		this.add(yourAttackStatus, BorderLayout.CENTER);
//		this.setBackground(Color.CYAN);
//		this.add(battlePanel);
//		this.add(opponentPanel);
		this.setVisible(true);
		
		
		
//		this.add();
	} // end of constructor
	
	
	
	
	
	// send some attacks to the serber
	private void sendAttacks(String whichVon) throws IOException {
		
		System.out.println(whichVon);
		
		// the sweet url to send the bitter attacks
		String url = "http://localhost:3000/attackOnTitan1";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "User 1");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(whichVon);
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
		
		// check if there was a hit
		if (response.toString().contains("HIT!"))
			yourAttackStatus.setText("Your attack was a hit!");
		
		else
			yourAttackStatus.setText("Your attack was a miss ¯\\_(ツ)_/¯");

		
	}
	
	
}
