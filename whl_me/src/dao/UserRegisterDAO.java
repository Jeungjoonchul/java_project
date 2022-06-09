package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.UserDTO;
import dto.UserRegisterDTO;

public class UserRegisterDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public UserRegisterDAO() {
		conn = DBConnection.getConnection();
	}

	public boolean insert(UserRegisterDTO newReg) {
		String sql = "insert into user_register(restaurant_name, restaurant_address,category_name,restaurant_phone,reg_description,user_id) values(?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newReg.restaurant_name);
			ps.setString(2, newReg.restaurant_address);
			ps.setString(3, newReg.category_name);
			ps.setString(4, newReg.restaurant_phone);
			ps.setString(5, newReg.reg_description);
			ps.setString(6, newReg.user_id);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {System.out.println("오류 사유 : "+e);}
		return false;
	}

	public ArrayList<UserRegisterDTO> searchList() {
		String sql = "select * from user_register where user_id = ?";
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		ArrayList<UserRegisterDTO> urList = new ArrayList<UserRegisterDTO>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserRegisterDTO ur = new UserRegisterDTO();
				ur.register_num = rs.getInt("register_num");
				ur.restaurant_name = rs.getString("restaurant_name");
				ur.restaurant_address = rs.getString("restaurant_address");
				ur.category_name = rs.getString("category_name");
				ur.restaurant_phone = rs.getString("restaurant_phone");
				ur.reg_description = rs.getString("reg_description");
				ur.is_register = rs.getString("is_register");
				ur.is_comment = rs.getString("is_comment");
				ur.user_id = rs.getString("user_id");
				urList.add(ur);
			}
		} catch (SQLException e) {}
		return urList;
	}

	public UserRegisterDTO select(int register_num) {
		String sql = "select * from user_register where user_id = ? and register_num = ?";
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		UserRegisterDTO ur = new UserRegisterDTO();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setInt(2, register_num);
			rs = ps.executeQuery();
			while (rs.next()) {
				ur.register_num = rs.getInt("register_num");
				ur.restaurant_name = rs.getString("restaurant_name");
				ur.restaurant_address = rs.getString("restaurant_address");
				ur.category_name = rs.getString("category_name");
				ur.restaurant_phone = rs.getString("restaurant_phone");
				ur.reg_description = rs.getString("reg_description");
				ur.is_register = rs.getString("is_register");
				ur.is_comment = rs.getString("is_comment");
				ur.user_id = rs.getString("user_id");
				return ur;
			}
		} catch (SQLException e) {}
		return null;
	}

	public boolean update(int choiceCol, String newData,int register_num) {
		//1. 음식점 이름/2. 주소/3. 카테고리 /4. 전화번호 /5. 사유
		
		String[] cols= {"", "restaurant_name", "restaurant_address","category_name", "restaurant_phone","reg_description"};
		String sql = "update user_register set "+cols[choiceCol]+" = ? where register_num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newData);
			ps.setInt(2, register_num);
			return ps.executeUpdate()==1;
		} catch (SQLException e) {}
		return false;
	}

	public boolean delete(int register_num) {
		String sql = "delete from user_register where register_num = ? and user_id = ?";
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, register_num);
			ps.setString(2, user_id);
			return ps.executeUpdate()==1;
		} catch (SQLException e) {}
		return false;
	}

}
