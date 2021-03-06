package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.BookDAO;
import dao.Check;
import dto.BookDTO;

public class PastBookView {
	public PastBookView() {
		while (true) {
			System.out.println("==============");
			System.out.println("πκ³Όκ±° μμ½ λ΄μ­π£");
			System.out.println("==============");
			ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
			BookDAO bdao = new BookDAO();
			// κ³Όκ±° μμ½ λ΄μ­μ λ³΄κΈ° μν΄ λ§€κ°λ³μλ‘ "past" μ€μ 
			String moment = "past";
			bookList = bdao.getList(moment);
			if (bookList.size() == 0) {
				System.out.println("β»μμ½ λ΄μ­μ μ°Ύμ μ μμ΅λλ€.");
				break;
			} else {
				String result = "";
				System.out.println("βκ³Όκ±° μμ½ λ΄μ­\tββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
				for (BookDTO pb : bookList) {
					System.out.println(pb);
				}
				System.out.println("ββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββββ");
			}
			System.out.println("β λ©λ΄λ₯Ό μ ννμΈμ.");
			System.out.println("1. μ­μ νκΈ° / 2. λ€λ‘κ°κΈ°");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 2)) {
				if (Integer.parseInt(choice) == 2) {
					System.out.println("π€κ³Όκ±° μμ½ λ³΄κΈ°λ₯Ό μ’λ£ν©λλ€.");
					break;
				} else if (Integer.parseInt(choice) == 1) {
					System.out.print("β μ­μ ν  μμ½ : ");
					String book_num = sc.next();
					if(Check.validateNumber(book_num)) {
						System.out.print("β μμ½μ μ λ§ μ­μ  νμκ² μ΅λκΉ?(Y/N) : ");
						String checkDelete = sc.next();
						if (checkDelete.equalsIgnoreCase("Y")) {
							if (bdao.delete(Integer.parseInt(book_num))) {
								System.out.println("βμμ½μ΄ μ­μ  λμμ΅λλ€.");
								break;
							} else {
								System.out.println("β»μμ½ μ­μ μ μ€ν¨νμμ΅λλ€.");
								break;
							}
						} else if (checkDelete.equalsIgnoreCase("N")) {
							System.out.println("π€μμ½ μ­μ λ₯Ό μ’λ£ν©λλ€.");
							break;
						} else {
							System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
						}
					}else {
						System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
					}
				}
			} else {
				System.out.println("β»μλͺ» μλ ₯νμμ΅λλ€. νμΈ ν λ€μ μλν΄μ£ΌμΈμ!");
			}
		}
	}
}
