package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.ReplyDAO;
import dto.ReplyDTO;

public class UserReplyView {
	public UserReplyView() {
		while (true) {
			ReplyDAO rpdao = new ReplyDAO();
			System.out.println("=============");
			System.out.println("🍜내 리뷰 관리🍣");
			System.out.println("=============");
			ArrayList<ReplyDTO> url = new ArrayList<ReplyDTO>();
			url = rpdao.getList();
			if(url.size()==0) {
				System.out.println("※작성한 리뷰가 없습니다.");
			}
			else {
				System.out.println("┏작성한 리뷰\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				for (ReplyDTO ur : url) {
					String result = "--------------------------------------------------\n";
					result += String.format("%d. %s(%d점 / %s)\n", ur.reply_num, ur.restaurant_name, ur.reply_score,
							ur.reply_date);
					result += String.format("내용 : %s\n", ur.reply_comment);
					result += "--------------------------------------------------\n";
					System.out.println(result);
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 리뷰 작성하기 / 2. 리뷰 삭제하기 / 3. 나가기");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if (choice == 3) {
				System.out.println("💤내 리뷰 관리를 종료합니다.");
				break;
			} else {
				switch (choice) {
				case 1:
					//리뷰 작성하기
					new AvailableReplyView();
					break;
				case 2:
					//리뷰 삭제하기
					System.out.print("■리뷰 번호 : ");
					int reply_num = sc.nextInt();
					if(rpdao.isReplyOn(reply_num)) {
						System.out.print("■리뷰를 정말 삭제하시겠습니까?(Y/N) : ");
						String check = sc.next();
						if(check.equalsIgnoreCase("Y")) {
							if(rpdao.delete(reply_num)) {
								System.out.println("◎리뷰가 삭제되었습니다.");
							}else {
								System.out.println("※리뷰 삭제에 실패했습니다.");
							}
						}else if(check.equalsIgnoreCase("N")) {
							System.out.println("※리뷰 삭제를 취소합니다.");
						}else {
							System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
						}
					}else {
						System.out.println("※리뷰를 찾을 수 없습니다. 확인 후 다시 시도해주세요!");
					}
					break;
				default:
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}
		}

	}
}
