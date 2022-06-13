package dto;

import dao.Check;

public class RestaurantDTO {
	public int restaurant_id;
	public String restaurant_name;
	public String category_name;
	public String restaurant_address;
	public String restaurant_phone;
	public int restaurant_capacity; // 예약인원
	public String restaurant_close; // 휴무일
	public String restaurant_description; // 음식점 정보
	public int restaurant_like_cnt;// DB에서 0으로 기본값 설정 되어있어 java에서 0으로 초기화 불필요
	public int avg_score;// 추가
	public int reply_cnt;// 추가

	public RestaurantDTO() {

	}

	public RestaurantDTO(String[] datas) {

		this.restaurant_name = datas[0];
		this.category_name = datas[1];
		this.restaurant_address = datas[2];
		this.restaurant_phone = datas[3];
		this.restaurant_capacity = 0;
		this.restaurant_capacity = Integer.parseInt(datas[4]);
		this.restaurant_close = datas[5];
		this.restaurant_description = datas[6];
	}

	@Override
	public String toString() {
		String restInfo = "";

		restInfo += String.format("%s (%s)\n", restaurant_name, category_name);
		restaurant_phone = Check.regPhone(restaurant_phone);
		restInfo += String.format("%s / %s\n", restaurant_address, restaurant_phone);

		restInfo += String.format("예약 가능 인원 : %d\n", restaurant_capacity);
		if (restaurant_close == null) {//수정 restaurant_close ="" 삭제
			restaurant_close = "휴무 없음";
		}
		restInfo += String.format("휴무일 : %s\n", restaurant_close);
		if (restaurant_description.equals("") || restaurant_description == null) {
			restaurant_description = "설명 없음";
		}
		restInfo += String.format("%s\n", restaurant_description);
		restInfo += String.format("♡%d / ☆%d(%d)\n", restaurant_like_cnt, avg_score, reply_cnt);
		return restInfo;
	}
}
