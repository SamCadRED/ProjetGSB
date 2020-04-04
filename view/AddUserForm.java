package view;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class AddUserForm extends AnchorPane {
    public WindowHeader header = new WindowHeader("Ajouter un utilisateur", "Retour");
    public AnchorPane mainPane = new AnchorPane();
    public Label userAddedLabel = new Label("Utilisateur Ajouté !");

    Label loginLib = new Label("Login :");
    Label nameLib = new Label("Nom :");
    Label surnameLib = new Label("Prénom :");
    Label passwordLib = new Label("Mot de passe :");
    Label adminLib = new Label("Administrateur :");

    public TextField login = new TextField();
    public TextField name = new TextField();
    public TextField surname = new TextField();
    public PasswordField password = new PasswordField();
    public CheckBox isAdmin = new CheckBox();

    public Button addUser = new Button("Ajouter");
    public Button btnCancel = new Button("Annuler");

    public AddUserForm() {
        super();
        setComposition();
        this.getChildren().addAll(header, mainPane, addUser, btnCancel, userAddedLabel);
    }

    public void setComposition() {
        mainPane.setPrefSize(580,250);
        mainPane.setLayoutX(10);
        mainPane.setLayoutY(80);
        mainPane.getChildren().addAll(loginLib,login,passwordLib,password,nameLib,name,surnameLib,surname, adminLib, isAdmin);

        loginLib.setId("labelBold");
        nameLib.setId("labelBold");
        surnameLib.setId("labelBold");
        passwordLib.setId("labelBold");
        adminLib.setId("labelBold");

        userAddedLabel.setVisible(false);
        userAddedLabel.setId("validationMessage");
        userAddedLabel.setLayoutX(10);
        userAddedLabel.setLayoutY(80);

        loginLib.setLayoutX(0);
        loginLib.setLayoutY(20);
        login.setLayoutX(0);
        login.setLayoutY(38);

        passwordLib.setLayoutX(200);
        passwordLib.setLayoutY(20);
        password.setLayoutX(200);
        password.setLayoutY(38);

        nameLib.setLayoutX(0);
        nameLib.setLayoutY(100);
        name.setLayoutX(0);
        name.setLayoutY(118);

        surnameLib.setLayoutX(200);
        surnameLib.setLayoutY(100);
        surname.setLayoutX(200);
        surname.setLayoutY(118);

        adminLib.setLayoutX(0);
        adminLib.setLayoutY(185);
        isAdmin.setLayoutX(0);
        isAdmin.setLayoutY(207);

        btnCancel.setCancelButton(true);
        btnCancel.setLayoutX(10);
        btnCancel.setLayoutY(350);
        addUser.setDefaultButton(true);
        addUser.setLayoutX(100);
        addUser.setLayoutY(350);
    }
}
