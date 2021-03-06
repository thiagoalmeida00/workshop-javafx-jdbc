package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

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
import model.services.SellerService;

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
		loadView("/gui/SellerList.fxml", (SellerListController controller) -> {
			controller.setSellerService(new SellerService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		// passar uma express?o lambda como par?metro para inicializar o controller
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> {
			controller.setDepartmentService(new DepartmentService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle resourceBundle) {
	}
	
	// carregar uma tela
	// n?o interromper o processamento durante o try (multithread)
	// fun??o gen?rica do tipo T (Generics)
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAciont) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			// exibir a view dentro da janela principal (pegar uma referencia da cena(main))
			Scene mainScene = Main.getMainScene();
			
			// pegar a refer?ncia da VBox
			// getRoot pega o primeiro elemento da View (ScrollPane)
			// casting do getRoot para ScrollPane
			// getContent j? ? uma referencia para o VBox do ScrollPane
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			// guarda a referencia para o Menu (preservar em todas as intera??es)
			// primeiro filho do Vbox da janela principal (MainMenu)
			Node mainMenu = mainVBox.getChildren().get(0);
			
			// limpar todos os filhos da MainVBox
			mainVBox.getChildren().clear();
			
			// adicionar no VBox o Main Menu e os filhos do MainVbox
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			// para ativar a fun??o passada no par?mtro (Consumer Initializing)
			// o getController vai retornar o controlador do tipo que chamar na fun??o (..departmentListController..)
			T controller = loader.getController();
			
			// para chamar a fun??o Consumer<T>
			initializingAciont.accept(controller);
			
		}
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
		
	}

}
