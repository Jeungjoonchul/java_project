package view;

import java.util.Scanner;

import dao.RegEx;

public class A_InsertRestView {

	public A_InsertRestView() {
		//View Title
		System.out.println("===============");
		System.out.println("🍜음식점 추가하기🍣");
		System.out.println("===============");
		//음식점 추가를 위해 입력 받아야할 내용
		//마지막은 등록 여부 확인 용으로 빈값으로 설정(for문에서 i==7일때 조건문 진입)
		String[] inputInfo= {"음식점 이름(","음식점 카테고리(","음식점 주소(도로명주소 /","음식점 전화번호(","예약 가능 인원(","휴무일(띄어쓰기 없이 쉼표(',')로 구분 / 휴무 없는 경우 '-' /","음식점 설명(",""};
		//사용자에게 입력 받은값 유효성 검사 후 데이터를 담을 배열 선언
		//데이터는 음식점 이름, 카테고리, 주소, 전화번호, 예약 가능 인원, 휴무일, 설명으로 7개
		String[] datas=new String[7];
		//입력을 위한 스캐너 생성
		Scanner sc = new Scanner(System.in);
		//받아야할 내용을 순서대로 사용자에게 제공 및 마지막에는 등록 여부 확인 후 음식점 등록
		//7개의 값을 입력받고 마지막에 등록 여부 확인 위해 조건문 진입
		for (int i = 0; i < inputInfo.length;) {
			if(i==7) {
				
			}else {
				//카테고리 설정은 번호 입력 시 해당 번호에 맞는 값을 datas배열에 저장
				if(i==1) {
					System.out.println("■카테고리를 선택해주세요.");
					System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
					System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
				}else {
					//입력 받아야할 내용 순서대로 출력
					System.out.print("■"+inputInfo[i]+" 나가기는 '!') :");
				}
				//스캐너로 사용자에게 입력 받기
				sc = new Scanner(System.in);
				String inputData = sc.nextLine();
				// 카테고리, 전화번호, 휴무일의 유효성 검사
				switch(i) {
				case 1:
					//1~6을 제외한 숫자 입력 시 
					if(!(RegEx.validateNumber(inputData)&&(1<=Integer.parseInt(inputData)&&Integer.parseInt(inputData)<=6))) {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 3:
					if(!RegEx.validatePhone(inputData)) {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					break;
				case 5:
					//
					if(inputData.equalsIgnoreCase("-")) {
						inputData = "";
					}else {
						String[] week_of_day = {"월","화","수","목","금","토","일"};
						for (int j = 0; j < inputData.split(",").length; j++) {
							for (int j2 = 0; j2 < week_of_day.length; j2++) {
								if(inputData.split(",")[j].equals(week_of_day[j2])) {								
								}else {
									System.out.println("※월~일 중 휴무일을 콤마(',')로 구분하여 띄어쓰기 없이 입력해주세요!");
									continue;
								}
							}
						}
					}
					break;	
				}
				while(true) {
					System.out.print("■입력하시려는 내용이 \""+inputData+"\"가 맞나요? : (Y/N)");
					String checkInput = sc.next();
					if(checkInput.equalsIgnoreCase("Y")) {
						datas[i] = inputData;
						i++;
						break;
					}else if(checkInput.equalsIgnoreCase("N")) {
						break;
					}else {
						System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
					}
				}
				
			}
		}
	}

}
