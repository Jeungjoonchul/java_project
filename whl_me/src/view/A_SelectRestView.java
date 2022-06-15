package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.RestaurantDAO;
import dao.Session;
import dto.RestaurantDTO;

public class A_SelectRestView {
	public A_SelectRestView() {
		while (true) {
			Session.setData("restList", null); // 필터를 이용하여 조회한 리스트 세션에 저장하기 위해 초기화
			Session.setData("selectedRest", null);// 필터를 이용하여 조회한 리스트 중 선택한 음식점 저장하기 위해 초기화
			Scanner sc = new Scanner(System.in);
			RestaurantDAO rdao = new RestaurantDAO();
			String choice = "";
			String choiceCate = "1"; 
			String choiceSort = ""; 
			String limit = ""; 
			//View Title\
			
			System.out.println("===============");
			System.out.println("(관리자)🍜음식점 보기🍣");
			System.out.println("===============");
			System.out.println("■필터 선택");
			System.out.println("1. 식당명 / 2. 전화번호 / 3. 카테고리  / 4. 뒤로가기");
			System.out.println("choiceCate:"+choiceCate+"\nchoiceSort:"+choiceSort+"\nlimit:"+limit);
			String inputNum = sc.next();
			if(Check.validateNumber_choiceOne(inputNum, 1, 5)) {
				choice=inputNum;
				if (Integer.parseInt(inputNum) == 4) {
					System.out.println("💤음식점 보기를 종료합니다.");
					break;
				}
				
				else if (Integer.parseInt(inputNum) == 3) {
					//숫자 외 문자 입력 하는 경우 생각
					System.out.println("■카테고리를 설정하세요.");
					System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
					System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
					inputNum = sc.next();
					if (Check.valiadateNumber_choiceMulti(inputNum, 1, 6)) {
						choiceCate = inputNum;
					}else {
						System.out.println("※잘못 입력하였습니다. 다시 시도해주세요!");
						continue;
					}
				}
				System.out.print("■보기 개수 : ");
				inputNum = sc.next();
				if (Check.validateNumber(inputNum)) {
					if (Integer.parseInt(inputNum) > 0) {
						limit = inputNum;
					} else {
						System.out.println("※보기 개수는 0보다 큰 숫자(정수)를 입력해주세요!");
						continue;
					}
				} else {
					System.out.println("※잘못 입력하였습니다. 다시 시도해주세요!");
					continue;
				}

				System.out.print("1. 오름차순으로 보기 / 2. 내림차순으로 보기");
				inputNum = sc.next();

				if (Check.validateNumber_choiceOne(inputNum, 1, 2)) {
					choiceSort = inputNum;
				} else {
					System.out.println("※잘못 입력하였습니다. 다시 시도해주세요!");
					continue;
				}

				ArrayList<RestaurantDTO> resultList = new ArrayList<RestaurantDTO>();

				resultList = rdao.getList(Integer.parseInt(choice), Integer.parseInt(choiceCate),Integer.parseInt(choiceSort), Integer.parseInt(limit));
				if (resultList.size() == 0) {
					System.out.println("※조회된 식당이 없습니다.");
				} else {
					new A_ResultSearchRestView();
				}
			}else {
				System.out.println("※잘못 입력하였습니다. 다시 시도해주세요!");
			}
			

			
		}
	}
}
