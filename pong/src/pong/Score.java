package pong;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Score extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	private int score_player1; 
	private int score_player2;
	private final int board_width; 
	private final int board_height; 
	private final int width = 80; 
	private final int height = 32; 
	private final Font font; 
	
	public Score(int board_width, int board_height) {
		/* set up board dimensions */ 
		this.board_width = board_width; 
		this.board_height = board_height; 
		font = new Font(Font.SANS_SERIF, Font.PLAIN, 30); 
		
		/* set up super class (Rectangle) variables */ 
		super.width = width; 
		super.height = height; 
		//super.x = (board_width / 2) - (width / 2); 
		//super.y = 10; 
	}
	
	public void draw(Graphics g) {
		String score_string = score_player1 + " | " + score_player2; 	
		x = (board_width / 2) - (g.getFontMetrics(font).stringWidth(score_string) / 2); 
		y = 5; 
		
		g.setColor(Color.white);
		g.setFont(font);
		g.drawString(score_string, x, y + g.getFontMetrics().getAscent());
	}
	
	public int incrementScore(int player) {
		if (player == 1) {
			score_player1 += 1; 
			return score_player1; 
		}
		else if (player == 2) {
			score_player2 += 1;
			return score_player2; 
		}
		return -1; 
	}
	
	public void resetScore() {
		score_player1 = 0; 
		score_player2 = 0; 
	}
	
}
