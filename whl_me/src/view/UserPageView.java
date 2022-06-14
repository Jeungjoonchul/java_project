package view;

import java.util.Scanner;

import dao.Check;
import dao.Session;

public class UserPageView {
	public UserPageView() {
		while (true) {
			if (Session.getData("loginUser") == null) {
				break;
			}
			System.out.println("");
			System.out.println("============");
			System.out.println("🍜마이 페이지🍣");
			System.out.println("============");
			System.out.println("");
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 내 정보 보기 / 2. 내 예약 보기 / 3. 내 리뷰 관리 / 4. 메인 메뉴로");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 4)) {
				if (Integer.parseInt(choice) == 4) {
					System.out.println("💤마이 페이지를 종료합니다.");
					break;
				} else {
					switch (Integer.parseInt(choice)) {
					case 1:
						// 내 정보 보기
						new UserInfoView();
						break;
					case 2:
						// 내 예약 보기
						new UserBookView();
						break;
					case 3:
						// 내 리뷰 관리
						new UserReplyView();
						break;
					}
				}
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
		}
	}
}
