package view;

import java.util.Scanner;

import dao.Check;
import dao.Session;

public class UserBookView {
	public UserBookView() {
		while (true) {
			Session.setData("selectedBook", null);
			System.out.println("");
			System.out.println("=============");
			System.out.println("🍜내 예약 보기🍣");
			System.out.println("=============");
			System.out.println("");
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 현재 예약 보기 / 2. 과거 예약 보기 / 3. 뒤로가기");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 3) {
					System.out.println("💤내 예약 보기를 종료합니다.");
					break;
				} else if (Integer.parseInt(choice) == 1) {
					// 현재 예약 보기
					new CurrentBookView();
				} else if (Integer.parseInt(choice) == 2) {
					// 과거 예약 보기
					new PastBookView();
				}
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
		}
	}
}
