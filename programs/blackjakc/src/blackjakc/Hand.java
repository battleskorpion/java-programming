package blackjakc;

import java.util.Arrays;

public class Hand {
	private String[] hand;

	public Hand() {
		hand  = new String[0];
	}

	public Hand(int numCards, String[] cards) {
		hand = new String[numCards];
		hand = cards;
	}

	public void add(String card) {
		String[] temp = new String[hand.length + 1];
        System.arraycopy(hand, 0, temp, 0, hand.length);
		temp[temp.length - 1] = card;

		hand = temp;
	}

	public String[] getHand() {
		return hand;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < hand.length; i++) {
			str += hand[i];
			str += "\n";
		}
		// test
		// System.out.println(str.length());
		return str;
	}

	public int length() {
		return hand.length;
	}

	public int[] handValue(String game) {
		int[] values = new int[hand.length + 1]; // will also return total hand value at end
		int totalValue = 0;

		// games
		switch (game.toLowerCase()) {

		    // for blackjack
            case "blackjack":
                boolean isAce = false;
                int aceIndex = -1;

                // first for assigning values, aces will all be given 1 for now
                for (int i = 0; i < hand.length; i++) {
                    values[i] = Deck.cardValue(hand[i], hand, game);
                    totalValue += values[i];
                }

                // find if there is ace(s) (only one matters actually) (change first ace value to 11 if thats ok)
                for (int i = 0; i < hand.length; i++) {
					if (values[i] == 1) {
						aceIndex = i;
						if (totalValue + 10 <= 21) {
							values[i] = 11;
							totalValue += 10;
						}
						break;
					}
				}

                // final stuff and return
                values[values.length - 1] = totalValue;
                return values;

		    // end blackjack case=
            default:
                throw new IllegalStateException("Unexpected value: " + game.toLowerCase());

        } // end switch
	}

}
