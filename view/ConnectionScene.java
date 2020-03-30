package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ConnectionScene extends AnchorPane {
    WindowHeader header = new WindowHeader("Connexion", "");
    Label login = new Label("Login : ");
    Label password = new Label("Mot de passe : ");
    GridPane fieldGrid = new GridPane();

    public Label errorConnLabel = new Label("Login ou mot de passe incorrect");
    public TextField loginField = new TextField();
    public PasswordField passField = new PasswordField();
    public Button btnConnection = new Button("Connexion");
    public Button btnCancel = new Button("Annuler");

    public ConnectionScene() {
        super();
        setComposition();
        this.getChildren().addAll(header, fieldGrid);
    }

    private void setComposition() {
        btnConnection.setDefaultButton(true);
        btnCancel.setCancelButton(true);

        fieldGrid.setLayoutY(60);
        fieldGrid.setPadding(new Insets(20));
        fieldGrid.setHgap(20);
        fieldGrid.setVgap(30);

        fieldGrid.add(login,0,0);
        fieldGrid.add(loginField,1,0);
        fieldGrid.add(password,0,1);
        fieldGrid.add(passField,1,1);

        fieldGrid.add(errorConnLabel,0,2,2,1);
        fieldGrid.add(btnConnection,0,3);
        fieldGrid.add(btnCancel,1,3);

        errorConnLabel.setVisible(true);
        btnCancel.setVisible(true);
    }
}
