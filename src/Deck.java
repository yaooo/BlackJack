import java.util.Random;

// This class represents the deck of cards from which cards are dealt to players.
public class Deck
{
	// define fields here
	Card [] deck;
	int counter = 0;
	
	// This constructor builds a deck of 52 cards.
	public Deck()
	{
		// complete this method
		this.deck = new Card [52];
		int num = 0;
		
			for (int i = 0; i < 4; i++){
				for (int j = 1; j <= 13; j++){
					this.deck[num] = new Card(i,j); 
					num++;
				}
			}
	}

	// This method shuffles the deck (randomizes the array of cards).
	// Hint: loop over the cards and swap each one with another card in a random position.
	public void shuffle()
	{
		// complete this method
		for (int i = 0; i < deck.length; i++){
			int random = (int)(Math.random() * 52);
			Card temp = deck[i];
			deck[i] = deck [random];
			deck[random] = temp;
		}
		counter = 0;
	}
	
	public boolean isEmpty()
	{
		//fill this method in
		if (counter > 51)
			return true;
		else return false;
	}
	
	// This method takes the top card off the deck and returns it.
	public Card drawCard()
	{
		if (isEmpty() == true)
			shuffle();
			
		Card temp = this.deck[counter];
		counter++;
		return temp;
	}
	
	// This method returns the number of cards left in the deck.
	public int getSize()
	{
		return 52 - counter; // replace this line with your code
	}
}

