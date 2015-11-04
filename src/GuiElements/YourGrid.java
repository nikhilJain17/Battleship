package GuiElements;
// main game gui
// (rhyming accidentally)
import java.awt.*;
import javax.swing.*;

public class YourGrid extends JFrame {

	JPanel actionPanel; // holds buttons, menu, etc
	
	JButton[] shipArray;	// array for ships (bruh)
	GridLayout battleGrid; // layout for battleFrame
	JPanel battlePanel; // frame to hold battleships
	
	int turnNumber = 1;
	
	public YourGrid() {
		
		// actionpanel stuff
		actionPanel = new JPanel();
		actionPanel.setBounds(0, 0, 1000, 200);
		actionPanel.add(new JLabel("\n\nTurn #" + turnNumber));
		
		
		
		// battlepanel stuff
		battleGrid = new GridLayout(10, 10);
		
		battlePanel = new JPanel();
		battlePanel.setBounds(10, 20, 300, 300);
		battlePanel.setAlignmentX(25);
		battlePanel.setAlignmentY(100);
		battlePanel.setPreferredSize(new Dimension(500, 500));
		battlePanel.setLayout(battleGrid);
		battlePanel.setVisible(true);
		
//		shipArray = new JButton[10][10];
		shipArray = new JButton[100];
		
		for (int i = 0; i < 100; i++) {
				
			shipArray[i] = new JButton("X");		
			battlePanel.add(shipArray[i]);
		
		}
		
		
		this.setTitle("Your NUTZ!");
//		this.setBackground(Color.BLUE);
		this.setVisible(true);
		this.setBounds(10, 10, 1000, 666);
		
		
		this.add(battlePanel);		
		this.add(actionPanel);
		this.setVisible(true);
		
		
//		this.add();
	}
	
	
}
