package view;

import classe.Product;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class MainWindow extends AnchorPane {
    public WindowHeader header = new WindowHeader("Produits","Quitter");
    public TableView productTable = new TableView();
    GridPane mainGrid = new GridPane();
    public TableColumn<Product, String> colId = new TableColumn<>("ID");
    public TableColumn<Product, String> colName = new TableColumn<>("Nom");
    public TableColumn<Product, String> colRef = new TableColumn<>("Reference");
    public TableColumn<Product, String> colPrice = new TableColumn<>("Prix");
    public TableColumn<Product, String> colRisk = new TableColumn<>("Risque");

    public Button detailButton = new Button("Voir le détail");

    public MainWindow() {
        super();
        setComposition();
        this.setBackground(Background.EMPTY);
        this.getChildren().addAll(header, mainGrid);
    }

    private void setComposition() {
        colId.setVisible(true);
        colId.setPrefWidth(80);
        colName.setPrefWidth(80);
        colRef.setPrefWidth(80);
        colPrice.setPrefWidth(80);
        colRisk.setPrefWidth(50);

        productTable.setPrefSize(580,280);
        productTable.getColumns().addAll(colId, colName, colRef, colPrice, colRisk);

        detailButton.setLayoutX(260);
        detailButton.setLayoutY(360);
        detailButton.setVisible(true);

        mainGrid.setLayoutX(10);
        mainGrid.setLayoutY(70);
        mainGrid.setVgap(10);
        mainGrid.setHgap(10);
        mainGrid.add(productTable,0,0);
        mainGrid.add(detailButton,0,1);
    }
}
