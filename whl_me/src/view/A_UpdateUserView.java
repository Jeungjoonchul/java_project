package view;

import java.util.Scanner;

import dao.Check;
import dao.Session;
import dao.UserDAO;
import dto.UserDTO;

public class A_UpdateUserView {

	public A_UpdateUserView() {
		while (true) {
			UserDAO udao = new UserDAO();
			System.out.println("■변경할 유저 정보를 선택하세요.");
			System.out.println("1. 이름 / 2. 닉네임 / 3.휴대폰번호 / 4. 성별 / 5. 이메일주소 / 6. 나가기 ");
			Scanner sc = new Scanner(System.in);
			int choice = sc.nextInt();
			if (choice == 6) {
				System.out.println("💤유저 정보 변경을 종료합니다.");
				break;
			} else {
				System.out.print("■새로운 값 입력 : ");
				String inputData = sc.next();
				switch (choice) {
				case 1:
					// 이름

					break;
				case 2:
					// 닉네임

					if (!udao.checkData(3, inputData)) {
						System.out.println("※중복되는 닉네임이 있습니다. 확인 후 다시 확인해주세요.");
						continue;
					}
					break;
				case 3:
					// 휴대폰번호

					if (!Check.validatePhone(inputData)) {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요.");
						continue;
					}else {
						//수정
						inputData=Check.phoneOnlyNumber(inputData);
					}
					break;
				case 4:
					// 성별
					if (!(inputData.equalsIgnoreCase("M") || inputData.equalsIgnoreCase("f"))) {
						System.out.println("※성별은 'M' 또는 'F'로만 입력해주세요.");
						continue;
					}
					break;
				case 5:
					// 이메일주소
					if (!Check.validateEmail(inputData)) {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요.");
						continue;
					}
					break;
				default:
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
					continue;
				}
				if(choice==3) {
					System.out.println("◎입력한 값 : " + Check.regPhone(inputData));
				}else {
					System.out.println("◎입력한 값 : " + inputData);
				}
				
				System.out.println("■정말 수정 하시겠습니까?(Y/N)");
				String checkUpdate = sc.next();
				if (checkUpdate.equalsIgnoreCase("Y")) {
					if (udao.update(choice, inputData)) {
						System.out.println("◎유저 정보 수정에 성공했습니다.");
						UserDTO updatedUser = new UserDTO();
						String user_id = ((UserDTO) Session.getData("selectedUser")).user_id;
						updatedUser = udao.select(user_id);
						System.out.println(updatedUser);
					} else {
						System.out.println("※유저 정보 수정에 실패했습니다.");
					}
				} else if (checkUpdate.equalsIgnoreCase("N")) {
					System.out.println("💤유저 정보 수정을 종료합니다.");
					break;
				} else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}
		}
	}
}
