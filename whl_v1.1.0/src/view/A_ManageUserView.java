package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class A_ManageUserView {

	public A_ManageUserView() {
		while (true) {
			Session.setData("selectedUser", null);
			UserDAO udao = new UserDAO();
			System.out.println("");
			System.out.println("==========");
			System.out.println("πνμκ΄λ¦¬π£");
			System.out.println("==========");
			System.out.println("");
			System.out.println("β κ²μ λ°©λ²μ μλ ₯νμΈμ.");
			System.out.println("1. μμ΄λ / 2. μ΄λ¦ / 3. λλ€μ / 4. μ νλ²νΈ / 5. μ΄λ©μΌ / 6. λκ°κΈ°");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 6)) {
				if (Integer.parseInt(choice) == 6) {
					System.out.println("π€νμ κ΄λ¦¬λ₯Ό μ’λ£ν©λλ€.");
					break;
				} else {
					System.out.print("β κ²μμ΄λ₯Ό μλ ₯νμΈμ : ");
					String inputData = sc.next();
					// νμ λ¦¬μ€νΈ(κ²μμ΄λ₯Ό ν¬ν¨νλ)
					ArrayList<UserDTO> ul = new ArrayList<UserDTO>();
					ul = udao.getList(Integer.parseInt(choice), inputData);
					if (ul.size() == 0) {
						System.out.println("β»κ²μλ μ μ κ° μμ΅λλ€. λ€λ₯Έ κ²μμ΄λ‘ λ€μ μλν΄μ£ΌμΈμ.");
						continue;
					} else {

						System.out.println(
								"βμ μ  κ²μ κ²°κ³Ό\tββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");

						for (UserDTO result : ul) {
							String user = String.format("%d. %s", ul.indexOf(result) + 1, result.user_id);
							System.out.println(user);
						}

						System.out.println(
								"ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
						System.out.print("β λ²νΈ μ ν(λ€λ‘ κ°κΈ°λ '!') : ");
						choice = sc.next();
						if (choice.equalsIgnoreCase("!")) {
							continue;
						} else if (Check.validateNumber(choice)) {
							UserDTO selectedUser = ul.get(Integer.parseInt(choice) - 1);
							if (selectedUser != null) {
								Session.setData("selectedUser", selectedUser);
								System.out.println(
										"βμ νν μ μ \tββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
								System.out.println(selectedUser);
								System.out.println(
										"ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
								while (true) {
									System.out.println("β λ©λ΄λ₯Ό μ ννμΈμ.");
									System.out.println("1. μ μ  μμ  or μ­μ νκΈ° / 2. μ μ  λ¦¬λ·° κ΄λ¦¬νκΈ° / 3. λ€μ κ²μνκΈ° ");
									choice = sc.next();
									if (Check.validateNumber_choiceOne(choice, 1, 3)) {
										if (Integer.parseInt(choice) == 3) {
											System.out.println("π€νμ κ΄λ¦¬λ₯Ό μ’λ£ν©λλ€.");
											break;
										} else if (Integer.parseInt(choice) == 1) {
											new A_ModifyUserView();
											break;
										} else if (Integer.parseInt(choice) == 2) {
											new A_ManageUserReply();
											break;
										}
									} else {
										System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
									}
								}
							} else {
								System.out.println("β»μ μ  μ λ³΄λ₯Ό μ°Ύμ μ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
							}

						} else {
							System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						}
					}
				}
			} else {
				System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
			}

		}
	}
}
