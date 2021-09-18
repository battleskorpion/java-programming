package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Paddle extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	private final int width = 20; 					// width of paddle
	private final int height = 80; 					// height of paddle
	private final int BOARD_WIDTH;
	private final int BOARD_HEIGHT; 
	//protected double y;
	protected double yVelocity; 
	protected boolean upAcceleration;
	protected boolean downAcceleration; 
	private final double FRICTION = 0.9; 
	private final double ACCELERATION_RATE = 1; 
	private final double VELOCITY_LIMIT = 15; 	
	protected int player;
	//protected int x; 
	
	//public Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT) {
	//	super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT); 
	//}
	public Paddle(int BOARD_WIDTH, int BOARD_HEIGHT) {
		/* set up super class (Rectangle) variables */ 
		super.width = width; 
		super.height = height; 
		//super.x = x; 
		//super.y = (int)y; 
		
		this.BOARD_WIDTH = BOARD_WIDTH; 
		this.BOARD_HEIGHT = BOARD_HEIGHT; 
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, (int)y, width, height);
		
	}
	
	public void move() {
		if(upAcceleration) {
			yVelocity -= ACCELERATION_RATE; 
		}
		else if (downAcceleration) {
			yVelocity += ACCELERATION_RATE; 
		}
		else if (!upAcceleration && !downAcceleration) {
			yVelocity *= FRICTION; 
		}
		
		/* MAXIMUM VELOCITY */ 
		if (yVelocity > VELOCITY_LIMIT) {
			yVelocity = VELOCITY_LIMIT; 
		}
		else if (yVelocity < -VELOCITY_LIMIT) {
			yVelocity = -VELOCITY_LIMIT; 
		}
		
		y += yVelocity; 
	}
	
	public void checkCollision() {
		// stops paddle at edge 
		if (this.y <= 0) {
			this.y = 0; 
		}
		if (this.y >= (BOARD_HEIGHT - height)) {
			this.y = BOARD_HEIGHT - height; 
		}
	}
	
	public double getY() {
		return (int)y; 
	}
	
	public void setUpAcceleration(boolean acceleration) {
		upAcceleration = acceleration; 
	}
	
	public void setDownAcceleration(boolean acceleration) {
		downAcceleration = acceleration; 
	}
	
}
