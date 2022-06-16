package view;

import java.util.Arrays;
import java.util.Scanner;

import dao.Check;
import dao.RestaurantDAO;
import dao.Session;
import dto.RestaurantDTO;
import dto.UserDTO;

public class A_InsertRestView {

	public A_InsertRestView() {
		// View Title
		RestaurantDAO rdao = new RestaurantDAO();
		System.out.println("===============");
		System.out.println("🍜음식점 추가하기🍣");
		System.out.println("===============");
		String[] inputInfo = { "음식점 이름(", "음식점 카테고리", "음식점 주소(도로명주소 /", "음식점 전화번호(", "예약 가능 인원(", "휴무일", "음식점 설명(",
				"" };
		String[] datas = new String[7];
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < inputInfo.length;) {
			if (i == 7) {
				RestaurantDTO newRest = new RestaurantDTO(datas);
				System.out.println("┏입력한 정보\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(newRest);
				System.out.println(
						"┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.print("■정말 등록하시겠습니까?(Y/N) : ");
				String checkInsert = sc.next();
				if (checkInsert.equalsIgnoreCase("Y")) {
					if (rdao.insert(newRest)) {
						System.out.println("◎음식점 등록이 완료되었습니다.");
						i++;
					} else {
						System.out.println("※음식점 등록에 실패하였습니다.");
						i++;
					}
				} else if (checkInsert.equalsIgnoreCase("N")) {
					System.out.println("💤음식점 추가하기를 종료합니다.");
					i++;
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			} else {
				// 카테고리 설정은 번호 입력 시 해당 번호에 맞는 값을 datas배열에 저장
				if (i == 1) {
					System.out.println("■카테고리를 선택해주세요.");
					System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
					System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
				} else if (i == 5) {
					System.out.println("■휴무일에 해당하는 숫자를 띄어쓰기 없이 선택해주세요.(ex : 토,일 -> '67'입력)");
					System.out.println("1. 월 / 2. 화 / 3. 수 / 4. 목 / 5. 금 / 6. 토 / 7. 일 / 8. 휴무없음 ");
				} else {
					System.out.print("■" + inputInfo[i] + " 나가기는 '!') : ");
				}
				sc = new Scanner(System.in);
				String inputData = sc.nextLine();
				if (inputData.equals("!")) {
					System.out.println("💤음식점 추가하기를 종료합니다.");
					break;
				}
				switch (i) {
				case 0:
					// 음식점 이름
					if (Check.validateRestName(inputData)) {
					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!(30자리까지 입력 가능)");
						continue;
					}
					break;
				case 1:
					// 음식점 카테고리
					if (Check.validateNumber_choiceOne(inputData, 1, 6)) {
						String[] category = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
						inputData = category[Integer.parseInt(inputData)];
					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 2:
					// 음식점 주소
					if (Check.validateAddress(inputData)) {
					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 3:
					// 음식점 전화번호
					if (Check.validatePhone(inputData)) {
						inputData = Check.regPhone(inputData);
					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 4:
					// 예약 가능 인원
					if (Check.validateNumber(inputData)) {
					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 5:
					// 음식점 휴무
					if (inputData.equals("8")) {
						inputData = "휴무 없음";
					} else if (Check.validateNumber_choiceMulti(inputData, 1, 7)) {
						String[] dow = { "", "월", "화", "수", "목", "금", "토", "일" };
						String[] sort_inputData = inputData.split("");
						Arrays.sort(sort_inputData);
						inputData = "";
						for (int j = 0; j < sort_inputData.length; j++) {
							if (j == sort_inputData.length - 1) {
								inputData += dow[Integer.parseInt(sort_inputData[j])];
							} else {
								inputData += dow[Integer.parseInt(sort_inputData[j])] + ",";
							}
						}
					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 6:
					if(inputData.length()<=330) {
					}else {
						System.out.println("※입력 가능한 글자수를 초과하였습니다. 330자 이내로 작성해주세요.");
						continue;
					}		
					break;
				}
				while (true) {
					System.out.print("■입력하시려는 내용이 \"" + inputData + "\"이(가) 맞나요?(Y/N) : ");
					String checkInput = sc.next();
					if (checkInput.equalsIgnoreCase("Y")) {
						if (i == 3) {
							inputData = Check.phoneOnlyNumber(inputData);
						} else if (i == 5) {
							if (inputData.equals("휴무 없음")) {
								inputData = null;
							}
						}
						datas[i] = inputData;
						i++;
						break;
					} else if (checkInput.equalsIgnoreCase("N")) {
						break;
					} else {
						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
					}
				}

			}
		}
	}
}
