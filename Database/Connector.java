package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Connector {
		
	private static String	databaseType	= "mysql";
	private static String	host			= "localhost";
	private static String 	port			= "3306";
	private static String	databaseName	= "Public_Transport";
	private static String 	username 		= "root";
	private static String 	password 		= "";
	
	private final static boolean printQueries = true;
	
	private Connection 	conn	= null;
	
	public Connector(String databaseType, String host, String port, String databaseName, String username, String password) throws SQLException {
		this.conn = DriverManager.getConnection(String.format("jdbc:%s://%s:%s/%s", databaseType, host, port, databaseName), username, password);
	}
	
	/*
	 * 	[0]	->	databaseType
	 *  [1] ->  host
	 *  [2] ->  port
	 *  [3] ->  databaseName
	 * 	[4]	->	username
	 * 	[5]	->	password
	 */
	public static Connector get(String... strings) {
		if (strings.length == 6) {
			databaseType	= strings[0];
			host			= strings[1];
			port			= strings[2];
			databaseName	= strings[3];
			username 		= strings[4];
			password 		= strings[5];
		}
		try {
			return new Connector(databaseType, host, port, databaseName, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public HashMap<String, List<String>> getQueryResult(String SQL) {
		if(printQueries)
			System.out.println(String.format("[ SQL ] %s", SQL));
		
		try {
			PreparedStatement st = this.conn.prepareStatement(SQL);
			return this.processResult(st.executeQuery(SQL));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new HashMap<String, List<String>>(); 
	}
		
	private HashMap<String, List<String>> processResult(ResultSet result) {
		HashMap<String, List<String>> endResult = new HashMap<String, List<String>>();

		try {
			ResultSetMetaData rsmd;
			rsmd = result.getMetaData();
		
			while (result.next())
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					if(endResult.containsKey(rsmd.getColumnName(i))) {
						endResult.put(
								rsmd.getColumnName(i), 
								Stream.concat(
										endResult.get(rsmd.getColumnName(i)).stream(), 
										Stream.of(result.getString(i))
								).collect(Collectors.toList())
						);	
					}
					else
						endResult.put(rsmd.getColumnName(i), Arrays.asList(result.getString(i)));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return endResult;
	}	
	
}
