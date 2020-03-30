package view;

import classe.Product;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Font;

public class MainWindow extends AnchorPane {
    WindowHeader header = new WindowHeader("Produits","Quitter");
    public TableView productTable = new TableView();
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
        this.getChildren().addAll(header, productTable, detailButton);
    }

    private void setComposition() {
        productTable.setPrefSize(580, 300);
        productTable.setLayoutX(10);
        productTable.setLayoutY(60);

        colId.setVisible(true);
        colId.setPrefWidth(80);
        colName.setPrefWidth(80);
        colRef.setPrefWidth(80);
        colPrice.setPrefWidth(80);
        colRisk.setPrefWidth(50);

        productTable.getColumns().addAll(colId, colName, colRef, colPrice, colRisk);

        detailButton.setLayoutX(260);
        detailButton.setLayoutY(360);
        detailButton.setVisible(true);

    }
}
