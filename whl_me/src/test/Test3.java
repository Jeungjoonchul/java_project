package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import dao.RestaurantDAO;
import dto.RestaurantDTO;

public class Test3 {
	public static void main(String[] args) {
		String[] dow = { "", "월", "화", "수", "목", "금", "토", "일" };
		String str = "154";
		String[] sort_str = str.split("");
		Arrays.sort(sort_str);
		for (String ss : sort_str) {
			System.out.println(ss);
		}
		str="";
		for (int i = 0; i < sort_str.length; i++) {
			if(i==sort_str.length-1) {
				str+=dow[Integer.parseInt(sort_str[i])];
			}else {
				str+=dow[Integer.parseInt(sort_str[i])]+",";
			}
				
					
				
			
		}
		System.out.println(str);
	}
}
