package dto;

import dao.Check;

public class UserDTO {
	public String user_id;
	public String user_pw;
	public String user_name;
	public String user_nickname;
	public String user_phone;
	public String user_gender;
	public String user_email;
	public String user_address;
	public String category_name;

	public UserDTO() {
	}

	public UserDTO(String[] datas) {
		this.user_id = datas[0];
		this.user_pw = datas[1];
		this.user_name = datas[2];
		this.user_nickname = datas[3];
		this.user_phone = datas[4];
	}

	@Override
	public String toString() {
		String result = "";
		result += String.format("┃ %s님의 정보\n", user_nickname);
		result += String.format("┃ 아이디\t: %s\n", user_id);
		result += String.format("┃ 이름\t: %s\n", user_name);
		
		user_phone=Check.regPhone(user_phone);
		result += String.format("┃ 전화번호\t: %s\n", user_phone);
		
		if(user_gender==null||user_gender.equals("")) {
			user_gender ="성별을 설정해주세요.";
		}
		result += String.format("┃ 성별\t: %s\n", user_gender.toUpperCase());
		
		if(user_email==null||user_email.equals("")) {
			user_email ="이메일을 설정해주세요.";
		}
		result += String.format("┃ 이메일\t: %s\n", user_email);
		
		if(user_address==null||user_address.equals("")) {
			user_address ="주소를 설정해주세요.";
		}
		result += String.format("┃ 주소\t: %s\n", user_address);
		
		if(category_name==null||category_name.equals("")) {
			category_name ="좋아하는 음식 카테고리가 없습니다.";
		}
		result += String.format("┃ 좋아하는 음식 카테고리 : %s", category_name);
		return result;
	}	
}
