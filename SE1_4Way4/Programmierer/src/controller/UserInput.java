  
package src.controller;

import java.util.Scanner;

public class UserInput {
	private static Scanner in = new Scanner(System.in);

	public static String readString() {
		
		return in.next();
	}
}