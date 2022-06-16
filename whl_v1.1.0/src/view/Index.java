package view;

import java.util.Scanner;

import dao.Check;

public class Index {
	public static void main(String[] args) {
		
		String pepe = new IndexImage().pepe;
		System.out.println(pepe);
		// 프로그램 title
		System.out.println("");
		System.out.println("========================");
		System.out.println("🍲🍜🍣우리 점심 머먹지?🍕🌭☕");
		System.out.println("========================");
		System.out.println("Copyright 2022.Ferrari Group 4 ver1.1.0");
		System.out.println("");
		while (true) {
			System.out.println("■메뉴를 선택하세요.\n1. 로그인 / 2. 회원가입 / 3. 나가기");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 1) {
					// 로그인
					new LoginView();
				} else if (Integer.parseInt(choice) == 2) {
					// 회원가입
					new JoinView();
				} else if (Integer.parseInt(choice) == 3) {
					// 나가기
					System.out.println("💤종료");
					break;
				}
			} else {
				System.out.println("※잘못 입력하였습니다. 다시 시도해주세요!");
			}
		}
	}
}
