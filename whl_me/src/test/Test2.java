package test;

import java.util.Scanner;

import dao.Check;

public class Test2 {
	public static void main(String[] args) {
		while(true) {
			//입력 형식 확인
			System.out.print("값 입력 : ");
			String input = new Scanner(System.in).nextLine();
			if(Check.validateAddress(input)) {
				System.out.println("성공");
				System.out.println(input);
			}else {
				System.out.println("실패");
				System.out.println(input);
			}
		}
			
	}
}
