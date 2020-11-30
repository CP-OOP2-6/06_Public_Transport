package Database;

import java.util.List;
import java.util.Map;

public class Login {

	public static String getPassword(String username) {		
		Map<String, List<String>> result = Connector.get().getQueryResult(String.format("SELECT password FROM Account WHERE username = '%s' LIMIT 1;", username));
		System.out.println(result);
		if (result.containsKey("password") && result.get("password").size() > 0)
			return result.get("password").get(0);
		return null;
	}
	
}
