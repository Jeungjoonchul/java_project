package view;

import java.util.Scanner;

import dao.Check;
import dao.RestaurantDAO;
import dao.Session;
import dto.RestaurantDTO;

public class SelectRestView {
	public SelectRestView() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			RestaurantDTO result = new RestaurantDTO();
			result = ((RestaurantDTO) Session.getData("selectedRest"));
			System.out.println("┏음식점 정보\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println(result);
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			System.out.println("1. 예약하기 / 2. 뒤로가기");
			String choice = sc.next();
			if(Check.validateNumber_choiceOne(choice, 1, 2))
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
