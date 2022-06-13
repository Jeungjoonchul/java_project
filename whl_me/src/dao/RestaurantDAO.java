package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import dto.RestaurantDTO;
import dto.UserDTO;

public class RestaurantDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;

	public RestaurantDAO() {
		conn = DBConnection.getConnection();
	}

	/**
	 * user 모드에서 음식점 리스트를 받아오는 메소드<br>
	 * 검색 조건, 정렬 방법, 보기 개수로 음식점 리스트를 검색<br>
	 * choiceCate는 카테고리로 검색 시 사용<br>
	 * 리스트는 "restList"의 key로 {@link dao.Session}에 저장<br>
	 * @param choice 검색 조건(1. 좋아요 / 2. 평점 / 3. 리뷰 수 / 4. 카테고리)
	 * @param choiceCate 카테고리로 조회 시 6개의 카테고리 중 1개 선택
	 * @param choiceSort 정렬 방법
	 * @param limit 결과 보기 개수
	 * @return
	 * 조건에 만족하는 restaurant 데이터들을 RestaurantDTO 타입의 객체들로 포장 후 ArrayList에 담아 반환<br>
	 */
	// 메소드명 변경 searchList => getRestList
	// 사용자가 설정한 조건에 따라 음식점 리스트 조회 후 restList Session에 저장
	public ArrayList<RestaurantDTO> getList(int choice, int choiceCate, int choiceSort, int limit) {
		ArrayList<RestaurantDTO> rl = new ArrayList<RestaurantDTO>();
		// category 배열 0번값 "" 추가
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
			// category 배열 0번에 ""값 추가로 choiceCate-1 => choiceCate로 변경
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

	/**
	 * admin 모드에서 음식점의 리스트를 받아오는 메소드<br>
	 * 검색 조건, 정렬 방법, 보기 개수, 검색 키워드로 음식점 리스트를 검색<br>
	 * choiceCate는 카테고리로 검색 시 사용<br>
	 * 리스트는 "restList"의 key로 {@link dao.Session}에 저장<br>
	 * @param choice 검색 조건(1. 음식점 이름 / 2. 음식점 전화번호 / 3. 카테고리)
	 * @param choiceCate 카테고리로 조회 시 6개의 카테고리 중 1개 선택
	 * @param choiceSort 정렬 방법
	 * @param limit 결과 보기 개수
	 * @param keyWord 검색 키워드
	 * @return
	 * 조건에 만족하는 restaurant 데이터들을 RestaurantDTO 타입의 객체들로 포장 후 ArrayList에 담아 반환<br>
	 */
	public ArrayList<RestaurantDTO> getList(int choice, int choiceCate, int choiceSort, int limit,
			String keyWord) {
		ArrayList<RestaurantDTO> rl = new ArrayList<RestaurantDTO>();
		// category 배열 0번값 "" 추가
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
			// 식당명으로 검색
			System.out.println(keyWord);
			//where r.restaurant_name like('%" + keyWord + "%')" + sort + " limit ?
			sql += "where r.restaurant_name like('%" + keyWord + "%') order by r.restaurant_name " + sort + " limit ?";
			break;
		case 2:
			// 전화번호로 검색
			sql += "where r.restaurant_phone like('%" + keyWord + "%') order by r.restaurant_phone " + sort + " limit ?";
			break;
		case 3:
			// category 배열 0번에 ""값 추가로 choiceCate-1 => choiceCate로 변경
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
	
	
	/**
	 * 검색된 음식점 리스트에서 특정 음식점을 선택하여 가져오는 메소드<br>
	 * 검색된 음식점은 "selectedRest"의 key로 {@link dao.Session}에 저장
	 * @param restaurant_id 음식점 고유 번호
	 * @return
	 * 음식점 정보와 평점, 리뷰 수를 포함한 restaurant 데이터를 RestaurantDTO로 포장하여 반환<br>
	 */
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
		} catch (SQLException e) {}
		return result;
	}

	
	/**
	 * restaurant 테이블에 새로운 데이터를 추가하는 메소드<br>
	 * 
	 * @param newRest RestaurantDTO 타입
	 * @return
	 * 데이터 추가 성공 시 true<br>
	 * 데이터 추가 실패 시 false<br>
	 */
	public boolean insert(RestaurantDTO newRest) {
		String sql = "insert into restaurant(restaurant_name,category_name,restaurant_address,restaurant_phone,restaurant_capacity,restaurant_close,restaurant_description) values(?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newRest.restaurant_name);
			ps.setString(2, newRest.category_name);
			ps.setString(3, newRest.restaurant_address);
			ps.setString(4, newRest.restaurant_phone);
			ps.setInt(5, newRest.restaurant_capacity);
			ps.setString(6, newRest.restaurant_close);
			ps.setString(7, newRest.restaurant_description);
			return ps.executeUpdate() == 1;
		} catch (SQLException e) {}
		return false;
	}
	
	/**
	 * user_register 테이블의 데이터를 admin이 확인 및 수정 후 restaurant 테이블에 새로운 데이터를 추가<br>
	 * 참조하는 user_register 데이터의 is_register의 값을 'N'에서 'Y'로 수정<br>
	 * @param newRest user_register 테이블의 데이터를 RestaurantDTO 타입의 객체로 포장한 것
	 * @param register_num user_register 테이블의 is_register 값을 수정하기 위해 필요함
	 * @return
	 * restaurant 테이블에 새로운 데이터 추가 및 is_register 값<br>
	 */
	// user_register에 있는 데이터 중 is_register가 'N'인 것과 is_comment가 null인 데이터는 유저가 추천한 음식점 중에서 관리자 확인이 진행되지 않은 데이터
	// 해당 데이터를 받아 데이터 내용을 확인하고 수정하여 RestaurantDTO타입의 객체로 만든 후 restaurant 테이블에 추가
	// restaurant에 새로운 데이터를 추가하는 것과 참조한 user_register 데이터의 is_register의 값 변경('N'->'Y')는 동시에 진행되어야 함
	// 각기 다른 테이블의 추가와 변경을 동시에 진행하는 것은 불가능하므로 restaurant에 새로운 데이터를 추가 후 참조한 user_register 데이터 변경 진행
	// 따라서 restaurant에 새로운 값 추가에 성공하면 추천한 음식점의 데이터에서 is_register의 값을 'N'에서 'Y'로 변경
	// is_register값 변경이 실패하면 결과적으로 해당 메소드는 실패이므로 추가한 restaurant(가장 마지막 데이터)를 삭제 후 false 리턴
	public boolean insert(RestaurantDTO newRest, int register_num) {
		String sql = "insert into restaurant(restaurant_name,category_name,restaurant_address,restaurant_phone,restaurant_capacity,restaurant_close,restaurant_description) values(?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, newRest.restaurant_name);
			ps.setString(2, newRest.category_name);
			ps.setString(3, newRest.restaurant_address);
			ps.setString(4, newRest.restaurant_phone);
			ps.setInt(5, newRest.restaurant_capacity);
			ps.setString(6, newRest.restaurant_close);
			ps.setString(7, newRest.restaurant_description);
			if (ps.executeUpdate() == 1) {
				UserRegisterDAO urdao = new UserRegisterDAO();
				if(urdao.update(6, "Y", register_num)) {
					return true;
				}else {
					sql = "delete from restaurant order by restaurant_id desc limit 1";
					ps = conn.prepareStatement(sql);
					ps.executeUpdate();
					return false;
				}
			}
		} catch (SQLException e) {}
		return false;
	}

	/**
	 * String 타입의 날짜("yyyy-MM-dd")를 Calendar를 이용하여 요일로 변환 후 restaurant_close 데이터와 비교<br>
	 * String date의 값을 restaurant_close 데이터가 포함하는 경우 음식점의 휴무일이므로 예약이 불가함<br>
	 * restaurant_close가 null인 경우 휴무일이 없으므로 true값 반환<br>
	 * @param date String 타입의 날짜("yyyy-MM-dd")
	 * @return
	 * date의 값을 restaurant_close 데이터가 포함하는 경우 false<br>
	 * 그 외에는 true<br>
	 */
	//BookDAO에서 RestaurantDAO로 클래스 변경(restaurant 테이블에 접근하기 때문)
	//255번 줄에 흐름 추가(restaurant 테이블에 restaurant_close의 빈값들이 null로 변경되었음)
	//restaurant_close가 null 인 경우 휴무가 없다는 뜻이므로 null값을 가져오면 바로 true로 리턴
	public boolean checkDate(String date) {
		Calendar now = Calendar.getInstance();
		Calendar bookDate = Calendar.getInstance();
		int[] date_num = {Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1]),
				Integer.parseInt(date.split("-")[2])};
		bookDate.set(date_num[0], date_num[1] - 1,
				date_num[2]);

		String[] dow = { "", "일", "월", "화", "수", "목", "금", "토" };
		int rest_id = ((RestaurantDTO) Session.getData("selectedRest")).restaurant_id;
		String sql = "select * from restaurant where restaurant_id =?";

		try {
			if (bookDate.compareTo(now) >= 0) {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, rest_id);
				rs = ps.executeQuery();
				if (rs.next()) {
					if(rs.getString("restaurant_close")==null) {
						return true;
					}else {
						String[] rest_close = rs.getString("restaurant_close").split(",");
						for (int i = 0; i < rest_close.length; i++) {
							if (dow[bookDate.get(Calendar.DAY_OF_WEEK)].equals(rest_close[i])) {
								return false;
							}
						}
					}
				}
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {}
		return false;
	}
	
	/**
	 * int 타입의 데이터를 restaurant_capacity 데이터와 비교<br>
	 * int companion_number가 restaurant_capacity보다 큰 값일 경우 예약이 불가함<br>
	 * 
	 * @param companion_number int 타입의 예약 인원
	 * @return
	 * companion_number의 값이 restaurant_capacity보다 큰 경우 false<br>
	 * companion_number의 값이 restaurant_capacity보다 같거나 작을 경우 true<br>
	 */
	// 메소드명 변경 capacityCheck => checkCapacity
	//BookDAO에서 RestaurantDAO로 클래스 변경(restaurant 테이블에 접근하기 때문)
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
}
