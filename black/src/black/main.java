package black;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

// 상속 따위 없이 자바의 필요한 기능 없이 거의 머 함수형으로 쉽게 
public class main {
	private User player;
	private User dealer;
	private Scanner scan;
	
	public void showCards(User user, boolean hidden) {
		int sIdx = 0;
		if (user.equals(dealer) && hidden == false)
			sIdx = 1;
		for (int i = sIdx; i < user.getMyCards().size(); i++) {
			System.out.print(user.getMyCards().get(i).getCardName() + " ");
		}
		System.out.println();
	}
	
	public void showMoney() {
		System.out.println("----------------");
		System.out.println("당신의 잔액 : " + player.getMoney());
		System.out.println("----------------");
	}
	
	public void showCards(boolean hidden) {
		System.out.println("----------------");
		System.out.print("딜러의 카드 : ");
		showCards(dealer, hidden);
		System.out.print("참가자의 카드 : ");
		showCards(player, hidden);
		System.out.println("----------------");
	}
	
	public int bMondy() {
		int money;
		
		if (player.getMoney() == 0) {
			System.out.println("돈이 없습니다");
			System.out.println("게임을 종료합니다");
			return 0;
		}
		
		do {
			System.out.print("배팅금액을 입력하세요 : ");
			try {
				money = scan.nextInt();
				if (player.getMoney() < money) {
					System.out.println("금액이 부족합니다");
					continue;
				} else {
					player.minusMoney(money);
					System.out.println("배팅이 완료되었습니다");
					return money;
				}
			} catch (InputMismatchException e) {
				System.out.println("입력이 잘못되었습니다");
				scan.nextLine();
			}
		} while (true);
	}
	
	public int select() {
		int selectNum;

		do {
			System.out.print("1.Hit, 2.Stand : ");
			selectNum = scan.nextInt();
			if (selectNum == 1 || selectNum == 2)
				return selectNum;
		} while(true);
	}
	
	public int sumVal(User user) {
		int sum = 0;
		int getA = 0;
		for (int i = 0; i < user.getMyCards().size(); i++) {
			sum += user.getMyCards().get(i).getCardVal();
			if (user.equals(player) && 
					user.getMyCards().get(i).getCardName().charAt(1) == 'A')
				getA++;
		}
		
		for (int i = 0; i < getA; i++)
			if (sum <= 11) {
				sum += 10;
				getA--;
			}
		if (user.equals(player)) System.out.println("Val : " + sum);
		return sum;
	}
	
	public void initPlayer() {
		player = new User(100000, new Vector<Card>(), "player");
		dealer = new User(0, new Vector<Card>(), "dealer");
	}
	
	public boolean checkMondy() {
		if (player.getMoney() <= 0)
			return true;
		return false;
	}
	
	public void start() {
		Cards cards = new Cards();
		scan = new Scanner(System.in);
		
		initPlayer();
		
		int battingmoney;
		String isOnGoing;
		
		System.out.println("시작");
		showMoney();
		do {
			cards.initCard();
			player.reSetMyCards();
			dealer.reSetMyCards();
			
			battingmoney= bMondy();
			
			if (battingmoney == 0)
				System.exit(0);
			
			if (checkMondy()) {
				System.out.println("돈이 없네요");
				System.out.println("게임을 종료합니다");
				System.exit(0);
			}
			showMoney();
			System.out.println("카드 분배");
			dealer.addMyCard(cards.getCard());
			dealer.addMyCard(cards.getCard());
			player.addMyCard(cards.getCard());
			player.addMyCard(cards.getCard());
			showCards(false);
			if (sumVal(player) == 21) {
				player.plusMoney(battingmoney * 2);
				showCards(true);
				System.out.println("축하합니다. 블랙잭!!");
				continue;
			}
			
			do {
				if (select() == 1) {
					if (sumVal(dealer) <= 16)
						dealer.addMyCard(cards.getCard());
					player.addMyCard(cards.getCard());
					if (sumVal(player) > 21) {
						showCards(true);
						System.out.println("버스트");
						break;
					} else if (sumVal(player) == 21) {
						System.out.println("축하합니다. 블랙잭!!");
						player.plusMoney(battingmoney * 2);
						break;
					}
					showCards(false);
					continue;
				} else {
					if (sumVal(dealer) <= 16)
						dealer.addMyCard(cards.getCard());
					showCards(true);
					
					if (Math.abs(21 - sumVal(dealer)) < Math.abs(21- sumVal(player)))
						System.out.println("딜러승리");
					else if (sumVal(dealer) == sumVal(player)) {
						player.plusMoney(battingmoney);
						System.out.println("비김");
					}
					else {
						player.plusMoney(battingmoney * 2);
						System.out.println("참가자 승");
					}
					break;
				}
			} while(true);
			System.out.println("----------------");
			System.out.println("종료하려면 아무키나 입력하세요");
			System.out.print("y를 입력하면 게임을 계속합니다  : ");
			isOnGoing = scan.next();
			System.out.println("----------------");
			if (!(isOnGoing.equals("y") || isOnGoing.equals("Y") || isOnGoing.equals("ㅛ"))) {
				break;
			}
				
		} while (true);
		System.out.print("빠이빠이~~");
	}
	
	main() {
		start();
	}
	
	public static void main(String[] args) {
		main main = new main();
	}
}