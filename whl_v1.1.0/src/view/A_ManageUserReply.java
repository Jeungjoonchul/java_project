package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.ReplyDAO;
import dao.Session;
import dto.ReplyDTO;
import dto.UserDTO;

public class A_ManageUserReply {

	public A_ManageUserReply() {
		while (true) {
			ReplyDAO rpdao = new ReplyDAO();
			System.out.println("");
			System.out.println("=================");
			System.out.println("πμ μ  λ¦¬λ·° κ΄λ¦¬νκΈ°π£");
			System.out.println("=================");
			System.out.println("");
			ArrayList<ReplyDTO> url = rpdao.getList();
			if (url.size() == 0) {
				System.out.println("β»ν΄λΉ μ μ μ μμ±λ λ¦¬λ·°κ° μμ΅λλ€.");
				break;
			} else {
				System.out.println(
						"ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				System.out.println("β" + ((UserDTO) Session.getData("selectedUser")).user_id + "μ μ κ° μμ±ν λ¦¬λ·°");
				for (ReplyDTO result : url) {
					System.out.println(result);
				}
				System.out.println(
						"ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				System.out.println("β λ©λ΄λ₯Ό μ ννμΈμ.");
				System.out.println("1. μ μ  λ¦¬λ·° μ­μ νκΈ° / 2. λκ°κΈ°");
				Scanner sc = new Scanner(System.in);
				String choice = sc.next();
				if (Check.validateNumber_choiceOne(choice, 1, 2)) {
					if (Integer.parseInt(choice) == 2) {
						System.out.println("π€μ μ  λ¦¬λ·° κ΄λ¦¬νκΈ°λ₯Ό μ’λ£ν©λλ€.");
						break;
					} else if (Integer.parseInt(choice) == 1) {
						System.out.print("β μ­μ ν  λ¦¬λ·° λ²νΈ μ ν : ");
						choice = sc.next();
						if (rpdao.isReplyOn(Integer.parseInt(choice))) {
							System.out.print("β μ λ§ 'μ­μ ' νμκ² μ΅λκΉ?(Y/N)");
							String checkDelete = sc.next();
							if (checkDelete.equalsIgnoreCase("Y")) {
								if (rpdao.delete(Integer.parseInt(choice))) {
									System.out.println("βμ μ  λ¦¬λ·° μ­μ μ μ±κ³΅νμ΅λλ€.");
								} else {
									System.out.println("β»μ μ  λ¦¬λ·° μ­μ μ μ€ν¨νμ΅λλ€.");
								}
							} else if (checkDelete.equalsIgnoreCase("N")) {
								System.out.println("π€μ μ  λ¦¬λ·° μ­μ κ° μ·¨μλμμ΅λλ€.");
							} else {
								System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
							}
						} else {
							System.out.println("β»λ¦¬λ·°λ₯Ό μ°Ύμ μ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						}
					}
				} else {

				}

			}
		}
	}
}
