package black;

public class Card {
	private int val;//īƮ �� 1~10... ���
	private String name;//�̰Ŵ� D1 , A2 ...��Ī ��Ÿ���°�
	
	Card (int inputVal, String inputName) {
		this.val = inputVal;
		this.name = inputName;
	}

	public int getCardVal() {
		return this.val;
	}
	public String getCardName() {
		return this.name;
	}
}
