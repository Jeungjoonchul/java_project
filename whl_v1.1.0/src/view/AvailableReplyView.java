package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import dao.BookDAO;
import dao.Check;
import dao.ReplyDAO;
import dto.BookDTO;
import dto.ReplyDTO;

public class AvailableReplyView {
	public AvailableReplyView() {
		while (true) {
			ReplyDAO rpdao = new ReplyDAO();
			BookDAO bdao = new BookDAO();
			ArrayList<BookDTO> url = bdao.notHasReplyBook();

			if (url.size() == 0) {
				System.out.println("");
				System.out.println("※현재 작성 가능한 리뷰가 없습니다.");
				System.out.println("");
				break;
			} else {
				System.out.println("┏작성 가능한 리뷰 : " + url.size() + "건\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				for (BookDTO arbl : url) {
					System.out.println(arbl);
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println("■메뉴를 선택하세요.");
				System.out.println("1. 리뷰 작성 / 2. 뒤로가기");
				Scanner sc = new Scanner(System.in);
				String choice = sc.next();
				if (Check.validateNumber_choiceOne(choice, 1, 2)) {
					if (Integer.parseInt(choice) == 1) {
						new InsertReplyView();
						break;
					} else if (Integer.parseInt(choice) == 2) {
						System.out.println("💤리뷰 작성하기를 종료합니다.");
						break;
					}
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}
		}
	}
}
