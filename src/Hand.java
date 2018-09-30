

// This class represents the set of cards held by one player (or the dealer).
public class Hand
{
	// define fields here
	Card[] hand;
	// This constructor builds a hand (with no cards, initially).
	
	public Hand()
	{
		hand = new Card[12];
		// complete this method
	}
	
	// This method retrieves the size of this hand.
	public int getNumberOfCards()
	{
		int number = 0;
		for(int i = 0 ; i < 12 ; i++){
			if(hand[i] == null) continue;
			if(hand[i].getValue() != 0) number ++;
		}
		return number;
	}

	// This method retrieves a particular card in this hand.  The card number is zero-based.
	public Card getCard(int index)
	{
		return hand[index];
	}

	// This method takes a card and places it into this hand.
	public void addCard(Card newcard)
	{
		for(int i = 0; i < 12; i++){
			if (hand[i] == null){
				hand[i] = newcard;
				break;
			}
		}
		// complete this method
	}

	// This method computes the score of this hand.
	public int getScore()
	{
		int score = 0;
		for(int i = 0; i < 12; i++){
			if(hand[i] == null) break;
			if(hand[i].getValue() != 0)
				score += hand[i].getValue();
		}
		
		if(score > 21){
			for(int i = 0; i < 12; i++){
				if(hand[i] == null) continue;
				if(hand[i].getFace() == 1){
					score -= 10;
					if(score < 21)
						break;
					if(hand[i] == null)
						break;
				}
			}
		}
		return score;
	}

	// This methods discards all cards in this hand.
	public void discardAll()
	{
		hand = new Card[12];
		// complete this method
	}
}
