package view;

import java.util.Scanner;

import dao.Check;
import dao.SMS;
import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class LoginView {
	public LoginView() {
		
		UserDAO udao = new UserDAO();
		SMS sms = new SMS();
		System.out.println("");
		System.out.println("=========");
		System.out.println("πλ‘κ·ΈμΈπ£");
		System.out.println("=========");
		System.out.println("");
		while (true) {
			System.out.println("β λ©λ΄λ₯Ό μ ννμΈμ.");
			System.out.println("1. λ‘κ·ΈμΈ / 2. μμ΄λ μ°ΎκΈ° / 3. λΉλ° λ²νΈ μ°ΎκΈ° / 4. λκ°κΈ°");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 4)) {
				if (Integer.parseInt(choice) == 4) {
					System.out.println("π€νμ¬ νμ΄μ§λ₯Ό μ’λ£ν©λλ€.");
					break;
				}
				switch (Integer.parseInt(choice)) {
				case 1:
					System.out.print("β μμ΄λ : ");
					String user_id = sc.next();
					System.out.print("β λΉλ°λ²νΈ : ");
					String user_pw = sc.next();
					if (udao.login(user_id, user_pw)) {
						String user_nickname = ((UserDTO) Session.getData("loginUser")).user_nickname;
						if (user_nickname.equals("κ΄λ¦¬μ")) {
							System.out.println("βκ΄λ¦¬μ λͺ¨λ μλλ€.");
							new AdminMainView();
						} else {
							System.out.println("");
							System.out.println("β" + user_nickname + "λ λ°κ°μμ!! λ§μ νμΈμ(β'β‘'β)");
							new MainView();
						}
					} else {
						System.out.println("β»λ‘κ·ΈμΈ μ€ν¨ γ γ (μμ΄λ νΉμ λΉλ°λ²νΈλ₯Ό νμΈ ν λ€μ μλν΄μ£ΌμΈμγ γ )");
					}
					break;
				case 2:
					System.out.print("β μ΄λ¦μ μλ ₯νμΈμ : ");
					String user_name = sc.next();
					System.out.print("β ν΄λν°λ²νΈλ₯Ό μλ ₯νμΈμ : ");
					String user_phone = sc.next();
					if(!Check.validatePhone(user_phone)) {
						System.out.println("β»ν΄λν° λ²νΈ μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						break;
					}
					user_phone = Check.phoneOnlyNumber(user_phone);
					UserDTO findUser = udao.findID(user_name, user_phone);
					if (findUser != null) {
						String code = sms.sendCode(user_phone);
						if (code != null) {
							while (true) {
								System.out.print("β ν΄λμ ν λ¬Έμλ‘ μμ  λ°μΌμ  μ½λ 6μλ¦¬λ₯Ό μλ ₯νμΈμ(λκ°κΈ°λ '!') : ");
								String checkCode = sc.next();
								if (checkCode.equals("!")) {
									break;
								} else if (checkCode.equals(code)) {
									System.out.println("βνμλμ μμ΄λλ '" + findUser.user_id + "' μλλ€.");
									break;
								} else {
									System.out.println("β»μλ ₯νμ  μ½λκ° μ¬λ°λ₯΄μ§ μμ΅λλ€. λ€μ μλ ₯ν΄μ£ΌμΈμ!");
								}
							}
						} else {
							System.out.println("β»μ½λ λ°μ‘μ μ€ν¨νμ΅λλ€. λ€μ μλν΄μ£ΌμΈμ!");
						}
					} else {
						System.out.println("β»μλ ₯νμ  μ λ³΄λ‘ μμ΄λλ₯Ό μ°Ύμ μ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
					}
					break;
				case 3:
					System.out.print("β μμ΄λλ₯Ό μλ ₯νμΈμ : ");
					user_id = sc.next();
					System.out.print("β ν΄λν°λ²νΈλ₯Ό μλ ₯νμΈμ : ");
					user_phone = sc.next();
					if(!Check.validatePhone(user_phone)) {
						System.out.println("β»ν΄λν° λ²νΈ μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						break;
					}
					user_phone = Check.phoneOnlyNumber(user_phone);
					findUser = udao.findPW(user_id, user_phone);
					if (findUser != null) {
						String code = sms.sendCode(user_phone);
						if (code != null) {
							while (true) {
								System.out.print("β ν΄λμ ν λ¬Έμλ‘ μμ  λ°μΌμ  μ½λ 6μλ¦¬λ₯Ό μλ ₯νμΈμ(λκ°κΈ°λ '!') : ");
								String checkCode = sc.next();
								if (checkCode.equals("!")) {
									break;
								} else if (checkCode.equals(code)) {
									System.out.println("βλΉλ°λ²νΈλ '" + findUser.user_pw + "' μλλ€.");
									break;
								} else {
									System.out.println("β»μλ ₯νμ  μ½λκ° μ¬λ°λ₯΄μ§ μμ΅λλ€. λ€μ μλ ₯ν΄μ£ΌμΈμ!");
								}
							}
						} else {
							System.out.println("β»μ½λ λ°μ‘μ μ€ν¨νμ΅λλ€. λ€μ μλν΄μ£ΌμΈμ!");
						}
					} else {
						System.out.println("β»μλ ₯νμ  μ λ³΄λ‘ λΉλ°λ²νΈλ₯Ό μ°Ύμ μ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
					}
					break;
				}
			} else {
				System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
				continue;
			}
		}
	}
}
