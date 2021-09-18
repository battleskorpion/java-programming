package pong;

import java.awt.Color;

import javax.swing.JFrame;

public class PongBoardFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	PongBoardPanel pongPanel; 
	
	public PongBoardFrame() {
		pongPanel = new PongBoardPanel(); 
		this.add(pongPanel); 
		this.setTitle("Pong"); 
		this.setResizable(false); 
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.pack(); 
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		// temp
		
	}
}
