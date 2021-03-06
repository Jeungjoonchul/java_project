package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.ReplyDAO;
import dto.ReplyDTO;

public class UserReplyView {
	public UserReplyView() {
		
		
		while (true) {
			ReplyDAO rpdao = new ReplyDAO();
			ArrayList<ReplyDTO> url = new ArrayList<ReplyDTO>();
			url = rpdao.getList();
			System.out.println("");
			System.out.println("=============");
			System.out.println("πλ΄ λ¦¬λ·° κ΄λ¦¬π£");
			System.out.println("=============");
			System.out.println("");
			if (url.size() == 0) {
				System.out.println("βββββββββββββββββββββββββ");
				System.out.println("β  π₯μμ±ν λ¦¬λ·°κ° μμ΅λλ€.	β");
				System.out.println("βββββββββββββββββββββββββ");
			} else {
				System.out.println("βμμ±ν λ¦¬λ·°\tββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				for (ReplyDTO ur : url) {
					System.out.println(ur);
				}
				System.out.println("ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
			}
			System.out.println("β λ©λ΄λ₯Ό μ ννμΈμ.");
			System.out.println("1. λ¦¬λ·° μμ±νκΈ° / 2. λ¦¬λ·° μ­μ νκΈ° / 3. λκ°κΈ°");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 3) {
					System.out.println("π€λ΄ λ¦¬λ·° κ΄λ¦¬λ₯Ό μ’λ£ν©λλ€.");
					break;
				} else {
					switch (Integer.parseInt(choice)) {
					case 1:
						// λ¦¬λ·° μμ±νκΈ°
						new AvailableReplyView();
						break;
					case 2:
						// λ¦¬λ·° μ­μ νκΈ°
						if(url.size()==0) {
							System.out.println("");
							System.out.println("β»μ­μ  κ°λ₯ν λ¦¬λ·°κ° μμ΅λλ€. λ¦¬λ·° λ±λ‘ ν μ΄μ©ν΄μ£ΌμΈμ.");
							System.out.println("");
						}else {
							System.out.print("β λ¦¬λ·° λ²νΈ : ");
							int reply_num = sc.nextInt();
							if (rpdao.isReplyOn(reply_num)) {
								System.out.print("β λ¦¬λ·°λ₯Ό μ λ§ μ­μ νμκ² μ΅λκΉ?(Y/N) : ");
								String check = sc.next();
								if (check.equalsIgnoreCase("Y")) {
									if (rpdao.delete(reply_num)) {
										System.out.println("βλ¦¬λ·°κ° μ­μ λμμ΅λλ€.");
									} else {
										System.out.println("β»λ¦¬λ·° μ­μ μ μ€ν¨νμ΅λλ€.");
									}
								} else if (check.equalsIgnoreCase("N")) {
									System.out.println("β»λ¦¬λ·° μ­μ λ₯Ό μ·¨μν©λλ€.");
								} else {
									System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
								}
							} else {
								System.out.println("β»λ¦¬λ·°λ₯Ό μ°Ύμ μ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
							}
						}
						break;
					}
				}
			} else {
				System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
			}
		}
	}
}
