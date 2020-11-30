package Database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Admin {
	
	public static List<String> getOfficeCompanies() {
		Map<String, List<String>> result = Connector.get().getQueryResult("SELECT Name, EIN FROM Company");
		return result.containsKey("name") ? result.get("name") : new ArrayList<String>();
	}
	
	public static boolean exists(String table, String column, String value) {
		Map<String, List<String>> result = Connector.get().getQueryResult(String.format("SELECT %s FROM %s \r\n" +
				"WHERE %s = '%s'", column, table, column, value));
		
		if(result.containsKey(column))
			return result.get(column).size() > 0;
		
		return false;
	}
	
	public static void createCompany(String name, String EIN, Object type) {
		Connector.get().executeUpdate(String.format("INSERT INTO Company (name, EIN) VALUES \r\n" + 
				"('%s', '%s')", name, EIN));
		if(type.toString().equals("Distributor"))
			Connector.get().executeUpdate(String.format("INSERT INTO Distributor (company) VALUES \r\n" + 
					"((SELECT ID FROM Company WHERE name = '%s' AND EIN = '%s'))", name, EIN));
		else if(type.toString().equals("Travel Company"))
			Connector.get().executeUpdate(String.format("INSERT INTO Organisator (company) VALUES \r\n" + 
					"((SELECT ID FROM Company WHERE name = '%s' AND EIN = '%s'))", name, EIN));
	}
	
	public static void createOffice(String address, String phone, String email, Object company) {		
		Connector.get().executeUpdate(String.format("INSERT INTO Office (address, phoneNumber, email, company) VALUES \r\n" + 
				"('%s', '%s', '%s', (SELECT ID FROM Company WHERE name = '%s' LIMIT 1))", address, phone, email, company.toString()));
	}

}
