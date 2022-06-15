package dto;


public class BookDTO {
	public int book_num;
    public String book_date;
    public String book_schedule;
    public int book_companion_number;
    public String has_reply;
    public String user_id;
    public int restaurant_id;
    public String restaurant_name;
    
	public BookDTO() {}
	
	public BookDTO(String[] datas) {
		String dt = datas[0]+" "+datas[1];
		this.book_schedule=dt;
		this.book_companion_number = Integer.parseInt(datas[2]);
	}

	@Override
	public String toString() {
		String result=String.format("┃ ⏲%d. %s(%d명) / 예약일시 : %s", book_num, restaurant_name,
				book_companion_number, book_schedule);
		return result;
	}
	
}
