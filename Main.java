import classe.Product;
import classe.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.*;
import model.*;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

public class Main extends Application {

    private Stage window;
    Scene connectionForm, mainWindow, productScene, addProductScene;
    User user;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.window = primaryStage;
        this.window.setTitle("GSB Wiki");
        this.window.getIcons().add(new Image("util/g_logo.png"));

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

        // Passe à la scene "détail" avec l'ID du produit sélectionné
        mainLayout.detailButton.setOnAction(e -> {
            Product selectedProduct = (Product) mainLayout.productTable.getSelectionModel().getSelectedItem();

            if (selectedProduct != null) {
                ProductDao pDao = new ProductDao();
                Product finalProduct = pDao.find(selectedProduct.getIdProduct());

                productLayout.productName.setText(finalProduct.getNameProduct());
                productLayout.productRef.setText(finalProduct.getRefProduct());
                productLayout.price.setText(Double.toString(finalProduct.getPrice()));
                productLayout.molecule.setText(finalProduct.getMolecule());
                productLayout.manufacturer.setText(finalProduct.getManufacturer());
                productLayout.quantity.setText(Integer.toString(finalProduct.getQuantity()));
                productLayout.description.setText(finalProduct.getDescription());

                window.setScene(productScene);
                window.setTitle("Wiki GSB - Détail du produit");
            } else {
                mainLayout.errorMessage.setVisible(true);
            }


        });
        // Rafraîchit les données de la page lorsque l'on retourne sur la page de produits
        mainLayout.addProduct.setOnAction(event -> {
            addFormLayout.productName.clear();
            addFormLayout.productRef.clear();
            addFormLayout.price.clear();
            addFormLayout.molecule.clear();
            addFormLayout.manufacturer.clear();
            addFormLayout.quantity.clear();
            addFormLayout.description.clear();

            addFormLayout.productAddedLabel.setVisible(false);
            addFormLayout.mainGrid.setVisible(true);
            window.setScene(addProductScene);
            window.setTitle(" Wiki GSB - Ajouter un produit");
        });

        // Partie fonctionnelle d'ajout de produits
        addFormLayout.addProduct.setOnAction(event -> {
            String productName =  addFormLayout.productName.getText();
            String productRef = addFormLayout.productRef.getText();
            Double price = Double.parseDouble(addFormLayout.price.getText());
            String molecule = addFormLayout.molecule.getText();
            String manufacturer = addFormLayout.manufacturer.getText();
            int quantity = Integer.parseInt(addFormLayout.quantity.getText());
            String description = addFormLayout.description.getText();
            Product product = new Product(productName, productRef, price, molecule, manufacturer, quantity, description);
            if (product != null) {
                ProductDao productDao = new ProductDao();
                productDao.add(product);

                System.out.println("Produit Ajouté !");
                addFormLayout.mainGrid.setVisible(false);
                addFormLayout.productAddedLabel.setVisible(true);
            } else {
                System.out.println("Remplissez tous les champs requis");
            }
        });

        // Ajout des fonctionnalité des liens de retour
        mainLayout.header.link.setOnAction(event -> {
            resetConnectionScreen();
            window.setScene(connectionForm);
            window.setTitle("Wiki GSB");
        });
        productLayout.header.link.setOnAction(event -> {
            mainLayout.errorMessage.setVisible(false);
            mainLayout.productTable.getItems().clear();
            ProductDao pDao = new ProductDao();
            fetchTableData(mainLayout, pDao);
            window.setScene(mainWindow);
            window.setTitle("Wiki GSB - Accueil");
        });
        addFormLayout.header.link.setOnAction(event -> {
            mainLayout.productTable.getItems().clear();
            ProductDao pDao = new ProductDao();
            fetchTableData(mainLayout, pDao);
            mainLayout.errorMessage.setVisible(false);
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
            mainLayout.colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
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
        try {
            URL iconURL = Main.class.getResource("util/g_logo.png");
            java.awt.Image image = new ImageIcon(iconURL).getImage();
            com.apple.eawt.Application.getApplication().setDockIconImage(image);
        } catch (Exception e) {
            // Won't work on Windows or Linux.
        }
        launch(args);
    }
}

