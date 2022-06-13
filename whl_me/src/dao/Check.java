package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class Check {
	
	/**
	 * String 타입으로 입력한 전화번호에 hypen("-")을 삽입하여 String 타입으로 반환<br>
	 * 
	 * @param phone
	 * @return
	 * 숫자 길이에 따라 hypen("-") 삽입 후 String 타입으로 반환<br>
	 * null 또는 숫자 없는 경우 "번호 없음" 반환<br>
	 */
	public static String regPhone(String phone) {
		String phone_reg = "";
		if(phone==null) {
			phone_reg = "번호 없음";
		}else {
			String phone_number = Check.phoneOnlyNumber(phone);
			if (phone_number.equalsIgnoreCase("")) {
				phone_reg = "번호 없음";
			} else if (phone_number.length() == 8) {
				phone_reg = phone.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
			} else if (phone_number.length() == 12) {
				phone_reg = phone_number.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
			} else {
				phone_reg = phone_number.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
			}
		}
		
		return phone_reg;
	}
	
	/**
	 * String 타입의 e-mail 형식 검사<br>
	 * 
	 * @param email String타입의 e-mail 주소
	 * @return
	 * 아이디@도메인주소 형식인 경우 true<br>
	 * 그 외 경우 false<br>
	 */
	public static boolean validateEmail(String email) {
//		String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		String pattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
		boolean validation = false;
		if (Pattern.matches(pattern, email)) {
			validation = true;
		}
		return validation;
	}


	/**
	 * String 타입의 전화번호를 숫자만 반환<br>
	 * 문자 포함 여부 확인<br>
	 * 특수문자 및 공백은 포함 가능<br>
	 * 
	 * @param phone String 타입의 전화번호
	 * @return
	 * 숫자로만 이루어진 String 타입<br>
	 * 
	 * ex)<br>
	 * 02-1234-5678	-> 0212345678<br>
	 * 02.9876.5432	-> 0298765432<br>
	 */
	public static String phoneOnlyNumber(String phone) {
		String result = "";
		for (int i = 0; i < phone.length(); i++) {
			if (48 <= phone.codePointAt(i) && phone.codePointAt(i) <= 57) {
				result += phone.charAt(i);
			}
		}
		return result;
	}
	
	/**
	 * String 타입의 전화번호 형식 검사<br>
	 * 문자 포함 여부 확인<br>
	 * 특수문자는 "-"와 "."만 사용 가능<br>
	 * @param phone String 타입의 전화번호
	 * @return
	 * 전화번호 형식 및 길이가 조건 충족 시 true<br>
	 * 그 외 false<br>
	 * <br>
	 * ex)<br>
	 * 02 123 4567	-> true<br>
	 * 02-1234.5678	-> true<br>
	 * 02-12-3456	-> false(자리수 적음)<br>
	 * 02-1234-56789-> false(자리수 많음)<br>
	 */
	public static boolean validatePhone(String phone) {
		String pattern = "^[0-9-.]{8,14}";
		if (Pattern.matches(pattern, phone)) {
			String pon = phoneOnlyNumber(phone);
			if(pon.startsWith("02")) {
				pattern = "^(02)([-.])?([0-9]{3,4})([-.])?([0-9]{4})$";
				return Pattern.matches(pattern, phone);
			}else if(pon.length()==8){
				pattern = "^([0-9]{4})([-.])?([0-9]{4})";
				return Pattern.matches(pattern, phone);
			}else {
				pattern = "([0-9]{3,4})([-.])?([0-9]{3,4})([-.])?([0-9]{4})$";
				return Pattern.matches(pattern, phone);
			}
		}
		return false;
	}
	

	/**
	 * String 타입의 날짜 유효성 및 형식 검사<br>
	 * 형식은 "yy-MM-dd"<br>
	 * 
	 * @param date String 타입의 날짜("yyyy-MM-dd")
	 * @return
	 * 날짜 형식이 맞는 경우 true<br>
	 * 그 외 경우 false<br>
	 * <br>
	 * ex)<br>
	 * 22-03-31 -> true<br>
	 * 22-04-31 -> false<br>
	 * 23-02-29 -> false<br>
	 * 24-02-29 -> true<br>
	 */
	public static boolean validateDate(String date) {

		try {
			SimpleDateFormat dateFormatParser = new SimpleDateFormat("yyyy-MM-dd");
			dateFormatParser.setLenient(false);
			dateFormatParser.parse(date);
			return true;
		} catch (ParseException e) {
		}
		return false;
	}
	
	/**
	 * String 타입의 시간 유효성 및 형식 검사<br>
	 * 형식은 "HH:mm"<br>
	 * 
	 * @param time String 타입의 시간("HH:mm")
	 * @return
	 * 시간 형식이 맞는 경우 true<br>
	 * 그 외 경우 false<br>
	 * <br>
	 * ex)<br>
	 * 23:59 -> true<br>
	 * 24:01 -> false<br>
	 * 00:01 -> true<br>
	 */
	public static boolean validateTime(String time) {
		try {
			SimpleDateFormat dateFormatParser = new SimpleDateFormat("HH:mm");
			dateFormatParser.setLenient(false);
			dateFormatParser.parse(time);
			return true;
		} catch (ParseException e) {
		}
		return false;
	}

	/**
	 * String 타입의 날짜와 시간이 현재 시점 기준으로 1시간 이후 인지 검사<br>
	 * 입력한 날짜와 시간의 유효성 검사는 하지 않음<br>
	 * 
	 * @param date String 타입의 날짜("yyyy-MM-dd")
	 * @param time String 타입의 시간("HH:mm")
	 * @return
	 * 현재 시점으로부터 1시간 이후의 날짜와 시간인 경우 true<br>
	 * 현재 시점으로부터 1시간 이후의 날짜와 시간이 아닌 경우 false<br>
	 */
	public static boolean validateDateTime(String date, String time) {
		Calendar hourLater = Calendar.getInstance();
		Calendar bookDate = Calendar.getInstance();

		hourLater.setTimeInMillis(Calendar.getInstance().getTimeInMillis() + 3600000);
		bookDate.set(Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1]) - 1,
				Integer.parseInt(date.split("-")[2]), Integer.parseInt(time.split(":")[0]),
				Integer.parseInt(time.split(":")[1]));

		if (bookDate.compareTo(hourLater) >= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * String타입의 값이 정수 형태이며 0~10 사이의 숫자인지 검사(reply_score검사용)<br>
	 * @param score String 타입의 정수
	 * @return
	 * 0~10사이의 정수인 경우 true<br>
	 * 그 외 숫자 및 문자, 공백을 입력한 경우 false<br>
	 * <br>
	 * ex)<br>
	 * 5	-> true<br>
	 * 00	-> true<br>
	 * 01	-> true<br>
	 * 010	-> true<br>
	 *  10	-> false(정수 앞 공백 포함)<br>
	 * 10	-> false(정수 뒤 공백 포함)<br>
	 * 11	-> false(10보다 큼)<br>
	 * 5.5	-> false(실수)<br>
	 */
	public static boolean validateScore(String score) {
		String pattern = "^[0-9]+$";
		if (Pattern.matches(pattern, score)) {
			if(0 <= Integer.parseInt(score) && Integer.parseInt(score) <= 10) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * String타입의 값이 정수인지 검사<br>
	 * 
	 * @param number String 타입의 정수
	 * @return
	 * 정수인 숫자의 값인 경우 true<br>
	 * 문자 또는 공백인 경우 false<br>
	 * <br>
	 * ex)<br>
	 * 1	-> true<br>
	 * 123	-> true<br>
	 * 1 23	-> false(띄어쓰기 포함)<br>
	 * 123a	-> false(문자 포함)<br>
	 */
	public static boolean validateNumber(String number) {
		String pattern= "^[0-9]+$";
		if(Pattern.matches(pattern, number)) {
			return true;
		}
		return false;	
	}
	
	
	/**
	 * 제시된 선택 가능한 메뉴에서 중복 없이 다중 선택하는 경우에 String타입의 값 형식 검사<br>
	 * 
	 * @param number String 타입의 정수
	 * @param firstNum 제시된 선택 가능한 번호 중 첫번째 숫자
	 * @param lastNum 제시된 선택 가능한 번호 중 마지막 숫자
	 * @return
	 * 매개변수로 설정해준 숫자값 사이의 정수이며 그 값의 중복이 없는 경우 true<br>
	 * 그 외 숫자나 문자, 공백이 있거나 숫자가 중복되는 경우 false<br>
	 * <br>
	 * ex)<br>
	 * 1. 월 / 2. 화 / 3. 수 / 4. 목 / 5. 금 / 6. 토 / 7. 일 <br>
	 * 123	-> true<br>
	 * 1233	-> false(3중복)<br>
	 * 1 23	-> false(띄어쓰기 포함)<br>
	 * 1238	-> false(8포함)<br> 
	*/
	public static boolean valiadateNumber_choiceMulti(String number, int firstNum, int lastNum) {
		boolean result = true;
		if(validateNumber(number)) {
			for (int i = 0; i < number.length(); i++) {
				if(!((firstNum+"").codePointAt(0)<=number.codePointAt(i)&&number.codePointAt(i)<=(lastNum+"").codePointAt(0))) {
					result= false;
				}
				for (int j = i+1; j < number.length(); j++) {
					if(number.codePointAt(i)==number.codePointAt(j)) {
						result = false;
					}
				}
			}
		}else {
			result = false;
		}
		return result;
	}
	
	/**
	 * 제시된 선택 가능한 메뉴에서 하나의 값을 선택하는 경우에 String타입 값의 형식 검사<br>
	 * 
	 * @param number String 타입의 정수
	 * @param firstNum 제시된 선택 가능한 번호 중 첫번째 숫자
	 * @param lastNum 제시된 선택 가능한 번호 중 마지막 숫자
	 * @return 
	 * 매개변수로 설정해 준 숫자값 사이의 하나의 정수인 경우 true<br>
	 * 숫자를 제외한 문자와 공백 입력 또는 여러개의 정수인 경우 false<br>
	 * <br>
	 * ex)<br>
	 * 1. 로그인  / 2. 회원가입 / 3. 나가기<br>
	 * 1~3 중 하나의 숫자 입력	-> true<br>
	 * 그 외				-> false<br>
	 */
	public static boolean validateNumber_choiceOne(String number, int firstNum, int lastNum) {
		if(validateNumber(number)&&(firstNum<=Integer.parseInt(number)&&Integer.parseInt(number)<=lastNum)) {
			return true;
		}
		return false;
	}
	/**
	 * 회원 가입 시 user_name 입력 형식 확인 메서드<br>
	 * user_name은 한글만 작성 가능하며 최소 2자리, 최대 17자리 입력 가능<br>
	 * @param user_name user의 이름
	 * @return
	 * 한글만 작성 및 2자리~17자리 인 경우 true<br>
	 * 그 외(공백 포함) false<br>
	 */
	public static boolean validateName(String user_name) {
		String pattern = "^[가-힣]{2,17}+";
		if(Pattern.matches(pattern, user_name)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 회원 가입 시 user_id 입력 형식 확인 메서드<br>
	 * user_id는 영문자와 숫자만 입력가능하며 최소 4자리, 최대 20자리 입력 가능<br>
	 * @param user_id user의 ID
	 * @return
	 * 영문자와 숫자를 이용하여 4자리~20자리 인 경우 true<br>
	 * 그 외 false<br>
	 */
	public static boolean validateID(String user_id) {
		String pattern = "^[a-zA-Z0-9]{4,20}+";
		if(Pattern.matches(pattern, user_id)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 회원 가입 시 user_nickname 입력 형식 확인 메서드<br>
	 * user_nickname은 공백 및 특수문자 사용 불가하며 최소 2자리, 최대 10자리 입력 가능<br>
	 * @param user_nickname user의 닉네임
	 * @return
	 * 한글 및 영문자, 숫자를 이용하여 2자리~10자리 인 경우 true<br>
	 * 그 외 false<br>
	 */
	public static boolean validateNickname(String user_nickname) {
		String pattern = "^[a-zA-Z0-9가-힣]{2,10}+";
		if(Pattern.matches(pattern, user_nickname)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 회원 가입 시 user_pw 입력 형식 확인 메서드<br>
	 * 비밀번호는 영문자와 숫자를 조합하여 최소 8자리, 최대 20자리 입력 가능<br>
	 * @param user_pw user의 비밀번호
	 * @return
	 * 영문자와 숫자를 이용하여 8자리~20자리 인 경우 true<br>
	 * 그 외 false<br>
	 */
	public static boolean validatePW(String user_pw) {
		String pattern = "^[a-zA-Z0-9]{8,20}+";
		if(Pattern.matches(pattern, user_pw)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 입력한 주소 형식 확인 메소드<br>
	 * 특수문자를 제외하여 사용 가능하며 가장 첫 자리 및 마지막 자리에 공백 사용 불가<br>
	 * 길이는 최대 30자리까지 입력 가능<br>
	 * @param address_data 주소와 관련된 데이터
	 * @return
	 * 30자리 이내이며 특수 문자를 제외한 문자를 사용하며, 첫 자리와 끝자리가 공백이 아닌 경우 true<br>
	 * 그 외 경우 false<br>
	 */
	public static boolean validateAddress(String address_data) {
		String pattern = "^([a-zA-Z0-9가-힣]{1})([a-zA-Z0-9가-힣\\s]{1,29})+";
		if(Pattern.matches(pattern, address_data)) {
			if((address_data.charAt(address_data.length()-1)+0)!=32) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 음식점 이름 형식 확인 메소드<br>
	 * 특수문자를 제외하여 사용 가능하며 가장 첫 자리 및 마지막 자리에 공백 사용 불가<br>
	 * 길이는 최대 20자리까지 입력 가능<br>
	 * @param restaurant_name restaurant의 이름
	 * @return
	 * 20자리 이내이며 특수 문자를 제외한 문자를 사용하며, 첫 자리와 끝자리가 공백이 아닌 경우 true<br>
	 * 그 외 경우 false<br>
	 */
	public static boolean validateRestName(String restaurant_name) {
		String pattern = "^([a-zA-Z0-9가-힣]{1})([a-zA-Z0-9가-힣\\s]{1,19})+";
		if(Pattern.matches(pattern, restaurant_name)) {
			if((restaurant_name.charAt(restaurant_name.length()-1)+0)!=32) {
				return true;
			}
		}
		return false;
	}
}
