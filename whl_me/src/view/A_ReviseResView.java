package view;

import java.util.Scanner;

import dao.Check;
import dao.RestaurantDAO;
import dao.Session;
import dto.RestaurantDTO;

public class A_ReviseResView {
	public A_ReviseResView() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			RestaurantDTO result = new RestaurantDTO();
			RestaurantDAO rdao = new RestaurantDAO();
			result = ((RestaurantDTO) Session.getData("selectedRest"));

			System.out.println("┏음식점 정보\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println(result);
			System.out
			.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 수정하기 / 2. 삭제하기 / 3. 뒤로가기");
			String choice = sc.next();
			if(Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 3) {// 3. 뒤로가기
					System.out.println("💤현재 페이지를 종료합니다.");
					break;
				} else if (Integer.parseInt(choice) == 1) {// 1. 수정하기
					new A_UpdateResView();
					break;
				} else if (Integer.parseInt(choice) == 2) {// 2. 삭제하기
					System.out.println("■선택한 음식점을 정말로 삭제 하시겠습니까?(Y/N) ");
					String checkDelete = sc.next();
					if (checkDelete.equalsIgnoreCase("Y")) {
						if (rdao.a_delete(result.restaurant_id)) {
							System.out.println("◎삭제가 완료되었습니다.");
							Session.setData("selectedRest", null);
							break;
						} else {
							System.out.println("※삭제에 실패하였습니다.");
							break;
						}
					} else if(checkDelete.equalsIgnoreCase("N")){// 3. 뒤로 가기
						System.out.println("※삭제가 취소되었습니다.");
						break;
					}else {
						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
					}
				}
			}else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
			
		}
	}
}
