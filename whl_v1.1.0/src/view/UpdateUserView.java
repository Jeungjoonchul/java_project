package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import dao.Check;
import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class UpdateUserView {
	public UpdateUserView() {
		while (true) {
			UserDTO user = (UserDTO) Session.getData("loginUser");
			System.out.println("■변경하려는 정보를 입력하세요.");
			System.out
					.println("1. 비밀번호 / 2. 닉네임 / 3. 휴대폰번호 / 4. 성별(M/F) / 5. 이메일 / 6. 주소 / 7. 좋아하는 음식 카테고리 / 8. 뒤로 가기");
			String[] cols = { "", "비밀번호", "닉네임", "휴대폰번호", "성별", "이메일", "주소", "좋아하는 음식 카테고리" };
			Scanner sc = new Scanner(System.in);
			UserDAO udao = new UserDAO();
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 8)) {
				if (Integer.parseInt(choice) == 8) {
					break;
				} else if (Integer.parseInt(choice) == 1) {
					System.out.print("■현재 비밀번호\t: ");
					String oldPW = sc.next();
					if (udao.checkPW(oldPW)) {
						System.out.print("■새로운 비밀번호\t: ");
						String newPW = sc.next();
						if (Check.validatePW(newPW)) {
							System.out.println("■새로운 비밀번호 확인\t: ");
							String checkNewPW = sc.next();
							if (newPW.equals(checkNewPW)) {
								if (udao.update(Integer.parseInt(choice), newPW)) {
									System.out.println("◎비밀 번호가 변경되었습니다.");
									break;
								} else {
									System.out.println("※비밀 번호 변경이 실패하였습니다. 확인 후 다시 시도해주세요!");
								}
							} else {
								System.out.println("※새로운 비밀번호가 일치하지 않습니다. 확인 후 다시 시도해주세요!");
							}
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 영문자와 숫자만 사용하여 8자리~20자리로 입력해주세요!");
						}
					} else {
						System.out.println("※현재 비밀번호가 일치하지 않습니다. 확인 후 다시 시도해주세요!");
					}

				} else if (Integer.parseInt(choice) == 7) {
					System.out.println("■좋아하는 카테고리의 숫자를 띄어쓰기 없이 입력해주세요.");
					System.out.println("예)한식, 일식, 패스트푸드 -> 135");
					System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
					System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
					sc = new Scanner(System.in);
					String choiceCate = sc.nextLine();
					String newData = "";
					String[] cate = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
					if (Check.validateNumber_choiceMulti(choiceCate, 1, 6)) {
						String[] sort_choiceCate = choiceCate.split("");
						Arrays.sort(sort_choiceCate);
						for (int i = 0; i < sort_choiceCate.length; i++) {
							if (choiceCate.length() - 1 == i) {
								newData += cate[Integer.parseInt(sort_choiceCate[i])];
							} else {
								newData += cate[Integer.parseInt(sort_choiceCate[i])] + ",";
							}
						}
						if (udao.update(Integer.parseInt(choice), newData)) {
							System.out.println("◎좋아하는 음식 카테고리 변경이 완료되었습니다.");
							break;
						} else {
							System.out.println("※좋아하는 음식 카테고리 변경이 실패하였습니다.");
							break;
						}
					} else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
					}

				} else {
					System.out.print("■새로운 " + cols[Integer.parseInt(choice)] + "을(를) 입력하세요 : ");
					sc = new Scanner(System.in);
					String newData = sc.nextLine();
					switch (Integer.parseInt(choice)) {
					case 2:
						// 닉네임 변경
						if (Check.validateNickname(newData)) {
							if (udao.checkData(3, newData)) {
								System.out.println("◎사용 가능한 닉네임입니다.");
								System.out.println(user.user_nickname + " -> " + newData);
							} else {
								System.out.println("※이미 사용 중인 닉네임입니다.");
								continue;
							}
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 영문자와 숫자, 한글만 사용하여 2자리~10자리로 입력해주세요!");
							continue;
						}

						break;
					case 3:
						if (Check.validatePhone(newData)) {
							newData = Check.phoneOnlyNumber(newData);
							System.out.println(Check.regPhone(user.user_phone) + " -> " + Check.regPhone(newData));
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
							continue;
						}
						break;
					case 4:
						if (newData.equalsIgnoreCase("M") || newData.equalsIgnoreCase("F")) {
							if(user.user_gender==null) {
								System.out.println("성별 설정 전 -> " + newData);
							}else {
							System.out.println(user.user_gender + " -> " + newData);
							}
						} else {
							System.out.println("※남성은 M, 여성은 F를 입력해주세요.");
							continue;
						}
						break;
					case 5:
						// asdf1234@naver.com
						if (Check.validateEmail(newData)) {
							if(user.user_email==null) {
								System.out.println("이메일 설정 전 -> " + newData);
							}else {
							System.out.println(user.user_email + " -> " + newData);
							}
						} else {
							System.out.println("※올바른 이메일 형식이 아닙니다. 확인 후 다시 시도해주세요!");
							continue;
						}
						break;
					case 6:
						if (Check.validateAddress(newData)) {
							if(user.user_address==null) {
								System.out.println("주소 설정 전 -> " + newData);
							}else {
							System.out.println(user.user_email + " -> " + newData);
							}
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
							continue;
						}
						break;
					}
					
					System.out.print("■정말 변경하시겠습니까?(Y/N)");
					sc = new Scanner(System.in);
					String check = sc.next();
					if (check.equalsIgnoreCase("Y")) {
						System.out.print("■비밀 번호 : ");
						String user_pw = sc.next();
						if (udao.checkPW(user_pw)) {
							if (udao.update(Integer.parseInt(choice), newData)) {
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
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
			}

		}

	}
}
