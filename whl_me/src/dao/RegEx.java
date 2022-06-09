package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;

public class RegEx {
	public static String regPhone(String phone) {
		String phone_reg = "";
		if (phone.equalsIgnoreCase("")||phone.equalsIgnoreCase("*")) {
			phone_reg = "번호 없음";
		} else if (phone.length() == 8) {
			phone_reg = phone.replaceFirst("^([0-9]{4})([0-9]{4})$", "$1-$2");
		} else if (phone.length() == 12) {
			phone_reg = phone.replaceFirst("(^[0-9]{4})([0-9]{4})([0-9]{4})$", "$1-$2-$3");
		} else {
			phone_reg = phone.replaceFirst("(^02|[0-9]{3})([0-9]{3,4})([0-9]{4})$", "$1-$2-$3");
		}
		return phone_reg;
	}

	public static boolean validateEmail(String email) {
		String pattern = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		boolean validation = false;
		if (Pattern.matches(pattern, email)) {
			validation = true;
		}
		return validation;
	}

	public static boolean validatePhone(String phone) {
		boolean validation = false;
		//pattern = ^[0-9]*$;
		//010-.O345
		String pattern = "^[^a-zA-Z가-힣]*$";
		String checkPhone = phoneOnlyNumber(phone);
		if (Pattern.matches(pattern, phone)) {
			if (checkPhone.startsWith("02")) {
				if (9<=checkPhone.length()&&checkPhone.length() <= 10) {
					validation = true;
				}
			} else if (checkPhone.startsWith("01")) {
				if (9<=checkPhone.length()&&checkPhone.length() <= 11) {
					validation = true;
				}//1588-1588			050711111111 / 0315453215
			} else if (8 <= phone.length() && phone.length() <= 12) {
				validation = true;
			}
		}
		return validation;
	}

	// 010-7777-.7777=>01077777777 //02--2222-2222=>0222222222
	public static String phoneOnlyNumber(String phone) {
		String result = "";
		for (int i = 0; i < phone.length(); i++) {
			if (48 <= phone.codePointAt(i) && phone.codePointAt(i) <= 57) {
				result += phone.charAt(i);
			}
		}
		return result;
	}

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

	public static boolean validateDateTime(String date, String time) {
		boolean check = false;
		Calendar hourLater = Calendar.getInstance();
		Calendar bookDate = Calendar.getInstance();

		hourLater.setTimeInMillis(Calendar.getInstance().getTimeInMillis() + 3600000);
		bookDate.set(Integer.parseInt(date.split("-")[0]), Integer.parseInt(date.split("-")[1]) - 1,
				Integer.parseInt(date.split("-")[2]), Integer.parseInt(time.split(":")[0]),
				Integer.parseInt(time.split(":")[1]));

		if (bookDate.compareTo(hourLater) > 0) {
			check = true;
		}
		return check;
	}
	public static boolean validateScore(String score) {
		boolean validation = false;
		String pattern = "^[0-9]*$";
		if (Pattern.matches(pattern, score)) {
			if(0 <= Integer.parseInt(score) && Integer.parseInt(score) <= 10) {
				validation = true;
			}
		}
		return validation;
	}
	
	public static boolean validateNumber(String number) {
		String pattern= "^[0-9]*$";
		if(Pattern.matches(pattern, number)) {
			return true;
		}
		return false;	
	}
}
