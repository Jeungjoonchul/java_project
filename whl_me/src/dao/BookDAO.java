package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dto.BookDTO;
import dto.ReplyDTO;
import dto.RestaurantDTO;
import dto.UserDTO;

public class BookDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public BookDAO() {
		conn = DBConnection.getConnection();
	}

	public boolean checkDate(String date) {
		Calendar now = Calendar.getInstance();
		Calendar bookDate = Calendar.getInstance();

		bookDate.set(Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1]) - 1,
				Integer.parseInt(date.split("-")[2]));

		String[] dow = { "", "일", "월", "화", "수", "목", "금", "토" };
		int rest_id = ((RestaurantDTO) Session.getData("selectedRest")).restaurant_id;
		String sql = "select * from restaurant where restaurant_id =?";

		try {
			if (bookDate.compareTo(now) >= 0) {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, rest_id);
				rs = ps.executeQuery();
				if (rs.next()) {
					String[] rest_close = rs.getString("restaurant_close").split(",");
					for (int i = 0; i < rest_close.length; i++) {
						if (dow[bookDate.get(Calendar.DAY_OF_WEEK)].equals(rest_close[i])) {
							return false;
						}
					}
				}
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
		}
		return false;
	}

//	public boolean checkDate(String date, String time) {
//		Calendar hourLater = Calendar.getInstance();
//		Calendar bookDate = Calendar.getInstance();
//		hourLater.setTimeInMillis(Calendar.getInstance().getTimeInMillis() + 3600000);
//		bookDate.set(Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1]) - 1,
//				Integer.parseInt(date.split("-")[2]), Integer.parseInt(time.split(":")[0]),
//				Integer.parseInt(time.split(":")[1]));
//
//		String[] dow = { "", "일", "월", "화", "수", "목", "금", "토" };
//		int rest_id = ((RestaurantDTO) Session.getData("selectedRest")).restaurant_id;
//		String sql = "select * from restaurant where restaurant_id =?";
//
//		try {
//			if (bookDate.compareTo(hourLater) > 0) {
//				ps = conn.prepareStatement(sql);
//				ps.setInt(1, rest_id);
//				rs = ps.executeQuery();
//				if (rs.next()) {
//					String[] rest_close = rs.getString("restaurant_close").split(",");
//					for (int i = 0; i < rest_close.length; i++) {
//						if (dow[bookDate.get(Calendar.DAY_OF_WEEK)].equals(rest_close[i])) {
//							return false;
//						}
//					}
//				}
//				return true;
//			} else {
//				return false;
//			}
//		} catch (SQLException e) {
//		}
//		return false;
//	}
	// 메소드명 변경 capacityCheck => checkCapacity
	public boolean checkCapacity(int companion_number) {
		String sql = "select * from restaurant where restaurant_id =?";
		int rest_id = ((RestaurantDTO) Session.getData("selectedRest")).restaurant_id;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rest_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				int cap = rs.getInt("restaurant_capacity");
				if (cap >= companion_number) {
					return true;
				}
			}
		} catch (SQLException e) {

		}
		return false;
	}

	public boolean insert(BookDTO newBook) {
		String sql = "insert into book(book_schedule,book_companion_number,user_id,restaurant_id) values(?,?,?,?)";
		newBook.restaurant_id = ((RestaurantDTO) Session.getData("selectedRest")).restaurant_id;
		newBook.user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newBook.book_schedule);
			ps.setInt(2, newBook.book_companion_number);
			ps.setString(3, newBook.user_id);
			ps.setInt(4, newBook.restaurant_id);
			if (ps.executeUpdate() == 1) {
				return true;
			}
		} catch (SQLException e) {

		}
		return false;
	}
	
	//메소드명 변경 searchList => getBookList
	public ArrayList<BookDTO> getBookList(String moment) {
		ArrayList<BookDTO> result = new ArrayList<BookDTO>();
		String sql = "select b.book_num, r.restaurant_name, b.book_companion_number, b.book_date, b.book_schedule,  b.user_id, b.restaurant_id, b.book_expired from book as b join restaurant as r on b.restaurant_id=r.restaurant_id where user_id = ?";
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				String bd = rs.getString("book_schedule");
				// 2022-06-05 16:20:00
//				String[] splited = {bd.split(" ")[0], bd.split(" ")[1]};
//				int[] date = new int[6];
//				date[0] = Integer.parseInt(splited[0].split("-")[0]);
//				date[1] = Integer.parseInt(splited[0].split("-")[1]);
//				date[2] = Integer.parseInt(splited[0].split("-")[2]);
//				date[3] = Integer.parseInt(splited[1].split(":")[0]);
//				date[4] = Integer.parseInt(splited[1].split(":")[1]);
//				date[5] = Integer.parseInt(splited[1].split(":")[2]);
				int[] date = new int[6];
				int k = 0;
				String[] splDateTime = new String[bd.split(" ").length];
				for (int i = 0; i < bd.split(" ").length; i++) {
					splDateTime[i] = bd.split(" ")[i];
					if (splDateTime[i].contains("-")) {
						for (int j = 0; j < splDateTime[i].split("-").length; j++) {
							date[k] = Integer.parseInt(splDateTime[i].split("-")[j]);
							k++;
						}
					} else if (splDateTime[i].contains(":")) {
						for (int j = 0; j < splDateTime[i].split(":").length; j++) {
							date[k] = Integer.parseInt(splDateTime[i].split(":")[j]);
							k++;
						}
					} else {
						break;
					}
				}
				Calendar now = Calendar.getInstance();
				Calendar schedule = Calendar.getInstance();
				schedule.set(date[0], date[1] - 1, date[2], date[3], date[4], date[5]);
				if (moment.equalsIgnoreCase("current")) {
					if (schedule.after(now)) {
						BookDTO bl = new BookDTO();
						bl.book_num = rs.getInt("book_num");
						bl.restaurant_name = rs.getString("restaurant_name");
						bl.book_companion_number = rs.getInt("book_companion_number");
						bl.book_date = rs.getString("b.book_date");
						bl.book_schedule = rs.getString("book_schedule");
						bl.user_id = rs.getString("user_id");
						bl.restaurant_id = rs.getInt("restaurant_id");
						bl.book_expired = rs.getString("book_expired");
						result.add(bl);
					}
					// 과거 예약 보기를 실행하기 위해 매개 변수("current" or "past") 추가 및 흐름 추가
				} else if (moment.equalsIgnoreCase("past")) {
					if (schedule.before(now)) {
						BookDTO bl = new BookDTO();
						bl.book_num = rs.getInt("book_num");
						bl.restaurant_name = rs.getString("restaurant_name");
						bl.book_companion_number = rs.getInt("book_companion_number");
						bl.book_date = rs.getString("book_date");
						bl.book_schedule = rs.getString("book_schedule");
						bl.user_id = rs.getString("user_id");
						bl.restaurant_id = rs.getInt("restaurant_id");
						bl.book_expired = rs.getString("book_expired");
						result.add(bl);
					}
				}
			}
		} catch (NumberFormatException e) {
		} catch (SQLException e) {
		}
		return result;
	}

	//미사용 메소드
//	public boolean checkBook(int book_num) {
//		String sql = "select * from book where book_number = ? and user_id = ?";
//		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, book_num);
//			ps.setString(2, user_id);
//			rs = ps.executeQuery();
//			return rs.next();
//		} catch (SQLException e) {
//		}
//		return false;
//	}
	
	//select메소드와 내용 유사하여 통합
//	public BookDTO selectForReply(int book_num) {
//		String sql = "select * from book where book_num = ? and user_id = ?";
//		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setInt(1, book_num);
//			ps.setString(2, user_id);
//			rs = ps.executeQuery();
//			if (rs.next()) {
//				BookDTO result = new BookDTO();
//				result.book_num = rs.getInt("book_num");
//				result.book_companion_number = rs.getInt("book_companion_number");
//				result.book_date = rs.getString("book_date");
//				result.book_schedule = rs.getString("book_schedule");
//				result.user_id = rs.getString("user_id");
//				result.restaurant_id = rs.getInt("restaurant_id");
//				result.book_expired = rs.getString("book_expired");
//				return result;
//			}
//		} catch (SQLException e) {
//		}
//		return null;
//	}
	
	//메소드명 변경 selectBookedRest => select
	public BookDTO select(int book_num) {
		BookDTO result = new BookDTO();
		String sql = "select b.book_num, r.restaurant_name, b.book_companion_number, b.book_date, b.book_schedule, b.user_id, b.restaurant_id, b.book_expired from book as b join restaurant as r on b.restaurant_id=r.restaurant_id where book_num=? and user_id=?";
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, book_num);
			ps.setString(2, user_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				
				result.book_num=rs.getInt("book_num");//0
				result.book_date=rs.getString("book_date");//0
				result.book_schedule=rs.getString("book_schedule");//0
				result.book_companion_number=rs.getInt("book_companion_number");//0
				result.book_expired=rs.getString("book_expired");//0
				result.user_id=rs.getString("user_id");//0
				result.restaurant_id=rs.getInt("restaurant_id");//0
				result.restaurant_name=rs.getString("restaurant_name");
				RestaurantDAO rdao = new RestaurantDAO();
				if(rdao.select(result.restaurant_id)!=null) {
					Session.setData("selectedBook", result);
					return result;
				}else {
					return null;
				}
			}
		} catch (SQLException e) {}

		return result;
	}

	public boolean delete(int book_num) {
		String sql = "delete from book where book_num = ? and user_id = ?";
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, book_num);
			ps.setString(2, user_id);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
		}
		return false;
	}

	public boolean update(int book_num, int choice, String newData) {
		String[] cols = { "", "book_companion_number", "book_schedule", "book_expired" };
		String sql = "Update book set " + cols[choice] + " = ? where book_num=? and user_id=?";
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		try {
			ps = conn.prepareStatement(sql);
			if (choice == 1) {
				ps.setInt(1, Integer.parseInt(newData));
			} else {
				ps.setString(1, newData);
			}
			ps.setInt(2, book_num);
			ps.setString(3, user_id);

			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
		}
		return false;
	}


	public ArrayList<BookDTO> notHasReplyBook() {
		ArrayList<BookDTO> pastBookList = this.getBookList("past");
		ArrayList<BookDTO> result = new ArrayList<BookDTO>();
		for (BookDTO pastBL : pastBookList) {
			if (pastBL.book_expired.equalsIgnoreCase("N")) {
				result.add(pastBL);
			}
		}
		return result;
	}



}
