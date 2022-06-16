package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.RestaurantDAO;
import dao.Session;
import dto.RestaurantDTO;

public class ResultSearchRestView {
	public ResultSearchRestView() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			RestaurantDAO rdao = new RestaurantDAO();
			ArrayList<RestaurantDTO> resultList = new ArrayList<RestaurantDTO>();
			resultList = ((ArrayList<RestaurantDTO>) Session.getData("restList"));

			System.out.println("┏검색 결과(" + resultList.size() + "개)\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			for (RestaurantDTO r : resultList) {
				String result = String.format("┃ %d. %s[%s, ♡%d / ☆%d(%d)]", resultList.indexOf(r) + 1, r.restaurant_name,
						r.category_name, r.restaurant_like_cnt, r.avg_score, r.reply_cnt);
				System.out.println(result);
			}
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 음식점 선택 / 2. 뒤로 가기");
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 2)) {
				if (Integer.parseInt(choice) == 2) {
					System.out.println("💤현재 페이지를 종료합니다.");
					break;
				}
				else if (Integer.parseInt(choice) == 1) {
					System.out.print("■음식점 선택 : ");
					choice = sc.next();
					if (Check.validateNumber(choice)) {
						int restaurant_id = resultList.get(Integer.parseInt(choice) - 1).restaurant_id;
						// resultList.get(3-1) => ar 2번방에 RestaurandDTO선택
						if (rdao.select(restaurant_id) == null) {
							System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
						} else {
							new SelectRestView();
						}
					} else {
						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
					}
				}
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
		}
	}
}
