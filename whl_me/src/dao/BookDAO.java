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

	/**
	 * book 테이블에 새로운 데이터(음식점 예약)를 추가하는 메소드<br>
	 * @param newBook 예약정보를 포함하는 BookDTO 타입의 객체
	 * @return
	 * book 테이블에 새로운 데이터 추가 성공 시 true<br>
	 * book 테이블에 새로운 데이터 추가 실패 시 false<br>
	 */
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
	
	/**
	 * user가 저장한 book(예약) 테이블에 데이터 리스트 검색<br>
	 * @param moment 검색하고자 하는 예약 설정(과거 예약-> "past" / 현재(미래) 예약 ->"current")
	 * @return
	 * 과거 또는 현재(미래)의 book 데이터들을 BooKDTO 타입의 객체들로 포장 후 ArrayList에 담아 반환<br>
	 */
	//메소드명 변경 searchList => getBookList => getList
	public ArrayList<BookDTO> getList(String moment) {
		ArrayList<BookDTO> result = new ArrayList<BookDTO>();
		String sql = "select b.book_num, r.restaurant_name, b.book_companion_number, b.book_date, b.book_schedule,  b.user_id, b.restaurant_id, b.has_reply from book as b join restaurant as r on b.restaurant_id=r.restaurant_id where user_id = ?";
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				String bd = rs.getString("book_schedule");
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
						bl.has_reply = rs.getString("has_reply");
						result.add(bl);
					}
					
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
						bl.has_reply = rs.getString("has_reply");
						result.add(bl);
					}
				}
			}
		} catch (NumberFormatException e) {
		} catch (SQLException e) {
		}
		return result;
	}
	
	/**
	 * user가 저장한 book(예약) 중 하나를 선택<br>
	 * 선택된 예약을 한 음식점 정보를 "selectedBook"의 key로 {@link dao.Session}에 저장<br>
	 * @param book_num 예약 번호
	 * @return
	 * 음식점 이름을 포함한 book 데이터를 BookDTO로 포장하여 반환<br>
	 */
	//메소드명 변경 selectBookedRest => select
	public BookDTO select(int book_num) {
		BookDTO result = new BookDTO();
		String sql = "select b.book_num, r.restaurant_name, b.book_companion_number, b.book_date, b.book_schedule, b.user_id, b.restaurant_id, b.has_reply from book as b join restaurant as r on b.restaurant_id=r.restaurant_id where book_num=? and user_id=?";
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
				result.has_reply=rs.getString("has_reply");//0
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

	/**
	 * 선택한 book(예약) 데이터 삭제<br>
	 * @param book_num 예약 번호
	 * @return
	 * book 테이블에 데이터 삭제 성공 시 true<br>
	 * book 테이블에 데이터 삭제 실패 시 false<br>
	 */
	public boolean delete(int book_num) {
		String sql = "delete from book where book_num = ? and user_id = ?";
//		String user_id = ((U serDTO)Session.getData("loginUser")).user_id;
		String user_id = ((UserDTO)Session.getData("loginUser")).user_id;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, book_num);
			ps.setString(2, user_id);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
		}
		return false;
	}

	/**
	 * 선택한 book(예약) 데이터의 값 변경<br>
	 * user 모드에서 예약 인원, 예약 날짜 변경 가능<br>
	 * has_reply는 해당 예약에 대해 리뷰 작성 시 'N'에서 'Y'로 수정<br>
	 * 
	 * @param book_num 예약 번호
	 * @param choice 바꾸고자 하는 데이터의 번호(1. 예약 인원 / 2. 예약 날짜 / 3. 리뷰 작성 여부)
	 * @param newData 새로운 데이터의 값
	 * @return
	 * 데이터 변경 성공 시 true<br>
	 * 데이터 변경 실패 시 false<br>
	 */
	public boolean update(int book_num, int choice, String newData) {
		String[] cols = { "", "book_companion_number", "book_schedule", "has_reply" };
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

	/**
	 * 현재 시점으로부터 과거의 예약 중 리뷰 작성 가능한 예약을 찾는 메소드<br>
	 * has_reply가 'N'인 예약만 ArrayList에 담아 반환<br>
	 * 같은 클래스에 있는 getList 메소드를 이용하여 과거의 예약 리스트를 뽑고 
	 * ArrayList에 있는 BookDTO 객체의 has_reply가 'N'인 BookDTO 객체만 다시 ArrayList에 담아 반환<br>
	 * @return
	 * has_reply가 'N'인 데이터들을 ArrayList에 담아 반환<br>
	 */
	public ArrayList<BookDTO> notHasReplyBook() {
		ArrayList<BookDTO> pastBookList = this.getList("past");
		ArrayList<BookDTO> result = new ArrayList<BookDTO>();
		for (BookDTO pastBook : pastBookList) {
			if (pastBook.has_reply.equalsIgnoreCase("N")) {
				result.add(pastBook);
			}
		}
		return result;
	}



}
