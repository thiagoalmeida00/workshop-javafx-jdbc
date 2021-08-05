package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable {
	
	// referencias para os componentes da tela do DepartmentList
	
	private DepartmentService service;
	
	@FXML
	private TableView<Department> tableViewDepartment;
	
	@FXML
	private TableColumn<Department, Integer> tableColumnId;
	
	@FXML
	private TableColumn<Department, String> tableColumnName;
	
	@FXML
	private Button btNew;
	
	// carregar os departamentos na obsList
	private ObservableList<Department> obsList;
	
	// tratamento de eventos
	
	@FXML
	public void onBtNewAction() {
		System.out.println("OnBtNewAction");
	}
	
	// metodo set para injetar depend�ncia por outro lugar (evitar acoplamento forte)
	// princ�pio SOLID
	public void setDepartmentService(DepartmentService service) {
		this.service = service;
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
		
		// tela ir at� o fim no layout
		// Window pega a referencia para a janela (para atribuir ao Stage faz downcast)
		Stage stage = (Stage) Main.getMainScene().getWindow();
		
		// tableViewDepartment acompanhar a altura da janela
		tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
		
	}
	
	// acessar o servi�o, carregar os departamentos e jogar na obsList
	public void updateTableView() {
		if (service == null) {
			// porque a inje��o de depend�ncia est� manual -> lan�ar exce��o
			throw new IllegalStateException("Service was null");
		}
		
		// recuperar os departamentos mockados
		List<Department> list = service.findAll();
		
		// instancia o obsList pegando os dados originais da list
		obsList = FXCollections.observableArrayList(list);
		
		// carregar os itens na tableView e mostrar na tela
		tableViewDepartment.setItems(obsList);
	}
	

}
