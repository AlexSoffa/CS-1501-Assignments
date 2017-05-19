import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
public class Player {
	public int chips;
	public String chipString;
	public ArrayList<Card> hand;
	public int handValue;
	public String name;
	public Player(String name){
		setName(name);
		setChips(1000);
		setChipString();
	}
	public Player(){
		
	}
	public void setChips(int chips){
		this.chips = chips;
	}
	public int getChips(){
		return this.chips;
	}
	public void setChipString(){
		this.chipString = NumberFormat.getNumberInstance(Locale.US).format(this.chips);
		String dollar = "$";
		this.chipString = dollar.concat(this.chipString);
	}
	public String getChipString(){
		return this.chipString;
	}
	public void setHand(ArrayList<Card> cards){
		this.hand = cards;
	}
	public ArrayList<Card> getHand(){
		return this.hand;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setHandValue(){
		this.handValue = 0;
		for(int i = 0; i < this.hand.size(); i++){
			if(this.hand.get(i).getCard().equalsIgnoreCase("A") && this.handValue + 11 <= 21){
				this.hand.get(i).setValue(11);
			}
			this.handValue += this.hand.get(i).getValue();
		}
	}
	public int getHandValue(){
		return this.handValue;
	}
}
