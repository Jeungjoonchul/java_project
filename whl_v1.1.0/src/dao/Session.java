 package dao;

import java.util.HashMap;
/**
 * 데이터 처리에 필요한 정보들을 빠르게 접근하기 위해 HashMap에 key와 함께 저장<br>
 * 사용된 Session list<br>
 * "loginUser"		로그인 후 유저 정보 저장(UserDTO)<br>
 * "selectedUser"	유저 리스트 중 선택된 1명의 유저 정보 저장(UserDTO / admin모드에서만 사용)<br>
 * "restList"		필터를 이용하여 음식점 리스트 검색 후 리스트 저장(ArrayList<RestaurantDTO>)<br>
 * "selectedrest"	음식점 리스트 중 선택된 1개의 음식점 정보 저장(RestaurantDTO)<br>
 * "selectedBook"	예약 리스트 중 선택된 1개의 예약 정보 저장(BookDTO)<br>
 * <br>
 * admin모드에서 음식점 수정 후 restList를 최신화 하기 위해 저장<br>
 * "choice", "choiceCate", "choiceSort", "limit", "keyword"<br>
 * 		
 * 
 * 
 * 
 * @author Joonchul Jeung
 *
 */
public class Session {
	
	private final static HashMap<String, Object> datas=new HashMap<String, Object>();

	public static HashMap<String, Object> getDatas() {
		return datas;
	}
	
	public static void setData(String key, Object value) {
		datas.put(key, value);
	}
	
	public static Object getData(String key) {
		return datas.get(key);
	}
}
