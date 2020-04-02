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
    Scene connectionForm, mainWindow, productScene, addProductScene, adminScene;
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
        setStylesheet(connectionForm);

        MainWindow mainLayout = new MainWindow();
        mainWindow = new Scene(mainLayout, 600, 400);
        setStylesheet(mainWindow);

        ProductDetailScene productLayout = new ProductDetailScene();
        productScene = new Scene(productLayout, 600,400);
        setStylesheet(productScene);

        AddProductForm addFormLayout = new AddProductForm();
        addProductScene = new Scene(addFormLayout, 600, 400);
        setStylesheet(addProductScene);

        AdminPage adminLayout = new AdminPage();
        adminScene = new Scene(adminLayout, 600,400);
        setStylesheet(adminScene);

        // ActionListener_________________
        // Cherche les données des produits en base et les affiche dans la scene suivante lorsque la connection est validée
        connLayout.btnConnection.setOnAction(e -> {
            ProductDao pDao = new ProductDao();

            String login = connLayout.loginField.getText();
            String password = connLayout.passField.getText();

            try {
                if (!login.equals("") && checkLoginData(login, password)) {
                    resetMainScreen(mainLayout);
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

        // Supprime un médicament
        mainLayout.deleteProduct.setOnAction(event -> {
            Product selectedProduct = (Product) mainLayout.productTable.getSelectionModel().getSelectedItem();

            if (selectedProduct != null) {
                ProductDao pDao = new ProductDao();
                pDao.delete(selectedProduct);

                productFetchTableData(mainLayout, pDao);
                resetMainScreen(mainLayout);
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
        // bouton annuler de l'écran d'ajout de produit
        addFormLayout.btnCancel.setOnAction(event -> {
            resetMainScreen(mainLayout);
        });

        // Page d'adminisatration des utilisateur
        // Supprimer un utilisateur
        adminLayout.deleteButton.setOnAction(event -> {
            User selectedUser = (User) adminLayout.userTable.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                UserDao userDao = new UserDao();
                userDao.delete(selectedUser);
                resetAdminScreen(adminLayout);
            } else {
                adminLayout.errorMessage.setVisible(true);
            }
        });

        // Ajout des fonctionnalité des liens de retour
        mainLayout.header.link.setOnAction(event -> {
            resetConnectionScreen();
            window.setScene(connectionForm);
            window.setTitle("Wiki GSB");
        });
        productLayout.header.link.setOnAction(event -> {
            resetMainScreen(mainLayout);
        });
        addFormLayout.header.link.setOnAction(event -> {
            resetMainScreen(mainLayout);
        });
        mainLayout.adminLink.setOnAction(event -> {
            resetAdminScreen(adminLayout);
        });
        adminLayout.header.link.setOnAction(event -> {
            resetMainScreen(mainLayout);
        });

        // fin de la fonction init
        window.setScene(connectionForm);
        window.setTitle("Wiki GSB");
        window.setResizable(false);
        window.show();
    }

    private void productFetchTableData(MainWindow mainLayout, ProductDao pDao) {
        for (Product p : pDao.fetchAllProduct()) {
            mainLayout.productTable.getItems().add(p);
            mainLayout.colId.setCellValueFactory(new PropertyValueFactory<>("idProduct"));
            mainLayout.colName.setCellValueFactory(new PropertyValueFactory<>("nameProduct"));
            mainLayout.colRef.setCellValueFactory(new PropertyValueFactory<>("refProduct"));
            mainLayout.colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            mainLayout.colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        }
    }

    private void userFetchTableData(AdminPage adminLayout) {
        UserDao uDao = new UserDao();
        for (User u : uDao.fetchAllUser()) {
            adminLayout.userTable.getItems().add(u);
            adminLayout.colId.setCellValueFactory(new PropertyValueFactory<>("idUser"));
            adminLayout.colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
            adminLayout.colName.setCellValueFactory(new PropertyValueFactory<>("nom"));
            adminLayout.colSurname.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            adminLayout.colAdmin.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
        }
    }

    private boolean checkLoginData(String login, String password) throws NoSuchAlgorithmException {
        AuthenticationModel auth = new AuthenticationModel();

        user = auth.checkAuth(login, password);

        String bdLogin = user.getLogin();
        String bdPassword = user.getPassword();
        System.out.println("checkLoginData : " + bdPassword);
        password = auth.stringToHash(password);

        if (login.equals(bdLogin) && password.equals(bdPassword)) {
            return true;
        }
        return false;
    }

    private void resetConnectionScreen() {
        try {
            initRootLayout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void resetMainScreen(MainWindow mainLayout) {
        mainLayout.productTable.getItems().clear();
        ProductDao pDao = new ProductDao();
        productFetchTableData(mainLayout, pDao);
        mainLayout.errorMessage.setVisible(false);
        window.setScene(mainWindow); 
        window.setTitle("Wiki GSB - Accueil");
    }

    private void resetAdminScreen(AdminPage adminLayout) {
        adminLayout.userTable.getItems().clear();
        userFetchTableData(adminLayout);
        adminLayout.errorMessage.setVisible(false);
        window.setScene(adminScene);
        window.setTitle("Wiki GSB - Administration");
    }

    private void setStylesheet(Scene scene) {
        scene.getStylesheets().add("util/stylesheet.css");
    }

    public static void main(String[] args) {
        try {
            URL iconURL = Main.class.getResource("util/g_logo.png");
            java.awt.Image image = new ImageIcon(iconURL).getImage();
            // com.apple.eawt.Application.getApplication().setDockIconImage(image);
        } catch (Exception e) {
            // Won't work on Windows or Linux.
        }
        launch(args);
    }
}

