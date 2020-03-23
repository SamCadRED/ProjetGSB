package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ConnectionScene extends AnchorPane {
    Label sceneTitle = new Label("Connexion");
    public Label errorConnLabel = new Label("Login ou mot de passe incorrect");
    public TextField loginField = new TextField("login");
    public PasswordField passField = new PasswordField();
    public Button btnConnection = new Button("Connexion");
    public Button btnCancel = new Button("Annuler");

    public ConnectionScene() {
        super();
        setComposition();
        this.getChildren().addAll(sceneTitle, errorConnLabel, loginField, passField, btnConnection, btnCancel);
    }

    private void setComposition() {
        sceneTitle.setLayoutX(78);
        sceneTitle.setLayoutY(51);

        errorConnLabel.setVisible(false);

        loginField.setLayoutX(76);
        loginField.setLayoutY(73);

        passField.setLayoutX(76);
        passField.setLayoutY(117);

        btnConnection.setLayoutX(76);
        btnConnection.setLayoutY(167);

        btnCancel.setVisible(false);
    }
}
