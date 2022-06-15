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
			String keyword="";
			System.out.println("");
			System.out.println("==================");
			System.out.println("(관리자)🍜음식점 보기🍣");
			System.out.println("==================");
			System.out.println("");
			System.out.println("■필터 선택");
			System.out.println("1. 식당명 / 2. 전화번호 / 3. 카테고리  / 4. 뒤로가기");
			String inputData = sc.next();
			if(Check.validateNumber_choiceOne(inputData, 1, 5)) {
				choice=inputData;
				if (Integer.parseInt(inputData) == 4) {
					System.out.println("💤음식점 보기를 종료합니다.");
					break;
				}
				
				else if (Integer.parseInt(inputData) == 3) {
					System.out.println("■카테고리를 설정하세요.");
					System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
					System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
					inputData = sc.next();
					if (Check.validateNumber_choiceOne(inputData, 1, 6)) {
						choiceCate = inputData;
					}else {
						System.out.println("※잘못 입력하였습니다. 다시 시도해주세요!");
						continue;
					}
				}else {
					System.out.print("■키워드를 입력하세요 : ");
					inputData = sc.next();
					if(Integer.parseInt(inputData)==1) {
						if(Check.validateRestName(inputData)) {
							keyword = inputData;
						}else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!(30자리까지 입력 가능)");
							continue;
						}
					}else if(Integer.parseInt(inputData)==2) {
						if(Check.validateNumber(inputData)&&inputData.length()<=12) {
							keyword = inputData;
						}else {
							System.out.println("※입력 형식이 올바르지 않습니다. 12자리 이내의 숫자만 입력해주세요!");
						}
					}
				}
				System.out.print("■보기 개수 : ");
				inputData = sc.next();
				if (Check.validateNumber(inputData)) {
					if (Integer.parseInt(inputData) > 0) {
						limit = inputData;
					} else {
						System.out.println("※보기 개수는 0보다 큰 숫자(정수)를 입력해주세요!");
						continue;
					}
				} else {
					System.out.println("※잘못 입력하였습니다. 다시 시도해주세요!");
					continue;
				}

				System.out.print("1. 오름차순으로 보기 / 2. 내림차순으로 보기");
				inputData = sc.next();

				if (Check.validateNumber_choiceOne(inputData, 1, 2)) {
					choiceSort = inputData;
				} else {
					System.out.println("※잘못 입력하였습니다. 다시 시도해주세요!");
					continue;
				}

				ArrayList<RestaurantDTO> resultList = new ArrayList<RestaurantDTO>();

				resultList = rdao.getList(Integer.parseInt(choice), Integer.parseInt(choiceCate),Integer.parseInt(choiceSort), Integer.parseInt(limit),keyword);
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
