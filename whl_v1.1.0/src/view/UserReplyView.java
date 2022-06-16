package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.ReplyDAO;
import dto.ReplyDTO;

public class UserReplyView {
	public UserReplyView() {
		
		
		while (true) {
			ReplyDAO rpdao = new ReplyDAO();
			ArrayList<ReplyDTO> url = new ArrayList<ReplyDTO>();
			url = rpdao.getList();
			System.out.println("");
			System.out.println("=============");
			System.out.println("🍜내 리뷰 관리🍣");
			System.out.println("=============");
			System.out.println("");
			if (url.size() == 0) {
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("┃  💥작성한 리뷰가 없습니다.	┃");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━┛");
			} else {
				System.out.println("┏작성한 리뷰\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				for (ReplyDTO ur : url) {
					System.out.println(ur);
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 리뷰 작성하기 / 2. 리뷰 삭제하기 / 3. 나가기");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 3)) {
				if (Integer.parseInt(choice) == 3) {
					System.out.println("💤내 리뷰 관리를 종료합니다.");
					break;
				} else {
					switch (Integer.parseInt(choice)) {
					case 1:
						// 리뷰 작성하기
						new AvailableReplyView();
						break;
					case 2:
						// 리뷰 삭제하기
						if(url.size()==0) {
							System.out.println("");
							System.out.println("※삭제 가능한 리뷰가 없습니다. 리뷰 등록 후 이용해주세요.");
							System.out.println("");
						}else {
							System.out.print("■리뷰 번호 : ");
							int reply_num = sc.nextInt();
							if (rpdao.isReplyOn(reply_num)) {
								System.out.print("■리뷰를 정말 삭제하시겠습니까?(Y/N) : ");
								String check = sc.next();
								if (check.equalsIgnoreCase("Y")) {
									if (rpdao.delete(reply_num)) {
										System.out.println("◎리뷰가 삭제되었습니다.");
									} else {
										System.out.println("※리뷰 삭제에 실패했습니다.");
									}
								} else if (check.equalsIgnoreCase("N")) {
									System.out.println("※리뷰 삭제를 취소합니다.");
								} else {
									System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
								}
							} else {
								System.out.println("※리뷰를 찾을 수 없습니다. 확인 후 다시 시도해주세요!");
							}
						}
						break;
					}
				}
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
		}
	}
}
