package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.BookDAO;
import dto.BookDTO;

public class PastBookView {
	public PastBookView() {
		while (true) {
			System.out.println("==============");
			System.out.println("🍜과거 예약 내역🍣");
			System.out.println("==============");
			ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
			BookDAO bdao = new BookDAO();
			// 과거 예약 내역을 보기 위해 매개변수로 "past" 설정
			String moment = "past";
			bookList = bdao.getBookList(moment);
			if (bookList.size() == 0) {
				System.out.println("※예약 내역을 찾을 수 없습니다.");
				break;
			} else {
				
				String result = "";
				System.out.println("┏과거 예약 내역\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				for (BookDTO pb : bookList) {

					System.out.println(pb);
				}
				
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 삭제하기 / 2. 뒤로가기");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if (choice == 2) {
				System.out.println("💤과거 예약 보기를 종료합니다.");
				break;
			} else if (choice == 1) {
				System.out.print("■삭제할 예약 : ");
				int book_num = sc.nextInt();
				System.out.print("■예약을 정말 삭제 하시겠습니까?(Y/N) : ");
				String checkDelete = sc.next();
				if (checkDelete.equalsIgnoreCase("Y")) {
					if (bdao.delete(book_num)) {
						System.out.println("◎예약이 삭제 되었습니다.");
						break;
					} else {
						System.out.println("※예약 삭제에 실패하였습니다.");
						break;
					}
				} else if (checkDelete.equalsIgnoreCase("N")) {
					System.out.println("💤예약 삭제를 종료합니다.");
					break;
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
		}
	}
}
