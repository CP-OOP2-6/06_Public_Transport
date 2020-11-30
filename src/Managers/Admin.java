package Managers;

import java.util.ArrayList;
import java.util.List;

import GUI.AdminWindow;
import GUI.MessageBox;

public class Admin {
	private static List<String> companyTypes = new ArrayList<String>() {{
		add("Distributor");
		add("Travel Company");
	}};
	
	public static void init(AdminWindow window) {
		initCreateCompany(window);
		initOfficeCompanies(window);
	}
		
	public static void initCreateCompany(AdminWindow window) {
		for (String item : Admin.companyTypes)
			window.GetComboBox("cb_companyType").getItems().add(item);
	}
	
	public static void initOfficeCompanies(AdminWindow window) {
		window.GetComboBox("cb_officeCompany").getItems().clear();
		for (String item : Database.Admin.getOfficeCompanies())
			window.GetComboBox("cb_officeCompany").getItems().add(item);
	}
	
	public static void createCompany(AdminWindow window) {
		boolean failuresFound = false;
		
		if (window.GetTextField("tf_companyName").getText().isEmpty() || window.GetTextField("tf_companyEIN").getText().isEmpty()) {
			MessageBox.show("Please fill all boxes in the company group.", "Invalid input");
			failuresFound = true;
		}
		if (window.GetComboBox("cb_companyType").getValue() == null) {
			MessageBox.show("Please select company type.", "Invalid input");
			failuresFound = true;
		}
		if (Database.Admin.exists("Company", "name", window.GetTextField("tf_companyName").getText())) {
			MessageBox.show("Company name already exists.", "Invalid input");
			failuresFound = true;
		}
		if (Database.Admin.exists("Company", "EIN", window.GetTextField("tf_companyEIN").getText())) {
			MessageBox.show("Company EIN already exists.", "Invalid input");
			failuresFound = true;
		}
		
		if (!failuresFound)
			Database.Admin.createCompany(
					window.GetTextField("tf_companyName").getText(),
					window.GetTextField("tf_companyEIN").getText(),
					window.GetComboBox("cb_companyType").getValue()
			);
	}
	
	public static void createOffice(AdminWindow window) {
		boolean failuresFound = false;
		
		if(window.GetTextField("tf_officeAddress").getText().isEmpty() || 
				window.GetTextField("tf_officePhone").getText().isEmpty() || 
				window.GetTextField("tf_officeEmail").getText().isEmpty()) {
			MessageBox.show("Please fill all boxes in the office group.", "Invalid input");
			failuresFound = true;
		}
		if(window.GetComboBox("cb_officeCompany").getValue() == null) {
			MessageBox.show("Please select company.", "Invalid input");
			failuresFound = true;
		}
		
		if (!failuresFound)
			Database.Admin.createOffice(
					window.GetTextField("tf_officeAddress").getText(),
					window.GetTextField("tf_officePhone").getText(),
					window.GetTextField("tf_officeEmail").getText(),
					window.GetComboBox("cb_officeCompany").getValue()
			);
	}

}
