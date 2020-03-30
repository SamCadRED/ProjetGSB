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

        // Cherche les données des produits en base et les affiche dans la scene suivante lorsque la connection est validée
        connLayout.btnConnection.setOnAction(e -> {
            ProductDao pDao = new ProductDao();

            // String login =

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
            window.setTitle("Wiki GSB - Accueil");
        });

        // Bouton annuler (efface les données entrées et réinitilaise visuellement la page
        connLayout.btnCancel.setOnAction(event -> {
            connLayout.errorConnLabel.setVisible(false);
            connLayout.loginField.clear();
            connLayout.passField.clear();
        });

        // Stock l'Id de la ligne sélectionnée dans une variable
        Object selectedProductId = mainLayout.productTable.getSelectionModel().getSelectedItem();

        // Passe à la scene suivante avec l'ID
        mainLayout.detailButton.setOnAction(e -> {
            System.out.println(selectedProductId);
            productLayout.productName.setText("");
            window.setScene(productScene);
            window.setTitle("Détail du produit");
        });

        // Ajout des fonctionnalité du lien retour et quitter
        mainLayout.header.link.setOnAction(event -> {
            window.setScene(connectionForm);
            window.setTitle("Wiki GSB");
        });
        productLayout.header.link.setOnAction(event -> {
            window.setScene(mainWindow);
            window.setTitle("Wiki GSB - Accueil");
        });

        // fin de la fonction init
        window.setScene(connectionForm);
        window.setTitle("Wiki GSB");
        window.setResizable(false);
        window.show();
    }

    public boolean checkLoginData(String login, String password) {
        System.out.println(login + password);
        if (login == "sam" && password == "sam") {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

