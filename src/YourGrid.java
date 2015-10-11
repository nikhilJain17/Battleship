// main game gui
// (rhyming accidentally)
import javax.swing.*;

public class YourGrid extends JPanel {

	JFrame mainFrame;
	JButton[] buttonArray;
	
	
	YourGrid() {
		
		mainFrame = new JFrame();
		mainFrame.setVisible(true);
		mainFrame.setBounds(10, 10, 1000, 750);
				
		
		
		this.add(mainFrame);
	}
	
	
}
