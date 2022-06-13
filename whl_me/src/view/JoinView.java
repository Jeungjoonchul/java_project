package view;

import java.util.Scanner;

import dao.Check;
import dao.UserDAO;
import dto.UserDTO;

public class JoinView {
	public JoinView() {
		String[] datas = new String[5]; // 입력 받은 값(사용자 입력한 값)
		String[] inputInfo = { "아이디", "비밀번호", "이름", "닉네임", "휴대폰번호",""}; // 입력 받을 값(사용자에게 입력 유도)
		System.out.println("==========");
		System.out.println("🍜회원가입🍣");
		System.out.println("==========");
		// 입력 받은 값의 유효성 검사 통과 시 i값 1증가하는 반복문
		for (int i = 0; i < inputInfo.length;) {
			UserDAO udao = new UserDAO();
			Scanner sc = new Scanner(System.in);
			if (i == 5) {
				UserDTO newUser = new UserDTO(datas);
				System.out.println("┏입력한 정보	━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(newUser);
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.print("입력한 정보가 맞습니까?(Y/N) : ");
				String checkJoin = sc.next();
				if (checkJoin.equalsIgnoreCase("Y")) {
					if (udao.join(newUser)) {
						System.out.println("◎회원 가입이 완료되었습니다.");
						i++;
					} else {
						System.out.println("※회원 가입이 실패했습니다. 확인 후 다시 시도해주세요!");
						i++;
					}
				} else if (checkJoin.equalsIgnoreCase("N")) {
					i = 0;
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}

			} else {
				System.out.print("■" + inputInfo[i] + "(종료는 '!'을 입력하세요) : ");
				String inputData = sc.nextLine();
				if (inputData.equals("!")) {
					System.out.println("🍜회원 가입을 종료합니다🍣");
					break;
				} // 탈출구

				else {
					// 사용자 입력 처리
					switch (i) {
					case 0:
						// 아이디
						if (Check.validateID(inputData)) {
							if (udao.checkData(i, inputData)) {
								System.out.println("◎사용 가능한 아이디입니다.");
								datas[i] = inputData;
								i++;
							} else {
								System.out.println("※이미 아이디가 존재합니다. 확인 후 다시 시도해주세요!");
							}
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 영문자와 숫자만 사용하여 4자리~20자리로 입력해주세요!");
						}
						break;
					case 1:
						// 비밀번호
						if (Check.validatePW(inputData)) {
							System.out.print("■비밀번호 확인 : ");
							String checkPW = sc.next();
							if (checkPW.equals(inputData)) {
								System.out.println("◎비밀번호 확인 성공");
								datas[i] = inputData;
								i++;
							} else {
								System.out.println("※비밀번호가 일치하지 않습니다. 확인 후 다시 시도해주세요!");
							}
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 영문자와 숫자만 사용하여 8자리~20자리로 입력해주세요!");
						}
						break;
					case 2:
						// 이름
						if (Check.validateName(inputData)) {
							datas[i] = inputData;
							i++;
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						}
						break;
					case 3:
						// 닉네임
						if (Check.validateNickname(inputData)) {
							if (udao.checkData(i, inputData)) {
								System.out.println("◎사용 가능한 닉네임입니다.");
								datas[i] = inputData;
								i++;
							} else {
								System.out.println("※이미 닉네임이 존재합니다. 확인 후 다시 시도해주세요!");
							}
						} else {
							System.out.println("※입력 형식이 올바르지 않습니다. 영문자와 숫자, 한글만 사용하여 2자리~10자리로 입력해주세요!");
						}

						break;
					case 4:
						// 휴대폰 번호
						if (Check.validatePhone(inputData)) {// 번호 유효성 검사(문자 입력 불가)
							datas[i] = Check.phoneOnlyNumber(inputData);// 특수문자("-", ".", ...) 제외한 숫자값만 반환
							i++;
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
