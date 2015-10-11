// main game gui
// (rhyming accidentally)
import java.awt.*;
import javax.swing.*;

public class YourGrid extends JFrame {

	JButton[] shipArray;
	GridLayout battleGrid; // layout for battleFrame
	
	JPanel battlePanel; // frame to hold battleships
	
	YourGrid() {
		

		battleGrid = new GridLayout(10, 10);
		
		battlePanel = new JPanel();
		battlePanel.setBounds(10, 20, 666, 666);
		battlePanel.setLayout(battleGrid);
		battlePanel.setVisible(true);
		
		shipArray = new JButton[100];
		
		for (int i = 0; i < shipArray.length; i++) {
			shipArray[i] = new JButton("X");
			battlePanel.add(shipArray[i]);
		}
		
		
		this.setTitle("Battleship!");
//		this.setBackground(Color.BLUE);
		this.setVisible(true);
		this.setBounds(10, 10, 1000, 750);
		
		this.add(battlePanel);		
		this.setVisible(true);
		
		
//		this.add();
	}
	
	
}
