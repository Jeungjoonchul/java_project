package view;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Check;
import dao.UserRegisterDAO;
import dto.UserRegisterDTO;

public class ModifySgtView {
	public ModifySgtView() {
		while (true) {
			Scanner sc = new Scanner(System.in);
			UserRegisterDAO urdao = new UserRegisterDAO();
			ArrayList<UserRegisterDTO> urList = urdao.getList();
			ArrayList<UserRegisterDTO> waitingList = new ArrayList<UserRegisterDTO>();
			for (UserRegisterDTO ur : urList) {
				if (ur.is_register.equalsIgnoreCase("N") && ur.admin_comment == null) {
					waitingList.add(ur);
				}
			}
			if (waitingList.size() == 0) {
				System.out.println("");
				System.out.println("※추천하신 음식점이 없습니다. 음식점 추천 후 이용해주세요.");
				System.out.println("");
				break;
			} else {
				System.out.println("┏등록 대기 중\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				for (UserRegisterDTO ur : waitingList) {
					System.out.println(String.format("┃ 📃등록 번호 : %d / %s(%s)", ur.register_num, ur.restaurant_name,
							ur.category_name));

				}
				System.out.println(
						"┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.print("■등록 번호 선택 : ");
				String register_num = sc.next();
				if (Check.validateNumber(register_num)) {
					UserRegisterDTO ur = urdao.select(Integer.parseInt(register_num));
					if (ur == null) {
						System.out.println("※추천하신 음식점을 찾을 수 없습니다. 확인 후 다시 시도해주세요!");
					} else {
						System.out.println(
								"┏선택된 음식점 추천 내용\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
						System.out.println(ur);
						System.out.println(
								"┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
						System.out.println("■메뉴를 선택하세요.");
						System.out.println("1. 수정하기 / 2. 삭제하기 / 3. 뒤로가기");
						String choice = sc.next();
						if (Check.validateNumber_choiceOne(choice, 1, 3)) {
							if (Integer.parseInt(choice) == 3) {
								System.out.println("💤추천 음식점 수정/삭제를 종료합니다.");
								break;
							} else if (Integer.parseInt(choice) == 1) {
								// 추천 음식점 수정하기
								new UpdateSgtView(register_num);
								break;
							} else if (Integer.parseInt(choice) == 2) {
								// 추천 음식점 삭제하기
								System.out.println("■정말 삭제하시겠습니까?(Y / N)");
								String checkDelete = sc.next();
								if (checkDelete.equalsIgnoreCase("Y")) {
									if (urdao.delete(Integer.parseInt(register_num))) {
										System.out.println("◎삭제가 완료되었습니다.");
										break;
									} else {
										System.out.println("※삭제에 실패하였습니다.");
										break;
									}
								} else if (checkDelete.equalsIgnoreCase("N")) {
									System.out.println("※삭제가 취소되었습니다.");
									break;
								} else {
									System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
								}
							}
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						}
					}
				} else {
					System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
				}
			}

		}
	}
}