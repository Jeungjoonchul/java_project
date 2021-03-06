package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.ReplyDAO;
import dao.Session;
import dto.ReplyDTO;
import dto.UserDTO;

public class A_ManageUserReply {

	public A_ManageUserReply() {
		while (true) {
			ReplyDAO rpdao = new ReplyDAO();
			System.out.println("");
			System.out.println("=================");
			System.out.println("🍜유저 리뷰 관리하기🍣");
			System.out.println("=================");
			System.out.println("");
			ArrayList<ReplyDTO> url = rpdao.getList();
			if (url.size() == 0) {
				System.out.println("※해당 유저의 작성된 리뷰가 없습니다.");
				break;
			} else {
				System.out.println(
						"┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("┃" + ((UserDTO) Session.getData("selectedUser")).user_id + "유저가 작성한 리뷰");
				for (ReplyDTO result : url) {
					System.out.println(result);
				}
				System.out.println(
						"┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println("■메뉴를 선택하세요.");
				System.out.println("1. 유저 리뷰 삭제하기 / 2. 나가기");
				Scanner sc = new Scanner(System.in);
				String choice = sc.next();
				if (Check.validateNumber_choiceOne(choice, 1, 2)) {
					if (Integer.parseInt(choice) == 2) {
						System.out.println("💤유저 리뷰 관리하기를 종료합니다.");
						break;
					} else if (Integer.parseInt(choice) == 1) {
						System.out.print("■삭제할 리뷰 번호 선택 : ");
						choice = sc.next();
						if (rpdao.isReplyOn(Integer.parseInt(choice))) {
							System.out.print("■정말 '삭제' 하시겠습니까?(Y/N)");
							String checkDelete = sc.next();
							if (checkDelete.equalsIgnoreCase("Y")) {
								if (rpdao.delete(Integer.parseInt(choice))) {
									System.out.println("◎유저 리뷰 삭제에 성공했습니다.");
								} else {
									System.out.println("※유저 리뷰 삭제에 실패했습니다.");
								}
							} else if (checkDelete.equalsIgnoreCase("N")) {
								System.out.println("💤유저 리뷰 삭제가 취소되었습니다.");
							} else {
								System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
							}
						} else {
							System.out.println("※리뷰를 찾을 수 없습니다. 확인 후 다시 시도해주세요!");
						}
					}
				} else {

				}

			}
		}
	}
}
