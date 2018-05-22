package black;

public class Card {
	private int val;//카트 값 1~10... 등등
	private String name;//이거는 D1 , A2 ...명칭 나타내는거
	
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
