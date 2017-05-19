import java.util.ArrayList;
import java.util.Scanner;


public class BlackJackDriver {
	public static Deck deck;
	public static Player player;
	public static Player dealer;
	public static boolean exit;
	public static int topOfDeck;
	public static void main(String[] args){
		Scanner keyboard = new Scanner(System.in);
		initGame(keyboard);
		gamePlay(keyboard);
		keyboard.close();
	}
	
	public static void initGame(Scanner keyboard){
		String name;
		System.out.print("Name? ");
		name = keyboard.nextLine();
		player = new Player(name);
		System.out.println(player.getName() + " has " + player.getChipString());
		dealer = new Player();
		deck = new Deck();
		deck.initDeck();
		deck.setDeck(deck.shuffle(deck.getDeck()));
		//deck.setDeck(deck.getDeck());  //Proof that deck is properly made in order
		//deck.printDeck(); //Prints out ordered and shuffled deck
	}
	public static void gamePlay(Scanner keyboard){
		exit = false;
		while(player.getChips() > 0){
			topOfDeck = 0;
			deck.setDeck(deck.shuffle(deck.getDeck()));
			System.out.print("Bet? (0 to quit, Enter to stay at $25) ");
			String strBet = keyboard.nextLine();
			int bet = 0;
			boolean validBet = false;
			while(!validBet && strBet != null){
				if(strBet.isEmpty()){
					bet = 25;
					validBet = true;
				}
				else{
					try{
						bet = Integer.parseInt(strBet);
						if(strBet.equalsIgnoreCase("0")){
							keyboard.close();
							System.exit(0);
						}
						validBet = true;
					}
					catch (Exception e){
						System.out.println("Please enter a valid bet! (0 to quit, Enter to stay at $25) ");
						strBet = keyboard.nextLine();
					}
				}
			}
			boolean newHand = true;
			boolean roundOver = false;
			while(!roundOver){
				System.out.println("\nBet: " + bet);
				if(newHand){
					startingCards();
					newHand = false;
				}
				System.out.print("Dealer's Hand:");
				for(int i = 0; i < dealer.getHand().size(); i++){
					System.out.print(" " + dealer.getHand().get(i).getCard() + " (" + dealer.getHand().get(i).getCardSuit() + ")");
				}
				System.out.println("\nValue: " + dealer.getHandValue());
				System.out.print("Player's Hand:");
				for(int i = 0; i < player.getHand().size(); i++){
					System.out.print(" " + player.getHand().get(i).getCard() + " (" + player.getHand().get(i).getCardSuit() + ")");
				}
				System.out.println("\nValue: " + player.getHandValue());
				System.out.println("");
				System.out.println("Move? (hit/stay) ");
				String move = keyboard.nextLine();
				if(move.equalsIgnoreCase("h")){
					playerHit();
					if(newHand){
						startingCards();
						newHand = false;
					}
					if(player.getHandValue() > 21){
						System.out.println("\n" + player.getName() + " bust");
						player.setChips(player.getChips() - bet);
						player.setChipString();
						System.out.println("\n" + player.getName() + " has " + player.getChipString());
						System.out.println();
						roundOver = true;
					}
				}
				else if(move.equalsIgnoreCase("s")){
					boolean dealerStays = false;
					boolean dealerBusts = false;
					while(!dealerStays){
						if(dealer.getHandValue() < 17){
							dealerHit();
							System.out.println("\nBet: " + bet);
							System.out.print("Dealer's Hand:");
							for(int i = 0; i < dealer.getHand().size(); i++){
								System.out.print(" " + dealer.getHand().get(i).getCard() + " (" + dealer.getHand().get(i).getCardSuit() + ")");
							}
							System.out.println("\nValue: " + dealer.getHandValue());
							System.out.print("Player's Hand:");
							for(int i = 0; i < player.getHand().size(); i++){
								System.out.print(" " + player.getHand().get(i).getCard() + " (" + player.getHand().get(i).getCardSuit() + ")");
							}
							System.out.println("\nValue: " + player.getHandValue());
						}
						else if(dealer.getHandValue() >= 17 && dealer.getHandValue() <=21){
							dealerStays = true;
						}
						else if(dealer.getHandValue() >= 21){
							dealerBusts = true;
							dealerStays = true;
						}
					}
					if(dealerBusts){
						System.out.println("\nDealer busts");
						player.setChips(player.getChips() + bet);
						player.setChipString();
						System.out.println("\n" + player.getName() + " has " + player.getChipString());
						System.out.println();
					}
					else if(!dealerBusts && dealer.getHandValue() > player.getHandValue()){
						System.out.println("\nDealer wins");
						player.setChips(player.getChips() - bet);
						player.setChipString();
						System.out.println("\n" + player.getName() + " has " + player.getChipString());
						System.out.println();
					}
					else if(!dealerBusts && dealer.getHandValue() == player.getHandValue()){
						System.out.println("\nPlayer pushes with Dealer");
						player.setChipString();
						System.out.println("\n" + player.getName() + " has " + player.getChipString());
						System.out.println();
					}
					else if(!dealerBusts && dealer.getHandValue() < player.getHandValue()){
						System.out.println("\nPlayer wins");
						player.setChips(player.getChips() + bet);
						player.setChipString();
						System.out.println("\n" + player.getName() + " has " + player.getChipString());
						System.out.println();
					}				
					roundOver = true;
				}
			}
		}
	}
	public static void startingCards(){
		ArrayList<Card> playerCards = new ArrayList<Card>();
		ArrayList<Card> dealerCards = new ArrayList<Card>();
		playerCards.add(deck.deck.get(topOfDeck++));
		dealerCards.add(deck.deck.get(topOfDeck++));
		playerCards.add(deck.deck.get(topOfDeck++));
		player.setHand(playerCards);
		player.setHandValue();
		dealer.setHand(dealerCards);
		dealer.setHandValue();
	}
	public static void playerHit(){
		ArrayList<Card> hand = player.getHand();
		hand.add(deck.deck.get(topOfDeck++));
		player.setHand(hand);
		player.setHandValue();
	}
	public static void dealerHit(){
		ArrayList<Card> hand = dealer.getHand();
		hand.add(deck.deck.get(topOfDeck++));
		dealer.setHand(hand);
		dealer.setHandValue();
	}
}
