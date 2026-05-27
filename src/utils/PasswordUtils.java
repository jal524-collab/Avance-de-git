package utils;

	import org.mindrot.jbcrypt.BCrypt;

	public class PasswordUtils {


	    public static String hashPassword(String plainTextPassword) {
	        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	    }

	    public static boolean checkPassword(String passwordPlana, String passwordHaseada) {
	        return BCrypt.checkpw(passwordPlana, passwordHaseada);
	    }
	}