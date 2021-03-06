package view;

import java.util.Arrays;
import java.util.Scanner;

import dao.Check;
import dao.RestaurantDAO;
import dao.Session;
import dto.RestaurantDTO;
import dto.UserDTO;

public class A_InsertRestView {

	public A_InsertRestView() {
		// View Title
		RestaurantDAO rdao = new RestaurantDAO();
		System.out.println("===============");
		System.out.println("πμμμ  μΆκ°νκΈ°π£");
		System.out.println("===============");
		String[] inputInfo = { "μμμ  μ΄λ¦(", "μμμ  μΉ΄νκ³ λ¦¬", "μμμ  μ£Όμ(λλ‘λͺμ£Όμ /", "μμμ  μ νλ²νΈ(", "μμ½ κ°λ₯ μΈμ(", "ν΄λ¬΄μΌ", "μμμ  μ€λͺ(",
				"" };
		String[] datas = new String[7];
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < inputInfo.length;) {
			if (i == 7) {
				RestaurantDTO newRest = new RestaurantDTO(datas);
				System.out.println("βμλ ₯ν μ λ³΄\tββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				System.out.println(newRest);
				System.out.println(
						"ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				System.out.print("β μ λ§ λ±λ‘νμκ² μ΅λκΉ?(Y/N) : ");
				String checkInsert = sc.next();
				if (checkInsert.equalsIgnoreCase("Y")) {
					if (rdao.insert(newRest)) {
						System.out.println("βμμμ  λ±λ‘μ΄ μλ£λμμ΅λλ€.");
						i++;
					} else {
						System.out.println("β»μμμ  λ±λ‘μ μ€ν¨νμμ΅λλ€.");
						i++;
					}
				} else if (checkInsert.equalsIgnoreCase("N")) {
					System.out.println("π€μμμ  μΆκ°νκΈ°λ₯Ό μ’λ£ν©λλ€.");
					i++;
				} else {
					System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
				}
			} else {
				// μΉ΄νκ³ λ¦¬ μ€μ μ λ²νΈ μλ ₯ μ ν΄λΉ λ²νΈμ λ§λ κ°μ datasλ°°μ΄μ μ μ₯
				if (i == 1) {
					System.out.println("β μΉ΄νκ³ λ¦¬λ₯Ό μ νν΄μ£ΌμΈμ.");
					System.out.println("1. νμπ²\t2. μ€μπ\t3. μΌμπ£\t4. μμπ");
					System.out.println("5. ν¨μ€νΈνΈλπ­\t6. μΉ΄ν/λμ νΈβ");
				} else if (i == 5) {
					System.out.println("β ν΄λ¬΄μΌμ ν΄λΉνλ μ«μλ₯Ό λμ΄μ°κΈ° μμ΄ μ νν΄μ£ΌμΈμ.(ex : ν ,μΌ -> '67'μλ ₯)");
					System.out.println("1. μ / 2. ν / 3. μ / 4. λͺ© / 5. κΈ / 6. ν  / 7. μΌ / 8. ν΄λ¬΄μμ ");
				} else {
					System.out.print("β " + inputInfo[i] + " λκ°κΈ°λ '!') : ");
				}
				sc = new Scanner(System.in);
				String inputData = sc.nextLine();
				if (inputData.equals("!")) {
					System.out.println("π€μμμ  μΆκ°νκΈ°λ₯Ό μ’λ£ν©λλ€.");
					break;
				}
				switch (i) {
				case 0:
					// μμμ  μ΄λ¦
					if (Check.validateRestName(inputData)) {
					} else {
						System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!(30μλ¦¬κΉμ§ μλ ₯ κ°λ₯)");
						continue;
					}
					break;
				case 1:
					// μμμ  μΉ΄νκ³ λ¦¬
					if (Check.validateNumber_choiceOne(inputData, 1, 6)) {
						String[] category = { "", "νμ", "μ€μ", "μΌμ", "μμ", "ν¨μ€νΈνΈλ", "μΉ΄ν/λμ νΈ" };
						inputData = category[Integer.parseInt(inputData)];
					} else {
						System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						continue;
					}
					break;
				case 2:
					// μμμ  μ£Όμ
					if (Check.validateAddress(inputData)) {
					} else {
						System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						continue;
					}
					break;
				case 3:
					// μμμ  μ νλ²νΈ
					if (Check.validatePhone(inputData)) {
						inputData = Check.regPhone(inputData);
					} else {
						System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						continue;
					}
					break;
				case 4:
					// μμ½ κ°λ₯ μΈμ
					if (Check.validateNumber(inputData)) {
					} else {
						System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						continue;
					}
					break;
				case 5:
					// μμμ  ν΄λ¬΄
					if (inputData.equals("8")) {
						inputData = "ν΄λ¬΄ μμ";
					} else if (Check.validateNumber_choiceMulti(inputData, 1, 7)) {
						String[] dow = { "", "μ", "ν", "μ", "λͺ©", "κΈ", "ν ", "μΌ" };
						String[] sort_inputData = inputData.split("");
						Arrays.sort(sort_inputData);
						inputData = "";
						for (int j = 0; j < sort_inputData.length; j++) {
							if (j == sort_inputData.length - 1) {
								inputData += dow[Integer.parseInt(sort_inputData[j])];
							} else {
								inputData += dow[Integer.parseInt(sort_inputData[j])] + ",";
							}
						}
					} else {
						System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						continue;
					}
					break;
				case 6:
					if(inputData.length()<=330) {
					}else {
						System.out.println("β»μλ ₯ κ°λ₯ν κΈμμλ₯Ό μ΄κ³Όνμμ΅λλ€. 330μ μ΄λ΄λ‘ μμ±ν΄μ£ΌμΈμ.");
						continue;
					}		
					break;
				}
				while (true) {
					System.out.print("β μλ ₯νμλ €λ λ΄μ©μ΄ \"" + inputData + "\"μ΄(κ°) λ§λμ?(Y/N) : ");
					String checkInput = sc.next();
					if (checkInput.equalsIgnoreCase("Y")) {
						if (i == 3) {
							inputData = Check.phoneOnlyNumber(inputData);
						} else if (i == 5) {
							if (inputData.equals("ν΄λ¬΄ μμ")) {
								inputData = null;
							}
						}
						datas[i] = inputData;
						i++;
						break;
					} else if (checkInput.equalsIgnoreCase("N")) {
						break;
					} else {
						System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
					}
				}

			}
		}
	}
}
