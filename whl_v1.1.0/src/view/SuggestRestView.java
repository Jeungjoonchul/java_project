package view;

import java.util.Scanner;

import dao.Check;

public class SuggestRestView {
	public SuggestRestView() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("");
			System.out.println("===============");
			System.out.println("πμμμ  μΆμ²νκΈ°π£");
			System.out.println("===============");
			System.out.println("");
			System.out.println("β λ©λ΄λ₯Ό μ νν΄μ£ΌμΈμ.");
			System.out.println("1. μμμ  μΆμ²νκΈ° / 2. λ΄ λ±λ‘ νν©λ³΄κΈ° / 3. λ©μΈ λ©λ΄λ‘");
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 3) {
					System.out.println("π€μμμ  μΆμ²νκΈ°λ₯Ό μ’λ£ν©λλ€.");
					break;
				} else if (Integer.parseInt(choice) == 1) {
					new SgtRestView();
				} else if (Integer.parseInt(choice) == 2) {
					new SgtListView();
				}
			} else {
				System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
			}
		}
	}
}
