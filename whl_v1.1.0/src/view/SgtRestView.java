package view;

import java.util.Scanner;

import dao.Check;
import dao.Session;
import dao.UserRegisterDAO;
import dto.UserDTO;
import dto.UserRegisterDTO;

public class SgtRestView {
	public SgtRestView() {
		System.out.println("");
		System.out.println("================");
		System.out.println("πμμμ   μΆμ² νκΈ°π£");
		System.out.println("================");
		System.out.println("");
		String[] inputInfo = { "μμμ  μ΄λ¦", "μμμ  μ£Όμ", "μΉ΄νκ³ λ¦¬", "μμμ  μ νλ²νΈ", "μμμ  μΆμ² μ¬μ ", "" };
		String[] datas = new String[6];
		for (int i = 0; i < inputInfo.length;) {
			UserRegisterDAO urdao = new UserRegisterDAO();
			Scanner sc = new Scanner(System.in);
			if (i == 5) {
				UserRegisterDTO newReg = new UserRegisterDTO(datas);
				System.out.println("βμλ ₯ν μ λ³΄\tββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				System.out.println(newReg);
				System.out.println("ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				System.out.print("β μ λ§ λ±λ‘νμκ² μ΅λκΉ?(Y/N) : ");
				String checkInsert = sc.next();
				if (checkInsert.equalsIgnoreCase("Y")) {
					if (urdao.insert(newReg)) {
						System.out.println("βμΆμ² λ±λ‘μ΄ μλ£λμμ΅λλ€.");
						new SgtListView();
						break;
					} else {
						System.out.println("β»μΆμ² λ±λ‘μ΄ μ€ν¨νμμ΅λλ€.");
						break;
					}
				} else if (checkInsert.equalsIgnoreCase("N")) {
					System.out.println("π€μΆμ² λ±λ‘μ΄ μ·¨μλμμ΅λλ€.");
					break;
				} else {
					System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
				}
			} else {
				if (i == 2) {
					System.out.println("β μΉ΄νκ³ λ¦¬λ₯Ό μ νν΄μ£ΌμΈμ.");
					System.out.println("1. νμπ²\t2. μ€μπ\t3. μΌμπ£\t4. μμπ");
					System.out.println("5. ν¨μ€νΈνΈλπ­\t6. μΉ΄ν/λμ νΈβ");
				} else {
					System.out.print("β " + inputInfo[i] + "(μ’λ£λ '!' ) : ");
				}
				sc = new Scanner(System.in);
				String inputData = sc.nextLine();

				if (inputData.equals("!")) {
					System.out.println("π€μμμ  μΆμ²μ μ’λ£ν©λλ€.");
					break;
				} else {
					switch (i) {
					case 0:
						// μμμ  μ΄λ¦
						if(Check.validateRestName(inputData)){
							datas[i] = inputData;
							i++;
						}else {
							System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!(30μλ¦¬κΉμ§ μλ ₯ κ°λ₯)");
						}
						break;
					case 1:
						// μμμ  μ£Όμ
						if(Check.validateAddress(inputData)) {
							datas[i] = inputData;
							i++;
						}else {
							System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						}
						break;
					case 2:
						//μΉ΄νκ³ λ¦¬
						String[] cate = { "", "νμ", "μ€μ", "μΌμ", "μμ", "ν¨μ€νΈνΈλ", "μΉ΄ν/λμ νΈ" };
						if(Check.validateNumber_choiceOne(inputData, 1, 6)) {
							datas[i] = cate[Integer.parseInt(inputData)];
							i++;
						}else {
							System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						}
						break;
					case 3:
						//μμμ  μ νλ²νΈ
						if (Check.validatePhone(inputData)) {
							datas[i] = Check.phoneOnlyNumber(inputData);
							i++;
						} else {
							System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						}
						break;
					case 4:
						//μμμ  μΆμ² μ¬μ 
						if(inputData.length()<=330) {
							datas[i] = inputData;
							datas[5] = ((UserDTO) Session.getData("loginUser")).user_id;
							i++;
						}else {
							System.out.println("β»μλ ₯ κ°λ₯ν κΈμμλ₯Ό μ΄κ³Όνμμ΅λλ€. 330μ μ΄λ΄λ‘ μμ±ν΄μ£ΌμΈμ.");
						}						
						break;
					}
				}
			}
		}
	}
}