package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Paddle extends Rectangle {
	
	private static final long serialVersionUID = 1L;
	
	protected final int width = 20; 					// width of paddle
	protected final int height = 80; 					// height of paddle
	protected final int board_width;
	protected final int board_height; 
	protected final int DISTANCE_FROM_EDGE = 5; 
	protected double yVelocity; 
	protected boolean upAcceleration;
	protected boolean downAcceleration; 
	protected final double FRICTION = 0.9; 
	protected final double ACCELERATION_RATE = 1; 
	protected final double VELOCITY_LIMIT = 7.5; 	
	protected int player;
	
	public Paddle(int board_width, int board_height) {
		/* set up super class (Rectangle) variables */ 
		super.width = width; 
		super.height = height; 

		this.board_width = board_width; 
		this.board_height = board_height; 
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
		if (this.y >= (board_height - height)) {
			this.y = board_height - height; 
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
