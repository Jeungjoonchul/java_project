package test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import dao.RegEx;

public class Test {
	public static void main(String[] args) {
		System.out.println("===============");
		System.out.println("🍜음식점 추가하기🍣");
		System.out.println("===============");
		String[] category = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
		int to = 0;
		String[] inputInfo = { "음식점 이름(", "음식점 카테고리(", "음식점 주소(도로명주소 /", "음식점 전화번호(", "예약 가능 인원(",
				"휴무일(월,화,수,목,금,토,일)중 하루만 입력해주세요 (휴무일이 없는경우 엔터 입력) /", "음식점 설명(", "" };
		String[] datas = new String[7];
		Scanner sc = new Scanner(System.in);
		HashMap<String, String> close = new HashMap<String, String>();
		for (int i = 0; i < inputInfo.length;) {
			if (i == 7) {

				System.out.println("===============입력하신 정보===============");
				for (int p = 0; p < datas.length; p++) {
					System.out.print("■" + inputInfo[p] + "" + datas[p] + ")\n");
				}
				System.out.println("음식점 등록이 완료되었습니다.");// 성공 시 출력 및 매소드 연결 부분 추가
				break;

			} else {
				// 카테고리 설정은 번호 입력 시 해당 번호에 맞는 값을 datas배열에 저장
				if (i == 1) {
					System.out.println("■카테고리를 선택해주세요.");
					System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
					System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");

				} else {
					// 입력 받아야할 내용 순서대로 출력
					System.out.print("■" + inputInfo[i] + " 나가기는 '!') :");
				}
				// 스캐너로 사용자에게 입력 받기
				sc = new Scanner(System.in);
				String inputData = sc.nextLine();
				if (inputData.equalsIgnoreCase("!")) {// "!"입력시 빠져나가기 위해서 추가
					break;
				}
				// 카테고리, 전화번호, 휴무일의 유효성 검사
				switch (i) {
				case 1:
					// 1~6을 제외한 숫자 입력 시
					if (!(RegEx.validateNumber(inputData)
							&& (1 <= Integer.parseInt(inputData) && Integer.parseInt(inputData) <= 6))) {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 3:// 전화번호 오류 검사
					if (!RegEx.validatePhone(inputData)) {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 5:
					if (inputData.equals("")) {
						close.isEmpty();
						inputData="휴무없음";
						i++;
					} else {
						String pattern = "^[월화수목금토일]*$";
						if (Pattern.matches(pattern, inputData)) {
							if (close.get(inputData) == null) {
								close.put(inputData, inputData);
								System.out.println("입력한 휴무일" + close.values());
								System.out.print("또 입력하실?(Y/N) : ");
								String yn = sc.nextLine();
								if (yn.equalsIgnoreCase("Y")) {
									continue;
								} else if (yn.equalsIgnoreCase("N")) {
									String[] keys = { "월", "화", "수", "목", "금", "토", "일" };
									inputData = "";
									for (int j = 0; j < keys.length; j++) {
										if (close.get(keys[j]) != null) {
											inputData += "," + close.get(keys[j]);
										}
									}
									inputData = inputData.substring(1, inputData.length());
									i++;
								} else {
									System.out.println("잘못 입력했음");
									continue;
								}
							} else {
								System.out.println("이미 입력한 값임");
								continue;
							}
						} else {
							System.out.println("입력 형식이 올바르지 않습니다.");
							continue;
						}
					}

					break;
				}
				if (i == 1) {
					to = Integer.parseInt(inputData);
					System.out.print("■입력하시려는 내용이 \"" + category[to] + "\"가 맞나요? : (Y/N)");

				} else {//월,수,금
					System.out.print("■입력하시려는 내용이 \"" + inputData + "\"가 맞나요? : (Y/N)");
				}
				String checkInput = sc.next();
				if (checkInput.equalsIgnoreCase("Y")) {
					if (to != 0) {
						datas[i] = category[to];
						i++;
						to = 0;
					} else {
						datas[i] = inputData;
						i++;// "Y"입력시 다음 으로 넘가기 위해 추가
					}
				} else {
					System.out.println("다시입력하세요,나가시려면'!'입력하세요");// 없으면 허전해서 추가함
				}
			}
		}

	}
}
