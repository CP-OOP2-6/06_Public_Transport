package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import GUI.Window;

public class LoginWindow extends Window {

	public LoginWindow(Stage stage) throws IOException {
		super(stage, "LoginWindow.fxml", "LoginWindow");
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource().getClass().toString().equals(Window.controls.get("Button")) && 
				((Button) (event.getSource())).getId().equals("btn_login")) {
			Managers.Login.login(this);
		}
	}
}
