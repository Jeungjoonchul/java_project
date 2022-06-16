package view;

import java.util.Scanner;

import dao.Check;
import dao.Session;
import dao.UserRegisterDAO;
import dto.UserDTO;
import dto.UserRegisterDTO;

public class UpdateSgtView {
	public UpdateSgtView(String register_num) {
		UserRegisterDAO urdao = new UserRegisterDAO();
		UserRegisterDTO ur = urdao.select(Integer.parseInt(register_num));
		String[] oldData = new String[6];
		oldData[1] = ur.restaurant_name;
		oldData[2] = ur.restaurant_address;
		oldData[3] = ur.category_name;
		oldData[4] = ur.restaurant_phone;
		oldData[5] = ur.reg_description;
		while (true) {

			String newData = "";
			System.out
					.println("■수정할 정보를 선택하세요.\n1. 음식점 이름 / 2. 음식점 주소 / 3. 카테고리 / 4. 음식점 전화번호 / 5. 음식점 추천 사유 / 6. 뒤로가기");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 6)) {
				if (Integer.parseInt(choice) == 6) {
					System.out.println("추천 음식점 수정하기를 종료합니다.");
					break;
				}
				if (Integer.parseInt(choice) == 3) {
					System.out.println("■카테고리를 선택해주세요.");
					System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
					System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
				} else {
					System.out.print("■새로운 내용을 입력하세요(나가기는 '!'): ");
				}

				sc = new Scanner(System.in);
				newData = sc.nextLine();
				switch (Integer.parseInt(choice)) {
				case 1:
					// 음식점 이름
					if (Check.validateRestName(newData)) {

					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						System.out.println("(특수문자는 입력할 수 없으며, 20자리까지 입력 가능합니다.)");
						continue;
					}
					break;
				case 2:
					// 음식점 주소
					if (Check.validateAddress(newData)) {
					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						System.out.println("(특수문자는 입력할 수 없으며, 100자리까지 입력 가능합니다.)");
						continue;
					}
					break;
				case 3:
					// 음식점 카테고리
					if (Check.validateNumber_choiceOne(newData, 1, 6)) {
						String[] category = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
						newData = category[Integer.parseInt(newData)];
					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 4:
					// 음식점 전화번호
					if (Check.validatePhone(newData)) {
						newData = Check.phoneOnlyNumber(newData);
					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 5:
					if(newData.length()<=330) {

					}else {
						System.out.println("※입력 가능한 글자수를 초과하였습니다. 330자 이내로 작성해주세요.");
						continue;
					}		
					break;
				}
				if (Integer.parseInt(choice) == 4) {
					System.out.println("수정 전 : " + Check.regPhone(oldData[Integer.parseInt(choice)]) + " -> 수정 후 : "
							+ Check.regPhone(newData));
				} else {
					System.out.println("수정 전 : " + oldData[Integer.parseInt(choice)] + " -> 수정 후 : " + newData);
				}

				while (true) {
					System.out.println("■정말 수정하시겠습니까?(Y / N)");
					String checkupdate = sc.next();
					if (checkupdate.equalsIgnoreCase("Y")) {
						if (urdao.update(Integer.parseInt(choice), newData, Integer.parseInt(register_num))) {
							System.out.println("◎변경이 완료되었습니다.");
							UserRegisterDTO afterUpdate = urdao.select(Integer.parseInt(register_num));
							System.out.println("┏변경 후 추천 음식점 내용\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
							System.out.println(afterUpdate);
							System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
							break;
						} else {
							System.out.println("※변경에 실패하였습니다.");
							break;
						}
					} else if (checkupdate.equalsIgnoreCase("N")) {
						System.out.println("※변경이 취소되었습니다.");
						break;
					} else {
						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
					}
				}

			} else {
				System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
			}
		}
	}
}
