package view;

import java.util.Scanner;

import dao.BookDAO;
import dao.RegEx;
import dao.Session;
import dto.BookDTO;
import dto.RestaurantDTO;

public class UpdateBookView {
	public UpdateBookView() {
		
		RestaurantDTO bookedRest=(RestaurantDTO) Session.getData("selectedRest");
		BookDTO selectedBook = (BookDTO) Session.getData("selectedBook");
		System.out.println("┏예약한 식당 정보 및 예약 현황\t━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("🍽식당 정보");
		System.out.println(bookedRest);
		System.out.println("🕒예약 정보");
		System.out.println(selectedBook);
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		
		while (true) {
			BookDAO bdao = new BookDAO();
			String newData = "";
			System.out.println("■수정할 내용을 선택하세요.");
			System.out.println("1. 예약 인원 / 2. 예약 일시 / 3. 뒤로가기");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if (choice == 3) {
				System.out.println("💤현재 예약 수정을 종료합니다.");
				break;
			} else {
				
				switch (choice) {
				case 1:
					System.out.print("■새로운 예약 인원 : ");
					int companion_number = sc.nextInt();
					if (bdao.checkCapacity(companion_number)) {
						System.out.println("◎수정 가능합니다.");
						newData = companion_number + "";
					} else {
						System.out.print("※"+((RestaurantDTO) Session.getData("selectedRest")).restaurant_capacity);
						System.out.println("명 이하로 예약 수정이 가능합니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 2:
					System.out.print("■새로운 예약 일(입력 형식 > yyyy-mm-dd) : ");
					String date = sc.next();
					if (RegEx.validateDate(date)) {
						if (bdao.checkDate(date)) {
							System.out.print("■새로운 예약 시간(입력 형식 > hh:mm) : ");
							String time = sc.next();
							if (RegEx.validateTime(time)) {
								if (RegEx.validateDateTime(date, time)) {
									newData = date + " " + time;
								} else {
									System.out.println("※현재 시간 1시간 이후 부터 예약 가능합니다. 확인 후 다시 시도해주세요!");
									continue;
								}
							} else {
								System.out.println("※입력 형식이 올바르지 않거나 시간 유효하지 않습니다. 확인 후 다시 시도해주세요!");
								continue;
							}
						} else {
							System.out.println("※해당 일에는 예약이 불가합니다. 확인 후 다시 시도해주세요!");
							continue;
						}
					}else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				default:
					System.out.println("※잘못 입력했습니다. 확인 후 다시 시도해주세요!");
				}
				System.out.println("■정말 예약을 수정 하시겠습니까?(Y/N) : ");
				String checkUpdate = sc.next();
				if (checkUpdate.equalsIgnoreCase("Y")) {
					int book_num = ((BookDTO)Session.getData("selectedBook")).book_num;
					if (bdao.update(book_num, choice, newData)) {
						System.out.println("◎예약이 수정 되었습니다.");
						break;
					} else {
						System.out.println("※예약 수정에 실패하였습니다.");
						break;
					}
				} else if (checkUpdate.equalsIgnoreCase("N")) {
					System.out.println("💤예약 수정을 종료합니다.");
					break;
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}

		}
	}
}
