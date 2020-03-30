import classe.Product;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.*;
import model.*;

import java.io.IOException;

public class Main extends Application {

    private Stage window;
    Scene connectionForm, mainWindow, productScene;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.window = primaryStage;
        this.window.setTitle("GSB Wiki");

        initRootLayout();
    }

    public void initRootLayout() throws IOException {
        rootLayout = FXMLLoader.load(getClass().getResource("view/RootLayout.fxml"));

        ConnectionScene connLayout = new ConnectionScene();
        connectionForm = new Scene(connLayout);

        MainWindow mainLayout = new MainWindow();
        mainWindow = new Scene(mainLayout, 600, 400);

        ProductDetailScene productLayout = new ProductDetailScene();
        productScene = new Scene(productLayout);

        // ActionListener
        connLayout.btnConnection.setOnAction(e -> {
            ProductDao pDao = new ProductDao();

            mainLayout.productTable.getItems().clear();
            for (Product p : pDao.fetchAllProduct()) {
                mainLayout.productTable.getItems().add(p);
                mainLayout.colId.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
                mainLayout.colName.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
                mainLayout.colRef.setCellValueFactory(new PropertyValueFactory<>("refProduct"));
                mainLayout.colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                mainLayout.colRisk.setCellValueFactory(new PropertyValueFactory<>("risk"));
            }
            window.setScene(mainWindow);
            window.setTitle("WikiGSB");
        });

        mainLayout.detailButton.setOnAction(e -> {
            window.setScene(productScene);
            window.setTitle("Détail du produit");
        });

        // fin de la fonction init
        window.setScene(connectionForm);
        window.setResizable(false);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

