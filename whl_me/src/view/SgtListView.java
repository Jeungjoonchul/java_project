package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.UserRegisterDAO;
import dto.UserRegisterDTO;

public class SgtListView {
	public SgtListView() {
		while (true) {
			//수정 필요
			//등록 대기중
			//등록 거절
			//등록 완료
			
			
			
			
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
				//기존 => user_register 테이블에 is_register가 Y인지 N인지 파악 후 등록완료와 미등록으로 나뉨
				//변경 =>	user_register는 admin 확인 후 Y또는 N으로 나뉘며 is_comment에 의견이 추가됨
				//		따라서 유저 입장에서는 is_register가 N이면서 is_comment가 null인 경우의 데이터가 admin이 확인하기 전의 데이터
				//		유저 기준에서 admin이 확인한 데이터와 확인하지 않은 데이터로 나눌 필요가 있음(admin이 확인한 데이터는 더이상 유효하지 않음)
				
				String isCheckY = "";
				String isCheckN = "";
				for (UserRegisterDTO ur : urList) {
					if (ur.is_register.equalsIgnoreCase("Y")||ur.is_comment!=null) {
						isCheckY += String.format("📃등록 번호 : %d / %s(%s)\n", ur.register_num, ur.restaurant_name,
								ur.category_name);
						isCheckY += String.format("등록 여부 : %s / 의견 : %s", ur.is_register,ur.is_comment);
					} else if(ur.is_register.equalsIgnoreCase("N")){
						isCheckN += String.format("📃등록 번호 : %d / %s(%s)\n", ur.register_num, ur.restaurant_name,
								ur.category_name);
					}
				}
				System.out.println("┏확인 완료 목록\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				if (isCheckY.equalsIgnoreCase("")) {
					System.out.println("※등록된 추천 음식점이 없습니다.");
				} else {
					System.out.println(isCheckN);
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println("┏등록 대기 중\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				if (isCheckN.equalsIgnoreCase("")) {
					System.out.println("※등록 대기 중인 추천 음식점이 없습니다.");
				} else {
					System.out.println(isCheckN);
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
