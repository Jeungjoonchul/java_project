package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.BookDAO;
import dao.Check;
import dao.RestaurantDAO;
import dao.Session;
import dto.BookDTO;
import dto.RestaurantDTO;

public class CurrentBookView {
	public CurrentBookView() {
		while(true) {
			System.out.println("==============");
			System.out.println("🍜현재 예약 내역🍣");
			System.out.println("==============");
			ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
			BookDAO bdao = new BookDAO();
			RestaurantDAO rdao = new RestaurantDAO();
			//현재 예약 내역을 보기 위해 매개변수로 "current"설정
			String moment = "current";
			bookList = bdao.getList(moment);
			if (bookList.size() == 0) {
				System.out.println("※예약 내역이 없습니다.");
				break;
			} else {
				System.out.println("┏현재 예약 내역\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");

				for (BookDTO cb : bookList) {
					//BookDTO toString override
//					result += String.format("%d. %s(%d명) / 예약일시 : %s\n", bd.book_num, bd.restaurant_name,
//							bd.book_companion_number, bd.book_schedule);
					System.out.println(cb);
				}
				
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 수정하기 / 2. 철회하기 / 3. 뒤로가기");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if (choice == 3) {
				System.out.println("💤현재 예약 보기를 종료합니다.");
				break;
			} else if (!(1 <= choice && choice <= 2)) {
				System.out.println("※잘못 입력하였습니다. 다시 시도해주세요.");
			} else {
				System.out.print("■변경할 예약 : ");
				int book_num = sc.nextInt();
				switch (choice) {
				case 1:
					// 수정하기
					
					
					if (bdao.select(book_num)!=null) {
						new UpdateBookView();
					} else {
						System.out.println("※예약 찾기에 실패했습니다.");
					}

					break;
				case 2:
					// 철회하기
					System.out.print("■예약을 정말 철회 하시겠습니까?(Y/N) : ");
					String checkDelete = sc.next();
					if (checkDelete.equalsIgnoreCase("Y")) {
						if (bdao.delete(book_num)) {
							System.out.println("◎예약이 철회 되었습니다.");
							break;
						} else {
							System.out.println("※예약 철회에 실패하였습니다.");
							break;
						}
					} else if (checkDelete.equalsIgnoreCase("N")) {
						System.out.println("💤예약 철회를 종료합니다.");
						break;
					} else {
						System.out.println("※잘못 입력하였습니다. 다시 시도해주세요.");
					}
					break;
				}

			}
		}
//		while (true) {
//			System.out.println("==========현재 예약 내역==========");
//
//			ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
//			BookDAO bdao = new BookDAO();
//			bookList = bdao.searchList();
//			if (bookList.size() == 0) {
//				System.out.println("예약 내역을 찾을 수 없습니다.");
//			} else {
//				String result = "";
//				for (BookDTO bd : bookList) {
//					result += String.format("%d. %s(%d명) / 예약일시 : %s", bd.book_num, bd.restaurant_name,
//							bd.book_companion_number, bd.book_schedule);
//				}
//				System.out.println(result);
//				System.out.println("==============================");
//			}
//
//			System.out.println("1. 수정하기 / 2. 철회하기 / 3. 뒤로가기");
//			Scanner sc = new Scanner(System.in);
//			int choice = sc.nextInt();
//			if (choice == 3) {
//				System.out.println("현재 예약 보기를 종료합니다.");
//				break;
//			} else if (choice == 1 || choice == 2) {
//				// 현재 예약 수정하기
//				System.out.print("변경할 예약 : ");
//				int book_num = sc.nextInt();
//				if(bdao.setSRSession(book_num)) {
//					if (choice == 1) {
//						String newData = "";
//						System.out.println("수정할 내용을 선택하세요.");
//						System.out.println("1. 예약 인원 / 2. 예약 일시 / 3. 뒤로가기");
//						choice = sc.nextInt();
//						if (choice == 3) {
//							continue;
//						} else if (choice == 1) {
//							System.out.print("새로운 예약 인원 : ");
//							int companion_number = sc.nextInt();
//							if(bdao.capacityCheck(companion_number)) {
//								System.out.println("수정 가능합니다.");
//								newData = companion_number+"";
//							}else {
//								System.out.print(((RestaurantDTO) Session.getData("selectedRest")).restaurant_capacity);
//								System.out.println("명 이하로 예약이 가능합니다. 다시 시도해주세요.");
//								continue;
//							}
//						} else if (choice == 2) {
//							System.out.println("새로운 예약 일(입력 형식 > yyyy-mm-dd) : ");
//							String date = sc.next();
//							if(RegEx.validateDate(date)) {
//								if(bdao.checkDate(date)) {
//									System.out.println("새로운 예약 시간(입력 형식 > hh:mm) : ");
//									String time = sc.next();
//									if(RegEx.validateTime(time)) {
//										if(RegEx.validateDateTime(date, time)) {
//											newData = date+" "+time;
//										}else {
//											System.out.println("현재 시간 1시간 이후 부터 예약 가능합니다.");
//											continue;
//										}
//									}else {
//										System.out.println("입력 형식이 올바르지 않거나 시간 유효하지 않습니다. 다시 시도해주세요.");
//										continue;
//									}
//								}else {
//									System.out.println("해당 일에는 예약이 불가합니다. 다시 확인해주세요.");
//									continue;
//								}
//							}else {
//								System.out.println("입력 형식이 올바르지 않거나 날짜가 유효하지 않습니다. 다시 시도해주세요.");
//								continue;
//							}
//						} else {
//							System.out.println("잘못 입력하였습니다. 다시 시도해주세요.");
//							continue;
//						}
//						System.out.println("정말 예약을 수정 하시겠습니까?(Y/N) : ");
//						String checkUpdate = sc.next();
//						if (checkUpdate.equalsIgnoreCase("Y")) {
//							if(bdao.update(book_num, choice, newData)) {
//								System.out.println("예약이 수정 되었습니다.");
//								break;
//							}else {
//								System.out.println("예약 수정에 실패하였습니다.");
//								break;
//							}
//						} else if (checkUpdate.equalsIgnoreCase("N")) {
//							System.out.println("예약 수정을 종료합니다.");
//							break;
//						} else {
//							System.out.println("잘못 입력하였습니다. 다시 시도해주세요.");
//						}
//					} else {
//						System.out.print("예약을 정말 철회 하시겠습니까?(Y/N) : ");
//						String checkDelete = sc.next();
//						if (checkDelete.equalsIgnoreCase("Y")) {
//							if(bdao.delete(book_num)) {
//								System.out.println("예약이 철회 되었습니다.");
//								break;
//							}else {
//								System.out.println("예약 철회에 실패하였습니다.");
//								break;
//							}
//						} else if (checkDelete.equalsIgnoreCase("N")) {
//							System.out.println("예약 철회를 종료합니다.");
//							break;
//						} else {
//							System.out.println("잘못 입력하였습니다. 다시 시도해주세요.");
//						}
//					}
//				}else {
//					System.out.println("예약 번호 찾기에 실패했습니다.");
//					break;
//				}	
//			} else {
//				System.out.println("잘못 입력하였습니다. 다시 시도해주세요.");
//			}
//
//		}
	}

}
