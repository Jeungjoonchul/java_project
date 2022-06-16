package test;

import java.util.Scanner;

import dao.Check;

public class Test2 {
	public static void main(String[] args) {
		System.out.println("1. / 2. / 3. /");
		Scanner sc = new Scanner(System.in);
		String choice = sc.next();
		if(Check.validateNumber_choiceOne(choice, 1, 3)) {
			if(Integer.parseInt(choice)==3) {
				//나가기
			}
			
			else {
				if(Integer.parseInt(choice)==1) {
					//1번 작업공간
					for (int i = 0; i < args.length; i++) {
						
					}
				}
				else if(Integer.parseInt(choice)==2) {
					//2번 작업공간
					switch(Integer.parseInt(choice)) {
					
					}
				}
			}
		}
		
		else {
			//오입력
		}
	}
}
