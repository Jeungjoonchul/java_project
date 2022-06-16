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
			System.out.println("🍜현재 예약 내역🍣");
			System.out.println("==============");
			ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
			BookDAO bdao = new BookDAO();
			RestaurantDAO rdao = new RestaurantDAO();
			// 현재 예약 내역을 보기 위해 매개변수로 "current"설정
			String moment = "current";
			bookList = bdao.getList(moment);
			if (bookList.size() == 0) {
				System.out.println("※예약 내역이 없습니다.");
				break;
			} else {
				System.out.println("┏현재 예약 내역\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				for (BookDTO cb : bookList) {
					System.out.println(cb);
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 수정하기 / 2. 철회하기 / 3. 뒤로가기");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 3) {
					System.out.println("💤현재 예약 보기를 종료합니다.");
					break;
				} else {
					System.out.print("■변경할 예약 : ");
					String book_num = sc.next();
					if (Check.validateNumber(book_num)) {
						switch (Integer.parseInt(choice)) {
						case 1:
							// 수정하기
							if (bdao.select(Integer.parseInt(book_num)) != null) {
								new UpdateBookView();
							} else {
								System.out.println("※예약 찾기에 실패했습니다.");
							}
							break;
						case 2:
							while (true) {
								// 철회하기
								System.out.print("■예약을 정말 철회 하시겠습니까?(Y/N) : ");
								String checkDelete = sc.next();
								if (checkDelete.equalsIgnoreCase("Y")) {
									if (bdao.delete(Integer.parseInt(book_num))) {
										System.out.println("◎예약이 철회 되었습니다.");
										break;
									} else {
										System.out.println("※예약 철회에 실패하였습니다.");
										break;
									}
								} else if (checkDelete.equalsIgnoreCase("N")) {
									System.out.println("💤예약 철회를 종료합니다.");
									break;
								} else {
									System.out.println("※잘못 입력하였습니다. 다시 시도해주세요.");
								} 
							}
							break;
						}
					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
					}
				}
			} else {
				System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
			}
		}
	}
}
