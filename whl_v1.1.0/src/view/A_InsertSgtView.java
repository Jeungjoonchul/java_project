package view;

import java.util.Arrays;
import java.util.Scanner;

import dao.Check;
import dao.RestaurantDAO;
import dao.UserRegisterDAO;
import dto.RestaurantDTO;

public class A_InsertSgtView {

	public A_InsertSgtView(String register_num) {
		UserRegisterDAO urdao = new UserRegisterDAO();
		String[] datas = new String[7];
		datas[0] = urdao.a_select(Integer.parseInt(register_num)).restaurant_name;
		datas[1] = urdao.a_select(Integer.parseInt(register_num)).category_name;
		datas[2] = urdao.a_select(Integer.parseInt(register_num)).restaurant_address;
		datas[3] = urdao.a_select(Integer.parseInt(register_num)).restaurant_phone;
		datas[4] = "0";
		datas[5] = null;
		datas[6] = null;

		while (true) {
			String[] tmp = new String[7];
			String[] inputInfo = { "음식점 이름", "카테고리", "음식점 주소", "음식점 전화번호", "예약 가능 인원", "음식점 휴무일", "음식점 설명" };
			RestaurantDTO newRest = new RestaurantDTO(datas);
			System.out.println("┏추천 음식점 입력 상태\t\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println(newRest);
			System.out
					.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 전체 수정 / 2. 일부 수정 / 3. 승인 / 4. 나가기");
			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine();
			if (Check.validateNumber_choiceOne(choice, 1, 4)) {
				if (Integer.parseInt(choice) == 1) {
					// 전체 수정
					for (int i = 0; i < inputInfo.length - 3;) {
						if (i == 1) {
							System.out.println("■카테고리를 선택해주세요.");
							System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
							System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
						} else {
							System.out.print("■" + inputInfo[i] + "(뒤로가기는 '!') : ");
						}
						sc = new Scanner(System.in);
						String inputData = sc.nextLine();
						if (inputData.equals("!")) {
							System.out.println("💤추천 음식점 전체 수정을 종료합니다.");
							break;
						} else {
							switch (i) {
							case 0:
								// 음식점 이름
								if (Check.validateRestName(inputData)) {
								} else {
									System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
									System.out.println("(특수문자는 입력할 수 없으며, 20자리까지 입력 가능합니다.)");
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
									System.out.println("(특수문자는 입력할 수 없으며, 100자리까지 입력 가능합니다.)");
									continue;
								}
								break;
							case 3:
								if (Check.validatePhone(inputData)) {
									inputData = Check.regPhone(inputData);
								} else {
									System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
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
									}
									tmp[i] = inputData;
									i++;
									if (i == 4) {
										for (int j = 0; j < tmp.length - 3; j++) {
											datas[j] = tmp[j];
										}
									}
									break;
								} else if (checkInput.equalsIgnoreCase("N")) {
									break;
								} else {
									System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
								}
							}
						}
					}
				} else if (Integer.parseInt(choice) == 2) {
					// 일부 수정
					System.out.println("■수정할 내용을 입력하세요.");
					System.out.println("1. 식당 이름 / 2. 카테고리 / 3. 식당 주소 / 4. 식당 전화번호 / 5. 뒤로가기");
					choice = sc.next();
					if (Check.validateNumber_choiceOne(choice, 1, 5)) {
						if (Integer.parseInt(choice) == 5) {
							continue;
						} else if (Integer.parseInt(choice) == 2) {
							System.out.println("■카테고리를 선택해주세요.");
							System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
							System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
						} else {
							System.out.print("■새로운 " + inputInfo[Integer.parseInt(choice) - 1] + "을(를) 입력해주세요 : ");
						}
						sc = new Scanner(System.in);
						String inputData = sc.nextLine();
						switch (Integer.parseInt(choice)) {
						case 1:
							// 음식점 이름
							if (Check.validateRestName(inputData)) {
							} else {
								System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
								System.out.println("(특수문자는 입력할 수 없으며, 20자리까지 입력 가능합니다.)");
								continue;
							}
							break;
						case 2:
							// 음식점 카테고리
							if (Check.validateNumber_choiceOne(inputData, 1, 6)) {
								String[] category = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
								inputData = category[Integer.parseInt(inputData)];
							} else {
								System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
								continue;
							}
							break;
						case 3:
							// 음식점 주소
							if (Check.validateAddress(inputData)) {
							} else {
								System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
								System.out.println("(특수문자는 입력할 수 없으며, 100자리까지 입력 가능합니다.)");
								continue;
							}
							break;
						case 4:
							if (Check.validatePhone(inputData)) {
								inputData = Check.regPhone(inputData);
							} else {
								System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
								continue;
							}
							break;
						}
						while (true) {
							System.out.print("■입력하시려는 내용이 \"" + inputData + "\"이(가) 맞나요?(Y/N) : ");
							String checkInput = sc.next();
							if (checkInput.equalsIgnoreCase("Y")) {
								if (Integer.parseInt(choice) == 4) {
									inputData = Check.phoneOnlyNumber(inputData);
								}
								datas[Integer.parseInt(choice) - 1] = inputData;
								break;
							} else if (checkInput.equalsIgnoreCase("N")) {
								break;
							} else {
								System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
							}
						}
					} else {
						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
				} else if (Integer.parseInt(choice) == 3) {
					// 승인
					boolean readyInsert = false;
					for (int i = 4; i < inputInfo.length;) {
						if (i == 5) {
							System.out.println("■휴무일에 해당하는 숫자를 띄어쓰기 없이 선택해주세요.(ex : 토,일 -> '67'입력)");
							System.out.println("1. 월 / 2. 화 / 3. 수 / 4. 목 / 5. 금 / 6. 토 / 7. 일 / 8. 휴무없음 ");
						} else {
							System.out.print("■" + inputInfo[i] + "(뒤로가기는 '!') : ");
						}
						sc = new Scanner(System.in);
						String inputData = sc.nextLine();
						if (inputData.equals("!")) {
							System.out.println("💤추천 음식점 승인을 종료합니다.");
							break;
						} else {
							switch (i) {
							case 4:
								if (Check.validateNumber(inputData)) {
									inputData = inputData + "명";
								} else {
									System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
									continue;
								}
								break;
							case 5:
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
								if (inputData.length() <= 330) {
								} else {
									System.out.println("※입력 가능한 글자수를 초과하였습니다. 330자 이내로 작성해주세요.");
									continue;
								}
								break;
							}
							while (true) {
								System.out.print("■입력하시려는 내용이 \"" + inputData + "\"이(가) 맞나요?(Y/N) : ");
								String checkInput = sc.next();
								if (checkInput.equalsIgnoreCase("Y")) {
									if (i == 4) {
										inputData = inputData.replace("명", "");
									} else if (i == 5) {
										if (inputData.equals("휴무 없음")) {
											inputData = null;
										}
									}
									tmp[i] = inputData;
									i++;
									if (i == 7) {
										for (int j = 4; j < tmp.length; j++) {
											datas[j] = tmp[j];
										}
									}
									readyInsert = true;
									break;
								} else if (checkInput.equalsIgnoreCase("N")) {
									i++;
									break;
								} else {
									System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
								}
							}

						}
					}
					if (readyInsert) {
						newRest = new RestaurantDTO(datas);
						System.out.println(
								"┏입력한 음식점 정보\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
						System.out.println(newRest);
						System.out.println(
								"┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
						System.out.print("■추천한 유저에게 전하는 한마디를 작성해주세요 : ");
						sc = new Scanner(System.in);
						String admin_comment = sc.nextLine();
						System.out.println("※의견 : " + admin_comment);
						System.out.print("정말 등록하시겠습니까?(Y/N) : ");
						String checkInsert = sc.next();
						if (checkInsert.equalsIgnoreCase("Y")) {
							RestaurantDAO rdao = new RestaurantDAO();
							if (rdao.insert(newRest, admin_comment, Integer.parseInt(register_num))) {
								System.out.println("등록 성공");
								break;
							} else {
								System.out.println("등록 실패");
								break;
							}
						} else if (checkInsert.equalsIgnoreCase("N")) {
							datas[4] = "0";
							datas[5] = null;
							datas[6] = null;
							break;
						} else {
							System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
						}
					}
				} else if (Integer.parseInt(choice) == 4) {
					// 나가기
					System.out.println("추천 음식점 등록을 종료합니다.");
					break;
				}
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}

		}
	}

}
