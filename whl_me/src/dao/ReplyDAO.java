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

	public ArrayList<ReplyDTO> getList() {
		ArrayList<ReplyDTO> result = new ArrayList<ReplyDTO>();
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
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

	public boolean delete(int reply_num) {
		String sql = "delete from reply where reply_num = ? and user_id = ?";
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reply_num);
			ps.setString(2, user_id);
			return ps.executeUpdate()==1;
		} catch (SQLException e) {}
		return false;
	}

	public boolean isReplyOn(int reply_num) {
		String sql = "select * from reply where reply_num = ? and user_id = ?";
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, reply_num);
			ps.setString(2, user_id);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {}
		return false;
	}

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
			return ps.executeUpdate()==1;
		} catch (SQLException e) {}
		return false;
	}
	
	
}
