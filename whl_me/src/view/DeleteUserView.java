package view;

import java.util.Scanner;

import dao.UserDAO;

public class DeleteUserView {
	public DeleteUserView() {
			while(true) {
				Scanner sc = new Scanner(System.in);
				UserDAO udao = new UserDAO();
				System.out.println("⚠회원 탈퇴 시 회원님의 모든 정보가 삭제됩니다.⚠");
				System.out.print("■정말 회원 탈퇴를 하시겠습니까?(Y/N) : ");
				String check = sc.next();
				if(check.equalsIgnoreCase("Y")) {
					System.out.print("■비밀 번호 : ");
					String user_pw = sc.next();
					if(udao.checkPW(user_pw)) {
						if(udao.deleteUserAll()) {
							System.out.println("🙇‍회원 탈퇴에 성공하였습니다. 그동안 이용해주셔서 감사합니다.🙇‍");
							break;
						}else {
							System.out.println("※회원 탈퇴에 실패하였습니다.");
						}
					}else {
						System.out.println("※비밀 번호가 올바르지 않습니다. 확인 후 다시 시도해주세요!");
					}
				}
				else if(check.equalsIgnoreCase("N")) {
					System.out.println("💤회원 탈퇴를 종료합니다.");
					break;
				}else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}
			
	}
}
