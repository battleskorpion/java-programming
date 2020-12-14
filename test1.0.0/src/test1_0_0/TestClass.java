package test1_0_0;

import java.awt.Color;
import java.awt.Container;

import javax.swing.*;
import java.util.*;

public class TestClass {
	public static void main(String[] args){
		//setupGUI(); 
		
		ArrayList<String> deck = setupCardDeck(); 
		
		//int again; 
		
		//do { 
			game(deck); 
		//	do {
		//		again = 0; 
		//		String again_in = JOptionPane.showInputDialog("Again? 0 or 1", ""); 
				//if ((again_in == "Yes") || (again_in == "yes")) 
		//			again = 1; 
				//else if ((again_in == "No") || (again_in == "no"))
		//			again = 0; 
				//else 
				//	System.out.println(again_in);
				//	System.out.println(again); 
				//	again = -1; 	
		//		again = Float.ParseFloat(again_in); 
		//	} 
		//	while (again == -1); 
		//} 
		//while (again == 1); 
		
	}
	
	public static void setupGUI() {
		JFrame theGUI = new JFrame();
		theGUI.setTitle("b");
		theGUI.setSize(1000, 600);
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel1 = new JPanel(); 
	    panel1.setBackground(Color.black);
	    Container pane = theGUI.getContentPane();
	    pane.add(panel1);
	    
		theGUI.setVisible(true);
	}
	
	public static ArrayList<String> setupCardDeck() { 
		ArrayList<String> card_deck = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			card_deck.add("Ace");
			card_deck.add("2");
			card_deck.add("2");
			card_deck.add("2");
			card_deck.add("2");
			card_deck.add("3");
			card_deck.add("4");
			card_deck.add("5");
			card_deck.add("6");
			card_deck.add("7");
			card_deck.add("8");
			card_deck.add("9");
			card_deck.add("10");
			card_deck.add("King");
			card_deck.add("Queen");
			card_deck.add("Jack");
		}
		// testing 
		//System.out.println("Original List : \n" + card_deck + "\n");
		Collections.shuffle(card_deck); 
		//System.out.println("\nShuffled List : \n" + card_deck  + "\n"); 
		return (card_deck); 
	}
	
	public static int handValue(ArrayList<String> hand) { 
		int hand_value = 0; 
		for (int i = 0; i < hand.size(); i++) { 
			switch (hand.get(i)) {
			case "Ace": 
				hand_value += 1; 
				break; 
			case "2":
				hand_value += 2; 
				break; 
			case "3": 
				hand_value += 3; 
				break; 
			case "4": 
				hand_value += 4; 
				break; 
			case "5": 
				hand_value += 5; 
				break; 
			case "6": 
				hand_value += 6; 
				break; 
			case "7": 
				hand_value += 7; 
				break; 
			case "8": 
				hand_value += 8; 
				break; 
			case "9":
				hand_value += 9; 
				break; 
			case "10": 
				hand_value += 10; 
				break; 
			case "King": 
				hand_value += 10; 
				break; 
			case "Queen": 
				hand_value += 10; 
				break; 
			case "Jack": 
				hand_value += 10; 
				break; 
			}
		}
		return (hand_value); 
	}
	
	public static void game(ArrayList<String> deck) { 
		ArrayList<String> hand = new ArrayList<String>();
		ArrayList<String> dealer_hand = new ArrayList<String>();
		int dealer_value = 0; 
		int player_value = 0; 
		
		for (int i = 0; i < 2; i++) { 
			hand.add(deck.get(0)); 
			deck.remove(0); 
			System.out.println(hand.get(hand.size() - 1));
			dealer_hand.add(deck.get(0)); 
			deck.remove(0);
		}
		
		boolean hit = true; 
		while (hit == true) {	
			String hit_in = JOptionPane.showInputDialog("Hit? true or false", "0"); 
			hit = Boolean.parseBoolean(hit_in); 
			if (hit) { 
				hand.add(deck.get(0)); 
				deck.remove(0); 
				System.out.println(hand.get(hand.size() - 1));
			} 
		} 
		
		dealer_value = handValue(dealer_hand);
		player_value = handValue(hand);
		if (player_value > 21) { 
			System.out.println("Busted. Dealer won"); 
		}
		else {
			while (handValue(dealer_hand) < 17) {
				dealer_hand.add(deck.get(0)); 
				deck.remove(0); 
			}
			dealer_value = handValue(dealer_hand); 
			
			System.out.println("\nDealer's hand: " + dealer_hand);
			System.out.println("Value: ");
			System.out.println(dealer_value + "\n"); 
			System.out.println("Players's hand: " + hand);
			System.out.println("Value: ");
			System.out.println(player_value + "\n"); 
			
			dealer_value = handValue(dealer_hand);
			player_value = handValue(hand);
			if (player_value == 21 && dealer_value != 21)
				System.out.println("Player won. (Blackjack)"); 
			else if ((player_value > 21))  
				System.out.println("Dealer won."); 
			else if ((dealer_value > 21) && (player_value <=21))
				System.out.println("Player won."); 
			else if ((player_value > dealer_value) && (player_value <= 21))
				System.out.println("Player won.");
			else if (player_value == dealer_value)
				System.out.println("Push."); 
			else if ((dealer_value > player_value) && (dealer_value <= 21))
				System.out.println("Dealer won."); 
			else 
				System.out.println("Error"); 
		}
	}
}




//String input1 = JOptionPane.showInputDialog("Enter : ", "0"); 			
//double radius = Double.parseDouble(input1); 