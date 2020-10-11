import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class MySQLConnection {

	
	public static Connection makeSQLConnection() {
		
		String url = "jdbc:mysql://localhost:3306/Public_Transport";
		String username = "root";
		String password = "";
		System.out.println("Connecting database...");
	

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			System.out.println("Database connected!"); 
			return connection;
		} catch (SQLException e) {
	    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	public static Map<String, List<String>> executeQuery(String query) throws SQLException {
	
		String url = "jdbc:mysql://localhost:3306/Public_Transport";
		String username = "root";
		String password = "";
		Connection connection = DriverManager.getConnection(url, username, password);
		System.out.println("Database connected!"); 
		Map<String, List<String>> map = new HashMap<>();
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(query);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnCount = rsmd.getColumnCount();
		List<String> columns = new ArrayList<String>();
		
			for (int i = 1; i < columnCount; i++) {
				String columnName = rsmd.getColumnName(i);
				columns.add(columnName);
			}
			 for (String columnName : columns) {
					List<String> values = new ArrayList<>();
					try {
			            while (rs.next()) {
			            	values.add(rs.getString(columnName));
			            }
			        } catch (SQLException e) {
			            e.printStackTrace();
			        }
					map.put(columnName, values);		
			 }	
		return map;
	}
}



