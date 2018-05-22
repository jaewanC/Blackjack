package black;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

// ��� ���� ���� �ڹ��� �ʿ��� ��� ���� ���� �� �Լ������� ���� 
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
		System.out.println("����� �ܾ� : " + player.getMoney());
		System.out.println("----------------");
	}
	
	public void showCards(boolean hidden) {
		System.out.println("----------------");
		System.out.print("������ ī�� : ");
		showCards(dealer, hidden);
		System.out.print("�������� ī�� : ");
		showCards(player, hidden);
		System.out.println("----------------");
	}
	
	public int bMondy() {
		int money;
		
		if (player.getMoney() == 0) {
			System.out.println("���� �����ϴ�");
			System.out.println("������ �����մϴ�");
			return 0;
		}
		
		do {
			System.out.print("���ñݾ��� �Է��ϼ��� : ");
			try {
				money = scan.nextInt();
				if (player.getMoney() < money) {
					System.out.println("�ݾ��� �����մϴ�");
					continue;
				} else {
					player.minusMoney(money);
					System.out.println("������ �Ϸ�Ǿ����ϴ�");
					return money;
				}
			} catch (InputMismatchException e) {
				System.out.println("�Է��� �߸��Ǿ����ϴ�");
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
		
		System.out.println("����");
		showMoney();
		do {
			cards.initCard();
			player.reSetMyCards();
			dealer.reSetMyCards();
			
			battingmoney= bMondy();
			
			if (battingmoney == 0)
				System.exit(0);
			
			if (checkMondy()) {
				System.out.println("���� ���׿�");
				System.out.println("������ �����մϴ�");
				System.exit(0);
			}
			showMoney();
			System.out.println("ī�� �й�");
			dealer.addMyCard(cards.getCard());
			dealer.addMyCard(cards.getCard());
			player.addMyCard(cards.getCard());
			player.addMyCard(cards.getCard());
			showCards(false);
			if (sumVal(player) == 21) {
				player.plusMoney(battingmoney * 2);
				showCards(true);
				System.out.println("�����մϴ�. ����!!");
				continue;
			}
			
			do {
				if (select() == 1) {
					if (sumVal(dealer) <= 16)
						dealer.addMyCard(cards.getCard());
					player.addMyCard(cards.getCard());
					if (sumVal(player) > 21) {
						showCards(true);
						System.out.println("����Ʈ");
						break;
					} else if (sumVal(player) == 21) {
						System.out.println("�����մϴ�. ����!!");
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
						System.out.println("�����¸�");
					else if (sumVal(dealer) == sumVal(player)) {
						player.plusMoney(battingmoney);
						System.out.println("���");
					}
					else {
						player.plusMoney(battingmoney * 2);
						System.out.println("������ ��");
					}
					break;
				}
			} while(true);
			System.out.println("----------------");
			System.out.println("�����Ϸ��� �ƹ�Ű�� �Է��ϼ���");
			System.out.print("y�� �Է��ϸ� ������ ����մϴ�  : ");
			isOnGoing = scan.next();
			System.out.println("----------------");
			if (!(isOnGoing.equals("y") || isOnGoing.equals("Y") || isOnGoing.equals("��"))) {
				break;
			}
				
		} while (true);
		System.out.print("���̺���~~");
	}
	
	main() {
		start();
	}
	
	public static void main(String[] args) {
		main main = new main();
	}
}