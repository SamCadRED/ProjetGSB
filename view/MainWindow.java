package view;

import classe.Product;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

public class MainWindow extends AnchorPane {
    public WindowHeader header = new WindowHeader("Produits","Quitter");
    public TableView productTable = new TableView();
    GridPane mainGrid = new GridPane();
    public Label errorMessage = new Label("Veuillez sélectionner un produit");

    public Hyperlink adminLink = new Hyperlink("Administration");

    public TableColumn<Product, String> colId = new TableColumn<>("ID");
    public TableColumn<Product, String> colName = new TableColumn<>("Nom");
    public TableColumn<Product, String> colRef = new TableColumn<>("Reference");
    public TableColumn<Product, String> colPrice = new TableColumn<>("Prix");
    public TableColumn<Product, String> colQuantity = new TableColumn<>("Quantité");

    public Button detailButton = new Button("Voir le détail");
    public Button addProduct = new Button("Ajouter un médicament");
    public Button deleteProduct = new Button("Supprimer");

    public MainWindow() {
        super();
        setComposition();
        this.setBackground(Background.EMPTY);
        this.getChildren().addAll(header, mainGrid, errorMessage, adminLink);
    }

    private void setComposition() {
        errorMessage.setVisible(false);
        errorMessage.setId("errorMessage");
        errorMessage.setLayoutX(200);
        errorMessage.setLayoutY(43);

        adminLink.setLayoutX(500);
        adminLink.setLayoutY(40);
        adminLink.setStyle("-fx-border-width: 0px;");

        colId.setVisible(false);
        colId.setPrefWidth(80);
        colName.setPrefWidth(100);
        colRef.setPrefWidth(250);
        colPrice.setPrefWidth(100);
        colQuantity.setPrefWidth(100);

        productTable.setPrefSize(580,280);
        productTable.getColumns().addAll(colId, colName, colRef, colPrice, colQuantity);

        deleteProduct.setId("deleteButton");

        mainGrid.setLayoutX(10);
        mainGrid.setLayoutY(70);
        mainGrid.setVgap(10);
        mainGrid.setHgap(10);
        mainGrid.add(productTable,0,0,3,1);
        mainGrid.add(detailButton,0,1);
        mainGrid.add(addProduct,1,1);
        mainGrid.add(deleteProduct,2,1);
    }
}
