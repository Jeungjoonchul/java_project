package view;

import java.util.Scanner;

public class SuggestRestView {
	public SuggestRestView() {
		while(true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("===============");
			System.out.println("🍜음식점 추천하기🍣");
			System.out.println("===============");
			System.out.println("■메뉴를 선택해주세요.");
			System.out.println("1. 음식점 추천하기 / 2. 내 등록 현황보기 / 3. 메인 메뉴로");
			int choice = sc.nextInt();
			if (choice==3) {
				System.out.println("💤음식점 추천하기를 종료합니다.");
				break;
			}else if(choice ==1) {
				new SgtRestView();
			}else if(choice == 2) {
				new SgtListView();
			}else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
		}
	}
}
