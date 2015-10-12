package GuiElements;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

// To place your ships on the board

public class SetupShipsGui extends JFrame {

	JButton[] setShips; // array to hold where you want to place the ships
	GridLayout gridLayout;
	
	final int SHIPS = 10;
	
	public SetupShipsGui() {
		
		this.setTitle("Place your ships");
		
		setShips = new JButton[100];
		gridLayout = new GridLayout(10, 10);
		this.setLayout(gridLayout);
		
		// init the frame
		for (int i = 0; i < 100; i++) {
			
			setShips[i] = new JButton("No Ship");
			setShips[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// Changes text on the Button
					if (((AbstractButton) e.getSource()).getText().equals("No Ship"))
						((AbstractButton) e.getSource()).setText("Ship Set");
					
					else
						((AbstractButton) e.getSource()).setText("No Ship");
					
				}
				
			}); // end of action Listener
			
			this.add(setShips[i]);
			
		} // end of for loop
		
		this.setBounds(0, 0, 450, 450);
		this.setVisible(true);
		
	} // end of constructor
	
	
	
	// once you are done, start YourGridGui
	
}
