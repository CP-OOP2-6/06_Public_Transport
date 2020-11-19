package main;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import GUI.LoginWindow;
import GUI.Window;
import javafx.application.Application;
import javafx.stage.Stage;

public class entry extends Application {

	public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
		// TODO Auto-generated method stub
		Map<String, List<String>> result = Database.Connector.get().getQueryResult("SELECT * FROM Account");

		for (Entry<String, List<String>> entry : result.entrySet()) {
			System.out.println(entry.getKey());
			for (String val : entry.getValue())
				System.out.println(String.format("\t%s", val));
		}
		
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Window window = new LoginWindow(arg0);
		window.Show();
	}

}
