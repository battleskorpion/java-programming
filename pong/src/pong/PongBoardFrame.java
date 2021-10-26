package pong;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class PongBoardFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	PongBoardPanel pongPanel; 
	
	public PongBoardFrame() {
		pongPanel = new PongBoardPanel(this); 
		this.add(pongPanel); 
		this.setTitle("Pong"); 
		this.setResizable(true); 
		this.setBackground(Color.black);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		this.pack(); 
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		// temp
	}
	
	public void fullscreen() {
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
	}
		
}
