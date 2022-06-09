package view;

import java.util.Scanner;

public class Index {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//프로그램 title
		String pepe =	"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n" + 
						"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n" + 
						"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n" + 
						"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\r\n" + 
						"@@@@@@@@@@@M2srr;riXM@@@@@#H33XhH@@@@@@@@@@@@@@@@@\r\n" + 
						"@@@@@@@@@S, .;rssr;, ;&B5:  ....  i@@@@@@@@@@@@@@@\r\n" + 
						"@@@@@@@3 .s55s;:...,;r: ,252225222.,@@@@@@@@@@@@@@\r\n" + 
						"@@@@@@S sii,    ,r2s,    s:,..      i@@@@@@@@@@@@@\r\n" + 
						"@@@@@9 SsSi  3#@@@@@@@@9  .2: r52XXhSi;5@@@@@@@@@@\r\n" + 
						"@@@@M Sir, i@@@@@     G@@A  ;@@@@@@@#@@hrA@@@@@@@@\r\n" + 
						"@@3i iS:rB@@@@@@@      r@@@,X@@@@@     @@9A@@@@@@@\r\n" + 
						"As   2S;r5@@@@@@@i     B@@@@X@@@@&     r@@s@@@@@@@\r\n" + 
						" SS;:2siS  #@@@@@@@@@@@@@@@@G#@@@@@.   @@@s@@@@@@@\r\n" + 
						"S5iiSsisi2:  X@@@@@@@@@@@@@@3@@@@@@@@@@@@S@@@@@@@@\r\n" + 
						"s2sssisiS22X:   .i2AM@@@#H2 :@@@@@@@@@Mss@@@@@@@@@\r\n" + 
						"sSssisS2r:sX222r,       . .r; :i222ir SM@@@@@@@@@@\r\n" + 
						"s2sssi5  ,  5355525255iSiisiis.     ..2@@@@@@@@@@@\r\n" + 
						"2Xis55X iS;, .s2X22iSiisisisis5225255S..@@@@@@@@@@\r\n" + 
						";r5X;i9..X. ::..,;s22X55iiiiiiiiiSi5S22.S@@@@@@@@@\r\n" + 
						"  r:  3.  rr. :,. ..,,;rii552222X2Sisr:  X@@@@@@@@\r\n" + 
						"i2  5  s s  :;,:,. .,,.....,,,,,,,...,,; 2@@@@@@@@\r\n" + 
						"5Xr22 rh 3hs:...:;r:,,,,,,..... ....,,,  5;@@@@@@@\r\n" + 
						"X9SSSi53 ,5X395r,, ..,,,.,.,,,,:,:;;::  ,:2@@@@@@@\r\n" + 
						" 99SSiiSi. ..;X2222SSsr;;;;:;:;r;.  r: &@@@@@@@@@@\r\n" + 
						"  :h32iii25Sr .92X2X2XX39G9A&G5s;i3Bs @@@@@@@@@@@@\r\n" + 
						"     3h52i559h ssrsrsrsrrrr ;         @@@@@@@@@@@@\r\n" + 
						"      ,SX3XX9  . .....                @@@@@@@@@@@@\r\n" + 
						" .                                   5@@@@@@@@@@@@";
		System.out.println(pepe);
		System.out.println("=================");
		System.out.println("🍲🍜🍣우리 점심 머먹지?🍕🌭☕");
		System.out.println("=================");
		System.out.println("Copyright 2022.Ferrari Group 4 All Right Reserved");
		//유저 비밀번호 초기화 추가
		while(true) {
			System.out.println("■메뉴를 선택하세요.\n1. 로그인 / 2. 회원가입 / 3. 나가기");
			int choice = sc.nextInt();
			if(choice == 1) {
				//로그인
				new LoginView();
			}else if(choice == 2) {
				//회원가입
				new JoinView();
			}else if(choice == 3) {
				//나가기
				System.out.println("💤종료");
				break;
			}else {
				System.out.println("※잘못 입력하였습니다. 다시 시도해주세요!");
			}
		}
	}
}
