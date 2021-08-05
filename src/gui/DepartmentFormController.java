package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constratints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable {

	// dependencia para o departamento
	
	private Department entity;
	
	// declaração dos componentes da tela
	
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
	
	public void setDepartment(Department entity) {
		this.entity = entity;
	}
	
	// declaração dos métodos para tratar os botões
	
	public void onBtSaveAction() {
		System.out.println("onBtSaveAction");
	}
	
	public void onBtCancelAction() {
		System.out.println("onBtCancelAction");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		initializeNodes();
	}
	
	private void initializeNodes() {
		Constratints.setTextFieldInteger(txtId);
		Constratints.setTextFieldMaxLength(txtName, 30);
	}

	public void updateFormData() {
		// programação defensiva
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}
	
}
