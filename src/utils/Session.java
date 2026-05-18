package utils;

import modelo.UserModelo;

public class Session {
	
	private static UserModelo currentUser;
	
	public static void login(UserModelo user) {
		currentUser = user;
	}
	
	public static UserModelo getCurrentUser() {
		return currentUser;
	}
	
	public static void logout() {
		currentUser = null;
	}
	
	public static boolean isLoggedIn() {
		return currentUser != null;
	}
	
	public static String getRole( ) {
		return currentUser.getRole();
	}
	

}