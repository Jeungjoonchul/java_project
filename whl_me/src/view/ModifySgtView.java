package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.RegEx;
import dao.UserRegisterDAO;
import dto.UserRegisterDTO;

public class ModifySgtView {
	public ModifySgtView() {
		while(true) {
			Scanner sc = new Scanner(System.in);
			UserRegisterDAO urdao = new UserRegisterDAO();
			ArrayList<UserRegisterDTO> urList = urdao.searchList();
			System.out.println("┏등록 대기 중\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			for (UserRegisterDTO ur : urList) {
				if(ur.is_register.equalsIgnoreCase("N")) {
					System.out.println(String.format("📃등록 번호 : %d / %s(%s)\n", ur.register_num, ur.restaurant_name,
							ur.category_name));
				}
			}
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.print("■등록 번호 선택 : ");
			int register_num = sc.nextInt();
			UserRegisterDTO ur = urdao.select(register_num);
			if(ur==null) {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}else {
				System.out.println(ur);
				System.out.println("■메뉴를 선택하세요.");
				System.out.println("1. 수정하기 / 2. 삭제하기 / 3. 뒤로가기");
				int choice = sc.nextInt();
				if(choice==3) {
					System.out.println("💤추천 음식점 수정/삭제를 종료합니다.");
					break;
				}else if(choice==1) {
					String newData = "";
					System.out.println("■수정할 정보를 선택하세요.\n1. 음식점 이름 / 2. 음식점 주소 / 3. 카테고리 / 4. 음식점 전화번호 / 5. 음식점 추천 사유");
					int choiceCol = sc.nextInt();
					if(1<=choiceCol&&choiceCol<=5) {
						if(choiceCol==3) {
							String[] cate = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
							System.out.println("■카테고리를 선택해주세요.");
							System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
							System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
							int choiceCate = sc.nextInt();
							newData = cate[choiceCate];
						}else {
							System.out.print("■수정할 내용을 입력하세요 : ");
							sc = new Scanner(System.in);
							newData = sc.nextLine();
							if(choiceCol==4) {
								if(RegEx.validatePhone(newData)) {
									newData = RegEx.phoneOnlyNumber(newData);
								}else {
									System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
									continue;
								}
							}
						}
						System.out.println("■정말 수정하시겠습니까?(Y / N)");
						String checkupdate = sc.next();
						if (checkupdate.equalsIgnoreCase("Y")) {
							if(urdao.update(choiceCol, newData, register_num)) {
								System.out.println("◎변경이 완료되었습니다.");
								break;
							}else {
								System.out.println("※변경에 실패하였습니다.");
								break;
							}
						}else if (checkupdate.equalsIgnoreCase("N")) {
							System.out.println("※변경이 취소되었습니다.");
							break;
						}else {
							System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
						}
					}else {
						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
					}
				}else if(choice==2) {
					System.out.println("■정말 삭제하시겠습니까?(Y / N)");
					String checkDelete = sc.next();
					if(checkDelete.equalsIgnoreCase("Y")) {
						if(urdao.delete(register_num)) {
							System.out.println("◎삭제가 완료되었습니다.");
							break;
						}else {
							System.out.println("※삭제에 실패하였습니다.");
							break;
						}
					}else if(checkDelete.equalsIgnoreCase("N")) {
						System.out.println("※삭제가 취소되었습니다.");
						break;
					}else {
						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
					}
				}else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}
		}
		
	}
}
