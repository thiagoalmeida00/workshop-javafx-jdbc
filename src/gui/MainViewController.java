package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;

public class MainViewController implements Initializable {

	// itens de controle de tela para corresponder ao menu da tela
	
	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	// eventHandlers (tratar eventos do Menu)
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView2("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle resourceBundle) {
	}
	
	// carregar uma tela
	// não interromper o processamento durante o try (multithread)
	private synchronized void loadView(String absoluteName) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			// exibir a view dentro da janela principal (pegar uma referencia da cena(main))
			Scene mainScene = Main.getMainScene();
			
			// pegar a referência da VBox
			// getRoot pega o primeiro elemento da View (ScrollPane)
			// casting do getRoot para ScrollPane
			// getContent já é uma referencia para o VBox do ScrollPane
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			// guarda a referencia para o Menu (preservar em todas as interações)
			// primeiro filho do Vbox da janela principal (MainMenu)
			Node mainMenu = mainVBox.getChildren().get(0);
			
			// limpar todos os filhos da MainVBox
			mainVBox.getChildren().clear();
			
			// adicionar no VBox o Main Menu e os filhos do MainVbox
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
		
	}
	
private synchronized void loadView2(String absoluteName) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			// exibir a view dentro da janela principal (pegar uma referencia da cena(main))
			Scene mainScene = Main.getMainScene();
			
			// pegar a referência da VBox
			// getRoot pega o primeiro elemento da View (ScrollPane)
			// casting do getRoot para ScrollPane
			// getContent já é uma referencia para o VBox do ScrollPane
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			// guarda a referencia para o Menu (preservar em todas as interações)
			// primeiro filho do Vbox da janela principal (MainMenu)
			Node mainMenu = mainVBox.getChildren().get(0);
			
			// limpar todos os filhos da MainVBox
			mainVBox.getChildren().clear();
			
			// adicionar no VBox o Main Menu e os filhos do MainVbox
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			// injetar a dependência no controller e chamar para atualizar os dados na tela do tableView
			DepartmentListController controller = loader.getController();
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
			
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
		
	}

}
