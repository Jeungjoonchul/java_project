package view;

import java.util.Scanner;

public class A_ManageRestView {

	public A_ManageRestView() {
		while (true) {
			System.out.println("===============");
			System.out.println("🍜음식점 관리하기🍣");
			System.out.println("===============");
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 음식점 추가하기 / 2. 음식점 보기 / 3. 뒤로가기");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if (choice == 3) {
				System.out.println("💤음식점 관리하기를 종료합니다.");
				break;
			} else {
				switch (choice) {
				case 1:
					//음식점 추가
					new A_InsertRestView();
					break;
				case 2:
					//음식점 보기(수정/삭제)
					new A_SelectRestView();
					break;
				default:
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}
		}
	}
}
