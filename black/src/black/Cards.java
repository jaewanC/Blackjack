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
					cards.addElement(new Card(j+1, sh[i] + "A")); // A�� 1, 11 ������ ���ִ� ������ 0���� �ְ� ���ý� card�� val�� 0�̸� ���� �ϵ��� �Ѵ�.
				else if (j == 10) // �̰��� �ε���
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
