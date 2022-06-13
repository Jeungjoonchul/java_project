package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import dao.Check;
import dao.RestaurantDAO;
import dao.UserRegisterDAO;
import dto.RestaurantDTO;
import dto.UserRegisterDTO;
import view.SgtRestView;

public class Test {
	public static void main(String[] args) {
		int register_num = 3;
		UserRegisterDAO urdao = new UserRegisterDAO();
		RestaurantDAO rdao = new RestaurantDAO();
		UserRegisterDTO urdto = new UserRegisterDTO();
		urdto = urdao.a_select(register_num);

		String[] datas = new String[7];
		datas[0] = urdto.restaurant_name;
		datas[1] = urdto.category_name;
		datas[2] = urdto.restaurant_address;
		datas[3] = urdto.restaurant_phone;
		while (true) {
			// 음식점 이름 : 파스타
			// 음식점 카테고리 : 양식
			// 음식점 주소 : 서울시 강남구
			// 음식점 전화번호 : 전화번호
			String rest_status = "";
			rest_status += String.format("음식점 이름 : %s\n", datas[0]);
			rest_status += String.format("카테고리 : %s\n", datas[1]);
			rest_status += String.format("음식점 주소 : %s\n", datas[2]);
			rest_status += String.format("음식점 전화번호 : %s\n", datas[3]);
			System.out.println(rest_status);
			// 1. 변경 없이 진행 / 2. 식당 이름 수정 / 3. 카테고리 수정 / 4. 주소 수정 / 5. 전화번호 수정 / 6. 전체 수정
			// 번호를 입력하세요 : 2
			// 새로운 값을 입력하세요 : 파스타1
			String inputData = "";
			String[] inputInfo = { "", "", "음식점 이름", "카테고리", "음식점 주소", "음식점 전화번호", "예약 가능 인원", "음식점 휴무", "음식점 설명" };
			System.out.println(
					"1. 전체 수정 / 2. 음식점 이름 수정 / 3. 카테고리 수정 / 4. 음식점 주소 수정 / 5. 음식점 전화번호 수정 / 6. 이대로 진행 / 7. 뒤로 가기");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if (choice == 7) {
				System.out.println("종료 합니다.");
				break;
			} else if (choice == 1) {
				for (int i = 2; i < inputInfo.length; i++) {
					System.out.print(inputInfo[i] + "을(를) 입력하세요 : ");
					sc = new Scanner(System.in);
					inputData = sc.nextLine();
					datas[i - 2] = inputData;
				}
				continue;
			} else if (2 <= choice && choice <= 5) {
				System.out.print("새로운 " + inputInfo[choice] + "을(를) 입력하세요 : ");
				sc = new Scanner(System.in);
				inputData = sc.nextLine();
				System.out.println(datas[choice - 2] + " -> " + inputData);
				if (choice == 5) {
					datas[choice - 2] = Check.phoneOnlyNumber(inputData);
				}
				datas[choice - 2] = inputData;
//				switch (choice) {
//				case 2:
//					System.out.println(urdto.restaurant_name + " -> " + inputData);
//					urdto.restaurant_name = inputData;
//					break;
//				case 3:
//					System.out.println(urdto.category_name + " -> " + inputData);
//					urdto.category_name = inputData;
//					break;
//				case 4:
//					System.out.println(urdto.restaurant_address + " -> " + inputData);
//					urdto.restaurant_address = inputData;
//					break;
//				case 5:
//					System.out.println(urdto.restaurant_phone + " -> " + inputData);
//					urdto.restaurant_phone = inputData;
//					break;
//				}
				continue;
			} else if (choice == 6) {
				for (int i = 6; i < inputInfo.length; i++) {
					System.out.print(inputInfo[i] + "을(를) 입력하세요 : ");
					sc = new Scanner(System.in);
					inputData = sc.nextLine();
					datas[i - 2] = inputData;
				}
				RestaurantDTO result = new RestaurantDTO(datas);
				System.out.println(result);
				System.out.println("정말 이대로 등록 하실래요?(Y/N)");
				String checkInsert = sc.next();
				if (checkInsert.equalsIgnoreCase("Y")) {
//					if (urdao.insert(result, 6, "Y", register_num)) {
//
//						System.out.println("등록 성공");
//						break;
//
//					} else {
//						System.out.println("음식점 등록 실패 ");
//						break;
//					}
				} else if (checkInsert.equalsIgnoreCase("N")) {
					System.out.println("추천 음식점 등록을 취소합니다.");
					break;
				} else {
					System.out.println("잘못 입력");
				}
			} else {
				System.out.println("잘못입력");
			}
		}
	}
}
