package GUI;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import GUI.Window;

public class AdminWindow extends Window {

	public AdminWindow() throws IOException {
		super("AdminWindow.fxml", String.format("Admin Window %s", main.LoggedUser.getTitle()));
		Managers.Admin.init(this);
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getSource().getClass().toString().equals(Window.controls.get("Button"))) {
			if (((Button) (event.getSource())).getId().equals("btn_companyCreate")) {
				Managers.Admin.createCompany(this);
				Managers.Admin.initOfficeCompanies(this);
			}
			else if (((Button) (event.getSource())).getId().equals("btn_officeCreate")) {
				Managers.Admin.createOffice(this);
			}
		}
	}
}
