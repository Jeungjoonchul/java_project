//package test;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Scanner;
//import java.util.Set;
//import java.util.regex.Pattern;
//
//import dao.Check;
//import dao.RestaurantDAO;
//import dao.UserRegisterDAO;
//import dto.RestaurantDTO;
//import dto.UserRegisterDTO;
//import view.SgtRestView;
//
//public class Test {
//	public static void main(String[] args) {
//		RestaurantDAO rtdao = new RestaurantDAO();
//		String[] datas = new String[7];
//		int register_id = 1;
//		datas[0] = rtdao.a_restSelect(register_id).restaurant_name;
//		datas[1] = rtdao.a_restSelect(register_id).category_name;
//		datas[2] = rtdao.a_restSelect(register_id).restaurant_address;
//		datas[3] = rtdao.a_restSelect(register_id).restaurant_phone;
//		datas[4] = Integer.toString(rtdao.a_restSelect(register_id).restaurant_capacity);
//		datas[5] = rtdao.a_restSelect(register_id).restaurant_close;
//		datas[6] = rtdao.a_restSelect(register_id).restaurant_description;
//		String[] inputInfo = { "음식점 이름", "카테고리", "음식점 주소", "음식점 전화번호", "예약 가능 인원", "음식점 휴무일", "음식점 설명" };
//		while (true) {
//			
//			// 1. 음식점 수정하기
//			System.out.println("1. 전체 수정 / 2. 일부 수정 / 3. 나가기");
//			Scanner sc = new Scanner(System.in);
//			String choice2 = sc.nextLine();
//			if (Check.validateNumber_choiceOne(choice2, 1, 3)) {
//				// 전체 수정
//				if(Integer.parseInt(choice2)==3) {
//					
//				}else {
//					if(Integer.parseInt(choice2)==1) {
//						//전체 수정
//						//inputData => datas로 저장
//					}else if(Integer.parseInt(choice2)==2){
//						//일부 수정
//						//inputData => datas로 저장
//					}
//					//미리보기
//					//저장할래?
//					//ㅇㅋ->RestaurantDTO newRest = new RestaurantDTO(datas); -> 메소드 호출
//				}
//				if (Integer.parseInt(choice2) == 1) {
//					for (int i = 0; i < inputInfo.length - 3;) {
//						if (i == 1) {
//							System.out.println("■카테고리를 선택해주세요.");
//							System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
//							System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
//						} else {
//							System.out.print("■" + inputInfo[i] + " 뒤로가기는 '!') : ");
//						}
//						sc = new Scanner(System.in);
//						String inputData = sc.nextLine();
//						if (inputData.equals("!")) {
//							System.out.println("💤추천 음식점 전체 수정을 종료합니다.");
//							break;
//						} else {
//							switch (i) {
//							case 0:
//								// 음식점 이름
//								if (Check.validateRestName(inputData)) {
//								} else {
//									System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
//									System.out.println("(특수문자는 입력할 수 없으며, 20자리까지 입력 가능합니다.)");
//									continue;
//								}
//								break;
//							case 1:
//								// 음식점 카테고리
//								if (Check.validateNumber_choiceOne(inputData, 1, 6)) {
//									String[] category = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
//									inputData = category[Integer.parseInt(inputData)];
//								} else {
//									System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
//									continue;
//								}
//								break;
//							case 2:
//								// 음식점 주소
//								if (Check.validateAddress(inputData)) {
//								} else {
//									System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
//									System.out.println("(특수문자는 입력할 수 없으며, 100자리까지 입력 가능합니다.)");
//									continue;
//								}
//								break;
//							case 3:
//								if (Check.validatePhone(inputData)) {
//									inputData = Check.regPhone(inputData);
//								} else {
//									System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
//									continue;
//								}
//								break;
//							}
//							while (true) {
//								System.out.print("■입력하시려는 내용이 \"" + inputData + "\"이(가) 맞나요?(Y/N) : ");
//								String checkInput = sc.next();
//								if (checkInput.equalsIgnoreCase("Y")) {
//									if (i == 3) {
//										inputData = Check.phoneOnlyNumber(inputData);
//									}
//									datas[i] = inputData;
//									i++;
//									break;
//								} else if (checkInput.equalsIgnoreCase("N")) {
//									break;
//								} else {
//									System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
//								}
//							}
//						}
//					}
//				} else if (Integer.parseInt(choice2) == 2) {
//					// 일부 수정
//					System.out.println("■수정할 내용을 입력하세요.");
//					System.out.println(
//							"1. 식당 이름 / 2. 카테고리 / 3. 식당 주소 / 4. 식당 전화번호  / 5. 예약 가능 인원 / 6. 식당 휴무일 / 7. 식당 설명");
//					choice2 = sc.next();
//					if (Check.validateNumber_choiceOne(choice2, 1, 7)) {
//						if (Integer.parseInt(choice2) == 2) {
//							System.out.println("■카테고리를 선택해주세요.");
//							System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
//							System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
//						} else {
//							System.out.print("■새로운 " + inputInfo[Integer.parseInt(choice2) - 1] + "을(를) 입력해주세요 : ");
//						}
//						sc = new Scanner(System.in);
//						String inputData = sc.nextLine();
//						switch (Integer.parseInt(choice2)) {
//						case 1:
//							// 식당 이름
//							System.out.println("■새로운 식당 이름을 써주세요.");
//							if (Check.validateRestName(inputData)) {
//							} else {
//								System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
//								System.out.println("(특수문자는 입력할 수 없으며, 20자리까지 입력 가능합니다.)");
//								continue;
//							}
//							break;
//						case 2:
//							// 음식점 카테고리
//							if (Check.validateNumber_choiceOne(inputData, 1, 6)) {
//								String[] category = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
//								inputData = category[Integer.parseInt(inputData)];
//							} else {
//								System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
//								continue;
//							}
//							break;
//						case 3:
//							// 식당 주소
//							System.out.println("■새로운 식당 주소를 써주세요.");
//							if (Check.validateAddress(inputData)) {
//							} else {
//								System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
//								System.out.println("(특수문자는 입력할 수 없으며, 100자리까지 입력 가능합니다.)");
//								continue;
//							}
//							break;
//						case 4:
//							// 식당 전화번호
//							System.out.println("■새로운 식당 전화번호를 써주세요");
//							if (Check.validatePhone(inputData)) {
//								inputData = Check.regPhone(inputData);
//							} else {
//								System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
//								continue;
//							}
//							break;
//						case 5:
//							// 식당 예약 가능 인원
//							System.out.println("■식당 예약 가능인원을 써주세요.");
//							if (Check.validateNumber(inputData)) {
//								inputData = inputData + "명";
//							} else {
//								System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
//								continue;
//							}
//							break;
//						case 6:
//							// 식당 휴무일
//							System.out.println("■휴무일에 해당하는 숫자를 띄어쓰기 없이 선택해주세요.(ex : 토,일 -> '67'입력)");
//							System.out.println("1. 월 / 2. 화 / 3. 수 / 4. 목 / 5. 금 / 6. 토 / 7. 일 / 8. 휴무없음 ");
//							if (inputData.equals("8")) {
//								inputData = "휴무없음";
//							} else if (Check.validateNumber_choiceMulti(inputData, 1, 7)) {
//								String[] dow = { "", "월", "화", "수", "목", "금", "토", "일" };
//								String result = inputData;
//								inputData = "";
//								for (int j = 0; j < result.split("").length; j++) {
//									if (j == result.split("").length - 1) {
//										inputData += dow[Integer.parseInt(result.split("")[j])];
//									} else {
//										inputData += dow[Integer.parseInt(result.split("")[j])] + ",";
//									}
//								}
//							} else {
//								System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
//								continue;
//							}
//							break;
//						case 7:
//							// 식당 설명
//							System.out.println("■간단한 식당 설명을 써주세요.");
//							inputData = inputData;
//							break;
//						}
//						System.out.print("■입력하시려는 내용이 \"" + inputData + "\"이(가) 맞나요?(Y/N) : ");
//						String checkInput = sc.next();
//						if (checkInput.equalsIgnoreCase("Y")) {
//							if (Integer.parseInt(choice2) == 4) {
//								inputData = Check.phoneOnlyNumber(inputData);
//							}
//							//db에서 a_restSelect메소드로 정보 받아와서 datas에 알맞은 곳에 저장
//							//inputDatas로 수정값 입력 받음
//							//변경 이후의 새로운 값인 inputDatas를 datas배열에 알맞은 방에 저장
//							//datas를 이용하여 new RestaurantDTO 생성 후 newRest에 저장
//							datas[Integer.parseInt(choice2) - 1] = inputData;
//							
//						} else if (checkInput.equalsIgnoreCase("N")) {
//							break;
//						} else {
//							System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
//						}
//					} else {
//						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
//						continue;
//					}
//					RestaurantDTO newRest = new RestaurantDTO(datas);
//					System.out.println("┏입력한 음식점 정보\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
//					System.out.println(newRest);
//					System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
//					System.out.print("정말 등록하시겠습니까?(Y/N) : ");
//					String checkInsert = sc.next();
//					if (checkInsert.equalsIgnoreCase("Y")) {
//						RestaurantDAO rdao = new RestaurantDAO();
//						if (rdao.update(newRest, register_id/* Integer.parseInt(register_id) */)) {
//							System.out.println("등록 성공");
//							break;
//						} else {
//							System.out.println("등록 실패");
//							break;
//						}
//					} else if (checkInsert.equalsIgnoreCase("N")) {
//						break;
//					} else {
//						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
//					}
//					// 나가기
//					System.out.println("나가기를 누르셨습니다");
//					break;
//				} else {
//					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
//				}
//			}else {
//				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
//			}
//		}
//	}
//}
