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
			System.out.println("πλ΄ μ λ³΄ λ³΄κΈ°π£");
			System.out.println("=============");
			System.out.println("");
			UserDTO user = (UserDTO) Session.getData("loginUser");
			System.out.println("ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
			System.out.println(user);
			System.out.println("ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
			
			System.out.println("β λ©λ΄λ₯Ό μ ννμΈμ.");
			System.out.println("1. λ΄ μ λ³΄ μμ νκΈ° / 2. νμ νν΄νκΈ° / 3. λ€λ‘κ°κΈ°");
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 3) {
					System.out.println("π€λ΄ μ λ³΄ λ³΄κΈ°λ₯Ό μ’λ£ν©λλ€.");
					break;
				} else if (Integer.parseInt(choice) == 1) {
					// λ΄ μ λ³΄ μμ 
					new UpdateUserView();
				} else if (Integer.parseInt(choice) == 2) {
					// νμ νν΄
					new DeleteUserView();
					break;
				}
			} else {
				System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
			}
		}
	}
}
