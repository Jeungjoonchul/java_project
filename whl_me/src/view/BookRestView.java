package view;

import java.util.Scanner;

import dao.BookDAO;
import dao.Check;
import dao.RestaurantDAO;
import dao.Session;
import dto.BookDTO;
import dto.RestaurantDTO;

public class BookRestView {
	public BookRestView() {
		BookDAO bdao = new BookDAO();
		RestaurantDAO rdao = new RestaurantDAO();
		System.out.println("");
		System.out.println("================");
		System.out.println("🍜음식점 예약 하기🍣");
		System.out.println("================");
		System.out.println("");
		String[] datas = new String[3];
		String[] datasInfo = { "예약 년월일(yyyy-mm-dd / ", "예약 시간(hh:mm / ", "예약 인원(", "" };

		for (int i = 0; i < datasInfo.length;) {
			// 입력한 데이터의 유효검 검사가 끝나면 실행
			// 예약 정보 입력(예약 날짜, 인원)
			Scanner sc = new Scanner(System.in);
			if (i == 3) {
				BookDTO newBook = new BookDTO(datas);
				newBook.restaurant_name=((RestaurantDTO) Session.getData("selectedRest")).restaurant_name;
				System.out.println("┏예약정보\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(newBook);
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println("■예약 하시겠습니까?(Y/N)");
				String check = sc.next();
				if (check.equalsIgnoreCase("Y")) {
					if (bdao.insert(newBook)) {
						System.out.println("◎예약이 완료되었습니다.");
						new CurrentBookView();
						i++;
					} else {
						System.out.println("※예약에 실패했습니다.");
						i++;
					}
				} else if (check.equalsIgnoreCase("N")) {
					System.out.println("💤예약하기를 종료합니다.");
					i++;
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			} else {
				System.out.print("■"+datasInfo[i] + "나가기는 '!') : ");
				String inputData = sc.next();
				if (inputData.equals("!")) {
					System.out.println("💤예약하기를 종료합니다.");
					break;
				} else {
					switch (i) {
					case 0:
						// 날짜 입력 "yyyy-MM-dd"
						if (Check.validateDate(inputData)) {
							if (rdao.checkDate(inputData)) {
								datas[i] = inputData;
								i++;
							} else {
								System.out.println("※예약이 불가합니다. 날짜 또는 음식점의 휴무일을 확인 후 다시 시도해주세요!");
							}
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						}
						break;
					case 1:
						if (Check.validateTime(inputData)) {
							if (Check.validateDateTime(datas[0], inputData)) {
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
						if (Check.validateNumber(inputData)) {
							if (rdao.checkCapacity(Integer.parseInt(inputData))) {
								datas[i] = inputData;
								i++;
							} else {
								System.out.print(
										"※" + ((RestaurantDTO) Session.getData("selectedRest")).restaurant_capacity);
								System.out.println("명 이하로 예약이 가능합니다. 확인 후 다시 시도해주세요!");
							}
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						}

						break;
					}

				}
			}
		}
	}
}
