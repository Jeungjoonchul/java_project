package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.UserRegisterDAO;
import dto.UserRegisterDTO;

public class SgtListView {
	public SgtListView() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			UserRegisterDAO urdao = new UserRegisterDAO();
			ArrayList<UserRegisterDTO> urList = urdao.getList();
			System.out.println("");
			System.out.println("================");
			System.out.println("🍜내 등록 현황 보기🍣");
			System.out.println("================");
			System.out.println("");
			if (urList.size() == 0) {
				System.out.println("");
				System.out.println("※추천한 음식점이 없습니다.");
				System.out.println("");
				break;
			} else {
				String isCheckY = "";
				String isCheckN = "";
				for (UserRegisterDTO ur : urList) {
					if (ur.is_register.equalsIgnoreCase("Y") || ur.admin_comment != null) {
						isCheckY += String.format("┃ 📃등록 번호 : %d / %s(%s)\n", ur.register_num, ur.restaurant_name,
								ur.category_name);
						if(ur.admin_comment ==null) {
							ur.admin_comment = "관리자 확인 후 작성 예정";
						}
						isCheckY += String.format("┃ 등록 여부 : %s / 의견 : %s", ur.is_register, ur.admin_comment);
					} else if (ur.is_register.equalsIgnoreCase("N")) {
						isCheckN += String.format("┃ 📃등록 번호 : %d / %s(%s)\n", ur.register_num, ur.restaurant_name,
								ur.category_name);
					}
				}
				System.out.println("┏확인 완료 목록\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				if (isCheckY.equalsIgnoreCase("")) {
					System.out.println("┃ 💥등록된 추천 음식점이 없습니다.");
				} else {
					System.out.println(isCheckY);
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println("┏등록 대기 중\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				if (isCheckN.equalsIgnoreCase("")) {
					System.out.println("┃ 💥등록 대기 중인 추천 음식점이 없습니다.");
				} else {
					System.out.print(isCheckN);
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 수정/삭제하기 / 2. 뒤로가기");
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 2)) {
				if (Integer.parseInt(choice) == 2) {
					System.out.println("💤내 등록 현황 보기를 종료합니다.");
					break;
				} else if(Integer.parseInt(choice) == 1){
					new ModifySgtView();
					break;
				}
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
		}
	}
}
