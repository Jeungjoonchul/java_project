package view;

import java.util.Scanner;

import dao.Check;

public class A_ManageRestView {

	public A_ManageRestView() {
		while (true) {
			System.out.println("");
			System.out.println("===============");
			System.out.println("πμμμ  κ΄λ¦¬νκΈ°π£");
			System.out.println("===============");
			System.out.println("");
			System.out.println("β λ©λ΄λ₯Ό μ ννμΈμ.");
			System.out.println("1. μμμ  μΆκ°νκΈ° / 2. μμμ  λ³΄κΈ° / 3. λ€λ‘κ°κΈ°");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if(Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 3) {
					System.out.println("π€μμμ  κ΄λ¦¬νκΈ°λ₯Ό μ’λ£ν©λλ€.");
					break;
				} else {
					switch (Integer.parseInt(choice)) {
					case 1:
						//μμμ  μΆκ°
						new A_InsertRestView();
						break;
					case 2:
						//μμμ  λ³΄κΈ°(μμ /μ­μ )
						new A_SearchRestView();
						break;
					}
				}
			}else {
				System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
			}
			
		}
	}
}
