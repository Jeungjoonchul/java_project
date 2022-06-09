package view;

import java.util.Scanner;

import dao.RestaurantDAO;
import dao.Session;
import dto.RestaurantDTO;

public class SelectRestView {
	public SelectRestView() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			RestaurantDTO result = new RestaurantDTO();
			result = ((RestaurantDTO) Session.getData("selectedRest"));
//			String selectedRest = "";
//			selectedRest+="=====================================\n";
//			selectedRest+=String.format("%s (%s)\n",result.restaurant_name,result.category_name);
//			selectedRest+=String.format("%s / %s\n", result.restaurant_address,result.restaurant_phone);
//			selectedRest+=String.format("예약 가능 인원 : %d\n",result.restaurant_capacity);
//			selectedRest+=String.format("휴무일 : %s\n",result.restaurant_close);
//			selectedRest+=String.format("%s\n",result.restaurant_description);
//			selectedRest+=String.format("♡%d / ☆%d(%d)\n",result.restaurant_like_cnt,result.avg_score,result.reply_cnt);
//			selectedRest+="=====================================\n";
//			System.out.println(selectedRest);
			System.out.println("┏음식점 정보\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println(result);
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			System.out.println("1. 예약하기 / 2. 뒤로가기");
			int choice = sc.nextInt();
			if (choice == 2) {// A
				System.out.println("💤현재 페이지를 종료합니다.");
				break;
			} else if (choice == 1) {// A
				new BookRestView();
				break;
			} else {// A
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
		}

	}
}
