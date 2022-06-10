package view;

import java.util.Scanner;

import dao.RegEx;
import dao.UserDAO;

public class UpdateUserView {
	public UpdateUserView() {
		while (true) {
			System.out.println("■변경하려는 정보를 입력하세요.");
			System.out
					.println("1. 비밀번호 / 2. 닉네임 / 3. 휴대폰번호 / 4. 성별(M/F) / 5. 이메일 / 6. 주소 / 7. 좋아하는 음식 카테고리 / 8. 뒤로 가기");
			String[] cols = { "", "비밀번호", "닉네임", "휴대폰번호", "성별", "이메일", "주소", "좋아하는 음식 카테고리" };
			Scanner sc = new Scanner(System.in);
			UserDAO udao = new UserDAO();
			int choice = sc.nextInt();
			if (choice == 8) {
				break;
			} else if (choice == 1) {
				System.out.print("■현재 비밀번호\t: ");
				String oldPW = sc.next();
				if (udao.checkPW(oldPW)) {
					System.out.print("■새로운 비밀번호\t: ");
					String newPW = sc.next();
					if (newPW.length() > 7) {
						System.out.println("■새로운 비밀번호 확인\t: ");
						String checkNewPW = sc.next();
						if (newPW.equals(checkNewPW)) {
							if (udao.update(choice, newPW)) {
								System.out.println("◎비밀 번호가 변경되었습니다.");
								break;
							} else {
								System.out.println("※비밀 번호 변경이 실패하였습니다. 확인 후 다시 시도해주세요!");
							}
						} else {
							System.out.println("※새로운 비밀번호가 일치하지 않습니다. 확인 후 다시 시도해주세요!");
						}
					} else {
						System.out.println("※새로운 비밀번호는 8자리 이상 설정해주세요. 확인 후 다시 시도해주세요!");
					}
				} else {
					System.out.println("※현재 비밀번호가 일치하지 않습니다. 확인 후 다시 시도해주세요!");
				}
			} else if (!(1<=choice&&choice<=8)) {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			} else if (choice == 7) {
				System.out.println("■좋아하는 카테고리의 숫자를 띄어쓰기 없이 입력해주세요.");
				System.out.println("예)한식, 일식, 패스트푸드 -> 135");
				System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
				System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
				sc = new Scanner(System.in);
				String choiceCate = sc.next();
				boolean checkCate = true;
				String newData = "";
				String[] cate = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
				if (RegEx.validateNumber(choiceCate)) {
					for (int i = 0; i < choiceCate.length(); i++) {
						if (49 > choiceCate.codePointAt(i) || choiceCate.codePointAt(i) > 54) {
							checkCate = false;
							break;
						}
					}
					if (checkCate) {
						for (int i = 0; i < choiceCate.length(); i++) {
							if (choiceCate.length() - 1 == i) {
								newData += cate[(choiceCate.codePointAt(i)) - 48];
							} else {
								newData += cate[(choiceCate.codePointAt(i)) - 48] + ",";
							}
						}
						if (udao.update(choice, newData)) {
							System.out.println("◎좋아하는 음식 카테고리 변경이 완료되었습니다.");
							break;
						} else {
							System.out.println("※좋아하는 음식 카테고리 변경이 실패하였습니다.");
							break;
						}
					} else {
						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
					}
				} else {
					System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
				}

			} else {
				System.out.print("■새로운 " + cols[choice] + "을(를) 입력하세요 : ");
				sc = new Scanner(System.in);
				String newData = sc.nextLine();
				switch (choice) {
				case 2:
					// 닉네임 변경
					if (udao.checkData(3, newData)) {
						System.out.println("◎사용 가능한 닉네임입니다.");
					} else {
						System.out.println("※이미 사용 중인 닉네임입니다.");
						continue;
					}
					break;
				case 3:
					if (RegEx.validatePhone(newData)) {
						newData = RegEx.phoneOnlyNumber(newData);
					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 4:
					if (!(newData.equalsIgnoreCase("M") || newData.equalsIgnoreCase("F"))) {
						System.out.println("※남성은 M, 여성은 F를 입력해주세요.");
						continue;
					}
					break;
				case 5:
					// asdf1234@naver.com
					if (!RegEx.validateEmail(newData)) {
						System.out.println("※올바른 이메일 형식이 아닙니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 6:
					break;
				default:
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
				System.out.print("■정말 변경하시겠습니까?(Y/N)");
				sc = new Scanner(System.in);
				String check = sc.next();
				if (check.equalsIgnoreCase("Y")) {
					System.out.print("■비밀 번호 : ");
					String user_pw = sc.next();
					if (udao.checkPW(user_pw)) {
						if (udao.update(choice, newData)) {
							System.out.println("◎회원 정보 변경이 완료되었습니다.");
							break;
						} else {
							System.out.println("※회원 정보 변경에 실패하였습니다.");
							break;
						}
					} else {
						System.out.println("※비밀 번호가 올바르지 않습니다. 다시 확인해주세요.");
					}
				} else if (check.equalsIgnoreCase("N")) {
					System.out.println("💤회원 정보 변경을 종료합니다.");
					break;
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}
		}

	}
}
