package pong;

import org.eclipse.swt.widgets.Shell;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class PongBoardPanel extends JPanel implements Runnable, KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	protected Shell shell;
	private static int KEY_MOVE_UP = KeyEvent.VK_UP; 
	private static int KEY_MOVE_DOWN = KeyEvent.VK_DOWN; 
	final int WIDTH = 900; 
	final int HEIGHT = 750; 
	private final Dimension SCREEN_DIMENSIONS = new Dimension(WIDTH, HEIGHT); 
	private Thread thread; 
	//protected Graphics g; 
	private PlayerPaddle paddle1; 
	private PlayerPaddle paddle2; 
	private TennisBall ball; 

	public PongBoardPanel() {
		paddle1 = new PlayerPaddle(1, WIDTH, HEIGHT); 
		paddle2 = new PlayerPaddle(2, WIDTH, HEIGHT); 
		ball = new TennisBall(WIDTH, HEIGHT); 
		this.setFocusable(true);
		this.addKeyListener(this);
		this.setPreferredSize(SCREEN_DIMENSIONS);
		
		thread = new Thread(this); 
		thread.start(); 
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	//public static void main(String[] args) {
	//	/********************/
	//	/* VARIABLE SECTION */
	//	/********************/
	//	PongBoard window = new PongBoard(); 
	//	
	//	/*******************/
	//	/* OPEN GUI WINDOW */
	//	/*******************/
	//	try {
	//		window.open(); 
	//	}
	//	catch(Exception exc) {
	//		exc.printStackTrace();
	//	}
	//}

	/********************************************/
	/* 				METHOD SECTION 				*/ 
	/********************************************/ 
	//public void open()
	///****************************************************************/
	///* PRECONDITION:  GUI INSTANCE NEEDS TO BE DISPLAYED            */
	///* POSTCONDITION: CREATES THE GUI DISPLAY AND OPENS THE DISPLAY	*/
	///****************************************************************/
	//{
	//	/********************/
	//	/* VARIABLE SECTION */
	//	/********************/
	//	Display display = Display.getDefault();			// MANAGES THE CONNECTION BETWEEN SWT 
	//													// AND THE UNsDERLYING OPERATING SYSTEM 
	//	
	//	/**********************************************/
	//	/* METHOD TO CREATE CONTENTS OF SHELL/DISPLAY */
	//	/**********************************************/
	//	createContents();
    //
	//	/****************************************/
	//	/* METHOD TO OPEN SHELL (ADD TO SCREEN) */
	//	/****************************************/
	//	shell.open();
	//	
	//	/******************************************************************/
	//	/* METHOD TO FORCE SHELL TO BE ACTIVE WINDOW (FOCUSED AND ON TOP) */
	//	/******************************************************************/
	//	shell.forceActive();							// SO WINDOW WILL BE FOCUSED WHEN CREATED
	//	
	//	/*************************************************/
	//	/* METHOD TO ENACT LAYOUT OF SHELL IF APPLICABLE */
	//	/*************************************************/
	//	shell.layout();
	//	
	//	//thread = new Thread(this); 
	//	//thread.start(); 
	//	run(); 
	//	
	//	/*********************************************************************************/
	//	/* WHILE SHELL IS NOT DISPOSED, SLEEP DISPLAY IF THERE IS NOTHING IT NEEDS TO DO */
	//	/*********************************************************************************/
	//	while (!shell.isDisposed()) 
	//	{
	//		if (!display.readAndDispatch()) 
	//		{
	//			display.sleep();
	//		}
	//	}
	//}
	//
	//protected void createContents()
	///****************************************************************/
	///* PRECONDITION:  CONTENTS OF WINDOW NEED TO BE CREATED         */
	///* POSTCONDITION: ADDS CONTENT TO WINDOW 						*/
	///****************************************************************/
	//{
	//	/* shell */ 
	//	shell = new Shell();
	//	shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
	//	shell.setSize(WIDTH, HEIGHT);
	//	shell.setText("SWT Application");
	//	shell.setLayout(null);
	//	
	//	Canvas canvas = new Canvas(shell, SWT.NONE);
	//	canvas.setBounds(10, 10, 864, 691);
	//	shell.addKeyListener(this);	// ?? 
	//	
	//	/* paddle 1 */ 
	//	paddle1 = new PlayerPaddle(1); 
	//	
	//}
	
	//public void draw(Graphics g) {
	//	paddle1.draw(g);
	//}
	
	public void paint(Graphics g) {
		g.setColor(Color.black); 
		g.fillRect(0, 0, WIDTH, HEIGHT);
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g); 
	}
	
	public void update(Graphics g) {
		paint(g); 
	}
	
	public void run() {
		for(;;) {
			paddle1.move(); 
			ball.move(); 
			checkCollision(); 
			repaint(); 
			try {
				Thread.sleep(10);	// ?? 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void checkCollision() {
		/* default collision checks of each object */ 
		paddle1.checkCollision();
		paddle2.checkCollision();
		ball.checkCollision(); 
		
		/* collision checks between objects */ 
		
		/* bounces ball off of paddles */
		if (ball.intersects(paddle1)) {
			ball.xVelocity *= -1;
			ball.xVelocity += ball.INCREMENT_VELOCITY; 
			if (ball.yVelocity > 0) {
				ball.yVelocity += ball.INCREMENT_VELOCITY; 		// more positive 
			}
			else {
				ball.yVelocity -= ball.INCREMENT_VELOCITY; 		// more negative
			}
			//ball.setXVelocity(ball.xVelocity);
			//ball.setYVelocity(ball.yVelocity); 
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KEY_MOVE_UP) {
			paddle1.setUpAcceleration(true);
		}
		else if(e.getKeyCode()  == KEY_MOVE_DOWN) {
			paddle1.setDownAcceleration(true);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()  == KEY_MOVE_UP) {
			paddle1.setUpAcceleration(false);
		}
		else if(e.getKeyCode()  == KEY_MOVE_DOWN) {
			paddle1.setDownAcceleration(false);
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
