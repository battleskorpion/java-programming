package pong;

public class PlayerPaddle extends Paddle {

	public PlayerPaddle(int player) {
		upAcceleration = false;
		downAcceleration = false; 
		y = 210;
		yVelocity = 0; 
		if (player == 1) {
			x = 20; 
		}
		else {
			x = 860; 
		}
	}
	
}
