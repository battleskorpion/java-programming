package blackjack;

import java.util.ArrayList;

public class Hand 
{
	/* variables */ 
	private ArrayList<Card> cards;				// cards in hand
	
	/* constructors */ 
	public Hand()
	{
		cards = new ArrayList<Card>(); 
	}

	public Hand(ArrayList<Card> cards)
	{
		this.cards = cards; 
	}
	
	/* methods */ 
	public ArrayList<Card> cards() 
	{
		return cards; 
	}

	public int size() 
	{
		return cards.size(); 
	}
	
	public void add(Card card) 
	{
		cards.add(card); 
	}
	
	public void add(CardValues value, CardSuits suit)
	{
		cards.add(new Card(value, suit)); 
	}
	
	// isnt necessary for every card game but will still add it
	public boolean remove(int index)
	{
		boolean removed = true; 	// will be set to false if error occurs
		try
		{
			cards.remove(index); 
		}
		catch (IndexOutOfBoundsException exc)
		{
			removed = false; 
		}
		
		return removed; 
	}
	
	public boolean remove(Card card)
	{
		return cards.remove(card);
	}
	
	public String toString()
	{
		return cards.toString(); 		
	}
	
	public int handValue() 
	{
		return blackjackHandValue(cards);  
	}
	
	private int blackjackHandValue(ArrayList<Card> hand)
	{
		ArrayList<Integer> values = new ArrayList<Integer>(hand.size()); 
		int totalValue = 0; 									// total hand value
		int aceValue = CardValues.ACE.value(); 					// value of a "soft" ace
		int hardAceValue = CardValues.ACE.hardValue();			// value of a "hard" ace value
		int aceValueDiff = hardAceValue - aceValue;				// difference between hard ace and soft ace values

        // first for assigning values, aces will all be given soft value for now
        for (Card card: hand) 
        {
            values.add(card.pointValue()); 
            totalValue += card.pointValue(); 
        }

        // find if there is ace(s) (only one matters actually) 
        // (change first ace value to hard value if thats ok)
        findAce: 
        for (int i = 0; i < values.size(); i++) 
        {
        	if (values.get(i) == aceValue) 					// if card is an ace
        	{					
				if (totalValue + (aceValueDiff) <= Blackjack.VALUE_LIMIT) 
				{
					values.set(i, hardAceValue);
					totalValue += (aceValueDiff);			
				}
				
				break findAce;								// ace card found, calculations complete
        	}
		}

        // final stuff and return
        return totalValue;
	}
}
