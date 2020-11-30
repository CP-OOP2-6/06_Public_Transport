package GUI;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class Window implements EventHandler<ActionEvent> {
	public Stage stage;
	public Scene scene;
	
	public AnchorPane mainPane;

	public List<Button> Buttons;
	public List<TextField> TextFields;
	public List<ComboBox> ComboBoxes;
	public List<PasswordField> PasswordFields;
	public List<Label> Labels;
	public List<ListView> ListViews;
	public List<DatePicker> DatePickers;
	
	
	public static Map<String, String> controls = new HashMap<String, String>() {{
		put("Button", 			"class javafx.scene.control.Button");		
		put("Label", 			"class javafx.scene.control.Label");
		put("TextField", 		"class javafx.scene.control.TextField");
		put("PasswordField", 	"class javafx.scene.control.PasswordField");
		put("ComboBox", 		"class javafx.scene.control.ComboBox");
		put("ListView", 		"class javafx.scene.control.ListView");
		put("DatePicker", 		"class javafx.scene.control.DatePicker");
		put("AnchorPane", 		"class javafx.scene.layout.AnchorPane");
	}};

	public Window(String fxmlFilePath, String windowName) throws IOException {

		this.stage = new Stage();

		Parent root = FXMLLoader.load(getClass().getResource(fxmlFilePath));
		this.InitLists(root);
		
		if(this.mainPane == null) {
			this.scene = new Scene(root, 500, 500);
			this.stage.setTitle("No main_ ID!");
		} else {
			this.scene = new Scene(root, this.mainPane.getPrefWidth(), this.mainPane.getPrefHeight());
			this.stage.setTitle(this.mainPane.getId());
		}
		this.stage.setScene(this.scene);
	}

	public void handle(ActionEvent event) {
	}

	public void Show() {
		this.stage.show();
	}
	
	public void Hide() {
		this.stage.hide();
	}

	public void Close() {
		this.stage.close();
	}

	public Button GetButton(String idName) {
		Optional<Button> result = this.Buttons.stream().filter(p -> p.getId().equals(idName)).findAny();
		if (result.isPresent()) 
			return result.get();
		return null;
	}

	public Label GetLabel(String labelID) {
		for (Label label : this.Labels)
			if (label.getId().equals(labelID))
				return label;
		return null;
	}

	public TextField GetTextField(String textFieldID) {
		Optional<TextField> result = this.TextFields.stream().filter(p -> p.getId().equals(textFieldID)).findAny();
		if (result.isPresent())
			return result.get();
		return null;
	}

	public ComboBox GetComboBox(String comboBoxID) {
		Optional<ComboBox> result = this.ComboBoxes.stream().filter(p -> p.getId().equals(comboBoxID)).findAny();
		if (result.isPresent())
			return result.get();
		return null;
	}

	public ListView GetTableView(String listViewID) {
		Optional<ListView> result = this.ListViews.stream().filter(p -> p.getId().equals(listViewID)).findAny();
		if (result.isPresent())
			return result.get();
		return null;
	}

	public PasswordField GetPasswordField(String passwordFieldID) {
		Optional<PasswordField> result = this.PasswordFields.stream().filter(p -> p.getId().equals(passwordFieldID))
				.findAny();
		if (result.isPresent())
			return result.get();
		return null;
	}

	public DatePicker GetDatePicker(String datePickerID) {
		Optional<DatePicker> result = this.DatePickers.stream().filter(p -> p.getId().equals(datePickerID)).findAny();
		if (result.isPresent())
			return result.get();
		return null;
	}

	public String GetStringFromDate(Date date) {
		String result = date.toString();
		return result;
	}

	private void InitLists(Parent root) {
		this.Buttons = new ArrayList<Button>();
		this.TextFields = new ArrayList<TextField>();
		this.ComboBoxes = new ArrayList<ComboBox>();
		this.PasswordFields = new ArrayList<PasswordField>();
		this.Labels = new ArrayList<Label>();
		this.ListViews = new ArrayList<ListView>();
		this.DatePickers = new ArrayList<DatePicker>();

		ArrayList<Node> nodes = getAllNodes(root);
		for (Node node : nodes) {
			if(node.getClass().toString().equals(Window.controls.get("Button"))) {
				this.Buttons.add((Button) node);
			} else if(node.getClass().toString().equals(Window.controls.get("Label"))) {
				this.Labels.add((Label) node);
			} else if(node.getClass().toString().equals(Window.controls.get("TextField"))) {
				this.TextFields.add((TextField) node);
			} else if(node.getClass().toString().equals(Window.controls.get("PasswordField"))) {
				this.PasswordFields.add((PasswordField) node);
			} else if(node.getClass().toString().equals(Window.controls.get("ComboBox"))) {
				this.ComboBoxes.add((ComboBox) node);
			} else if(node.getClass().toString().equals(Window.controls.get("ListView"))) {
				this.ListViews.add((ListView) node);
			} else if(node.getClass().toString().equals(Window.controls.get("DatePicker"))) {
				this.DatePickers.add((DatePicker) node);
			} else if(node.getClass().toString().equals(Window.controls.get("AnchorPane"))) {
				if(node.getId() != null && node.getId().contains("main_")) {
					node.setId(node.getId().substring(5));
					this.mainPane = (AnchorPane) node;
				}
			}
		}

		for (Button button : this.Buttons) {
			button.setOnAction(this);
			button.requestFocus();
		}
	}

	protected static Boolean IsEventButtonCall(ActionEvent event) {
		if (event.getSource().getClass().toString() == "class javafx.scene.control.Button") {
			return true;
		}

		return false;
	}

	private static ArrayList<Node> getAllNodes(Parent root) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		addAllDescendents(root, nodes);
		return nodes;
	}

	private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
		for (Node node : parent.getChildrenUnmodifiable()) {
			nodes.add(node);
			if (node instanceof Parent)
				addAllDescendents((Parent) node, nodes);
		}
	}
}
