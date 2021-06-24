package blackjack;

import java.awt.Color;
import java.awt.Container;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Blackjack 
{
	// variables
	public static final String title = "Blackjack";
	public static final int VALUE_LIMIT = 21; 					// 21 is max in Blackjack, over 21 is a bust. 
	public static final int INITIAL_CARDS = 2; 					// number of cards to deal initially
	
	public static void main(String[] args)
	{
		Deck deck = new Deck();
		
		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();
		ArrayList<Hand> hands = new ArrayList<Hand>(); 			// should be in the dealing order (dealer last)
		hands.add(playerHand);
		hands.add(dealerHand); 
		
		JOptionPane.showMessageDialog(null, title + "!", title, JOptionPane.PLAIN_MESSAGE);
		
		// starting deal
		initialDeal(deck, hands);

		displayHands(hands);

		// let player hit or stay
		dealPlayer(deck, hands, 0);

		displayWinner(calculateWinner(hands));
	}
	
	static void initialDeal(Deck deck, ArrayList<Hand> hands) 
	{
		for (int i = 0; i < INITIAL_CARDS; i++) 
		{
			for (int j = 0; j < hands.size(); j++) 
			{
				hands.get(j).add(deck.deal());
				// testing
				//System.out.println(j + " Hand length: " + hands[0].length());
				//System.out.println(deck.deal());
			}
		}
	}
	
	static void displayHands(ArrayList<Hand> hands) 
	{
		JOptionPane.showMessageDialog(null, "Hand: \n" + hands.get(0).toString() + "\n"
				+ "Dealer's Hand: \n" + hands.get(hands.size() - 1).toString(),
				title, JOptionPane.PLAIN_MESSAGE);
	}
	
	static void dealPlayer(Deck deck, ArrayList<Hand> hands, int handIndex) 
	{
		String[] options = {"Hit", "Stay"};
		int choice;

		do 
		{
			choice = JOptionPane.showOptionDialog(null, "Hit or Stay?", title, 
					JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, 
					null, options, options[1]);
			if (options[choice].equals("Hit")) {
				hands.get(handIndex).add(deck.deal());
				displayHands(hands);
			}
		}
		while (options[choice].equals("Hit"));

	}
	
	static String calculateWinner(ArrayList<Hand> hands) 
	{
		int player = 0;
		int dealer = hands.size() - 1; 		// dealer is last because they deal themselves last 
		
		if (hands.get(dealer).handValue() > VALUE_LIMIT) {
			return "dealer";
			// player busted
		}
		else if (hands.get(dealer).handValue() == VALUE_LIMIT) {
			return "dealer";
			// dealer got 21
		}
		else if (hands.get(dealer).handValue() > VALUE_LIMIT) {
			return "player";
			// dealer busted, player did not
		}
		else if (hands.get(dealer).handValue() > hands.get(player).handValue()) {
			return "dealer";
			// dealer exceeded player
		}
		else if (hands.get(dealer).handValue() == hands.get(player).handValue()) {
			return "push";
			// push
		}
		
		else if (hands.get(dealer).handValue() < hands.get(player).handValue()) {
			return "player";
			// player exceeded dealer
		}

		return "idk";
		// idk

  	}

  	static void displayWinner(String winner) 
  	{
		JOptionPane.showMessageDialog(null, winner, title, JOptionPane.PLAIN_MESSAGE); 
	}
}
