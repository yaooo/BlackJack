

// This class represents one blackjack player (or the dealer)
public class Player
{
	// define fields here
	//String dealerName;
	String playerName;
	Hand hand;
	Boolean isDealer;
	
	// This constructor creates a player.
	// If isDealer is true, this Player object represents the dealer.
	public Player(String playerName, boolean isDealer)
	{
		if(!isDealer)
			this.playerName = playerName;
		else{
			this.playerName = "Dealer";
		}
		
		hand = new Hand();
		this.isDealer = isDealer;
	}

	// This method retrieves the player's name.
	public String getName()
	{
		return playerName;
	}
	
	public int getNumberOfCards(){
		return hand.getNumberOfCards();
	}
	
	public Card getCard(int i){
		return hand.getCard(i);
	}

	// This method retrieves the player's hand of cards.
	public Hand getHand()
	{
		return hand;
	}
	
	// This method deals two cards to the player (one face down if this is the dealer).
	// The window input should be used to redraw the window whenever a card is dealt.
	public void startRound(Deck deck, BlackjackWindow window)
	{
		Card one = deck.drawCard();
		window.redraw();
		Card two = deck.drawCard();
		window.redraw();
		hand.addCard(one);
		hand.addCard(two);
		
		if(isDealer == true){
			one.turnFaceUp();
			window.redraw();
			two.turnFaceDown();
			window.redraw();
		}
		else{
			one.turnFaceUp();
			window.redraw();
			two.turnFaceUp(); 
			window.redraw();
		}
			
		// complete this method
	}

	// This method executes gameplay for one player.
	// If this player is the dealer:
	//	- hits until score is at least 17
	// If this is an ordinary player:
	//	- repeatedly asks the user if they want to hit (draw another card)
	//	  until either the player wants to stand (not take any more cards) or
	//	  his/her score exceeds 21 (busts).
	// The window input should be used to redraw the window whenever a card is dealt or turned over.
	public void playRound(Deck deck, BlackjackWindow window){
		if(isDealer){
			hand.getCard(1).turnFaceUp();
			window.redraw();
			while(hand.getScore() < 17){
				Card dealerCard = deck.drawCard();
				window.redraw();
				dealerCard.turnFaceUp();
				window.redraw();
				hand.addCard(dealerCard);
			}
		}
		else{
			System.out.println(this.playerName + ", do you want to hit or stand? Please enter in either hit or stand.");
			String temp = IO.readString();
			
			while(!temp.equals("hit") && !temp.equals("stand")){
				System.out.println("Please enter in either hit or stand:");
				temp = IO.readString();
			}
			
			while(temp.equals("hit")){
				Card playerCard = deck.drawCard();
				window.redraw();
				hand.addCard(playerCard);
				playerCard.turnFaceUp();
				window.redraw();
				System.out.println(this.playerName + ", your score is " + hand.getScore() + ".");
				
				if(hand.getScore() > 21){
					System.out.println(this.playerName + ", you are busted.");
					break;
				}
				
				System.out.println(this.playerName +" ,do you want to hit or stand? Please enter in either hit or stand.");
				temp = IO.readString();
				while(!temp.equals("hit") && !temp.equals("stand")){
					System.out.println("Please enter in either hit or stand:");
					temp = IO.readString();
				}
			}
		}
		// complete this method
	}

	// This method informs the player about whether they won, lost, or pushed.
	// It also discards the player's cards to prepare for the next round.
	// The window input should be used to redraw the window after cards are discarded.
	public void finishRound(int dealerScore, BlackjackWindow window){
		if(isDealer != true){
			if(dealerScore <= 21 && dealerScore > hand.getScore()){
				System.out.println(this.playerName + " lost.");
			}
			else if(dealerScore <= 21 && hand.getScore() > 21){
				System.out.println(this.playerName + " lost.");
			}
			else if(dealerScore <= 21 && dealerScore == hand.getScore()){
				System.out.println(this.playerName + " pushed.");
			}
			else if(dealerScore < hand.getScore() && hand.getScore() <= 21){
				System.out.println(this.playerName + " won.");
			}
			else if(dealerScore > 21 && hand.getScore() < 21){
				System.out.println(this.playerName + " won.");
			}
			else if(dealerScore > 21 && hand.getScore() > 21){
				System.out.println(this.playerName + " lost.");
			}
			hand.discardAll();
			window.redraw();
		}else{
			hand.discardAll();
			window.redraw();
		}
	}
}
