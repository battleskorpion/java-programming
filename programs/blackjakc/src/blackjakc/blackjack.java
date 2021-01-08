package blackjakc;

import java.awt.*;

// imports
import javax.swing.*;
public class blackjack {

	// variables
	public static String title = "Blackjack";

	public static void main(String[] args) {
		Deck deck = new Deck();

		Hand playerHand = new Hand();
		Hand dealerHand = new Hand();
		Hand[] hands = {playerHand, dealerHand};

		JOptionPane.showMessageDialog(null, "blackjack!", title, JOptionPane.PLAIN_MESSAGE);

		// starting deal
		initDeal(deck, hands);

		displayHands(hands);

		// let player hit or stay
		dealPlayer(hands, 0, deck);

		displayWinner(calculateWinner(hands));

	}

	static void initDeal(Deck deck, Hand[] hands) {
		int deal_amt = 2;
		for (int i = 0; i < deal_amt; i++) {
			for (int j = 0; j < hands.length; j++) {
				hands[j].add(deck.deal());
				// testing
				//System.out.println(j + " Hand length: " + hands[0].length());
				//System.out.println(deck.deal());
			}

		}
	}

	static void displayHands(Hand[] hands) {
		JOptionPane.showMessageDialog(null, "Hand: \n" + hands[0].toString() + "\n"
				+ "Dealer's Hand: \n" + hands[1].toString(),
				title, JOptionPane.PLAIN_MESSAGE);
	}

	static void displayHands(Hand[] hands, Component window) {
		JOptionPane.showMessageDialog(window, "Hand: \n" + hands[0].toString() + "\n"
				+ "Dealer's Hand: \n" + hands[1].toString(),
				title, JOptionPane.PLAIN_MESSAGE);
	}

	static void dealPlayer(Hand[] hands, int handIndex, Deck deck) {

		String[] options = {"Hit", "Stay"};
		int choice;

		do {
			Container pane1;
			Panel panel1 = new Panel();
			JFrame window1 = new JFrame();
			window1.setTitle(title);
			window1.setSize (600, 600);
			window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			panel1.setBackground(Color.white);
			pane1 = window1.getContentPane(); 			// SETS PANE1 AS OUTPUT WINDOW CONTENT PANE
			pane1.add(panel1);
			window1.setVisible(true);

			choice = JOptionPane.showOptionDialog(window1, "Hit or Stay?", title, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
			if (options[choice].equals("Hit")) {
				hands[handIndex].add(deck.deal());
				displayHands(hands, window1);
			}
		}
		while (options[choice].equals("Hit"));

	}

	static String calculateWinner(Hand[] hands) {
		int player = 0;
		int dealer = 1;

		if (hands[player].handValue(title)[hands[player].length()] > 21) {
			return "dealer";
			// player busted
		}
		else if (hands[dealer].handValue(title)[hands[dealer].length()] == 21) {
			return "dealer";
			// dealer got 21
		}
		else if (hands[dealer].handValue(title)[hands[dealer].length()] > 21) {
			return "player";
			// dealer busted, player did not
		}
		else if (hands[dealer].handValue(title)[hands[dealer].length()] > hands[player].handValue(title)[hands[player].length()]) {
			return "dealer";
			// dealer exceeded player
		}
		else if (hands[dealer].handValue(title)[hands[dealer].length()] == hands[player].handValue(title)[hands[player].length()]) {
			return "push";
			// push
		}

		else if (hands[dealer].handValue(title)[hands[dealer].length()] < hands[player].handValue(title)[hands[player].length()]) {
			return "player";
			// player exceeded dealer
		}

		return "idk";
		// idk

  	}

  	static void displayWinner(String winner) {
		WinnerDisplayDialog winnerDisplay = new WinnerDisplayDialog();

		winnerDisplay.setWinnerNameText(winner);

		winnerDisplay.pack();
		winnerDisplay.setVisible(true);
	}

}
