package blackjack;

public enum CardValues {
	/* enumerations */ 
	ACE 	(1, 11, "Ace"), 			
	TWO 	(2, "2"), 
	THREE 	(3, "3"), 
	FOUR 	(4, "4"), 
	FIVE 	(5, "5"), 
	SIX 	(6, "6"), 
	SEVEN 	(7, "7"),
	EIGHT	(8, "8"), 
	NINE	(9, "9"), 
	TEN		(10, "10"), 
	JACK	(10, "Jack"), 
	QUEEN	(10, "Queen"), 
	KING	(10, "King"); 
	
	/* enum class variables */ 
	private final int pointValue; 			// normal point value associated with card value
	private final int secondaryValue; 		// for "hard" point values such as with aces, or etc. 
	private final String name; 				// name of card value
	
	/* constructor */
	// pointValue: default point value of card
	CardValues(int pointValue, String name)
	{
		this.pointValue = pointValue; 
		this.secondaryValue = pointValue; 
		this.name = name; 
	}

	// pointValue: default point value of card
	// secondaryValue: secondary point value of card 
	CardValues(int pointValue, int secondaryValue, String name)
	{
		this.pointValue = pointValue; 
		this.secondaryValue = secondaryValue; 
		this.name = name; 
	}
	
	public String valueName() { return name; } 	// NOTE: "name" is already a function name
	public int value() { return pointValue; } 
	public int hardValue() { return secondaryValue; } 
}
