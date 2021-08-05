package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable {

	// referencias para os componentes da tela do DepartmentList
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	// tratamento de eventos
	
	@FXML
	public void onBtNewAction() {
		System.out.println("OnBtNewAction");
	}
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// inicializar algum componente da tela
		initializeNodes();
	}

	private void initializeNodes() {
		// iniciar o comportamento das colunas da tabela
		tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		// tela ir até o fim no layout
		// Window pega a referencia para a janela (para atribuir ao Stage faz downcast)
		Stage stage = (Stage) Main.getMainScene().getWindow();
		
		// tableViewDepartment acompanhar a altura da janela
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
		
	}

}
