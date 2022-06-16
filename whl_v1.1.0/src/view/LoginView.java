package view;

import java.util.Scanner;

import dao.Check;
import dao.SMS;
import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class LoginView {
	public LoginView() {
		
		UserDAO udao = new UserDAO();
		SMS sms = new SMS();
		System.out.println("");
		System.out.println("=========");
		System.out.println("🍜로그인🍣");
		System.out.println("=========");
		System.out.println("");
		while (true) {
			System.out.println("■메뉴를 선택하세요.");
			System.out.println("1. 로그인 / 2. 아이디 찾기 / 3. 비밀 번호 찾기 / 4. 나가기");
			Scanner sc = new Scanner(System.in);
			String choice = sc.next();
			if (Check.validateNumber_choiceOne(choice, 1, 4)) {
				if (Integer.parseInt(choice) == 4) {
					System.out.println("💤현재 페이지를 종료합니다.");
					break;
				}
				switch (Integer.parseInt(choice)) {
				case 1:
					System.out.print("■아이디 : ");
					String user_id = sc.next();
					System.out.print("■비밀번호 : ");
					String user_pw = sc.next();
					if (udao.login(user_id, user_pw)) {
						String user_nickname = ((UserDTO) Session.getData("loginUser")).user_nickname;
						if (user_nickname.equals("관리자")) {
							System.out.println("◎관리자 모드 입니다.");
							new AdminMainView();
						} else {
							System.out.println("");
							System.out.println("◎" + user_nickname + "님 반가워요!! 맛점하세요(●'◡'●)");
							new MainView();
						}
					} else {
						System.out.println("※로그인 실패 ㅠㅠ(아이디 혹은 비밀번호를 확인 후 다시 시도해주세요ㅠㅠ)");
					}
					break;
				case 2:
					System.out.print("■이름을 입력하세요 : ");
					String user_name = sc.next();
					System.out.print("■휴대폰번호를 입력하세요 : ");
					String user_phone = sc.next();
					if(!Check.validatePhone(user_phone)) {
						System.out.println("※휴대폰 번호 입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						break;
					}
					user_phone = Check.phoneOnlyNumber(user_phone);
					UserDTO findUser = udao.findID(user_name, user_phone);
					if (findUser != null) {
						String code = sms.sendCode(user_phone);
						if (code != null) {
							while (true) {
								System.out.print("■휴대전화 문자로 수신 받으신 코드 6자리를 입력하세요(나가기는 '!') : ");
								String checkCode = sc.next();
								if (checkCode.equals("!")) {
									break;
								} else if (checkCode.equals(code)) {
									System.out.println("◎회원님의 아이디는 '" + findUser.user_id + "' 입니다.");
									break;
								} else {
									System.out.println("※입력하신 코드가 올바르지 않습니다. 다시 입력해주세요!");
								}
							}
						} else {
							System.out.println("※코드 발송에 실패했습니다. 다시 시도해주세요!");
						}
					} else {
						System.out.println("※입력하신 정보로 아이디를 찾을 수 없습니다. 확인 후 다시 시도해주세요!");
					}
					break;
				case 3:
					System.out.print("■아이디를 입력하세요 : ");
					user_id = sc.next();
					System.out.print("■휴대폰번호를 입력하세요 : ");
					user_phone = sc.next();
					if(!Check.validatePhone(user_phone)) {
						System.out.println("※휴대폰 번호 입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						break;
					}
					user_phone = Check.phoneOnlyNumber(user_phone);
					findUser = udao.findPW(user_id, user_phone);
					if (findUser != null) {
						String code = sms.sendCode(user_phone);
						if (code != null) {
							while (true) {
								System.out.print("■휴대전화 문자로 수신 받으신 코드 6자리를 입력하세요(나가기는 '!') : ");
								String checkCode = sc.next();
								if (checkCode.equals("!")) {
									break;
								} else if (checkCode.equals(code)) {
									System.out.println("◎비밀번호는 '" + findUser.user_pw + "' 입니다.");
									break;
								} else {
									System.out.println("※입력하신 코드가 올바르지 않습니다. 다시 입력해주세요!");
								}
							}
						} else {
							System.out.println("※코드 발송에 실패했습니다. 다시 시도해주세요!");
						}
					} else {
						System.out.println("※입력하신 정보로 비밀번호를 찾을 수 없습니다. 확인 후 다시 시도해주세요!");
					}
					break;
				}
			} else {
				System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				continue;
			}
		}
	}
}
