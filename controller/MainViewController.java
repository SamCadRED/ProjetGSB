package controller;

import classe.Product;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;


public class MainViewController {
    @FXML
    private TableColumn libelle;
    @FXML
    private TableColumn molecule;
    @FXML
    private TableColumn prix;
    @FXML
    private TableColumn risque;

    public MainViewController() {
        //initTable();
    }

    public void initTable() {}
}
