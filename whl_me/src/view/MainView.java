package view;

import java.util.Scanner;

import dao.Session;
import dto.UserDTO;

public class MainView {
	public MainView() {
		while (true) {
			
			UserDTO loginUser = (UserDTO) Session.getData("loginUser");
			if (loginUser == null) {
				System.out.println("※로그인 후 이용하세요");
				break;
			}
			System.out.println("===========");
			System.out.println("🍜 우 점 머 🍣");
			System.out.println("===========");
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 음식점 찾아보기 / 2. 음식점 추천하기 / 3. 마이페이지 / 4. 로그아웃");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			
			if (choice == 4) {
				System.out.println("💤"+loginUser.user_nickname + "님 다음에 또 오세요");
				Session.setData("loginUser", null);
				break;
			}
			
			switch (choice) {
			case 1:
				// 음식점 찾아보기
				new SearchRestView();
				break;
			case 2:
				// 음식점 추천하기
				new SuggestRestView();
				break;
			case 3:
				// 마이페이지
				new UserPageView();
				break;
			default:
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}

		}
	}

}
