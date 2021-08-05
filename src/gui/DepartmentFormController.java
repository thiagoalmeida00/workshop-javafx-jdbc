package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constratints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepartmentFormController implements Initializable {

	// declara��o dos componentes da tela
	
	@FXML
	private TextField txtId;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private Label labelErrorName;
	
	@FXML
	private Button btSave;
	
	@FXML
	private Button btCancel;
	
	// declara��o dos m�todos para tratar os bot�es
	
	public void onBtSaveAction() {
		System.out.println("onBtSaveAction");
	}
	
	public void onBtCancelAction() {
		System.out.println("onBtCancelAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
	}
	
	private void initializeNodes() {
		Constratints.setTextFieldInteger(txtId);
		Constratints.setTextFieldMaxLength(txtName, 30);
	}

}
