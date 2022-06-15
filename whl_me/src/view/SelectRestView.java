package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.ReplyDAO;
import dao.RestaurantDAO;
import dao.Session;
import dto.ReplyDTO;
import dto.RestaurantDTO;

public class SelectRestView {
	public SelectRestView() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			RestaurantDTO result = new RestaurantDTO();
			result = ((RestaurantDTO) Session.getData("selectedRest"));
			int restaurant_id = result.restaurant_id;
			ReplyDAO rdao = new ReplyDAO();
			ArrayList<ReplyDTO> userReplyList = rdao.getList();
			ArrayList<ReplyDTO> restReplyList = new ArrayList<ReplyDTO>();
			for (ReplyDTO ur : userReplyList) {
				if (ur.restaurant_id == restaurant_id) {
					restReplyList.add(ur);
				}
			}
			System.out.println("┏음식점 정보\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println(result);
			System.out
					.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			System.out.println("┏작성된 리뷰\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			if (restReplyList.size() == 0) {
				System.out.println("┃ 💥작성된 리뷰가 없습니다. 예약을 하고 첫번째 리뷰의 주인공이 되세요!!");
			} else {
				for (ReplyDTO rr : restReplyList) {
					String reply = String.format("┃ %d. %s(☆%d점 / %s)", restReplyList.indexOf(rr)+1, rr.reply_comment,
							rr.reply_score, rr.reply_date);
					System.out.println(reply);
				}
			}
			System.out
					.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.println("1. 예약하기 / 2. 뒤로가기");
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 2))
				if (Integer.parseInt(choice) == 2) {
					System.out.println("💤현재 페이지를 종료합니다.");
					break;
				} else if (Integer.parseInt(choice) == 1) {
					new BookRestView();
					break;
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
		}

	}
}
