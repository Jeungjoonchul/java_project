package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.UserRegisterDAO;
import dto.UserRegisterDTO;

public class A_ManageSgtRestView {

	public A_ManageSgtRestView() {

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("");
			System.out.println("==================");
			System.out.println("🍜추천 음식점 관리하기🍣");
			System.out.println("==================");
			System.out.println("");
			UserRegisterDAO urdao = new UserRegisterDAO();
			ArrayList<UserRegisterDTO> ucs = urdao.getList();
			if (ucs.size() == 0) {
				System.out.println("※현재 확인하지 않은 추천 음식점이 없습니다. 현재 페이지를 종료합니다.");
				break;
			} else {
				System.out.println("┏확인하지 않은 추천 음식점\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				for (UserRegisterDTO ur : ucs) {
					String result = String.format("┃ 📃등록 번호 %d. %s(추천 사유 : %s)", ur.register_num, ur.restaurant_name,
							ur.reg_description);
					System.out.println(result);
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.print("■등록 번호를 선택하세요 : ");
				String register_num = sc.next();
				if (Check.validateNumber(register_num)) {
					UserRegisterDTO ur = urdao.a_select(Integer.parseInt(register_num));
					if (ur != null) {
						System.out.println("┏선택된 추천 음식점\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
						System.out.println(ur);
						System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
						System.out.println("■메뉴를 선택하세요.");
						System.out.println("1. 추천 음식점 수정 & 승인 / 2. 추천 음식점 반려 / 3. 뒤로가기");
						String choice = sc.next();
						if (Check.validateNumber_choiceOne(choice, 1, 3)) {
							if (Integer.parseInt(choice) == 3) {
								System.out.println("💤추천 음식점 관리하기를 종료합니다.");
								break;
							} else if (Integer.parseInt(choice) == 1) {
								new A_InsertSgtView(register_num);
							} else if (Integer.parseInt(choice) == 2) {
								System.out.print("■의견(반려 사유)를 작성해주세요 : ");
								sc=new Scanner(System.in);
								String admin_comment = sc.nextLine();
								System.out.println("※의견(반려 사유) : "+admin_comment);
								System.out.print("■정말 반려하시겠습니까?(Y/N)");
								String checkReject = sc.next();
								if(checkReject.equalsIgnoreCase("Y")) {
									if(urdao.update(7, admin_comment, Integer.parseInt(register_num))) {
										System.out.println("◎추천 음식점 반려에 성공했습니다.");
										break;
									}else {
										System.out.println("※추천 음식점 반려에 실패했습니다.");
										break;
									}
								}else if(checkReject.equalsIgnoreCase("N")){
									System.out.println("💤추천 음식점 반려를 종료합니다.");
								}else {
									System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
								}
							}
						} else {
							System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
						}
					} else {
						System.out.println("※추천한 음식점을 찾을 수 없습니다. 확인 후 다시 시도해주세요!");
					}
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}
		}
	}
}
