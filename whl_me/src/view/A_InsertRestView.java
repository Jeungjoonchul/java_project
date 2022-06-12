package view;

import java.util.Scanner;

import dao.RegEx;
import dao.RestaurantDAO;
import dto.RestaurantDTO;

public class A_InsertRestView {

	public A_InsertRestView() {
		//View Title
		RestaurantDAO rdao = new RestaurantDAO();
		System.out.println("===============");
		System.out.println("🍜음식점 추가하기🍣");
		System.out.println("===============");
		//음식점 추가를 위해 입력 받아야할 내용
		//마지막은 등록 여부 확인 용으로 빈값으로 설정(for문에서 i==7일때 조건문 진입)
		String[] inputInfo= {"음식점 이름(","음식점 카테고리","음식점 주소(도로명주소 /","음식점 전화번호(","예약 가능 인원(","휴무일","음식점 설명(",""};
		//사용자에게 입력 받은값 유효성 검사 후 데이터를 담을 배열 선언
		//데이터는 음식점 이름, 카테고리, 주소, 전화번호, 예약 가능 인원, 휴무일, 설명으로 7개
		String[] datas=new String[7];
		//입력을 위한 스캐너 생성
		Scanner sc = new Scanner(System.in);
		//받아야할 내용을 순서대로 사용자에게 제공 및 마지막에는 등록 여부 확인 후 음식점 등록
		//7개의 값을 입력받고 마지막에 등록 여부 확인 위해 조건문 진입
		for (int i = 0; i < inputInfo.length;) {
			if(i==7) {
				RestaurantDTO newRest = new RestaurantDTO(datas);
				System.out.println("┏입력한 정보\t━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println(newRest);
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.print("■정말 등록하시겠습니까?(Y/N) : ");
				String checkInsert = sc.next();
				if(checkInsert.equalsIgnoreCase("Y")) {
					if(rdao.insert(newRest)) {
						System.out.println("◎음식점 등록이 완료되었습니다.");
						break;
					}else {
						System.out.println("※음식점 등록에 실패하였습니다.");
						break;
					}
				}else if(checkInsert.equalsIgnoreCase("N")) {
					System.out.println("💤음식점 추가하기를 종료합니다.");
					break;
				}else {
					System.out.println("※잘못 입력하였습니다. 확인 후 다시 시도해주세요!");
				}
			}else {
				//카테고리 설정은 번호 입력 시 해당 번호에 맞는 값을 datas배열에 저장
				if(i==1) {
					System.out.println("■카테고리를 선택해주세요.");
					System.out.println("1. 한식🍲\t2. 중식🍜\t3. 일식🍣\t4. 양식🍕");
					System.out.println("5. 패스트푸드🌭\t6. 카페/디저트☕");
				}else if(i==5) {
					System.out.println("■휴무일을 선택해주세요.(ex : 토,일 -> '67'입력)");
					System.out.println("1. 월 / 2. 화 / 3. 수 / 4. 목 / 5. 금 / 6. 토 / 7. 일 / 8. 휴무없음 ");
				}
				else {
					//입력 받아야할 내용 순서대로 출력
					System.out.print("■"+inputInfo[i]+" 나가기는 '!') : ");
				}
				//스캐너로 사용자에게 입력 받기
				sc = new Scanner(System.in);
				String inputData = sc.nextLine();
				if(inputData.equals("!")) {
					System.out.println("💤음식점 추가하기를 종료합니다.");
					break;
				}
				// 카테고리, 전화번호, 휴무일의 유효성 검사
				switch(i) {
				case 1:
					//1~6을 제외한 숫자 입력 시 
					if(!(RegEx.validateNumber(inputData)&&(1<=Integer.parseInt(inputData)&&Integer.parseInt(inputData)<=6))) {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}else {
						String[] category = {"","한식","중식","일식","양식","패스트푸드","카페/디저트"};
						inputData = category[Integer.parseInt(inputData)];
					}
					break;
				case 3:
					if(!RegEx.validatePhone(inputData)) {
						System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
						continue;
					}
					inputData = RegEx.regPhone(inputData);
					break;
				case 5:
					boolean checkData = true;
					for (int j = 0; j < inputData.length(); j++) {
						if(!(49<=inputData.codePointAt(j)&&inputData.codePointAt(j)<=56)) {
							checkData = false;
							break;
						}
						for (int j2 = j+1; j2 < inputData.length(); j2++) {
							if(inputData.codePointAt(j)==inputData.codePointAt(j2)) {
								checkData=false;
								break;
							}
						}
					}
					if(inputData.equalsIgnoreCase("8")) {
						inputData="휴무 없음";
					}else {
						//1 3 5 7
						
						if(checkData) {
							String[] dow = {"","월","화","수","목","금","토","일"};
							String result = inputData;
							for (int j = 0; j < result.split("").length; j++) {
								if(j==result.split("").length-1) {
									inputData+=dow[Integer.parseInt(result.split("")[j])];
									
								}
								inputData+=dow[Integer.parseInt(result.split("")[j])]+",";
							}
						}else {
							System.out.println("※입력 형식이 올바르지 않습니다. 확인 후 다시 시도해주세요!");
							continue;
						}
					}
					
					break;	
				}
				while(true) {
					System.out.print("■입력하시려는 내용이 \""+inputData+"\"이(가) 맞나요?(Y/N) : ");
					String checkInput = sc.next();
					if(checkInput.equalsIgnoreCase("Y")) {
						if(i==3) {
							inputData = RegEx.phoneOnlyNumber(inputData);
						}
						else if(i==5) {
							inputData = "";
						}
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
