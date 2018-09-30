//This is the main program for the blackjack game.

public class Blackjack
{
	// This method should:
	//	- Ask the user how many people want to play (up to 3, not including the dealer).(good)
	//	- Create an array of players.(good)
	//	- Create a Blackjack window.(good)
	// 	- Play rounds until the players want to quit the game.(......half done)
	//	- Close the window.(good)
	public static void main(String[] args){
		System.out.println("How many players?");
		
		int numPlayer = IO.readInt();
		while(numPlayer > 3 || numPlayer < 1){
			System.out.println("Only up to 3 people can play at the same time." + "\n" + "Please enter in your number again:");
			numPlayer = IO.readInt();
		}
		
		Player[] players = new Player[numPlayer + 1]; // first player is the dealer and players behind
		
		players[0] = new Player("a", true);

		for(int i = 1; i < players.length; i++){
			System.out.println("Please enter in player's name:");
			String playerName = IO.readString();
			
			players[i] = new Player (playerName, false);// fix it, constructor
		}
		
		BlackjackWindow window = new BlackjackWindow(players);
		
		do{
			playRound(players, window);
			
			System.out.println("Do you want to continue this game? Please enter in either yes or no.");
			
		}while(!IO.readString().equals("no"));
		
		window.close();
		
	}

	// This method executes an single round of play (for all players).  It should:
	//	- Create and shuffle a deck of cards.
	//	- Start the round (deal cards) for each player, then the dealer.
	//	- Allow each player to play, then the dealer.
	//	- Finish the round (announce results) for each player.
	public static void playRound(Player[] players, BlackjackWindow window)
	{
		Deck deck = new Deck();
		deck.shuffle();
		  
		for(int i = 1; i < players.length; i ++){
			players[i].startRound(deck, window);
			window.redraw();
			System.out.println(players[i].getName() + ", your score is " + players[i].getHand().getScore() + ".");
		}
		
		players[0].startRound(deck, window);
		
		for(int i = 1; i < players.length; i ++){
			players[i].playRound(deck, window);
			window.redraw();
		}	
		
		players[0].playRound(deck, window);
		
		System.out.println("Dealer's score is " + players[0].getHand().getScore() + ".");
		for(int i = 1; i < players.length; i ++){
			players[i].finishRound(players[0].getHand().getScore(), window);
			window.redraw();
		}
		players[0].finishRound(players[0].getHand().getScore(), window);
	}
}
