package view;

import java.util.Scanner;

import dao.Check;
import dao.Session;
import dao.UserRegisterDAO;
import dto.UserDTO;
import dto.UserRegisterDTO;

public class SgtRestView {
	public SgtRestView() {
		System.out.println("================");
		System.out.println("🍜음식점  추천 하기🍣");
		System.out.println("================");
		String[] inputInfo = { "음식점 이름", "음식점 주소", "카테고리", "음식점 전화번호", "음식점 추천 사유", "" };
		String[] datas = new String[6];
		for (int i = 0; i < inputInfo.length;) {
			UserRegisterDAO urdao = new UserRegisterDAO();
			Scanner sc = new Scanner(System.in);
			
			if (i == 5) {
				
				System.out.print("■정말 등록하시겠습니까?(Y/N) : ");
				String checkInsert = sc.next();
				if (checkInsert.equalsIgnoreCase("Y")) {
					UserRegisterDTO newReg = new UserRegisterDTO(datas);
					if (urdao.insert(newReg)) {
						System.out.println("◎추천 등록이 완료되었습니다.");
						new SgtListView();
						break;
					} else {
						System.out.println("※추천 등록이 실패하였습니다.");
						break;
					}
				} else if (checkInsert.equalsIgnoreCase("N")) {
					System.out.println("💤추천 등록이 취소되었습니다.");
					break;
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}

			else {
				if (i == 2) {
					System.out.println("■카테고리를 선택해주세요.");
					System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
					System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
				}else {
					System.out.print("■"+inputInfo[i] + "(종료는 '!' ) : ");
				}
				sc = new Scanner(System.in);
				String inputData = sc.nextLine();
				
				if (inputData.equals("!")) {
					System.out.println("💤음식점 추천을 종료합니다.");
					break;
				} else {
					switch (i) {
					case 0:
					case 1:
					case 4:
						datas[i] = inputData;
						i++;
						break;
					case 2:
						String[] cate = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
						int choiceCate = Integer.parseInt(inputData);
						if (!(Check.validateNumber(inputData)&&(1<=Integer.parseInt(inputData)&&Integer.parseInt(inputData)<=6))) {
							System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
						} else {
							datas[i] = cate[choiceCate];
							i++;
						}
						break;
					case 3:
						if (Check.validatePhone(inputData)) {
							datas[i] = Check.phoneOnlyNumber(inputData);
							datas[5] = ((UserDTO) Session.getData("loginUser")).user_id;
							i++;
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						}
						break;
					}
				}
			}
		}

//		if (i == 3 && data.length() > 14) {
//			System.out.println("형식이 올바르지 않습니다. 다시 입력해주세요.");
//		} else if (i == 5) {
//			if (data.equalsIgnoreCase("Y")) {
//				String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
//				UserRegisterDTO newReg = new UserRegisterDTO(inputDatas.get(0), inputDatas.get(1),
//						inputDatas.get(2), inputDatas.get(3), inputDatas.get(4), user_id);
//				if (urdao.insert(newReg)) {
//					System.out.println("추천 등록이 완료되었습니다. 관리자 확인 후 등록이 진행됩니다.");
//					new UserSgtView();
//					break;
//				} else if (data.equalsIgnoreCase("N")) {
//					System.out.println("추천 등록이 취소되었습니다.");
//					break;
//				} else {
//
//				}
//			}
//		} else {
//			inputDatas.add(data);
//			i++;
//		}
//			System.out.print("음식점 이름 : ");
//			String restaurant_name = sc.next();
//
//			System.out.print("음식점 주소 : ");
//			sc = new Scanner(System.in);
//			String restaurant_address = sc.nextLine();
//
//			System.out.print("카테고리 : ");
//			sc = new Scanner(System.in);
//			String category_name = sc.nextLine();
//
//			System.out.print("음식점 전화번호 : ");
//			String restaurant_phone = sc.next();
//
//			System.out.print("음식점 추천 사유 : ");
//			sc = new Scanner(System.in);
//			String reg_description = sc.nextLine();
//			while (true) {
//				System.out.println("정말로 추천하시겠습니까(Y/N)?");
//				String check = sc.next();
//				if (check.equalsIgnoreCase("Y")) {
//					String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
//					UserRegisterDTO newReg = new UserRegisterDTO(restaurant_name, restaurant_address, category_name,
//							restaurant_phone, reg_description, user_id);
//					if (urdao.insert(newReg)) {
//						System.out.println("추천 등록이 완료되었습니다. 관리자 확인 후 등록이 진행됩니다.");
//						new UserSgtView();
//						break;
//					}
//				} else if (check.equalsIgnoreCase("N")) {
//					System.out.println("추천 등록이 취소되었습니다.");
//					break;
//				} else {
//					System.out.println("잘못 입력하였습니다.");
//				}
//			}

	}
}