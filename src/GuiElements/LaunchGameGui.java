package GuiElements;
// Creates a gui to enter name
// And thats about it

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LaunchGameGui extends JFrame {
	
	JTextArea enterUsername;
	JButton startGame;

	ButtonGroup collin;
	JRadioButton playerOne;
	JRadioButton playerTwo;
	
	boolean isPlayerOne;
	
	// constructor
	public LaunchGameGui() {
		
		// create and show gui
		this.setBounds(10, 10, 600, 600);
		
		// collin was here
		collin = new ButtonGroup();
		
//		mainFrame = new JFrame();
		this.setTitle("Battleship® created by Nikhil Jain, Ishan Vachhani, Rohan Shah. No Patent Intended.");
		this.setSize(600, 500);
		this.setLocation(420, 150); // @yy lm@0
		this.setLayout(new GridLayout(3,2));
		
		// a bit of cheeky design
		Color myColor = new Color(0, 255, 153);
		this.setBackground(myColor);
//		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)); // grid layout
		
		JLabel intro = new JLabel("<html><font size=\"6\" color=\"blue\">Battleship ®</font></html>");
		intro.setSize(500, 200);
//		intro.setLocation(70, 20);
//		intro.setHorizontalAlignment(250);
		
		startGame = new JButton("Start Game");
		startGame.setLocation(100, 150);
		startGame.setPreferredSize(new Dimension(100, 50));
		startGame.setVisible(true);
		startGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (playerOne.isSelected())
					isPlayerOne = true;
				
				else
					isPlayerOne = false;
				
				launchSetupShipsGui();
				deleteMe();
				
			}
			
		});
		
		
		playerOne = new JRadioButton("Player One", true);
		playerOne.setSize(100, 50);
		
		playerTwo = new JRadioButton("Player Two", false);
		playerTwo.setSize(100, 50);
		
		collin.add(playerOne);
		collin.add(playerTwo);
		
		// padding
		this.add(new JLabel());
		this.add(intro);
		
		this.setVisible(true);

		// padding
		this.add(new JLabel());
		this.add(playerOne);
		
		// padding
		this.add(new JLabel());
		this.add(playerTwo);

		// padding
		this.add(new JLabel());
		this.add(startGame);
		
		this.show();
		
	
	}
	
	// ingenius delete me function
	private void deleteMe() {
		this.setVisible(false);
	}
	
	// launch SetupShipsGui
	private void launchSetupShipsGui() {
		SetupShipsGui gui = new SetupShipsGui(isPlayerOne);
	}

	
	
	
}
