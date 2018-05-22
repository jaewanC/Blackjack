package black;

import java.util.Random;
import java.util.Vector;

public class Cards {
	private Vector<Card> cards;
	public void initCard() {
		cards = new Vector<Card>();
		String[] sh = new String[4];
		sh[0] = "D"; sh[1] = "C"; sh[2] = "S"; sh[3] = "H";
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 13; j++) {
				if (j == 0)
					cards.addElement(new Card(j+1, sh[i] + "A")); // A는 1, 11 선택할 수있다 지금은 0으로 주고 선택시 card의 val이 0이면 선택 하도록 한다.
				else if (j == 10) // 이것은 인덱스
					cards.addElement(new Card(10, sh[i] + "J"));
				else if (j == 11)
					cards.addElement(new Card(10, sh[i] + "Q"));
				else if (j == 12)
					cards.addElement(new Card(10, sh[i] + "K"));
				else
					cards.addElement(new Card(j+1, sh[i] + (j+1)));
			}
		}
	}
	
	public Card getCard() {
		Random random = new Random();
		Card card;

		card = cards.get(random.nextInt(cards.size()));
		cards.remove(card);
		
		return card;
	}
}
