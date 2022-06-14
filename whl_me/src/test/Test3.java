package test;

import java.util.ArrayList;
import java.util.Scanner;

import dao.RestaurantDAO;
import dto.RestaurantDTO;

public class Test3 {
	public static void main(String[] args) {
		String choice = new Scanner(System.in).next();
		String choiceCate = new Scanner(System.in).next();
		String choiceSort = new Scanner(System.in).next();
		String limit = new Scanner(System.in).next();
		ArrayList<RestaurantDTO> srl= new RestaurantDAO().getList(Integer.parseInt(choice), Integer.parseInt(choiceCate),
				Integer.parseInt(choiceSort), Integer.parseInt(limit));
		System.out.println(srl.size());
	}
}
