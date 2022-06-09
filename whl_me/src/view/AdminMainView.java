package view;

import java.util.Scanner;

import dao.Session;

public class AdminMainView {
	public AdminMainView() {
		System.out.println("=============");
		System.out.println("🍜관리자 페이지🍣");
		System.out.println("=============");
		while (true) {
			if (Session.getData("loginUser") == null) {
				System.out.println("로그인 후 이용하세요.");
				break;
			}
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 음식점 관리 / 2. 추천 음식점 관리 / 3. 회원 관리 / 4. 로그아웃");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if (choice == 4) {
				System.out.println("💤관리자 모드를 종료합니다.");
				Session.setData("loginUser", null);
				break;
			} else {
				switch (choice) {
				case 1:
					//음식점 관리
					new A_ManageRestView();
					break;
				case 2:
					//추천 음식점 관리
					new A_ManageSgtRestView();
					break;
				case 3:
					//3. 회원 관리
					new A_ManageUserView();
					break;
				default:
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}
		}

	}
}
