package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.UserRegisterDAO;
import dto.UserRegisterDTO;

public class A_ManageSgtRestView {

	public A_ManageSgtRestView() {

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("==================");
			System.out.println("🍜추천 음식점 관리하기🍣");
			System.out.println("==================");
			UserRegisterDAO urdao = new UserRegisterDAO();
			ArrayList<UserRegisterDTO> ucs = urdao.getList();
			if (ucs.size() == 0) {
				System.out.println("※현재 확인하지 않은 추천 음식점이 없습니다. 현재 페이지를 종료합니다.");
				break;
			} else {
				System.out.println("┏확인하지 않은 추천 음식점\t━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("┃등록 번호\t음식점 이름\t");
				for (UserRegisterDTO ur : ucs) {
					String result = String.format("┃%d\t%s\t\t%s", ur.register_num, ur.restaurant_name,
							ur.reg_description);
					System.out.println(result);
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.print("등록 번호를 선택하세요 : ");
				int register_num = sc.nextInt();
				UserRegisterDTO ur = urdao.a_select(register_num);
				System.out.println(ur);
				System.out.println("메뉴를 선택하세요.");
				System.out.println("1. 추천 음식점 수정 & 승인 / 2. 추천 음식점 반려 / 3. 뒤로가기");
				int choice = sc.nextInt();
				if (choice == 3) {
					System.out.println("💤추천 음식점 관리하기를 종료합니다.");
					break;
				} else if (choice == 1) {
					new A_InsertSgtView(register_num);
					
				} else if (choice == 2) {

				} else {

				}
			}
		}

	}

}
