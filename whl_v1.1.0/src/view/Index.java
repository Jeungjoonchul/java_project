package view;

import java.util.Scanner;

import dao.Check;

public class Index {
	public static void main(String[] args) {
		
		String pepe = new IndexImage().pepe;
		System.out.println(pepe);
		// νλ‘κ·Έλ¨ title
		System.out.println("");
		System.out.println("========================");
		System.out.println("π²ππ£μ°λ¦¬ μ μ¬ λ­λ¨Ήμ§?ππ­β");
		System.out.println("========================");
		System.out.println("Copyright 2022.Ferrari Group 4 ver1.1.1");
		System.out.println("");
		while (true) {
			System.out.println("β λ©λ΄λ₯Ό μ ννμΈμ.\n1. λ‘κ·ΈμΈ / 2. νμκ°μ / 3. λκ°κΈ°");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 1) {
					// λ‘κ·ΈμΈ
					new LoginView();
				} else if (Integer.parseInt(choice) == 2) {
					// νμκ°μ
					new JoinView();
				} else if (Integer.parseInt(choice) == 3) {
					// λκ°κΈ°
					System.out.println("π€μ’λ£");
					break;
				}
			} else {
				System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. λ€μ μλν΄μ£ΌμΈμ!");
			}
		}
	}
}
