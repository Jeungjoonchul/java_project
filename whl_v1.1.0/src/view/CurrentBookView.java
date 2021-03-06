package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.BookDAO;
import dao.Check;
import dao.RestaurantDAO;
import dao.Session;
import dto.BookDTO;
import dto.RestaurantDTO;

public class CurrentBookView {
	public CurrentBookView() {
		while (true) {
			System.out.println("==============");
			System.out.println("πνμ¬ μμ½ λ΄μ­π£");
			System.out.println("==============");
			ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
			BookDAO bdao = new BookDAO();
			RestaurantDAO rdao = new RestaurantDAO();
			// νμ¬ μμ½ λ΄μ­μ λ³΄κΈ° μν΄ λ§€κ°λ³μλ‘ "current"μ€μ 
			String moment = "current";
			bookList = bdao.getList(moment);
			if (bookList.size() == 0) {
				System.out.println("β»μμ½ λ΄μ­μ΄ μμ΅λλ€.");
				break;
			} else {
				System.out.println("βνμ¬ μμ½ λ΄μ­\tββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				for (BookDTO cb : bookList) {
					System.out.println(cb);
				}
				System.out.println("ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
			}
			System.out.println("β λ©λ΄λ₯Ό μ ννμΈμ.");
			System.out.println("1. μμ νκΈ° / 2. μ² ννκΈ° / 3. λ€λ‘κ°κΈ°");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 3) {
					System.out.println("π€νμ¬ μμ½ λ³΄κΈ°λ₯Ό μ’λ£ν©λλ€.");
					break;
				} else {
					System.out.print("β λ³κ²½ν  μμ½ : ");
					String book_num = sc.next();
					if (Check.validateNumber(book_num)) {
						switch (Integer.parseInt(choice)) {
						case 1:
							// μμ νκΈ°
							if (bdao.select(Integer.parseInt(book_num)) != null) {
								new UpdateBookView();
							} else {
								System.out.println("β»μμ½ μ°ΎκΈ°μ μ€ν¨νμ΅λλ€.");
							}
							break;
						case 2:
							while (true) {
								// μ² ννκΈ°
								System.out.print("β μμ½μ μ λ§ μ² ν νμκ² μ΅λκΉ?(Y/N) : ");
								String checkDelete = sc.next();
								if (checkDelete.equalsIgnoreCase("Y")) {
									if (bdao.delete(Integer.parseInt(book_num))) {
										System.out.println("βμμ½μ΄ μ² ν λμμ΅λλ€.");
										break;
									} else {
										System.out.println("β»μμ½ μ² νμ μ€ν¨νμμ΅λλ€.");
										break;
									}
								} else if (checkDelete.equalsIgnoreCase("N")) {
									System.out.println("π€μμ½ μ² νλ₯Ό μ’λ£ν©λλ€.");
									break;
								} else {
									System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. λ€μ μλν΄μ£ΌμΈμ.");
								} 
							}
							break;
						}
					} else {
						System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
					}
				}
			} else {
				System.out.println("β»μλ ₯ νμμ΄ μ¬λ°λ₯΄μ§ μμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
			}
		}
	}
}
