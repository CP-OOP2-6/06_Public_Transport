package Managers;


import GUI.LoginWindow;

public class Login {
	
	public static void login(LoginWindow window) {
		if(!main.LoggedUser.loggedin && Managers.Password.verify(Database.Login.getPassword(window.GetTextField("tf_username").getText()), 
				window.GetPasswordField("pf_password").getText())) {
			Database.InitializeUser.initializeUser(window.GetTextField("tf_username").getText());
			window.stage.setTitle(String.format("%s%s", window.stage.getTitle(), main.LoggedUser.getTitle()));
			System.out.print(main.LoggedUser.getTitle());
		}
	}

}
