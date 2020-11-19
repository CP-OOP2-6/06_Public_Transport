package Managers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
	public static String stringToHash(String string) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(string.getBytes());
			byte[] digest = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for(byte b: digest)
				sb.append(String.format("%02x", b & 0xff));
			
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;		
	}
	
	public static boolean verify(String hash, String password) {
		if(!password.isEmpty())
			return hash.equals(Password.stringToHash(password));
		return false;
	}
}
