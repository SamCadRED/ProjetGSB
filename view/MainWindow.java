package view;

import classe.Product;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class MainWindow extends AnchorPane {
    public WindowHeader header = new WindowHeader("Produits","Quitter");
    public TableView productTable = new TableView();
    GridPane mainGrid = new GridPane();
    public Label errorMessage = new Label("Veuillez sélectionner un produit");

    public TableColumn<Product, String> colId = new TableColumn<>("ID");
    public TableColumn<Product, String> colName = new TableColumn<>("Nom");
    public TableColumn<Product, String> colRef = new TableColumn<>("Reference");
    public TableColumn<Product, String> colPrice = new TableColumn<>("Prix");
    public TableColumn<Product, String> colQuantity = new TableColumn<>("Risque");

    public Button detailButton = new Button("Voir le détail");
    public Button addProduct = new Button("Ajouter un médicament");

    public MainWindow() {
        super();
        setComposition();
        this.setBackground(Background.EMPTY);
        this.getChildren().addAll(header, mainGrid, errorMessage);
    }

    private void setComposition() {
        errorMessage.setVisible(false);
        errorMessage.setTextFill(Color.RED);
        errorMessage.setLayoutX(410);
        errorMessage.setLayoutY(40);

        colId.setVisible(false);
        colId.setPrefWidth(80);
        colName.setPrefWidth(80);
        colRef.setPrefWidth(80);
        colPrice.setPrefWidth(80);
        colQuantity.setPrefWidth(50);

        productTable.setPrefSize(580,280);
        productTable.getColumns().addAll(colId, colName, colRef, colPrice, colQuantity);

        detailButton.setLayoutX(260);
        detailButton.setLayoutY(360);
        detailButton.setVisible(true);

        addProduct.setLayoutX(300);
        addProduct.setLayoutY(360);
        addProduct.setVisible(true);

        mainGrid.setLayoutX(10);
        mainGrid.setLayoutY(70);
        mainGrid.setVgap(10);
        mainGrid.setHgap(10);
        mainGrid.add(productTable,0,0,2,1);
        mainGrid.add(detailButton,0,1);
        mainGrid.add(addProduct,1,1);
    }
}
