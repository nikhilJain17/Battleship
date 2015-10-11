package GuiElements;
// Creates a gui to enter name
// And thats about it

import java.awt.*;
import javax.swing.*;

public class LaunchGameGui extends JPanel{
	
	JFrame mainFrame; // ayy lmao
	JTextArea enterUsername;
	JButton startGame;
	
	// constructor
	LaunchGameGui() {
		
		// create and show gui
		this.setBounds(10, 10, 500, 500);
		
		mainFrame = new JFrame();
		mainFrame.setTitle("Battleship!");
		mainFrame.setSize(500, 500);
		mainFrame.setLayout(null); // absolute layout
		
		enterUsername = new JTextArea();
		enterUsername.setBounds(100, 100, 100, 50);
		enterUsername.setPreferredSize(new Dimension(100, 50));
		enterUsername.setVisible(true);
		
		startGame = new JButton("Start Game");
		startGame.setLocation(100, 150);
		startGame.setPreferredSize(new Dimension(100, 50));
		startGame.setVisible(true);
		
		mainFrame.add(startGame);
		mainFrame.add(enterUsername);
		mainFrame.setVisible(true);
		this.add(mainFrame);
		this.show();
	
	}

	
	
	
}
