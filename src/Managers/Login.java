package Managers;

import java.io.IOException;

import GUI.AdminWindow;
import GUI.LoginWindow;
import GUI.MessageBox;
import GUI.Window;

public class Login {
	
	public static void login(LoginWindow window) {
		if(!main.LoggedUser.loggedin && Managers.Password.verify(Database.Login.getPassword(window.GetTextField("tf_username").getText()), 
				window.GetPasswordField("pf_password").getText())) {
			Database.InitializeUser.initializeUser(window.GetTextField("tf_username").getText());
			window.stage.setTitle(String.format("%s%s", window.stage.getTitle(), main.LoggedUser.getTitle()));

			try {
				window.Hide();
				if(main.LoggedUser.hasPermission(Consts.Permissions.perm.isAdmin)) {
					Window wnd = new AdminWindow();
					wnd.Show();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			MessageBox.show("Invalid credentials", "Login Failure");
		}
	}

}
