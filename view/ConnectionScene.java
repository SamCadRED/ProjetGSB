package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ConnectionScene extends AnchorPane {
    WindowHeader header = new WindowHeader("Connexion", "");
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

        loginField.setPromptText("Identifiant..");
        passField.setPromptText("Mot de passe..");

        fieldGrid.add(loginField,0,0,2,1);
        fieldGrid.add(passField,0,1,2,1);

        fieldGrid.add(errorConnLabel,0,2,2,1);

        fieldGrid.add(btnConnection,0,3);
        fieldGrid.add(btnCancel,1,3);

        errorConnLabel.setTextFill(Color.RED);
        errorConnLabel.setVisible(true);
        btnCancel.setVisible(true);
    }
}
