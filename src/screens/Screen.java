package screens;

import java.util.Scanner;

public class Screen {
	public static Scanner sc = new Scanner(System.in);

	public static void separator() {
		System.out.println("\n--------------------------------------\n");
	}

	static void clearScreen() {
		System.out.print("\033[H\033[2J");
	}
}
