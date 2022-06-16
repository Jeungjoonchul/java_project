package view;

import java.util.Scanner;

import dao.Check;
import dao.UserDAO;

public class A_ModifyUserView {
	// 유저 정보 수정/삭제
	public A_ModifyUserView() {
		while (true) {
			UserDAO udao = new UserDAO();
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 유저 정보 수정하기 / 2. 유저 삭제하기 / 3. 나가기");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 3) {
					System.out.println("💤유저 정보 수정하기를 종료합니다.");
					break;
				} else if (Integer.parseInt(choice) == 1) {
					// 유저 정보 수정하기
					new A_UpdateUserView();
					break;
				} else if (Integer.parseInt(choice) == 2) {
					// 유저 삭제하기
					System.out.println("※해당 유저의 모든 정보가 삭제됩니다.");
					System.out.println("■정말 삭제하시겠습니까?(Y/N)");
					String checkDelete = sc.next();
					if (checkDelete.equalsIgnoreCase("Y")) {
						if (udao.deleteUserAll()) {
							System.out.println("※유저 아이디 삭제에 성공했습니다.");
							break;
						} else {
							System.out.println("※유저 아이디 삭제에 실패했습니다.");
							break;
						}
					} else if (checkDelete.equalsIgnoreCase("N")) {
						System.out.println("💤유저 삭제하기를 종료합니다.");
						break;
					} else {
						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
					}
				}
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
		}
	}
}
