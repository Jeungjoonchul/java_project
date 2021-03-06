package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.UserRegisterDAO;
import dto.UserRegisterDTO;

public class A_ManageSgtRestView {

	public A_ManageSgtRestView() {

		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("");
			System.out.println("==================");
			System.out.println("πμΆμ² μμμ  κ΄λ¦¬νκΈ°π£");
			System.out.println("==================");
			System.out.println("");
			UserRegisterDAO urdao = new UserRegisterDAO();
			ArrayList<UserRegisterDTO> ucs = urdao.getList();
			if (ucs.size() == 0) {
				System.out.println("β»νμ¬ νμΈνμ§ μμ μΆμ² μμμ μ΄ μμ΅λλ€. νμ¬ νμ΄μ§λ₯Ό μ’λ£ν©λλ€.");
				break;
			} else {
				System.out.println("βνμΈνμ§ μμ μΆμ² μμμ \tββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				for (UserRegisterDTO ur : ucs) {
					String result = String.format("β πλ±λ‘ λ²νΈ %d. %s(μΆμ² μ¬μ  : %s)", ur.register_num, ur.restaurant_name,
							ur.reg_description);
					System.out.println(result);
				}
				System.out.println(
						"ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				System.out.print("β λ±λ‘ λ²νΈλ₯Ό μ ννμΈμ(λκ°κΈ°λ '!') : ");
				String register_num = sc.next();
				if (register_num.equalsIgnoreCase("!")) {
					System.out.println("π€μΆμ² μμμ  κ΄λ¦¬νκΈ°λ₯Ό μ’λ£ν©λλ€.");
					break;
				} else if (Check.validateNumber(register_num)) {
					UserRegisterDTO ur = urdao.a_select(Integer.parseInt(register_num));
					if (ur != null) {
						System.out.println(
								"βμ νλ μΆμ² μμμ \tββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
						System.out.println(ur);
						System.out.println(
								"ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
						System.out.println("β λ©λ΄λ₯Ό μ ννμΈμ.");
						System.out.println("1. μΆμ² μμμ  μμ  & μΉμΈ / 2. μΆμ² μμμ  λ°λ € / 3. λ€λ‘κ°κΈ°");
						String choice = sc.next();
						if (Check.validateNumber_choiceOne(choice, 1, 3)) {
							if (Integer.parseInt(choice) == 3) {
								System.out.println("π€μΆμ² μμμ  κ΄λ¦¬νκΈ°λ₯Ό μ’λ£ν©λλ€.");
								break;
							} else if (Integer.parseInt(choice) == 1) {
								new A_InsertSgtView(register_num);
							} else if (Integer.parseInt(choice) == 2) {
								System.out.print("β μκ²¬(λ°λ € μ¬μ )λ₯Ό μμ±ν΄μ£ΌμΈμ : ");
								sc = new Scanner(System.in);
								String admin_comment = sc.nextLine();
								System.out.println("β»μκ²¬(λ°λ € μ¬μ ) : " + admin_comment);
								System.out.print("β μ λ§ λ°λ €νμκ² μ΅λκΉ?(Y/N)");
								String checkReject = sc.next();
								if (checkReject.equalsIgnoreCase("Y")) {
									if (urdao.update(7, admin_comment, Integer.parseInt(register_num))) {
										System.out.println("βμΆμ² μμμ  λ°λ €μ μ±κ³΅νμ΅λλ€.");
										break;
									} else {
										System.out.println("β»μΆμ² μμμ  λ°λ €μ μ€ν¨νμ΅λλ€.");
										break;
									}
								} else if (checkReject.equalsIgnoreCase("N")) {
									System.out.println("π€μΆμ² μμμ  λ°λ €λ₯Ό μ’λ£ν©λλ€.");
								} else {
									System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
								}
							}
						} else {
							System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						}
					} else {
						System.out.println("β»μΆμ²ν μμμ μ μ°Ύμ μ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
					}
				} else {
					System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
				}
			}
		}
	}
}
