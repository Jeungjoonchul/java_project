package view;

import java.util.Scanner;

import dao.Check;
import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class UserInfoView {
	public UserInfoView() {
		while (true) {

			UserDAO udao = new UserDAO();
			Scanner sc = new Scanner(System.in);
			System.out.println("");
			System.out.println("=============");
			System.out.println("🍜내 정보 보기🍣");
			System.out.println("=============");
			System.out.println("");
			UserDTO user = (UserDTO) Session.getData("loginUser");
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println(user);
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 내 정보 수정하기 / 2. 회원 탈퇴하기 / 3. 뒤로가기");
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 3) {
					System.out.println("💤내 정보 보기를 종료합니다.");
					break;
				} else if (Integer.parseInt(choice) == 1) {
					// 내 정보 수정
					new UpdateUserView();
				} else if (Integer.parseInt(choice) == 2) {
					// 회원 탈퇴
					new DeleteUserView();
					break;
				}
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
		}
	}
}
