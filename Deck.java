import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class Deck{
	ArrayList<Card> deck = new ArrayList<Card>();
	String [] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	String [] suits = {"Clubs", "Spades", "Hearts", "Diamonds"}; 
	public void initDeck(){ 
		for(int j = 0; j < suits.length; j++){
			for(int k = 0; k < cards.length; k++){
				Card currCard = new Card();
				currCard.setCardNum(((j)*13)+(k+1));
				if(k < 9)
					currCard.setValue(k + 1);
				else
					currCard.setValue(10);
				currCard.setCardSuit(suits[j]);
				currCard.setCard(cards[k]);
				this.deck.add(currCard);
			}
		}
	}
	public void setDeck(ArrayList<Card> deck){
		this.deck = deck;
	}
	public ArrayList<Card> getDeck(){
		return this.deck;
	}
	public ArrayList<Card> shuffle(ArrayList<Card> currDeck){
		Random range = new Random();
		for(int i = 0; i < 52; i++){
			int randomSeedSort = range.nextInt(100); 
			currDeck.get(i).setSeedValue(randomSeedSort);
		}
		Collections.sort(currDeck);
		return currDeck;
	}
	public void printDeck(){
		for(int i = 0; i < 52; i++){
			System.out.println(deck.get(i).cardNumOfDeck + " " + deck.get(i).getCard() + " " + deck.get(i).getCardSuit());
		}
	}
}
