package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.RestaurantDAO;
import dao.Session;
import dto.RestaurantDTO;

public class SearchRestView {
	public SearchRestView() {
		while (true) {
			Session.setData("restList", null); // 필터를 이용하여 조회한 리스트 세션에 저장하기 위해 초기화
			Session.setData("selectedRest", null);// 필터를 이용하여 조회한 리스트 중 선택한 음식점 저장하기 위해 초기화
			Scanner sc = new Scanner(System.in);
			RestaurantDAO rdao = new RestaurantDAO();
			int choiceCate = 0; 
			int choiceSort = 0; 
			int limit = 0; 
			System.out.println("===============");
			System.out.println("🍜음식점 찾아보기🍣");
			System.out.println("===============");
			System.out.println("■필터 선택");
			System.out.println("1. 좋아요 / 2. 평점 / 3. 리뷰 / 4. 카테고리 / 5. 메인 메뉴로");
			int choice = sc.nextInt();

			if (choice == 5) {
				System.out.println("💤음식점 찾아보기를 종료합니다.");
				break;
			}

			else if (!(1<=choice&&choice<=5)) {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				continue;
			}

			else if (choice == 4) {
				//숫자 외 문자 입력 하는 경우 생각
				System.out.println("■카테고리를 설정하세요.");
				System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
				System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
				choiceCate = sc.nextInt();
				if (!(1 <= choiceCate && choiceCate <= 6)) {
					System.out.println("※잘못 입력하였습니다. 다시 시도해주세요!");
					continue;
				}
			}

			System.out.print("■보기 개수 : ");
			limit = sc.nextInt();
			if (limit < 1) {
				System.out.println("※잘못 입력하였습니다. 다시 시도해주세요!");
				continue;
			}

			System.out.print("1. 오름차순으로 보기 / 2. 내림차순으로 보기");
			choiceSort = sc.nextInt();
			if (!(1 <= choiceSort && choiceSort <= 2)) {
				System.out.println("※잘못 입력하였습니다. 다시 시도해주세요!");
				continue;
			}

			ArrayList<RestaurantDTO> resultList = new ArrayList<RestaurantDTO>();

			// 메소드명 변경 searchList -> getRestList
			// getRestList 실행 시 조회된 음식점 리스트가 "restList" Session에 저장됨
			resultList = rdao.getList(choice, choiceCate, choiceSort, limit);
			if (resultList.size() == 0) {
				System.out.println("※조회된 식당이 없습니다.");
			} else {
				new ResultSearchRestView();
//				break;
			}

		}
	}
}
