package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class A_ManageUserView {

	public A_ManageUserView() {
		while (true) {
			UserDAO udao = new UserDAO();
			System.out.println("==========");
			System.out.println("🍜회원관리🍣");
			System.out.println("==========");
			System.out.println("■검색 방법을 입력하세요.");
			System.out.println("1. 아이디 / 2. 이름 / 3. 닉네임 / 4. 전화번호 / 5. 이메일 / 6. 나가기");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if (choice == 6) {
				System.out.println("💤회원 관리를 종료합니다.");
				break;
			} else if (!(1 <= choice && choice <= 6)) {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				continue;
			}
			else {
				System.out.print("■검색어를 입력하세요 : ");
				String inputData = sc.next();
				// 회원 리스트(검색어를 포함하는)
				ArrayList<UserDTO> ul = new ArrayList<UserDTO>();
				ul = udao.getList(choice, inputData);
				if (ul.size() == 0) {
					System.out.println("※검색된 유저가 없습니다. 다른 검색어로 다시 시도해주세요.");
					continue;
				} else {

					System.out.println("┏유저 검색 결과\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					
					for (UserDTO result : ul) {
						String user = String.format("%d. %s", ul.indexOf(result) + 1, result.user_id);
						System.out.println(user);
					}

					System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
					System.out.print("■번호 선택 : ");
					choice = sc.nextInt();
					UserDTO selectedUser = ul.get(choice-1);
					Session.setData("selectedUser", selectedUser);
					System.out.println(selectedUser);
					
					while (true) {
						System.out.println("■메뉴를 선택하세요.");
						System.out.println("1. 유저 수정 or 삭제하기 / 2. 유저 리뷰 관리하기 / 3. 다시 검색하기 ");
						choice = sc.nextInt();
						if (choice == 3) {
							System.out.println("💤회원 관리를 종료합니다.");
							break;
						} else if (choice == 1) {
							new A_ModifyUserView();
							break;
						} else if (choice == 2) {
							new A_ManageUserReply();
							break;
						} else {
							System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
						}
					}
				}
			}
		}
	}
}
