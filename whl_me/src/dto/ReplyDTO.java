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
	    
		public ReplyDTO() {}

		@Override
		public String toString() {
			String result = "";
			result+=String.format("\n%d. %s (예약 일자 : %s)\n", reply_num, restaurant_name,book_schedule);
			result+=String.format("%s\n", reply_comment);
			result+=String.format("(%d점 / 작성 일자 : %s)\n", reply_score,reply_date);
			return result;
		}
		
		
}
