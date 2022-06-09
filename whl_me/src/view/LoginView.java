package view;

import java.util.Scanner;

import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class LoginView {
	public LoginView() {
		Scanner sc = new Scanner(System.in);
		UserDAO udao = new UserDAO();
		System.out.println("=========");
		System.out.println("🍜로그인🍣");
		System.out.println("=========");
		System.out.print("■아이디 : ");
		String user_id = sc.next();
		System.out.print("■비밀번호 : ");
		String user_pw = sc.next();
		if (udao.login(user_id, user_pw)) {
			String user_nickname = ((UserDTO) Session.getData("loginUser")).user_nickname;
			if (user_nickname.equals("관리자")) {
				System.out.println("◎관리자 모드 입니다.");
				new AdminMainView();
			} else {
				System.out.println("◎"+user_nickname + "님 반가워요!! 맛점하세요(●'◡'●)");
				new MainView();
			}
		} else {
			System.out.println("※로그인 실패 ㅠㅠ(아이디 혹은 비밀번호를 확인 후 다시 시도해주세요ㅠㅠ)");
		}
	}
}
