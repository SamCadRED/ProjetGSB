package view;

import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class ProductDetailScene extends AnchorPane {
    WindowHeader header = new WindowHeader("Détails du produit", "Retour");
    GridPane infoGrid = new GridPane();
    Label productNameLib = new Label("Produit :");
    Label productRefLib = new Label("Reference :");
    Label priceLib = new Label("Prix (TTC) :");
    Label moleculeLib = new Label("Molecule :");
    Label labLib = new Label("Fabriquant :");
    Label riskLib = new Label("Risque :");
    Label descLib = new Label("Description :");

    Label productName = new Label("");
    Label productRef = new Label("");
    Label price = new Label("");
    Label molecule = new Label("");
    Label lab = new Label("");
    Label risk = new Label("");
    Label description = new Label("");

    public ProductDetailScene() {
        super();
        setComposition();
        this.setBackground(Background.EMPTY);
        this.getChildren().addAll(header, infoGrid);
    }

    private void setComposition() {
        infoGrid.setLayoutY(60);
        infoGrid.setPadding(new Insets(20));
        infoGrid.setHgap(30);
        infoGrid.setVgap(20);

        infoGrid.add(productNameLib,0,1);
        infoGrid.add(productName,1,1);

        infoGrid.add(productRefLib,2,1);
        infoGrid.add(productRef,3,1);

        infoGrid.add(priceLib,0,2);
        infoGrid.add(price,1,2);

        infoGrid.add(moleculeLib,2,2);
        infoGrid.add(molecule,3,2);

        infoGrid.add(labLib,0,3);
        infoGrid.add(lab,1,3);

        infoGrid.add(riskLib,2,3);
        infoGrid.add(risk,3,3);

        infoGrid.add(descLib,0,4);
        infoGrid.add(description,1,4);
    }
}
