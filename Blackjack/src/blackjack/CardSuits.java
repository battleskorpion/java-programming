package blackjack;

public enum CardSuits {
	/* enumerations */ 
	HEARTS		("Hearts"),
	DIAMONDS	("Diamonds"),		// TODO: OR TILES
	CLOVERS 	("Clovers"),		// TODO: OR CLUBS
	SPADES		("Spades");			// TODO: OR PIKES
	
	/* enum class variables */ 
	private final String name; 		// name of card value
	
	/* constructor */
	// gameValue: default value of card in game 
	CardSuits(String name)
	{
		this.name = name; 
	}
	
	public String suitName() { return name; } 	// NOTE: "name" is already a function name
}
