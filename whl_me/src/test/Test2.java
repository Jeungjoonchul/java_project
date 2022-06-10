package test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

import dao.RegEx;
import dto.RestaurantDTO;
import dto.UserDTO;

public class Test2 {
	public static void main(String[] args) {
		HashMap<String, String> test = new HashMap<String, String>();
		System.out.println(test.isEmpty());
		test.put("월", "월");
		System.out.println(test.isEmpty());
	}
}
