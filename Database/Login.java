package Database;

import java.util.List;
import java.util.Map;

public class Login {

	public static String getPassword(String username) {		
		Map<String, List<String>> result = Connector.get().getQueryResult(String.format("SELECT Password FROM Account WHERE Username = '%s' LIMIT 1;", username));
		if (result.containsKey("Password") && result.get("Password").size() > 0)
			return result.get("Password").get(0);
		return null;
	}
	
}
