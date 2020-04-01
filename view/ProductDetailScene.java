package view;

import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ProductDetailScene extends AnchorPane {
    public WindowHeader header = new WindowHeader("Détails du produit", "Retour");
    GridPane formPanel = new GridPane();

    Label productNameLib = new Label("Produit :");
    Label productRefLib = new Label("Référence :");
    Label priceLib = new Label("Prix (€ TTC):");
    Label moleculeLib = new Label("Molécule :");
    Label manufacturerLib = new Label("Fabriquant :");
    Label quantityLib = new Label("Quantité (mg):");
    Label descLib = new Label("Description :");

    public Label productName = new Label("");
    public Label productRef = new Label("");
    public Label price = new Label("");
    public Label molecule = new Label("");
    public Label manufacturer = new Label("");
    public Label lab = new Label("");
    public Label quantity = new Label("");
    public Label description = new Label("");

    public ProductDetailScene() {
        super();
        setComposition();
        this.setBackground(Background.EMPTY);
        this.getChildren().addAll(header, formPanel);
    }

    private void setComposition() {
        productNameLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        productRefLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        priceLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        moleculeLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        manufacturerLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        quantityLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        descLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        formPanel.setPrefSize(580,250);
        formPanel.setLayoutX(10);
        formPanel.setLayoutY(80);
        formPanel.setVgap(40);
        formPanel.setHgap(15);

        formPanel.add(productNameLib,0,0);
        formPanel.add(productName,1,0);
        formPanel.add(productRefLib,3,0);
        formPanel.add(productRef,4,0);

        formPanel.add(moleculeLib,0,1);
        formPanel.add(molecule,1,1);
        formPanel.add(manufacturerLib,3,1);
        formPanel.add(manufacturer,4,1);

        formPanel.add(priceLib,0,2);
        formPanel.add(price,1,2);
        formPanel.add(quantityLib,3,2);
        formPanel.add(quantity,4,2);

        formPanel.add(descLib,0,3);
        formPanel.add(description,1,3,4,1);
    }
}
