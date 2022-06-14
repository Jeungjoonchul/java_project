package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.ReplyDTO;
import dto.UserDTO;

public class ReplyDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public ReplyDAO() {
		conn = DBConnection.getConnection();
	}

	/**
	 * user가 작성한 reply 데이터들을 리스트로 받기 위한 메소드<br>
	 * admin 모드에서는 선택한 user의 reply 데이터들을 리스트로 담아 반환<br>
	 * @return
	 * 조건(user_id)에 맞는 reply 데이터를 ReplyDTO로 포장하여 ArrayList에 담아 반환<br>
	 */
	public ArrayList<ReplyDTO> getList() {
		ArrayList<ReplyDTO> result = new ArrayList<ReplyDTO>();
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		if(user_id.equals("admin")) {
			user_id = ((UserDTO) Session.getData("selectedUser")).user_id;
		}
		String sql = "select rp.reply_num,rp.reply_date,rp.reply_comment,rp.reply_score,rp.user_id,rp.restaurant_id,rp.book_num,rs.restaurant_name,b.book_schedule from reply rp join restaurant rs on rp.restaurant_id=rs.restaurant_id join book b on b.book_num=rp.book_num where rp.user_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			while(rs.next()) {
				ReplyDTO url = new ReplyDTO();
				url.reply_num = rs.getInt("reply_num");
				url.reply_date = rs.getString("reply_date");
				url.reply_comment =rs.getString("reply_comment");
				url.reply_score =rs.getInt("reply_score");
				url.user_id = rs.getString("user_id");
				url.restaurant_id =rs.getInt("restaurant_id");
				url.book_num = rs.getInt("book_num");
				url.restaurant_name=rs.getString("restaurant_name");
				url.book_schedule = rs.getString("book_schedule");
				result.add(url);
			}
		} catch (SQLException e) {}
		return result;
	}

	/**
	 * user 모드 또는 admin 모드에서 선택한 reply를 삭제하는 메소드<br>
	 * 
	 * @param reply_num 리뷰 번호
	 * @return
	 * 리뷰 삭제 성공 시 true<br>
	 * 리뷰 삭제 실패 시 false<br>
	 */
	public boolean delete(int reply_num) {
		String sql = "delete from reply where reply_num = ? and user_id = ?";
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		if(user_id.equals("admin")) {
			user_id = ((UserDTO) Session.getData("selectedUser")).user_id;
		}
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reply_num);
			ps.setString(2, user_id);
			return ps.executeUpdate()==1;
		} catch (SQLException e) {}
		return false;
	}
	
	/**
	 * user 또는 admin 모드에서 선택한 리뷰 번호에 해당하는 reply 데이터가 있는지 확인<br>
	 * @param reply_num 리뷰 번호
	 * @return
	 * 리뷰 번호에 해당하는 reply 데이터가 있는 경우 true<br>
	 * 리뷰 번호에 해당하는 reply 데이터가 없는 경우 false<br>
	 */
	public boolean isReplyOn(int reply_num) {
		String sql = "select * from reply where reply_num = ? and user_id = ?";
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		if(user_id.equals("admin")) {
			user_id = ((UserDTO) Session.getData("selectedUser")).user_id;
		}
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reply_num);
			ps.setString(2, user_id);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {}
		return false;
	}

	/**
	 * user의 리뷰를 reply 테이블에 삽입하는 메소드<br>
	 * @param newReply 리뷰 정보를 담고 있는 ReplyDTO
	 * @return
	 * reply 테이블에 데이터 추가 성공 시 true<br>
	 * reply 테이블에 데이터 추가 실패 시 false<br>
	 */
	public boolean insert(ReplyDTO newReply) {
		//comment, score, user_id, restaurant_id, book_num
		String sql = "insert into reply(reply_comment,reply_score,user_id,restaurant_id,book_num) values(?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newReply.reply_comment);
			ps.setInt(2, newReply.reply_score);
			ps.setString(3, newReply.user_id);
			ps.setInt(4, newReply.restaurant_id);
			ps.setInt(5, newReply.book_num);
			if(ps.executeUpdate()==1) {
				BookDAO bdao = new BookDAO();
				int book_num = newReply.book_num;
				if(bdao.update(book_num, 3, "Y")) {
					return true;
				}else {
					sql = "delete from reply order by reply_num desc limit 1";
					ps = conn.prepareStatement(sql);
					ps.executeUpdate();
					return false;
				}
			}
		} catch (SQLException e) {}
		return false;
	}
	
	
}
