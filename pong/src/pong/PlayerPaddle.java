package pong;

public class PlayerPaddle extends Paddle {
	
	private static final long serialVersionUID =1L;

	public PlayerPaddle(int player, int board_width, int board_height) {
		super(board_width, board_height); 
		//super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT); 
		upAcceleration = false;
		downAcceleration = false; 
		y = 210;
		yVelocity = 0; 
		this.player = player; 
		if (player == 1) {
			x = DISTANCE_FROM_EDGE; 
		}
		else {
			x = board_width - width - DISTANCE_FROM_EDGE; 
		}
	}
	
}
