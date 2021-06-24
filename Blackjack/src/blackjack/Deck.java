package blackjack;

import java.util.ArrayList;

public class Deck 
{
	/* variables */ 
	private int deckSize = 52;										// standard size of one deck for the french-suited deck
	private int numDecks = 1; 										// blackjack is typically played with 1 to 8 decks 
	private ArrayList<Card> deck; 									// consists of either one deck or multiple decks of cards
	
	/* constructors */ 
	public Deck () 
	{
		newDeck();
	}
	
	/* methods */ 
	private void newDeck() 
	{
		deck = new ArrayList<Card>(deckSize * numDecks); 
		
		for (int i = 0; i < deckSize; i++) {
			for (CardValues value: CardValues.values()) {
				for (CardSuits suit: CardSuits.values()) {
					deck.add(new Card(value, suit)); 
				}
			}
		}
	}
	
	public Card deal()
	{
		return deck.remove(0); 			// removes card from deck and returns the removed card 
	}
	
	public String toString()
	{
		return deck.toString(); 		
	}
}
