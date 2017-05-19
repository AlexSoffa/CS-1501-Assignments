public class Card implements Comparable<Card>{
	public int value;
	public String suit;
	public String card;
	public int cardNumOfDeck;
	public int seedValue;
	public Card(){
	}
	public void setValue(int value){
		this.value = value;
	}
	public void setCardNum(int cardNumOfDeck){
		this.cardNumOfDeck = cardNumOfDeck;
	}
	public void setSeedValue(int seedValue){
		this.seedValue = seedValue;
	}
	public void setCardSuit(String suit){
		this.suit = suit;
	}
	public void setCard(String card){
		this.card = card;
	}
	public int getValue(){
		return this.value;
	}
	public int getCardNum(){
		return this.cardNumOfDeck;
	}
	public int getSeedValue(){
		return this.seedValue;
	}
	public String getCardSuit(){
		return this.suit;
	}
	public String getCard(){
		return this.card;
	}
	public int compareTo(Card card) {
		if(this.seedValue > card.getSeedValue())
			return 1;
		else if(this.seedValue < card.getSeedValue())
			return -1;
		else 
			return 0;
			
	}
}
