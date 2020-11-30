package Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InitializeUser {
	
	public static void initializeUser(String username) {
		main.LoggedUser.username = username;
		main.LoggedUser.loggedin = true;
		InitializeUser.setPerms(username);
		InitializeUser.setName(username);
	}
	
	public static void setPerms(String username) {
		Map<String, List<String>> result = Connector.get().getQueryResult(String.format("SELECT perm FROM Person_Permission \r\n" + 
				"JOIN permission on Person_Permission.permission = permission.ID \r\n" + 
				"JOIN person on Person_Permission.person = person.ID \r\n" + 
				"JOIN Account on Person.Account = account.ID \r\n" + 
				"WHERE account.Username = '%s'", username));
		
		if(result.containsKey("perm")) {
			main.LoggedUser.permissions = new ArrayList<>();
			for(String perm : result.get("perm"))
				main.LoggedUser.permissions.add(Consts.Permissions.perm.valueOf(perm));
		}
	}
	
	public static void setName(String username) {

		Map<String, List<String>> result = Connector.get().getQueryResult(String.format("SELECT lastName FROM Person \r\n" + 
				"JOIN account on Account.ID = person.account \r\n" + 
				"WHERE account.username = '%s' \r\n" +
				"LIMIT 1", username));
		
		if(result.containsKey("lastName")) {
			if (result.size() > 0)
				main.LoggedUser.name = result.get("lastName").get(0);
			else
				main.LoggedUser.name = "unknown";
		} else
			main.LoggedUser.name = "unknown";
		
	}

}
