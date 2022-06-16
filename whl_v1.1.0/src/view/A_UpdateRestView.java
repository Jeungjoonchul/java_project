package view;

import java.util.Scanner;
import java.util.regex.Pattern;

import dao.Check;
import dao.RestaurantDAO;
import dao.Session;
import dto.RestaurantDTO;

public class A_UpdateRestView {

	public A_UpdateRestView() {
		int weekcnt = 0;
		String yn = "";
		String[] weekend = { "월", "화", "수", "목", "금", "토", "일" };
		String[] weekarr = { "", "", "", "", "", "", "" };
		String close = null;
		while (true) {
			RestaurantDTO result = new RestaurantDTO();
			RestaurantDAO rdao = new RestaurantDAO();
			result = ((RestaurantDTO) Session.getData("selectedRest"));
			String[] category = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
			int to = 0;
			System.out.println("■변경하는 정보를 선택해주세요");
			System.out
					.println("1. 음식점 이름 / 2. 카테고리 / 3. 주소 / 4. 전화번호 / 5. 예약 가능 인원 / 6. 음식점 휴무일 / 7. 음식점 설명 / 8 .뒤로가기 ");
			Scanner sc = new Scanner(System.in);
			String check;
			String updatedata;
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 8)) {
				if (Integer.parseInt(choice) == 8) {// 8. 뒤로가기
					System.out.println("💤음식점 수정을 종료합니다.");
					break;
				} else if (Integer.parseInt(choice) == 1) {// 1.음식점 이름
					System.out.println("◎선택된 음식점 이름 :" + result.restaurant_name + " \n■변경할 음식점 이름 입력해주세요(나가기'!') :");
					sc = new Scanner(System.in);
					updatedata = sc.nextLine();
					if (updatedata.equalsIgnoreCase("!")) {// "!"입력시 빠져나가기 위해서 추가
						break;
					}
					System.out.println("■입력한 정보가 :" + updatedata + "가 맞으신가요?(Y입력시 변경됩니다.)");
					check = sc.next();
					if (check.equalsIgnoreCase("Y")) {

						if (rdao.update(Integer.parseInt(choice), updatedata, result.restaurant_id)) {
							System.out.println("◎변경 완료 되었습니다.");
							result.restaurant_name = updatedata;
							
						} else {
							System.out.println("※변경 실패");
						}
					} else {
						System.out.println("※다시 입력해주세요");
						continue;
					}

				} else if (Integer.parseInt(choice) == 2) {// 2.카테고리
					System.out.println("◎선택된 음식점 이름 :" + result.restaurant_name + " \n◎카테고리:" + result.category_name);
					System.out.println("■카테고리를 선택해주세요.");
					System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
					System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕(나가기'!')");
					sc = new Scanner(System.in);
					updatedata = sc.nextLine();
					if (updatedata.equalsIgnoreCase("!")) {// "!"입력시 빠져나가기 위해서 추가
						break;
					}
					if (!(Check.validateNumber(updatedata)
							&& (1 <= Integer.parseInt(updatedata) && Integer.parseInt(updatedata) <= 6))) {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					System.out.println("■입력한 정보가 :" + updatedata + "가 맞으신가요?(Y입력시 변경됩니다.)");
					check = sc.next();
					if (check.equalsIgnoreCase("Y")) {
						to = Integer.parseInt(updatedata);

						if (rdao.update(Integer.parseInt(choice), category[to], result.restaurant_id)) {
							System.out.println("◎변경완료 되었습니다.");
							result.category_name = updatedata;
							
						} else {
							System.out.println("※변경 실패");
						}
					} else {
						System.out.println("※다시 입력해주세요");
						continue;
					}

				} else if (Integer.parseInt(choice) == 3) {// 3.주소
					System.out.println("◎선택된 음식점 이름 :" + result.restaurant_name + " \n◎기존 주소 : "
							+ result.restaurant_address + "\n ■변경할 주소를 입력해주세요(나가기'!') :");
					sc = new Scanner(System.in);
					updatedata = sc.nextLine();
					if (updatedata.equalsIgnoreCase("!")) {// "!"입력시 빠져나가기 위해서 추가
						break;
					}
					System.out.println("■입력한 정보가 :" + updatedata + "가 맞으신가요?(Y입력시 변경됩니다.)");
					check = sc.next();
					if (check.equalsIgnoreCase("Y")) {
						if (rdao.update(Integer.parseInt(choice), updatedata, result.restaurant_id)) {
							System.out.println("◎변경완료 되었습니다.");
							result.restaurant_address = updatedata;
							
						} else {
							System.out.println("※변경 실패");
						}
					} else {
						System.out.println("※다시 입력해주세요");
						continue;
					}

				} else if (Integer.parseInt(choice) == 4) {// 4.전화번호
					System.out.println("◎선택된 음식점 이름 :" + result.restaurant_name + " \n◎기존 전화번호 : "
							+ result.restaurant_phone + "\n ■변경할 전화번호를 입력해주세요(나가기'!') :");
					sc = new Scanner(System.in);
					updatedata = sc.nextLine();
					if (updatedata.equalsIgnoreCase("!")) {// "!"입력시 빠져나가기 위해서 추가
						break;
					}
					if (!Check.validatePhone(updatedata)) {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					System.out.println("■입력한 정보가 :" + updatedata + "가 맞으신가요?(Y입력시 변경됩니다.)");
					check = sc.next();
					if (check.equalsIgnoreCase("Y")) {
						if (rdao.update(Integer.parseInt(choice), updatedata, result.restaurant_id)) {
							System.out.println("◎변경완료 되었습니다.");
							result.restaurant_phone = updatedata;
							
						} else {
							System.out.println("※변경 실패");
						}
					} else {
						System.out.println("※다시 입력해주세요");
						continue;
					}

				} else if (Integer.parseInt(choice) == 5) {// 5.예약가능한 인원
					System.out.println("◎선택된 음식점 이름 :" + result.restaurant_name + " \n◎기존 예약가능한 인원 : "
							+ result.restaurant_capacity + "\n ■변경할 인원을 입력해주세요(나가기'!') :");
					sc = new Scanner(System.in);
					updatedata = sc.nextLine();
					if (updatedata.equalsIgnoreCase("!")) {// "!"입력시 빠져나가기 위해서 추가
						break;
					}
					System.out.println("■입력한 정보가 :" + updatedata + "가 맞으신가요?(Y입력시 변경됩니다.)");
					check = sc.next();
					if (check.equalsIgnoreCase("Y")) {
						if (rdao.update(Integer.parseInt(choice), updatedata, result.restaurant_id)) {
							System.out.println("◎변경완료 되었습니다.");
							result.restaurant_capacity = Integer.parseInt(updatedata);
						
						} else {
							System.out.println("※변경 실패");
						}
					} else {
						System.out.println("※다시 입력해주세요");
						continue;
					}

				} else if (Integer.parseInt(choice) == 6) {// 6.휴무일
					System.out.println(
							"◎선택된 음식점 이름 :" + result.restaurant_name + " \n◎기존 휴무일 : " + result.restaurant_close);
					while (true) {
						System.out.println("■휴무일(월,화,수,목,금,토,일)중 하루만 입력해주세요(휴무일이 없는경우 엔터 입력)(나가기'!') :");
						sc = new Scanner(System.in);
						updatedata = sc.nextLine();
						if (updatedata.equals("")) {
							updatedata = "휴무없음";
							System.out.println("입력하신 정보가 \""+updatedata+"\"가 맞으신가요?(Y/N)");
							String checkDOW = sc.next();
							if(checkDOW.equalsIgnoreCase("Y")) {
								updatedata = null;
								if(rdao.update(Integer.parseInt(choice), updatedata, result.restaurant_id)){
									System.out.println("◎휴무일 변경에 성공했습니다.");
									break;
								}else {
									System.out.println("※휴무일 변경에 실패했습니다.");
									break;
								}
							}else if(checkDOW.equalsIgnoreCase("N")){
								System.out.println("※휴무일 변경을 종료합니다.");
								break;
							}else {
								System.out.println("※잘못 입력했습니다. 확인 후 다시 입력해주세요!");
								continue;
							}
						} else {
							System.out.println(updatedata);
							String pattern = "^[월화수목금토일]*$";
							if (Pattern.matches(pattern, updatedata)) {

								for (int j1 = 0; j1 < weekarr.length; j1++) {

									if (weekarr[j1].equals(updatedata)) {

										weekcnt++;
									}
								}
								if (weekcnt != 0) {
									System.out.println("※이미 입력된 값입니다. 해당 요일제외한 값을 입력해주세요");
									weekcnt = 0;
									updatedata = null;
									continue;
								}
								if (yn.equals("")) {
									close = updatedata;
								} else {
									close += ",";
									close += updatedata;
								}
								System.out.println("◎입력한 휴무일:" + close);
								System.out.print(" ■추가 휴무일 있으신가요?(Y/N) : ");
								yn = sc.nextLine();

								if (yn.equalsIgnoreCase("Y")) {
									for (int j = 0; j < weekend.length; j++) {
										if (updatedata.equals(weekend[j])) {
											weekarr[j] = updatedata;
										}

									} // weekarr에 값넣어둠
									updatedata = null;
									continue;
								} else if (yn.equalsIgnoreCase("N")) {
									if (updatedata.equalsIgnoreCase("!")) {// "!"입력시 빠져나가기 위해서 추가
										break;
									}

									System.out.println("■입력한 정보가 :" + close + "가 맞으신가요?(Y입력시 변경됩니다.)");
									check = sc.next();
									if (check.equalsIgnoreCase("Y")) {
										if (rdao.update(Integer.parseInt(choice), close, result.restaurant_id)) {
											System.out.println("◎변경완료 되었습니다.");
											result.restaurant_close = updatedata;
										
											break;
										} else {
											System.out.println("※변경 실패");
										}
									} else {
										System.out.println("※다시 입력해주세요");
										continue;
									}
									break;
								} else {
									System.out.println("※잘못 입력하셨습니다. 처음부터 다시 입력해주세요");
									for (int w = 0; w < weekarr.length; w++) {
										weekarr[w] = "";
									}
									yn = "";
									close = "";
									continue;
								}

							} else {
								System.out.println("※잘못 입력하셨습니다.");
								continue;
							}
						}
					}
				} else if (Integer.parseInt(choice) == 7) {// 7.음식점 설명
					System.out.println("◎선택된 음식점 이름 :" + result.restaurant_name + " \n◎기존 음식점 설명 : "
							+ result.restaurant_description + "\n■음식점 설명 변경할 내용입력해주세요 :(나가기'!') :");
					sc = new Scanner(System.in);
					updatedata = sc.nextLine();
					if (updatedata.equalsIgnoreCase("!")) {// "!"입력시 빠져나가기 위해서 추가
						break;
					}else if(updatedata.length()>=330) {
						System.out.println("※입력 가능한 글자수를 초과하였습니다. 330자 이내로 작성해주세요.");
						continue;
					}
					System.out.println("■입력한 정보가 :" + updatedata + "가 맞으신가요?(Y입력시 변경됩니다.)");
					check = sc.next();
					if (check.equalsIgnoreCase("Y")) {
						if (rdao.update(Integer.parseInt(choice), updatedata, result.restaurant_id)) {
							System.out.println("◎변경완료 되었습니다.");
							result.restaurant_name = updatedata;
							
						} else {
							System.out.println("※변경 실패");
						}
					} else {
						System.out.println("※다시 입력해주세요");
						continue;
					}

				}
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}

		}
	}
}
