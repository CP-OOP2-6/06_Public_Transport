package main;

import java.util.List;

public class LoggedUser {
	
	public static String 							username;
	public static String 							name;
	public static List<Consts.Permissions.perm> 	permissions;
	public static boolean							loggedin;
	
	
	public static String getTitle() {
		return String.format(" - %s", name);
	}
	
	public static boolean hasPermission(Consts.Permissions.perm permission) {
		return permissions.contains(permission);
	}	
	
}
