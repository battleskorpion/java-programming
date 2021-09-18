package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class TennisBall extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	private final int BOARD_WIDTH;
	private final int BOARD_HEIGHT; 
	private final int BALL_DIAMETER = 16; 
	//protected double x; 
	//protected double y; 
	protected double xVelocity;
	protected double yVelocity; 
	protected double INITIAL_VELOCITY = 2; 
	protected double INCREMENT_VELOCITY = 0.5; 		// velocity increases by this amount when the ball bounces off of a paddle
	private Random random; 
	//private final double FRICTION = 0.9; 
	//private final double ACCELERATION_RATE = 1; 
	//private final double VELOCITY_LIMIT = 15; 	
	
	public TennisBall(int board_width, int board_height) {
		/* set up super class (Rectangle) variables */ 
		super.width = BALL_DIAMETER; 
		super.height = BALL_DIAMETER; 
		
		this.BOARD_WIDTH = board_width; 
		this.BOARD_HEIGHT = board_height; 
		
		/* set default location of ball */ 
		x = BOARD_WIDTH / 2 - (BALL_DIAMETER / 2); 
		y = BOARD_HEIGHT / 2 - (BALL_DIAMETER / 2); 
		
		/* velocity of ball */ 
		/* random generator for direction */ 
		random = new Random(); 
		/* random x */ 
		int randomX = random.nextInt(2); 
		if (randomX == 0) {
			randomX--; 
		}
		setXVelocity(randomX * INITIAL_VELOCITY);
		/* random y */ 
		int randomY = random.nextInt(2); 
		if (randomY == 0) {
			randomY--; 
		}
		setYVelocity(randomY * INITIAL_VELOCITY); 
	
	}
	
	public void setXVelocity(double xDirection) {
		xVelocity = xDirection; 
	}
	
	public void setYVelocity(double yDirection) {
		yVelocity = yDirection; 
	}

	public void move() {
		x += xVelocity; 
		y += yVelocity; 
	}
	
	public void draw(Graphics g) { 
		g.setColor(Color.white); 
		g.fillOval((int)x, (int)y, BALL_DIAMETER, BALL_DIAMETER);
	}
	
	public void checkCollision() {
		/* bounces ball off of top and bottom walls */ 
		if (this.y <= 0) {
			this.setYVelocity(-this.yVelocity); 
		}
		if (this.y >= BOARD_HEIGHT - BALL_DIAMETER) {
			this.setYVelocity(-this.yVelocity);
		}
	}
	
}
