package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.RestaurantDTO;
import dto.UserDTO;
import dto.UserRegisterDTO;

public class UserRegisterDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public UserRegisterDAO() {
		conn = DBConnection.getConnection();
	}

	/**
	 * user_register 테이블에 새로운 데이터(유저가 추천한 음식점)를 추가하는 메소드<br>
	 * @param newReg 추천 음식점의 정보를 담은 UserRegisterDTO
	 * @return
	 * 새로운 데이터 삽입 성공 시 true<br>
	 * 새로운 데이터 삽입 실패 시 false<br>
	 */
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
	
	/**
	 * user 및 admin 모드에서 user가 작성한 user_register 데이터의 리스트를 받는 메소드<br>
	 * 
	 * @return
	 * user 모드에서는 user가 등록한 모든 user_register의 데이터가 ArrayList에 담겨 반환<br>
	 * admin 모드에서는 is_register가 'N'이면서 is_comment가 null인 데이터만 ArrayList에 담겨 반환<br>
	 */
	public ArrayList<UserRegisterDTO> getList() {
		String user_id = ((UserDTO) Session.getData("loginUser")).user_id;
		String sql = "select * from user_register where user_id = '"+user_id+"'";
		if(user_id.equals("admin")) {
			sql = "select * from user_register where is_register = 'N' and is_comment is null";
		}
		ArrayList<UserRegisterDTO> urList = new ArrayList<UserRegisterDTO>();
		try {
			ps = conn.prepareStatement(sql);
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
	
	/**
	 * user모드에서 register_num(등록 번호)로 추천 음식점 선택하는 메소드<br>
	 * @param register_num 추천 음식점 등록 번호
	 * @return
	 * register_num에 맞는 user_register 데이터 정보를 UserRegisterDTO로 포장하여 반환<br>
	 */
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
	
	/**
	 * admin 모드에서 register_num(등록번호)로 추천 음식점 선택하는 메소드<br>
	 * @param register_num 추천 음식점 등록 번호
	 * @return
	 * register_num에 맞는 user_register 데이터 정보를 UserRegisterDTO로 포장하여 반환<br>
	 */
	public UserRegisterDTO a_select(int register_num) {
		String sql = "select * from user_register where register_num = ?";
		UserRegisterDTO ur = new UserRegisterDTO();
		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, register_num);
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
	
	/**
	 * user모드에서 user가 추천한 음식점의 일부 정보를 수정하는 메소드<br>
	 * user모드에서는 매개변수인 choiceCol의 값은 1~5만 설정 가능<br>
	 * admin 모드에서는 is_register와 is_comment만 변경 가능(choiceCol의 값은 6, 7)<br>
	 * @param choiceCol 변경하고자 하는 정보 선택(1. restaurant_name / 2. restaurant_address / 3. category_name / 4. restaurant_phone / 5. reg_description / 6. is_register / 7. is_comment)
	 * @param newData 새로운 데이터의 값
	 * @param register_num 추천 음식점 등록 번호
	 * @return
	 * 변경 성공 시 true<br>
	 * 변경 실패 시 false<br>
	 */
	public boolean update(int choiceCol, String newData,int register_num) {
		//1. 음식점 이름/2. 주소/3. 카테고리 /4. 전화번호 /5. 사유
		
		String[] cols= {"", "restaurant_name", "restaurant_address","category_name", "restaurant_phone","reg_description","is_register","is_comment"};
		String sql = "update user_register set "+cols[choiceCol]+" = ? where register_num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newData);
			ps.setInt(2, register_num);
			return ps.executeUpdate()==1;
		} catch (SQLException e) {}
		return false;
	}

	/**
	 * user 모드에서 user가 추가한 추천 음식점 데이터 삭제하는 메소드<br>
	 * @param register_num 추천 음식점의 등록 번호
	 * @return
	 * 삭제 성공 시 true<br>
	 * 삭제 실패 시 false<br>
	 */
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
