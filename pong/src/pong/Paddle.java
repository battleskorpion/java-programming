package pong;

import java.awt.Graphics;

public abstract class Paddle {
	protected double y;
	protected double yVelocity; 
	protected boolean upAcceleration;
	protected boolean downAcceleration; 
	protected int player;
	protected int x; 
	
	public void draw(Graphics g) {
		
	}
	public void move() {
		
	}
	public int getY() {
		return (int)y; 
	}
}
