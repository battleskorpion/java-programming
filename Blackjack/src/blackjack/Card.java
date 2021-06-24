package blackjack;

public class Card 
{
	/* variables */ 
	private final CardValues value; 	// value of card		// value of individual card cannot (and should not) change 
																// (ie ace, 2, 3, 4) (point value can change ofc)
	private final CardSuits suit; 		// suit of card			// suit of individual card cannot (and should not) change
	private final String name;			// name of card			// name of individual card also cannot change

	/* constructors */ 
	public Card(CardValues value, CardSuits suit)
	{
		this.value = value;
		this.suit = suit; 
		this.name = value.valueName() + " of " + suit.suitName(); 
	}
	
	/* methods */ 
	public String cardName()
	{
		return name; 
	}
	
	public String toString()
	{
		return name; 
	}
	
	public CardValues value()
	{
		return value; 
	}
	
	public CardSuits suit()
	{
		return suit; 
	}
	
	public int pointValue()
	{
		return value.value();
	}
	
	// get file location of image 
	public String getImageAddress()
	{
		return value.name() + "_of_" + suit.name() + ".jpg"; 
	}
}
