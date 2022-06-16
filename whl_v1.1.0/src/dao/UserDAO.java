package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import dto.UserDTO;

public class UserDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public UserDAO() {
		conn = DBConnection.getConnection();
	}

	/**
	 * user_id, user_nickname의 중복 여부를 확인하는 메소드<br>
	 * user_id의 경우 매개변수 값은 0이어야하며, user_nickname의 경우 매개변수의 값은 3이어야함<br>
	 * 
	 * @param choice 변경하고자 하는 데이터의 배열 인덱스(0. user_id / 3. user_nickname)
	 * @param data   중복된 값이 있는지 확인할 값
	 * @return 중복 발생 시 false<br>
	 *         중복 없는 경우 true<br>
	 */
	public boolean checkData(int choice, String data) {
		String[] cols = { "user_id", "", "", "user_nickname" };
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

	/**
	 * user 데이터의 변경 및 삭제 과정의 비밀번호 일치 여부를 확인하는 메소드<br>
	 * 
	 * @param user_pw user가 입력한 비밀번호
	 * @return 매개변수의 값(입력한 값)이 user_pw와 일치하는 경우 true<br>
	 *         매개변수의 값(입력한 값)이 user_pw와 일치하지 않는 경우 false<br>
	 */
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

	/**
	 * user 테이블에 데이터를 추가하는 메소드(회원가입)<br>
	 * 
	 * @param newUser user의 정보를 담은 UserDTO
	 * @return user 테이블에 데이터 삽입 성공 시 true<br>
	 *         user 테이블에 데이터 삽입 실패 시 false<br>
	 */
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

	/**
	 * user 로그인 메소드<br>
	 * 로그인 성공 시 "loginUser"의 key로 {@link dao.Session}에 유저 정보를 담은 UserDTO 타입의 객체
	 * 저장<br>
	 * 
	 * @param user_id
	 * @param user_pw
	 * @return user_id, user_pw가 일치하는 경우 true<br>
	 *         그 외의 경우 false<br>
	 */
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

	/**
	 * user 및 admin 모드에서 정보를 수정하는 메소드<br>
	 * 
	 * @param choice  변경하고자 하는 데이터의 칼럼
	 * @param newData 새로운 데이터의 값
	 * @return 수정 성공 시 true<br>
	 *         수정 실패 시 false<br>
	 */
	public boolean update(int choice, String newData) {
		String user_id = "";
		String[] cols = new String[8];
		String loginUser = ((UserDTO) Session.getData("loginUser")).user_id; // loginUser = admin
		if (loginUser.equals("admin") && (1 <= choice && choice <= 5)) {
			user_id = ((UserDTO) Session.getData("selectedUser")).user_id; // 선택한 삭제할 유저 아이디
			String[] col_name = { "", "user_name", "user_nickname", "user_phone", "user_gender", "user_email" };
			for (int i = 0; i < col_name.length; i++) {
				cols[i] = col_name[i];
				//
			}
		} else {
			user_id = ((UserDTO) Session.getData("loginUser")).user_id;
			String[] col_name = { "", "user_pw", "user_nickname", "user_phone", "user_gender", "user_email",
					"user_address", "category_name" };
			for (int i = 0; i < col_name.length; i++) {
				cols[i] = col_name[i];
			}
		}

		try {
			String sql = "update user set " + cols[choice] + "= ? where user_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, newData);
			ps.setString(2, user_id);
			ps.executeUpdate(); // 변경완료
			// 유저 정보 가져와서 세션화
			sql = "select * from user where user_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				UserDTO updated_user = new UserDTO();
				updated_user.user_id = rs.getString("user_id");
				updated_user.user_pw = rs.getString("user_pw");
				updated_user.user_name = rs.getString("user_name");
				updated_user.user_nickname = rs.getString("user_nickname");
				updated_user.user_phone = rs.getString("user_phone");
				updated_user.user_gender = rs.getString("user_gender");
				updated_user.user_email = rs.getString("user_email");
				updated_user.user_address = rs.getString("user_address");
				updated_user.category_name = rs.getString("category_name");
				// 수정
				if (loginUser.equals("admin")) {
					Session.setData("selectedUser", updated_user);
				} else {
					Session.setData("loginUser", updated_user);
				}
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
		}

		return false;
	}

	/**
	 * user 및 admin 모드에서 user의 모든 정보를 삭제<br>
	 * 
	 * @return reply, book, user_register, user 테이블에서 해당 유저의 모든 정보 삭제 성공 시 true<br>
	 *         하나라도 삭제 실패 시 false<br>
	 */
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

	/**
	 * admin모드에서 유저 리스트를 받아오는 메소드<br>
	 * 포함 검색어와 검색 조건을 설정해야함<br>
	 * email 검색은 포함 검색어가 아닌 정확한 값을 입력<br>
	 * 
	 * @param choice 검색 조건(1. id / 2. name / 3. nickname / 4. phone / 5. email)
	 * @param data   포함 검색어
	 * @return 검색 조건 및 포함 검색어를 만족하는 user 데이터를 UserDTO로 포장하여 ArrayList에 담아 반환<br>
	 */
	public ArrayList<UserDTO> getList(int choice, String data) {
		ArrayList<UserDTO> result = new ArrayList<UserDTO>();
		String[] cols = { "", "user_id", "user_name", "user_nickname", "user_phone", "user_email" };
		String sql = "";

		// 이메일 검색 조건
		if (choice == 5) {
			sql = "select * from user where " + cols[choice] + " ='" + data + "'";

		}
		sql = "select * from user where " + cols[choice] + " like ('%" + data + "%')";
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

	/**
	 * admin 모드에서 유저를 선택하는 메소드<br>
	 * 
	 * @param user_id user의 id
	 * @return 유저 정보를 담은 UserDTO 반환<br>
	 */
	public UserDTO select(String user_id) {
		String sql = "select * from user where user_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			if (rs.next()) {
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
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * user가 id를 찾고자 할 때 사용<br>
	 * 
	 * @param user_name  user의 이름
	 * @param user_phone user의 휴대폰 번호
	 * @return user 테이블에 있는 정보와 비교하여 일치하는 경우 true<br>
	 *         그 외 false<br>
	 */
	public UserDTO findID(String user_name, String user_phone) {
		user_phone = Check.phoneOnlyNumber(user_phone);
		String sql = "select * from user where user_name = ? and user_phone = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, user_phone);
			rs = ps.executeQuery();
			if (rs.next()) {
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
		} catch (SQLException e) {
		}
		return null;
	}

	/**
	 * user가 pw를 찾고자 할 때 사용<br>
	 * 
	 * @param user_id    user의 id
	 * @param user_phone user의 휴대폰
	 * @return user 테이블에 있는 정보와 비교하여 일치하는 경우 true<br>
	 *         그 외 false<br>
	 */
	public UserDTO findPW(String user_id, String user_phone) {
		String sql = "select * from user where user_id = ? and user_phone = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user_id);
			ps.setString(2, user_phone);
			rs = ps.executeQuery();
			if (rs.next()) {
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
		} catch (SQLException e) {
		}
		return null;
	}

}
