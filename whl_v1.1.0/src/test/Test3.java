package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import dao.Check;
import dao.RestaurantDAO;
import dao.Session;
import dto.RestaurantDTO;

public class Test3 {
	public static void main(String[] args) {
		while(true) {
			Scanner sc = new Scanner(System.in);
			String phone = sc.nextLine();
			if(Check.validatePhone(phone)) {
				System.out.println("트루");
			}else {
				System.out.println("낫트루");
			}
		}
	}
}
