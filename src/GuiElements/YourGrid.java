package GuiElements;
// main game gui
// (rhyming accidentally)
import java.awt.*;
import javax.swing.*;

public class YourGrid extends JFrame {

	JPanel actionPanel; // holds buttons, menu, etc
	
	JButton[] shipArray;	// array for YOUR alwhe;iwahtships (bruh)
	GridLayout battleGrid; // layout for battleFrame
	
	JPanel battlePanel; // frame to hold your battleships
	JPanel opponentPanel; // opponent's ships(?) where u send ur attacks
	
	// for sending attacks u mormon
	JButton[] attackButtonArray;
	
	// so far this variable has proved to be as useful as [redacted] 
	int turnNumber = 1;
	
	public YourGrid() {
		
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
		
//		shipArray = new JButton[10][10];
		shipArray = new JButton[100];
		
		for (int i = 0; i < 100; i++) {
				
			shipArray[i] = new JButton("X");		
			battlePanel.add(shipArray[i]);
		
		}
		
		
		// opponent panel stuff to send attacks
		opponentPanel = new JPanel();
		opponentPanel.setBounds(350, 20, 300, 300);
		opponentPanel.setPreferredSize(new Dimension(500, 500));
		opponentPanel.setAlignmentX(25);
		opponentPanel.setAlignmentY(400);
		opponentPanel.setLayout(new GridLayout(10,10));
		opponentPanel.setVisible(true);
		
		attackButtonArray = new JButton[100];
		
		for (int i = 0; i < 100; i++) {
			
			attackButtonArray[i] = new JButton("O");
			opponentPanel.add(attackButtonArray[i]);
			
		}
		
		
		this.setTitle("Your $hips!");
		this.setBackground(Color.BLUE);
		this.setVisible(true);
		this.setBounds(0, 0, 1280, 1080);
		
		this.setLayout(new BorderLayout());
		this.add(battlePanel, BorderLayout.WEST);
		this.add(opponentPanel, BorderLayout.EAST);
		this.setVisible(true);
		
		
//		this.add();
	}
	
	
}
