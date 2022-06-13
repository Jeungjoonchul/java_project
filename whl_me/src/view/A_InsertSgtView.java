package view;

import java.util.Scanner;

import dao.Check;
import dao.RestaurantDAO;
import dao.UserRegisterDAO;
import dto.RestaurantDTO;


public class A_InsertSgtView {

	public A_InsertSgtView(int register_num) {
		UserRegisterDAO urdao = new UserRegisterDAO();
		String[] datas = new String[7];
		datas[0]=urdao.a_select(register_num).restaurant_name;
		datas[1]=urdao.a_select(register_num).category_name;
		datas[2]=urdao.a_select(register_num).restaurant_address;
		datas[3]=urdao.a_select(register_num).restaurant_phone;
		datas[4]="0";
		datas[5]="";
		datas[6]="";
		String[] inputInfo = { "식당 이름", "카테고리","식당 주소",  "식당 전화번호" ,"예약 인원","휴무일","식당 설명"};
		//카테고리만 수정하는 경우
		//datas[0],datas[1],datas[4]
		while (true) {
			RestaurantDTO newRest = new RestaurantDTO(datas);
			System.out.println(newRest);
			System.out.println("1. 전체 수정 / 2. 일부 수정 / 3. 승인 / 4. 나가기");
			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine();
			if(Check.validateNumber_choiceOne(choice, 1, 4)) {
				if (Integer.parseInt(choice)==1) {
					// 전체 수정
					String inputData;
					for (int i = 0; i < inputInfo.length-3; i++) {
						System.out.print(inputInfo[i] + " : ");
						sc = new Scanner(System.in);
						inputData = sc.nextLine();
						datas[i] = inputData;
					}
				} else if (Integer.parseInt(choice)== 2) {
					// 일부 수정
					System.out.println("수정할 내용을 입력하세요.");
					System.out.println("1. 식당 이름 / 2. 카테고리 / 3. 식당 주소 / 4. 식당 전화번호");

					choice = sc.next();
					if(Check.validateNumber_choiceOne(choice, 1, 4)) {
						if(Integer.parseInt(choice)==1) {
							System.out.print("새로운 식당 이름을 써주세요 : ");
							sc = new Scanner(System.in);
							String restaurant_name = sc.nextLine();
							datas[Integer.parseInt(choice)-1]=restaurant_name;
						}else if(Integer.parseInt(choice)==2) {
							String[] cate = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
							System.out.println("카테고리를 선택해주세요.");
							System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
							System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
							sc = new Scanner(System.in);
							choice = sc.nextLine();
							String category_name = cate[Integer.parseInt(choice)];
							datas[Integer.parseInt(choice)-1]=category_name;
						}else if(Integer.parseInt(choice)==3) {
							System.out.print("새로운 식당 주소를 써주세요 : ");
							sc = new Scanner(System.in);
							String restaurant_address = sc.nextLine();
							datas[Integer.parseInt(choice)-1]=restaurant_address;
						}else if(Integer.parseInt(choice)==4) {
							System.out.print("새로운 식당 전화번호를 써주세요 : ");
							sc = new Scanner(System.in);
							String restaurant_phone = sc.nextLine();
							datas[Integer.parseInt(choice)-1]=restaurant_phone;
						}
					}else {
						System.out.println("잘못 입력함");
					}
				} else if (Integer.parseInt(choice)== 3) {
					// 승인
					for (int i = 4; i < inputInfo.length; i++) {
						System.out.print(inputInfo[i]+" : ");
						sc = new Scanner(System.in);
						String inputData = sc.nextLine();
						datas[i] = inputData;
					}
					newRest = new RestaurantDTO(datas);
					System.out.println(newRest);
					System.out.print("정말 등록하시겠습니까?(y/n) : ");
					String checkInsert = sc.next();
					if(checkInsert.equalsIgnoreCase("Y")) {
						RestaurantDAO rdao = new RestaurantDAO();
						if(rdao.insert(newRest,register_num)) {
							System.out.println("등록 성공");
							break;
						}else {
							System.out.println("등록 실패");
							break;
						}
					}else if(checkInsert.equalsIgnoreCase("N")) {
						break;
					}else {
						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
					}
				} else if (Integer.parseInt(choice)== 4) {
					// 나가기
					System.out.println("추천 음식점 등록을 종료합니다.");
					break;
				} 
			}else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}
			
		}
	}

}
