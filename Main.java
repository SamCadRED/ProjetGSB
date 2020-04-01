import classe.Product;
import classe.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import view.*;
import model.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main extends Application {

    private Stage window;
    Scene connectionForm, mainWindow, productScene, addProductScene;
    User user;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.window = primaryStage;
        this.window.setTitle("GSB Wiki");

        initRootLayout();
    }

    public void initRootLayout() throws IOException {
        ConnectionScene connLayout = new ConnectionScene();
        connectionForm = new Scene(connLayout);

        MainWindow mainLayout = new MainWindow();
        mainWindow = new Scene(mainLayout, 600, 400);

        ProductDetailScene productLayout = new ProductDetailScene();
        productScene = new Scene(productLayout, 600,400);

        AddProductForm addFormLayout = new AddProductForm();
        addProductScene = new Scene(addFormLayout, 600, 400);

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

                    fetchTableData(mainLayout, pDao);

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
            resetConnectionScreen();
        });

        // Passe à la scene suivante "détail" avec l'ID
        mainLayout.detailButton.setOnAction(e -> {
            Product selectedProduct = (Product) mainLayout.productTable.getSelectionModel().getSelectedItem();

            if (selectedProduct != null) {
                ProductDao pDao = new ProductDao();
                Product finalProduct = pDao.find(selectedProduct.getIdProduct());

                productLayout.productName.setText(finalProduct.getNameProduct());
                productLayout.productRef.setText(finalProduct.getRefProduct());
                productLayout.price.setText("");//TODO make it a string
                productLayout.molecule.setText(finalProduct.getMolecule());
                productLayout.lab.setText(finalProduct.getManufacturer());
                productLayout.risk.setText("");//TODO make it a string
                productLayout.description.setText(finalProduct.getDescription());

                window.setScene(productScene);
                window.setTitle("Wiki GSB - Détail du produit");
            } else {
                mainLayout.errorMessage.setVisible(true);
            }


        });
        mainLayout.addProduct.setOnAction(event -> {
            window.setScene(addProductScene);
            window.setTitle(" Wiki GSB - Ajouter un produit");
        });

        // Partie fonctionnelle d'ajout de produits
        addFormLayout.addProduct.setOnAction(event -> {
            System.out.println("Produit Ajouté !");
            addFormLayout.mainGrid.setVisible(false);
            addFormLayout.productAddedLabel.setVisible(true);
        });

        // Ajout des fonctionnalité des liens de retour
        mainLayout.header.link.setOnAction(event -> {
            resetConnectionScreen();
            window.setScene(connectionForm);
            window.setTitle("Wiki GSB");
        });
        productLayout.header.link.setOnAction(event -> {
            window.setScene(mainWindow);
            window.setTitle("Wiki GSB - Accueil");
        });
        addFormLayout.header.link.setOnAction(event -> {
            window.setScene(mainWindow);
            window.setTitle("Wiki GSB - Accueil");
        });

        // fin de la fonction init
        window.setScene(connectionForm);
        window.setTitle("Wiki GSB");
        window.setResizable(false);
        window.show();
    }

    public void fetchTableData(MainWindow mainLayout, ProductDao pDao) {
        for (Product p : pDao.fetchAllProduct()) {
            mainLayout.productTable.getItems().add(p);
            mainLayout.colId.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
            mainLayout.colName.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
            mainLayout.colRef.setCellValueFactory(new PropertyValueFactory<>("refProduct"));
            mainLayout.colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            mainLayout.colRisk.setCellValueFactory(new PropertyValueFactory<>("risk"));
        }
    }

    public boolean checkLoginData(String login, String password) throws NoSuchAlgorithmException {
        AuthenticationModel auth = new AuthenticationModel();

        user = auth.checkAuth(login, password);

        String bdLogin = user.getLogin();
        String bdPassword = user.getPassword();

        if (login.equals(bdLogin) && password.equals(bdPassword)) {
            return true;
        }
        return false;
    }

    public void resetConnectionScreen() {
        try {
            initRootLayout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

