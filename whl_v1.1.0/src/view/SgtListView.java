package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.UserRegisterDAO;
import dto.UserRegisterDTO;

public class SgtListView {
	public SgtListView() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			UserRegisterDAO urdao = new UserRegisterDAO();
			ArrayList<UserRegisterDTO> urList = urdao.getList();
			System.out.println("");
			System.out.println("================");
			System.out.println("πλ΄ λ±λ‘ νν© λ³΄κΈ°π£");
			System.out.println("================");
			System.out.println("");
			if (urList.size() == 0) {
				System.out.println("");
				System.out.println("β»μΆμ²ν μμμ μ΄ μμ΅λλ€.");
				System.out.println("");
				break;
			} else {
				String isCheckY = "";
				String isCheckN = "";
				for (UserRegisterDTO ur : urList) {
					if (ur.is_register.equalsIgnoreCase("Y") || ur.admin_comment != null) {
						isCheckY += String.format("β πλ±λ‘ λ²νΈ : %d / %s(%s)\n", ur.register_num, ur.restaurant_name,
								ur.category_name);
						if(ur.admin_comment ==null) {
							ur.admin_comment = "κ΄λ¦¬μ νμΈ ν μμ± μμ ";
						}
						isCheckY += String.format("β λ±λ‘ μ¬λΆ : %s / μκ²¬ : %s\n", ur.is_register, ur.admin_comment);
					} else if (ur.is_register.equalsIgnoreCase("N")) {
						isCheckN += String.format("β πλ±λ‘ λ²νΈ : %d / %s(%s)\n", ur.register_num, ur.restaurant_name,
								ur.category_name);
					}
				}
				System.out.println("βνμΈ μλ£ λͺ©λ‘\tββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				if (isCheckY.equalsIgnoreCase("")) {
					System.out.println("β π₯λ±λ‘λ μΆμ² μμμ μ΄ μμ΅λλ€.");
				} else {
					System.out.print(isCheckY);
				}
				System.out.println("ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				System.out.println("βλ±λ‘ λκΈ° μ€\tββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				if (isCheckN.equalsIgnoreCase("")) {
					System.out.println("β π₯λ±λ‘ λκΈ° μ€μΈ μΆμ² μμμ μ΄ μμ΅λλ€.");
				} else {
					System.out.print(isCheckN);
				}
				System.out.println("ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
			}
			System.out.println("β λ©λ΄λ₯Ό μ ννμΈμ.");
			System.out.println("1. μμ /μ­μ νκΈ° / 2. λ€λ‘κ°κΈ°");
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 2)) {
				if (Integer.parseInt(choice) == 2) {
					System.out.println("π€λ΄ λ±λ‘ νν© λ³΄κΈ°λ₯Ό μ’λ£ν©λλ€.");
					break;
				} else if(Integer.parseInt(choice) == 1){
					new ModifySgtView();
					break;
				}
			} else {
				System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
			}
		}
	}
}
