package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.UserRegisterDAO;
import dto.UserRegisterDTO;

public class SgtListView {
	public SgtListView() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			UserRegisterDAO urdao = new UserRegisterDAO();
			ArrayList<UserRegisterDTO> urList = urdao.searchList();
			System.out.println("================");
			System.out.println("🍜내 등록 현황 보기🍣");
			System.out.println("================");
			if (urList.size() == 0) {
				System.out.println("※추천한 음식점이 없습니다.");
				break;
			} else {
				String isRegY = "";
				String isRegN = "";
				for (UserRegisterDTO ur : urList) {
					if (ur.is_register.equalsIgnoreCase("Y")) {
						isRegY += String.format("📃등록 번호 : %d / %s(%s)\n", ur.register_num, ur.restaurant_name,
								ur.category_name);
					} else if(ur.is_register.equalsIgnoreCase("N")){
						isRegN += String.format("📃등록 번호 : %d / %s(%s)\n", ur.register_num, ur.restaurant_name,
								ur.category_name);
					}
				}
				System.out.println("┏등록 완료 목록\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				if (isRegY.equalsIgnoreCase("")) {
					System.out.println("※등록된 추천 음식점이 없습니다.");
				} else {
					System.out.println(isRegY);
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println("┏등록 대기 중\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				if (isRegN.equalsIgnoreCase("")) {
					System.out.println("※등록 대기 중인 추천 음식점이 없습니다.");
				} else {
					System.out.println(isRegN);
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
//			String isRegY = "===========등록  완료===========\n";	
//			String isRegN = "==========등록  대기 중==========\n";
//			if(urList.size()==0) {
//				System.out.println("목록이 없습니다.");
//				break;
//			}else {
//				for (UserRegisterDTO ur : urList) {
//					if(ur.is_register.equalsIgnoreCase("Y")){
//						isRegY += String.format("등록 번호 : %d / %s(%s)\n", ur.register_num,ur.restaurant_name,ur.category_name);
//					}else {
//						isRegN += String.format("등록 번호 : %d / %s(%s)\n", ur.register_num,ur.restaurant_name,ur.category_name);
//					}
//				}
//			}
//			isRegY += "=============================\n";
//			isRegN += "=============================\n";
//			System.out.println(isRegY);
//			System.out.println(isRegN);
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 수정/삭제하기 / 2. 뒤로가기");
			int choice = sc.nextInt();
			if (choice == 2) {
				System.out.println("💤내 등록 현황 보기를 종료합니다.");
				break;
			} else if (choice == 1) {
				new ModifySgtView();
				break;
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
		}

	}
}
