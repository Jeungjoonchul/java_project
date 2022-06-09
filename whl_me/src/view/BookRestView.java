package view;

import java.util.Scanner;

import dao.BookDAO;
import dao.RegEx;
import dao.Session;
import dto.BookDTO;
import dto.RestaurantDTO;

public class BookRestView {
	public BookRestView() {
		BookDAO bdao = new BookDAO();
		Scanner sc = new Scanner(System.in);
		System.out.println("================");
		System.out.println("🍜음식점 예약 하기🍣");
		System.out.println("================");
		String[] datas = new String[3];
		String[] datasInfo = { "■예약 년월일(yyyy-mm-dd / ", "■예약 시간(hh:mm / ", "■예약 인원(", "" };

		for (int i = 0; i < datasInfo.length;) {
			// 입력한 데이터의 유효검 검사가 끝나면 실행
			if (i == 3) {
				System.out.println("■예약 하시겠습니까?(Y/N)");
				String check = sc.next();
				if (check.equalsIgnoreCase("Y")) {
					BookDTO newBook = new BookDTO(datas);
					//메소드명 변경 insertBook => insert
					if (bdao.insert(newBook)) {
						System.out.println("◎예약이 완료되었습니다.");
						new CurrentBookView();
						i++;
					} else {
						System.out.println("※예약에 실패했습니다.");
						break;
					}
				} else if (check.equalsIgnoreCase("N")) {
					System.out.println("💤예약하기를 종료합니다.");
					break;
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}

			// 예약 정보 입력(예약 날짜, 인원)
			else {
				System.out.print(datasInfo[i] + "나가기는 '!') : ");
				String inputData = sc.next();
				if (inputData.equals("!")) {
					System.out.println("💤예약하기를 종료합니다.");
					break;
				}

				else {
					switch (i) {
					case 0:
						if (RegEx.validateDate(inputData)) {
							if (bdao.checkDate(inputData)) {
								datas[i] = inputData; // 2022-06-07
								i++;
							} else {
								System.out.println("※예약이 불가합니다. 날짜 또는 음식점의 휴무일을 확인 후 다시 시도해주세요!");
							}
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						}
						break;
					case 1:
						if (RegEx.validateTime(inputData)) {
							if (RegEx.validateDateTime(datas[0], inputData)) {
								System.out.println("◎예약 가능합니다.");
								datas[i] = inputData;
								i++;
							} else {
								System.out.println("※입력하신 시간으로 예약이 불가합니다. 확인 후 다시  시도해주세요!(1시간 이전까지 예약 가능합니다.)");
							}
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						}
						break;
					case 2:
						if (bdao.checkCapacity(Integer.parseInt(inputData))) {
							datas[i] = inputData;
							i++;
						} else {
							System.out.print("※"+((RestaurantDTO) Session.getData("selectedRest")).restaurant_capacity);
							System.out.println("명 이하로 예약이 가능합니다. 확인 후 다시 시도해주세요!");
						}
						break;
					}
				}
			}

		}
//			while (true) {
//			System.out.print("예약 년 (입력형식 숫자 4자리): ");
//			int year = sc.nextInt();
//			System.out.print("예약 월 (입력형식 숫자 2자리): ");
//			int month = sc.nextInt();
//			System.out.print("예약 일 (입력형식 숫자 2자리): ");
//			int day = sc.nextInt();
//			System.out.print("예약 시간 (입력형식 숫자 2자리 ex:19): ");
//			int hour = sc.nextInt();
//			System.out.print("예약 분 (입력형식 숫자 2자리 ex:00): ");
//			int minute = sc.nextInt();
//			if (RegEx.validateDate(year, month, day, hour, minute)) {
//				if (bdao.checkDate(year, month, day, hour, minute)) {
//					System.out.print("예약 인원 : ");
//					int companion_number = sc.nextInt();
//					if (bdao.capacityCheck(companion_number)) {
//						System.out.println("예약 하시겠습니까?(Y/N)");
//						String check = sc.next();
//						if (check.equalsIgnoreCase("Y")) {
//							if (bdao.insertBook(year, month, day, hour, minute, companion_number)) {
//								System.out.println("예약이 완료되었습니다.");
//								new UserBookView();
//								break;
//							} else {
//								System.out.println("예약이 실패했습니다.");
//								break;
//							}
//						} else if (check.contentEquals("N")) {
//							break;
//						}
//					} else {
//						System.out.print(((RestaurantDTO) Session.getData("selectedRest")).restaurant_capacity);
//						System.out.println("명 이하로 예약이 가능합니다. 다시 시도해주세요.");
//						continue;
//					}
//				} else {
//					System.out.println("예약 일시가 올바르지 않습니다. 다시 확인해주세요.");
//					continue;
//				}
//			} else {
//				System.out.println("입력 형식이 잘못되었습니다. 다시 확인해주세요.");
//				continue;
//			}
//		}

	}
}
