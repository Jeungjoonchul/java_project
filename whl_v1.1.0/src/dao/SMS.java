package dao;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import org.json.simple.JSONObject;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SMS {

	public SMS() {
	}

	public String sendCode(String user_phone) {
		String api_key = "NCSADWN71F8APDIX";
		String api_secret = "QQ0O55HVMGRRRRRONBWVKBIKSIS4ZLCD";
		Message coolsms = new Message(api_key, api_secret);

		// 4 params(to, from, type, text) are mandatory. must be filled
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", user_phone);
		params.put("from", "01077268919");
		params.put("type", "SMS");
		params.put("app_version", "test app 1.2"); // application name and version

		Random r = new Random();
		String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int len = source.length();// 위 문자열의 전체 길이 74
		String result = "";
		for (int i = 0; i < 6; i++) {
			int idx = r.nextInt(len);// 0~길이-1 0~73
			result += source.charAt(idx);
		}
		params.put("text", "아래 코드를 입력하세요\n" + result);
		try {
			JSONObject obj = (JSONObject) coolsms.send(params);
//	      System.out.println(obj.toString());
//			System.out.println("문자 보내기 성공!");
			return result;
		} catch (CoolsmsException e) {
//			System.out.println(e.getMessage());
//			System.out.println(e.getCode());
//			System.out.println("문자 보내기 실패!");
		}
		return null;
	}
}
