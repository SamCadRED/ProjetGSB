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

    private Main mainApp;

    public ConnectionFormController() {} // constructeur

    @FXML
    public void acceptButtonClicked() {
        System.out.println("Accept button clicked !");
        System.out.println(login.getText());
        System.out.println(password.getText());


    }

    @FXML
    public void cancelButtonClicked() {
        System.out.println("Cancel button clicked !");
        login.setText("");
        password.setText("");
    }

    public void setMainapp(Main mainApp) {
        this.mainApp = mainApp;
        mainApp.getClass();
    }
}
