package GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MessageBox {

    public static void show(String infoMessage, String titleBar)
    {
    	show(infoMessage, titleBar, null);
    }

    public static void show(String message, String title, String header)
    {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
