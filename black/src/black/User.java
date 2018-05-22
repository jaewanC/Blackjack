package black;

import java.util.Vector;

public class User {
	private int money;
	private Vector<Card> cards;
	private String name;
	User(int money, Vector<Card> cards, String name) {
		this.money = money;
		this.cards = cards;
		this.name = name;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void plusMoney(int mondy) {
		this.money += money;
	}
	
	public void minusMoney(int money) {
		this.money -= money;
	}
	
	public void addMyCard(Card card) {
		this.cards.addElement(card);
	}
	
	public Vector<Card> getMyCards() {
		return this.cards;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void reSetMyCards() {
		this.cards.clear();
	}
}
