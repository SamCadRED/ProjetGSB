import classe.Product;
import classe.User;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.*;
import model.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main extends Application {

    private Stage window;
    Scene connectionForm, mainWindow, productScene, addProductScene, adminScene, addUserScene;
    User user;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.window = primaryStage;
        this.window.setTitle("GSB Wiki");
        this.window.getIcons().add(new Image("util/g_logo.png"));

        initRootLayout();
    }

    public void initRootLayout() throws IOException {

        // Initialisation des diférents écrans (scenes) de l'application
        ConnectionScene connLayout = new ConnectionScene();
        connectionForm = new Scene(connLayout);
        setStylesheet(connectionForm);

        MainWindow mainLayout = new MainWindow();
        mainWindow = new Scene(mainLayout, 600, 400);
        setStylesheet(mainWindow);

        ProductDetailScene productLayout = new ProductDetailScene();
        productScene = new Scene(productLayout, 600,400);
        setStylesheet(productScene);

        AddProductForm addProductLayout = new AddProductForm();
        addProductScene = new Scene(addProductLayout, 600, 400);
        setStylesheet(addProductScene);

        AdminPage adminLayout = new AdminPage();
        adminScene = new Scene(adminLayout, 600,400);
        setStylesheet(adminScene);

        AddUserForm addUserLayout = new AddUserForm();
        addUserScene = new Scene(addUserLayout, 400, 400);
        setStylesheet(addUserScene);

        // ActionListener
        // Page de connexion :
        //  Authentification de l'utilisateur et fetch data
        connLayout.btnConnection.setOnAction(e -> {
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

        // Page principale "MainLayout" :
        // Bouton "détail" => ouvre une nouvelle fenêtre ProductDetailScene
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
        // Bouton "Supprimer un médicament"
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
        // Bouton "ajouter" => ouvre une nouvelle page AddProductScene
        mainLayout.addProduct.setOnAction(event -> {
            addProductLayout.productName.clear();
            addProductLayout.productRef.clear();
            addProductLayout.price.clear();
            addProductLayout.molecule.clear();
            addProductLayout.manufacturer.clear();
            addProductLayout.quantity.clear();
            addProductLayout.description.clear();

            addProductLayout.productAddedLabel.setVisible(false);
            addProductLayout.mainPane.setVisible(true);
            window.setScene(addProductScene);
            window.setTitle(" Wiki GSB - Ajouter un produit");
        });

        // Page d'ajout d'un produit
        // Bouton "Ajouter" de la scene ajouter un produit
        addProductLayout.addProduct.setOnAction(event -> {
            String productName =  addProductLayout.productName.getText();
            String productRef = addProductLayout.productRef.getText();
            Double price = Double.parseDouble(addProductLayout.price.getText());
            String molecule = addProductLayout.molecule.getText();
            String manufacturer = addProductLayout.manufacturer.getText();
            int quantity = Integer.parseInt(addProductLayout.quantity.getText());
            String description = addProductLayout.description.getText();
            Product product = new Product(productName, productRef, price, molecule, manufacturer, quantity, description);
            if (product != null) {
                ProductDao productDao = new ProductDao();
                productDao.add(product);

                System.out.println("Produit Ajouté !");
                addProductLayout.mainPane.setVisible(false);
                addProductLayout.productAddedLabel.setVisible(true);
            } else {
                // TODO message d'échec de l'ajout
            }
        });
        // bouton annuler de scene d'ajout de produit
        addProductLayout.btnCancel.setOnAction(event -> {
            resetMainScreen(mainLayout);
        });

        // Page d'administration des utilisateur
        // Bouton Supprimer un utilisateur
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
        // Bouton Ajouter un utilisateur (AdminPage)
        adminLayout.addButton.setOnAction(event -> {
            addUserLayout.login.clear();
            addUserLayout.name.clear();
            addUserLayout.surname.clear();
            addUserLayout.password.clear();
            addUserLayout.userAddedLabel.setVisible(false);
            addUserLayout.mainPane.setVisible(true);
            window.setScene(addUserScene);
            window.setTitle(" Wiki GSB - Ajouter un utilisateur");
        });

        // Page d'ajout d'un utilisateur (AddUserScene)
        // Bouton annuler
        addUserLayout.btnCancel.setOnAction(event -> {
            resetAdminScreen(adminLayout);
        });
        // Bouton ajouter
        addUserLayout.addUser.setOnAction(event -> {
            String login = addUserLayout.login.getText();
            String name = addUserLayout.name.getText();
            String surname = addUserLayout.surname.getText();
            String password = addUserLayout.password.getText();
            Boolean isAdmin = addUserLayout.isAdmin.isSelected();
            AuthenticationModel authModel = new AuthenticationModel();
            if (!login.equals("") && !name.equals("") && !surname.equals("") && !password.equals("")) {
                try {
                    String hashPassword = authModel.stringToHash(password);
                    User user = new User(login, name, surname, hashPassword, isAdmin);
                    UserDao uDao = new UserDao();
                    uDao.add(user);
                    System.out.println("Utilisateur Ajouté !");
                    addUserLayout.userAddedLabel.setVisible(true);
                    addUserLayout.addUser.setVisible(false);
                    addUserLayout.btnCancel.setVisible(false);
                    addUserLayout.mainPane.setVisible(false);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                    addUserLayout.errorMessage.setVisible(true);
                }
            } else {
                addUserLayout.fillAllFields.setVisible(true);
            }
        });

        // Ajout des fonctionnalité des liens de retour
        // Bouton "quitter" de la page principale
        mainLayout.header.link.setOnAction(event -> {
            resetConnectionScreen();
            window.setScene(connectionForm);
            window.setTitle("Wiki GSB");
        });
        // Bouton retour de la page des détail produit
        productLayout.header.link.setOnAction(event -> {
            resetMainScreen(mainLayout);
        });
        // Bouton retour de la page d'ajout de produit
        addProductLayout.header.link.setOnAction(event -> {
            resetMainScreen(mainLayout);
        });
        // Bouton "Administrateur" de la page principale
        mainLayout.adminLink.setOnAction(event -> {
            resetAdminScreen(adminLayout);
        });
        // Bouton "retour" de la page d'administration
        adminLayout.header.link.setOnAction(event -> {
            resetMainScreen(mainLayout);
        });
        // Bouton "retour" de la page d'ajout d'utilisateur
        addUserLayout.header.link.setOnAction(event -> {
            resetAdminScreen(adminLayout);
        });

        // Fin de la fonction init
        window.setScene(connectionForm);
        window.setTitle("Wiki GSB");
        window.setResizable(false);
        window.show();
    }

    // Récupérer les données de la base dans le tableau de la page d'accueil
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

    // Récupérer les données de la page d'administration
    private void userFetchTableData(AdminPage adminLayout, UserDao uDao) {
        for (User u : uDao.fetchAllUser()) {
            adminLayout.userTable.getItems().add(u);
            adminLayout.colId.setCellValueFactory(new PropertyValueFactory<>("idUser"));
            adminLayout.colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
            adminLayout.colName.setCellValueFactory(new PropertyValueFactory<>("nom"));
            adminLayout.colSurname.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            adminLayout.colAdmin.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().isAdmin() ? "Oui" : "Non"));
        }
    }

    // Verification des données de connexion
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

    // Réinitialise l'écran de connexion
    private void resetConnectionScreen() {
        try {
            initRootLayout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Réinitialise l'écran d'accueil
    private void resetMainScreen(MainWindow mainLayout) {
        mainLayout.productTable.getItems().clear();
        ProductDao pDao = new ProductDao();
        productFetchTableData(mainLayout, pDao);
        mainLayout.errorMessage.setVisible(false);
        window.setScene(mainWindow); 
        window.setTitle("Wiki GSB - Accueil");
    }

    // Réinitialise l'écran d'administration
    private void resetAdminScreen(AdminPage adminLayout) {
        adminLayout.userTable.getItems().clear();
        UserDao uDao = new UserDao();
        userFetchTableData(adminLayout, uDao);
        adminLayout.errorMessage.setVisible(false);
        window.setScene(adminScene);
        window.setTitle("Wiki GSB - Administration");
    }

    private void setStylesheet(Scene scene) {
        scene.getStylesheets().add("util/stylesheet.css");
    }

    public static void main(String[] args) {

        launch(args);
    }
}

