package view;

import java.util.Scanner;

import dao.Check;
import dao.Session;
import dao.UserRegisterDAO;
import dto.UserDTO;
import dto.UserRegisterDTO;

public class SgtRestView {
	public SgtRestView() {
		System.out.println("");
		System.out.println("================");
		System.out.println("🍜음식점  추천 하기🍣");
		System.out.println("================");
		System.out.println("");
		String[] inputInfo = { "음식점 이름", "음식점 주소", "카테고리", "음식점 전화번호", "음식점 추천 사유", "" };
		String[] datas = new String[6];
		for (int i = 0; i < inputInfo.length;) {
			UserRegisterDAO urdao = new UserRegisterDAO();
			Scanner sc = new Scanner(System.in);
			if (i == 5) {
				UserRegisterDTO newReg = new UserRegisterDTO(datas);
				System.out.println("┏입력한 정보\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(newReg);
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.print("■정말 등록하시겠습니까?(Y/N) : ");
				String checkInsert = sc.next();
				if (checkInsert.equalsIgnoreCase("Y")) {
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
			} else {
				if (i == 2) {
					System.out.println("■카테고리를 선택해주세요.");
					System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
					System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
				} else {
					System.out.print("■" + inputInfo[i] + "(종료는 '!' ) : ");
				}
				sc = new Scanner(System.in);
				String inputData = sc.nextLine();

				if (inputData.equals("!")) {
					System.out.println("💤음식점 추천을 종료합니다.");
					break;
				} else {
					switch (i) {
					case 0:
						// 음식점 이름
						if(Check.validateRestName(inputData)){
							datas[i] = inputData;
							i++;
						}else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!(30자리까지 입력 가능)");
						}
						break;
					case 1:
						// 음식점 주소
						if(Check.validateAddress(inputData)) {
							datas[i] = inputData;
							i++;
						}else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						}
						break;
					case 2:
						//카테고리
						String[] cate = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
						if(Check.validateNumber_choiceOne(inputData, 1, 6)) {
							datas[i] = cate[Integer.parseInt(inputData)];
							i++;
						}else {
							System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
						}
						break;
					case 3:
						//음식점 전화번호
						if (Check.validatePhone(inputData)) {
							datas[i] = Check.phoneOnlyNumber(inputData);
							i++;
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						}
						break;
					case 4:
						//음식점 추천 사유
						datas[i] = inputData;
						datas[5] = ((UserDTO) Session.getData("loginUser")).user_id;
						i++;
						break;
					}
				}
			}
		}
	}
}