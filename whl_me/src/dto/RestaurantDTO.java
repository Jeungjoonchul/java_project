package dto;

import dao.RegEx;

public class RestaurantDTO {
	public int restaurant_id;
    public String restaurant_name;
    public String category_name;
    public String restaurant_address;
    public String restaurant_phone;
    public int restaurant_capacity;
    public String restaurant_close;
    public String restaurant_description;
    public int restaurant_like_cnt;//DB에서 0으로 기본값 설정 되어있어 java에서 0으로 초기화 불필요
    public int avg_score;// 추가
    public int reply_cnt;// 추가
	
    
    public RestaurantDTO() {

	}


	public RestaurantDTO(String[] datas) {

	}


	@Override
	public String toString() {
		String restInfo = "";
		
		restInfo+=String.format("%s (%s)\n",restaurant_name,category_name);
		restaurant_phone = RegEx.regPhone(restaurant_phone);
		restInfo+=String.format("%s / %s\n", restaurant_address,restaurant_phone);
		restInfo+=String.format("예약 가능 인원 : %d\n",restaurant_capacity);
		if(restaurant_close.equals("")||restaurant_close==null) {
			restaurant_close = "휴무 없음";
		}
		restInfo+=String.format("휴무일 : %s\n",restaurant_close);
		restInfo+=String.format("%s\n",restaurant_description);
		restInfo+=String.format("♡%d / ☆%d(%d)\n",restaurant_like_cnt,avg_score,reply_cnt);
		return restInfo;
	}
}
