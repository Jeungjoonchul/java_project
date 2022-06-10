package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.RestaurantDTO;
import dto.UserDTO;

public class RestaurantDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public RestaurantDAO() {
		conn = DBConnection.getConnection();
	}

	// 메소드명 변경 searchList => getRestList
	// 사용자가 설정한 조건에 따라 음식점 리스트 조회 후 restList Session에 저장
	public ArrayList<RestaurantDTO> getRestList(int choice, int choiceCate, int choiceSort, int limit) {
		ArrayList<RestaurantDTO> rl = new ArrayList<RestaurantDTO>();
		//category 배열 0번값 "" 추가
		String[] category = { "", "한식", "중식", "일식", "양식", "패스트푸드", "카페/디저트" };
		String sort = "";
		String sql = "select r.restaurant_id,r.restaurant_name,r.category_name,r.restaurant_address,r.restaurant_phone,r.restaurant_capacity,r.restaurant_close,r.restaurant_description,r.restaurant_like_cnt,rs.avg_score,rs.reply_cnt\r\n"
				+ "    from restaurant r left join(select avg(reply_score) as avg_score, count(reply_num) as reply_cnt,restaurant_id from reply group by restaurant_id) as rs on r.restaurant_id=rs.restaurant_id ";
		if (choiceSort == 1) {
			sort = "asc";
		} else {
			sort = "desc";
		}
		switch (choice) {
		case 1:
			sql += "order by r.restaurant_like_cnt " + sort + " limit ?";
			break;
		case 2:
			sql += "order by rs.avg_score " + sort + " limit ?";
			break;
		case 3:
			sql += "order by rs.reply_cnt " + sort + " limit ?";
			break;
		case 4:
			//category 배열 0번에 ""값 추가로 choiceCate-1 => choiceCate로 변경 
			sql += "where r.category_name='" + category[choiceCate] + "' order by r.restaurant_id " + sort + " limit ?";
			break;
		}
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, limit);
			rs = ps.executeQuery();
			while (rs.next()) {
				RestaurantDTO r = new RestaurantDTO();
				r.restaurant_id = rs.getInt("restaurant_id");
				r.restaurant_name = rs.getString("restaurant_name");
				r.category_name = rs.getString("category_name");
				r.restaurant_address = rs.getString("restaurant_address");
				r.restaurant_phone = rs.getString("restaurant_phone");
				r.restaurant_capacity = rs.getInt("restaurant_capacity");
				r.restaurant_close = rs.getString("restaurant_close");
				r.restaurant_description = rs.getString("restaurant_description");
				r.restaurant_like_cnt = rs.getInt("restaurant_like_cnt");
				r.avg_score = rs.getInt("avg_score");
				r.reply_cnt = rs.getInt("reply_cnt");
				rl.add(r);
				Session.setData("restList", rl);
			}
		} catch (SQLException e) {
		}
		return rl;
	}

	// 메소드명 변경 selectRest => select
	public RestaurantDTO select(int restaurant_id) {
		RestaurantDTO result = new RestaurantDTO();
		String sql = "select * from restaurant r left join(select avg(reply_score) as avg_score, count(reply_num) as reply_cnt,restaurant_id from reply group by restaurant_id) as rs on r.restaurant_id=rs.restaurant_id where r.restaurant_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, restaurant_id);
			rs = ps.executeQuery();
			if (rs.next()) {
				result.restaurant_id = rs.getInt("restaurant_id");
				result.restaurant_name = rs.getString("restaurant_name");
				result.category_name = rs.getString("category_name");
				result.restaurant_address = rs.getString("restaurant_address");
				result.restaurant_phone = rs.getString("restaurant_phone");
				result.restaurant_capacity = rs.getInt("restaurant_capacity");
				result.restaurant_close = rs.getString("restaurant_close");
				result.restaurant_description = rs.getString("restaurant_description");
				result.restaurant_like_cnt = rs.getInt("restaurant_like_cnt");
				result.avg_score = rs.getInt("avg_score");
				result.reply_cnt = rs.getInt("reply_cnt");
				Session.setData("selectedRest", result);
			}
		} catch (SQLException e) {

		}
		return result;
	}
}
