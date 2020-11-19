package Database;

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
		Map<String, List<String>> result = Connector.get().getQueryResult(String.format("SELECT Permission FROM person_to_permission \r\n" + 
				"JOIN permission on person_to_permission.permission = permission.ID \r\n" + 
				"JOIN person on person_to_permission.person = person.ID \r\n" + 
				"JOIN Account on Person.Account = account.ID \r\n" + 
				"WHERE account.Username = '%s'", username));
		
		if(result.containsKey("Permission"))
			main.LoggedUser.permissions = result.get("Permission");
	}
	
	public static void setName(String username) {

		Map<String, List<String>> result = Connector.get().getQueryResult(String.format("SELECT LastName FROM Person \r\n" + 
				"JOIN account on Account.ID = person.account \r\n" + 
				"WHERE account.Username = '%s' \r\n" +
				"LIMIT 1", username));
		
		if(result.containsKey("LastName")) {
			if (result.size() > 0)
				main.LoggedUser.name = result.get("LastName").get(0);
			else
				main.LoggedUser.name = "unknown";
		} else
			main.LoggedUser.name = "unknown";
		
	}

}
