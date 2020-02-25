package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sun.rmi.rmic.Main;

public class ConnectionFormController {
    @FXML
    private TextField login;
    @FXML
    private TextField password;
    @FXML
    private Button accept;
    @FXML
    private Button cancel;

    public ConnectionFormController() {} // constructeur

    @FXML
    private void acceptButtonClicked() {
        System.out.println("Accept button clicked !");
        System.out.println(login.getText());
        System.out.println(password.getText());

    }

    @FXML
    private void cancelButtonClicked() {
        System.out.println("Cancel button clicked !");
        login.setText("");
        password.setText("");
    }
}
