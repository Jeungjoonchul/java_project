package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.UserDTO;

public class UserDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public UserDAO() {
		conn = DBConnection.getConnection();
	}

	public boolean checkData(int choice,String data) {
		String[] cols = { "user_id","","user_nickname" };
		String sql = "select * from user where " + cols[choice] + "=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, data);
			rs = ps.executeQuery();
			return !rs.next();
		} catch (SQLException e) {

		}
		return false;
	}

	public boolean checkPW(String user_pw) {
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		String sql = "select * from user where user_id=? and user_pw = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, user_pw);
			rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {

		}
		return false;
	}

	public boolean join(UserDTO newUser) {
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newUser.user_id);
			ps.setString(2, newUser.user_pw);
			ps.setString(3, newUser.user_name);
			ps.setString(4, newUser.user_nickname);
			ps.setString(5, newUser.user_phone);
			ps.setString(6, newUser.user_gender);
			ps.setString(7, newUser.user_email);
			ps.setString(8, newUser.user_address);
			ps.setString(9, newUser.category_name);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {
		}
		return false;
	}

	public boolean login(String user_id, String user_pw) {
		try {
			String sql = "select * from user where user_id=? and user_pw=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, user_pw);
			rs = ps.executeQuery();
			if (rs.next()) {
				UserDTO loginUser = new UserDTO();
				loginUser.user_id = rs.getString("user_id");
				loginUser.user_pw = rs.getString("user_pw");
				loginUser.user_name = rs.getString("user_name");
				loginUser.user_nickname = rs.getString("user_nickname");
				loginUser.user_phone = rs.getString("user_phone");
				loginUser.user_gender = rs.getString("user_gender");
				loginUser.user_email = rs.getString("user_email");
				loginUser.user_address = rs.getString("user_address");
				loginUser.category_name = rs.getString("category_name");
				Session.setData("loginUser", loginUser);
				return true;
			}
		} catch (SQLException e) {

		}
		return false;
	}

	// 미사용 메소드
//	public UserDTO select() {
//		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
//		String sql = "select * from user where user_id = ?";
//		try {
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, user_id);
//			rs = ps.executeQuery();
//			if (rs.next()) {
//				UserDTO loginUser = new UserDTO();
//				loginUser.user_id = rs.getString("user_id");
//				loginUser.user_pw = rs.getString("user_pw");
//				loginUser.user_name = rs.getString("user_name");
//				loginUser.user_nickname = rs.getString("user_nickname");
//				loginUser.user_phone = rs.getString("user_phone");
//				loginUser.user_gender = rs.getString("user_gender");
//				loginUser.user_email = rs.getString("user_email");
//				loginUser.user_address = rs.getString("user_address");
//				loginUser.category_name = rs.getString("category_name");
//				return loginUser;
//			}
//		} catch (SQLException e) {
//		}
//		return null;
//	}

	public boolean update(int choice, String newData) {
		String user_id = "";
		String[] cols = new String[8];
		String loginUser = ((UserDTO) Session.getData("loginUser")).user_id; // loginUser = admin
		if (loginUser.equals("admin")&&(1<=choice&&choice<=5)) {
			user_id = ((UserDTO) Session.getData("selectedUser")).user_id; // 선택한 삭제할 유저 아이디
			String[] col_name = {"", "user_name", "user_nickname", "user_phone", "user_gender", "user_email"};
			for (int i = 0; i < col_name.length; i++) {
				cols[i] = col_name[i];
				//
			}
		} else {
			user_id = ((UserDTO) Session.getData("loginUser")).user_id;
			String[] col_name = {"", "user_pw", "user_nickname", "user_phone", "user_gender", "user_email", "user_address",
			"category_name"};
			for (int i = 0; i < col_name.length; i++) {
				cols[i] = col_name[i];
			}
		}
		
		try {
			String sql = "update user set " + cols[choice] + "= ? where user_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, newData);
			ps.setString(2, user_id);
			ps.executeUpdate();
			sql = "select * from user where user_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				UserDTO login_user = new UserDTO();
				login_user.user_id = rs.getString("user_id");
				login_user.user_pw = rs.getString("user_pw");
				login_user.user_name = rs.getString("user_name");
				login_user.user_nickname = rs.getString("user_nickname");
				login_user.user_phone = rs.getString("user_phone");
				login_user.user_gender = rs.getString("user_gender");
				login_user.user_email = rs.getString("user_email");
				login_user.user_address = rs.getString("user_address");
				login_user.category_name = rs.getString("category_name");
				Session.setData("loginUser", loginUser);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
		}

		return false;
	}

	public boolean deleteUserAll() {
		String user_id = "";
		String loginUser = ((UserDTO) Session.getData("loginUser")).user_id; // loginUser = admin
		if (loginUser.equals("admin")) {
			user_id = ((UserDTO) Session.getData("selectedUser")).user_id; // 선택한 삭제할 유저 아이디
		} else {
			user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		}
		String[] table = { "reply", "book", "user_register", "user" };
		try {
			for (int i = 0; i < table.length; i++) {
				String sql = "delete from " + table[i] + " where user_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, user_id);
				if (i == table.length - 1) {
					if (loginUser.equals("admin")) {
						Session.setData("selectedUser", null);
					} else {
						Session.setData("loginUser", null);
					}
					return ps.executeUpdate() == 1;
				}
				ps.executeUpdate();
			}
		} catch (SQLException e) {
		}
		return false;
	}

	public ArrayList<UserDTO> getUserList(int choice, String data) {
		ArrayList<UserDTO> result = new ArrayList<UserDTO>();
		String[] cols = { "", "user_id", "user_name", "user_nickname", "user_phone", "user_email" };
		String sql = "";
		
		if (choice == 5) {
			sql = "select * from user where " + cols[choice] + " ='"+data+"'";
			
		}
		sql = "select * from user where " + cols[choice] + " like ('%" + data + "%')";// ('%'data'%')
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserDTO ul = new UserDTO();
				ul.user_id = rs.getString("user_id");
				ul.user_pw = rs.getString("user_pw");
				ul.user_name = rs.getString("user_name");
				ul.user_nickname = rs.getString("user_nickname");
				ul.user_phone = rs.getString("user_phone");
				ul.user_gender = rs.getString("user_gender");
				ul.user_email = rs.getString("user_email");
				ul.user_address = rs.getString("user_address");
				ul.category_name = rs.getString("category_name");
				result.add(ul);
			}
		} catch (SQLException e) {
		}
		return result;
	}

	public UserDTO select(String user_id) {
		String sql = "select * from user where user_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			if(rs.next()) {
				UserDTO selectedUser = new UserDTO();
				selectedUser.user_id = rs.getString("user_id");
				selectedUser.user_pw = rs.getString("user_pw");
				selectedUser.user_name = rs.getString("user_name");
				selectedUser.user_nickname = rs.getString("user_nickname");
				selectedUser.user_phone = rs.getString("user_phone");
				selectedUser.user_gender = rs.getString("user_gender");
				selectedUser.user_email = rs.getString("user_email");
				selectedUser.user_address = rs.getString("user_address");
				selectedUser.category_name = rs.getString("category_name");
				return selectedUser;
			}
		} catch (SQLException e) {}
		return null;
	}

}
