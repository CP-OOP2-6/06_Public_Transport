package main;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import GUI.LoginWindow;
import GUI.Window;
import javafx.application.Application;
import javafx.stage.Stage;

public class entry extends Application {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
		// TODO Auto-generated method stub	
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Window window = new LoginWindow();
		window.Show();
	}

}
