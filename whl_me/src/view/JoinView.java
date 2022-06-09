package view;

import java.util.Scanner;

import dao.RegEx;
import dao.UserDAO;
import dto.UserDTO;

public class JoinView {
	public JoinView() {
		//y/n 추가 필요
		
		UserDAO udao = new UserDAO(); 
		String[] datas = new String[5]; //입력 받은 값(사용자 입력한 값)
		String[] inputInfo = { "아이디", "비밀번호", "이름", "닉네임", "휴대폰번호" }; //입력 받을 값(사용자에게 입력 유도)
		System.out.println("==========");
		System.out.println("🍜회원가입🍣");
		System.out.println("==========");
		//입력 받은 값의 유효성 검사 통과 시 i값 1증가하는 반복문
		for (int i = 0; i < inputInfo.length;) {
			System.out.print("■"+inputInfo[i] + "(종료는 '!'을 입력하세요) : ");
			Scanner sc = new Scanner(System.in);
			String inputData = sc.nextLine();
			
			if (inputData.equals("!")) {
				System.out.println("🍜회원 가입을 종료합니다🍣");
				break;
			} //탈출구
			
			else {
				//사용자 입력 처리
				switch (i) {
				case 0:
					//아이디
					if (udao.checkData(i, inputData)) {
						System.out.println("◎사용 가능한 아이디입니다.");
						datas[i] = inputData;
						i++;
					} else {
						System.out.println("※이미 아이디가 존재합니다. 확인 후 다시 시도해주세요!");
					}
					break;
				case 1:
					//비밀번호
					if (inputData.length() < 8) {
						System.out.println("※비밀 번호는 8자리 이상 설정해주세요!");
					} else {
						System.out.print("비밀번호 확인 : ");
						String checkPW = sc.next();
						if (checkPW.equals(inputData)) {
							System.out.println("◎비밀번호 확인 성공");
							datas[i] = inputData;
							i++;
						} else {
							System.out.println("※비밀번호가 일치하지 않습니다. 확인 후 다시 시도해주세요!");
						}
					}
					break;
				case 2:
					//이름
					datas[i] = inputData;
					i++;
					break;
				case 3:
					//닉네임
					if (udao.checkData(i, datas[i])) {
						System.out.println("◎사용 가능한 닉네임입니다.");
						datas[i] = inputData;
						i++;
					} else {
						System.out.println("※이미 닉네임이 존재합니다. 확인 후 다시 시도해주세요!");
					}
					break;
				case 4:
					//휴대폰 번호
					if(RegEx.validatePhone(inputData)) {//번호 유효성 검사(문자 입력 불가)
						datas[i] = RegEx.phoneOnlyNumber(inputData);//특수문자("-", ".", ...) 제외한 숫자값만 반환
						i++;
					}else {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
					}
					break;
				}
				if(i==5) {
					UserDTO newUser = new UserDTO(datas);
					if (udao.join(newUser)) {
						System.out.println("◎회원 가입이 완료되었습니다.");
					} else {
						System.out.println("※회원 가입이 실패했습니다. 확인 후 다시 시도해주세요!");
					}
				}
			}
		}
	}
}
