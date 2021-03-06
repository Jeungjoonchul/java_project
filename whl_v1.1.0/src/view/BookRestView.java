package view;

import java.util.Scanner;

import dao.BookDAO;
import dao.Check;
import dao.RestaurantDAO;
import dao.Session;
import dto.BookDTO;
import dto.RestaurantDTO;

public class BookRestView {
	public BookRestView() {
		BookDAO bdao = new BookDAO();
		RestaurantDAO rdao = new RestaurantDAO();
		System.out.println("");
		System.out.println("================");
		System.out.println("πμμμ  μμ½ νκΈ°π£");
		System.out.println("================");
		System.out.println("");
		String[] datas = new String[3];
		String[] datasInfo = { "μμ½ λμμΌ(yyyy-mm-dd / ", "μμ½ μκ°(hh:mm / ", "μμ½ μΈμ(", "" };

		for (int i = 0; i < datasInfo.length;) {
			// μλ ₯ν λ°μ΄ν°μ μ ν¨κ² κ²μ¬κ° λλλ©΄ μ€ν
			// μμ½ μ λ³΄ μλ ₯(μμ½ λ μ§, μΈμ)
			Scanner sc = new Scanner(System.in);
			if (i == 3) {
				BookDTO newBook = new BookDTO(datas);
				newBook.restaurant_name=((RestaurantDTO) Session.getData("selectedRest")).restaurant_name;
				System.out.println("βμμ½μ λ³΄\tββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				String result=String.format("β β²%s(%dλͺ) / μμ½μΌμ : %s", newBook.restaurant_name,
						newBook.book_companion_number, newBook.book_schedule);
				System.out.println(result);
				//β β²0. λμ€ννΈ μ­μΌμ (6λͺ) / μμ½μΌμ : 2022-06-16 20:00
				System.out.println("ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				System.out.println("β μμ½ νμκ² μ΅λκΉ?(Y/N)");
				String check = sc.next();
				if (check.equalsIgnoreCase("Y")) {
					if (bdao.insert(newBook)) {
						System.out.println("βμμ½μ΄ μλ£λμμ΅λλ€.");
						new CurrentBookView();
						i++;
					} else {
						System.out.println("β»μμ½μ μ€ν¨νμ΅λλ€.");
						i++;
					}
				} else if (check.equalsIgnoreCase("N")) {
					System.out.println("π€μμ½νκΈ°λ₯Ό μ’λ£ν©λλ€.");
					i++;
				} else {
					System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
				}
			} else {
				System.out.print("β "+datasInfo[i] + "λκ°κΈ°λ '!') : ");
				String inputData = sc.next();
				if (inputData.equals("!")) {
					System.out.println("π€μμ½νκΈ°λ₯Ό μ’λ£ν©λλ€.");
					break;
				} else {
					switch (i) {
					case 0:
						// λ μ§ μλ ₯ "yyyy-MM-dd"
						if (Check.validateDate(inputData)) {
							if (rdao.checkDate(inputData)) {
								datas[i] = inputData;
								i++;
							} else {
								System.out.println("β»μμ½μ΄ λΆκ°ν©λλ€. λ μ§ λλ μμμ μ ν΄λ¬΄μΌμ νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
							}
						} else {
							System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						}
						break;
					case 1:
						if (Check.validateTime(inputData)) {
							if (Check.validateDateTime(datas[0], inputData)) {
								System.out.println("βμμ½ κ°λ₯ν©λλ€.");
								datas[i] = inputData;
								i++;
							} else {
								System.out.println("β»μλ ₯νμ  μκ°μΌλ‘ μμ½μ΄ λΆκ°ν©λλ€. νμΈ ν λ€μ  μλν΄μ£ΌμΈμ!(1μκ° μ΄μ κΉμ§ μμ½ κ°λ₯ν©λλ€.)");
							}
						} else {
							System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						}
						break;
					case 2:
						if (Check.validateNumber(inputData)) {
							if (rdao.checkCapacity(Integer.parseInt(inputData))) {
								datas[i] = inputData;
								i++;
							} else {
								System.out.print(
										"β»" + ((RestaurantDTO) Session.getData("selectedRest")).restaurant_capacity);
								System.out.println("λͺ μ΄νλ‘ μμ½μ΄ κ°λ₯ν©λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
							}
						} else {
							System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						}

						break;
					}

				}
			}
		}
	}
}
