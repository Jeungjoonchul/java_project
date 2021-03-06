package dto;

import dao.Check;

public class UserRegisterDTO {
	public int register_num;
	public String restaurant_name;
	public String restaurant_address;
	public String category_name;
	public String restaurant_phone;
	public String reg_description;
	public String is_register;
	public String admin_comment;
	public String user_id;

	public UserRegisterDTO() {

	}

	public UserRegisterDTO(String restaurant_name, String restaurant_address, String category_name,
			String restaurant_phone, String reg_description, String user_id) {
		this.restaurant_name = restaurant_name;
		this.restaurant_address = restaurant_address;
		this.category_name = category_name;
		this.restaurant_phone = restaurant_phone;
		this.reg_description = reg_description;
		this.user_id = user_id;
	}

	public UserRegisterDTO(String[] datas) {
		this.restaurant_name = datas[0];
		this.restaurant_address = datas[1];
		this.category_name = datas[2];
		this.restaurant_phone = datas[3];
		this.reg_description = datas[4];
		this.user_id = datas[5];
	}

	@Override
	public String toString() {
		String infoUR = "";

		infoUR += String.format("┃ 음식점 이름 : %s(%s)\n", restaurant_name, category_name);
		infoUR += String.format("┃ 주소 : %s / 전화번호 : %s\n", restaurant_address, Check.regPhone(restaurant_phone));
		infoUR += String.format("┃ 추천 사유 : %s\n", reg_description);
		if (admin_comment == null) {
			infoUR += String.format("┃ 의견 : %s", "관리자 확인 후 작성 예정");
		} else {
			infoUR += String.format("┃ 의견 : %s", admin_comment);
		}
		return infoUR;
	}
}
