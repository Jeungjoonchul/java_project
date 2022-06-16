package dto;

public class ReplyDTO {
	public int reply_num; // 자동
	public String reply_date; // 자동
	public String reply_comment; // 0
	public int reply_score; // 1
	public String user_id; // 2
	public int restaurant_id; // 3
	public int book_num; // 4
	public String restaurant_name; // 추가
	public String book_schedule; // 추가

	public ReplyDTO() {
	}

	@Override
	public String toString() {
		String result = "";
		if (reply_date == null) {
			result += "┃------------------------------------------------------------------------------------\n";
			result += String.format("┃ %d. %s (예약 일자 : %s / 작성일자 : %s)\n", reply_num, restaurant_name, book_schedule,"리뷰 등록 전");
			result += String.format("┃ 📃내용 : %s(⭐%d점)\n", reply_comment, reply_score);
			result += "┃------------------------------------------------------------------------------------";
		}else {
			result += "┃------------------------------------------------------------------------------------\n";
			result += String.format("┃ %d. %s (예약 일자 : %s / 작성일자 : %s)\n", reply_num, restaurant_name, book_schedule,
					reply_date);
			result += String.format("┃ 📃내용 : %s(⭐%d점)\n", reply_comment, reply_score);
			result += "┃------------------------------------------------------------------------------------";
		}
		
		return result;
	}

}
