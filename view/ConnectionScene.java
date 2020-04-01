package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class ConnectionScene extends AnchorPane {
    WindowHeader header = new WindowHeader("Connexion", "");
    GridPane gridPane = new GridPane();

    Label login = new Label("Identifiant :");
    Label passwd = new Label("Mot de passe :");
    public TextField loginField = new TextField();
    public PasswordField passField = new PasswordField();

    public Label errorConnLabel = new Label("Login ou mot de passe incorrect");
    public Button btnConnection = new Button("Connexion");
    public Button btnCancel = new Button("Annuler");

    public ConnectionScene() {
        super();
        setComposition();
        this.getChildren().addAll(header, gridPane);
    }

    private void setComposition() {
        btnConnection.setDefaultButton(true);
        btnCancel.setCancelButton(true);

        gridPane.setLayoutY(40);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(25));
        gridPane.add(login,0,0);
        gridPane.add(loginField, 1,0);
        gridPane.add(passwd,0,1);
        gridPane.add(passField,1,1);
        gridPane.add(errorConnLabel,0,2,2,1);
        gridPane.add(btnCancel, 0,3);
        gridPane.add(btnConnection,1,3);


        errorConnLabel.setId("errorMessage");
        errorConnLabel.setVisible(false);
        btnCancel.setVisible(true);
    }
}
