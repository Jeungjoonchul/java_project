package view;

import java.util.Scanner;

import dao.Check;
import dao.Session;

public class AdminMainView {
	public AdminMainView() {
		System.out.println("");
		System.out.println("=============");
		System.out.println("πκ΄λ¦¬μ νμ΄μ§π£");
		System.out.println("=============");
		System.out.println("");
		while (true) {
			if (Session.getData("loginUser") == null) {
				System.out.println("λ‘κ·ΈμΈ ν μ΄μ©νμΈμ.");
				break;
			}
			System.out.println("β λ©λ΄λ₯Ό μ ννμΈμ.");
			System.out.println("1. μμμ  κ΄λ¦¬ / 2. μΆμ² μμμ  κ΄λ¦¬ / 3. νμ κ΄λ¦¬ / 4. λ‘κ·Έμμ");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 4)) {
				if (Integer.parseInt(choice) == 4) {
					System.out.println("π€κ΄λ¦¬μ λͺ¨λλ₯Ό μ’λ£ν©λλ€.");
					Session.setData("loginUser", null);
					break;
				} else {
					switch (Integer.parseInt(choice)) {
					case 1:
						// μμμ  κ΄λ¦¬
						new A_ManageRestView();
						break;
					case 2:
						// μΆμ² μμμ  κ΄λ¦¬
						new A_ManageSgtRestView();
						break;
					case 3:
						// 3. νμ κ΄λ¦¬
						new A_ManageUserView();
						break;
					}
				}
			} else {
				System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
			}
		}
	}
}
