package view;

import classe.Product;
import classe.User;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

public class AdminPage extends AnchorPane {
    public WindowHeader header = new WindowHeader("Aministration des utilisateurs","Revenir");
    public TableView userTable = new TableView();
    public GridPane mainGrid = new GridPane();
    public Label errorMessage = new Label("Veuillez sélectionner un utilisateur");

    public TableColumn<User, String> colId = new TableColumn<>("ID");
    public TableColumn<User, String> colName = new TableColumn<>("Nom");
    public TableColumn<User, String> colSurname = new TableColumn<>("Prénom");
    public TableColumn<User, String> colLogin = new TableColumn<>("Login");
    public TableColumn<User, String> colAdmin = new TableColumn<>("Admin");

    public Button addButton = new Button("Ajouter");
    public Button deleteButton = new Button("Supprimer");
    public Button modifyButton = new Button("Modifier");

    public AdminPage() {
        super();
        setComposition();
        this.setBackground(Background.EMPTY);
        this.getChildren().addAll(header, mainGrid, errorMessage);
    }

    private void setComposition() {
        errorMessage.setVisible(false);
        errorMessage.setId("errorMessage");
        errorMessage.setLayoutX(410);
        errorMessage.setLayoutY(40);

        userTable.setPrefSize(580,280);

        colId.setVisible(true);
        userTable.getColumns().addAll(colId, colName, colSurname, colLogin, colAdmin);

        mainGrid.setLayoutX(10);
        mainGrid.setLayoutY(70);
        mainGrid.setVgap(10);
        mainGrid.setHgap(10);
        mainGrid.add(userTable,0,0,3,1);
        mainGrid.add(addButton,0,1);
        mainGrid.add(deleteButton,1,1);
        mainGrid.add(modifyButton,2,1);

    }
}
