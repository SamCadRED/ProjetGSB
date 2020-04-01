import classe.Product;
import classe.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import view.*;
import model.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main extends Application {

    private Stage window;
    Scene connectionForm, mainWindow, productScene;
    User user;
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
        productScene = new Scene(productLayout, 600,400);

        // ActionListener_________________
        // Cherche les données des produits en base et les affiche dans la scene suivante lorsque la connection est validée
        connLayout.btnConnection.setOnAction(e -> {
            ProductDao pDao = new ProductDao();

            String login = connLayout.loginField.getText();
            String password = connLayout.passField.getText();

            try {
                if (checkLoginData(login, password)) {
                    System.out.println(connLayout.loginField.getText());
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
                    System.out.println("Login & Password Ok");
                } else {
                    connLayout.errorConnLabel.setVisible(true);
                    System.out.println("Wrong Login and Password");
                }
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }
        });

        // Bouton annuler (efface les données entrées et réinitilaise visuellement la page
        connLayout.btnCancel.setOnAction(event -> {
            connLayout.errorConnLabel.setVisible(false);
            connLayout.loginField.clear();
            connLayout.passField.clear();
        });

        // Passe à la scene suivante avec l'ID
        mainLayout.detailButton.setOnAction(e -> {
            Product selectedProduct = (Product) mainLayout.productTable.getSelectionModel().getSelectedItem();

            ProductDao pDao = new ProductDao();
            Product finalProduct = pDao.find(selectedProduct.getIdProduct());

            productLayout.productName.setText(finalProduct.getProductName());
            productLayout.productRef.setText(finalProduct.getProductRef());
            productLayout.price.setText("");
            productLayout.molecule.setText(finalProduct.getMolecule());
            productLayout.lab.setText("GSB Laboratory");
            productLayout.risk.setText("");
            productLayout.description.setText("");
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

    public boolean checkLoginData(String login, String password) throws NoSuchAlgorithmException {
        AuthenticationModel auth = new AuthenticationModel();

        User user = auth.checkAuth(login, password);

        String bdLogin = user.getLogin();
        String bdPassword = user.getPassword();

        if (login.equals(bdLogin) && password.equals(bdPassword)) {
            return true;
        } else if (user.equals(null)) {
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

