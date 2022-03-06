package com.hpm.nichols;

public class Main {

	public static void main(String[] args) {

		LoginPage loginWindow = new LoginPage();
		loginWindow.open();

		if(loginWindow.getLoggedIn()) {
			System.out.println("Was true");
		} else {
			System.out.println("Was False");
			System.exit(0);
		}
		
		MainWindow mainWindow = new MainWindow();
		mainWindow.open();

	}

}
