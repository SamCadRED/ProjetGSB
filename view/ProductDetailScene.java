package view;

import javafx.geometry.Insets;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

public class ProductDetailScene extends AnchorPane {
    public WindowHeader header = new WindowHeader("Détails du produit", "Retour");
    GridPane infoGrid = new GridPane();
    Label productNameLib = new Label("Produit :");
    Label productRefLib = new Label("Reference :");
    Label priceLib = new Label("Prix (TTC) :");
    Label moleculeLib = new Label("Molecule :");
    Label labLib = new Label("Fabriquant :");
    Label riskLib = new Label("Risque :");
    Label descLib = new Label("Description :");

    public Label productName = new Label("");
    public Label productRef = new Label("");
    public Label price = new Label("");
    public Label molecule = new Label("");
    public Label lab = new Label("");
    public Label risk = new Label("");
    public Label description = new Label("");

    public ProductDetailScene() {
        super();
        setComposition();
        this.setBackground(Background.EMPTY);
        this.getChildren().addAll(header, infoGrid);
    }

    private void setComposition() {
        infoGrid.setLayoutY(70);
        infoGrid.setPadding(new Insets(20));
        infoGrid.setHgap(30);
        infoGrid.setVgap(10);

        infoGrid.add(productNameLib,0,1);
        infoGrid.add(productName,1,1);

        infoGrid.add(productRefLib,3,1);
        infoGrid.add(productRef,4,1);

        infoGrid.add(priceLib,0,2);
        infoGrid.add(price,1,2);

        infoGrid.add(moleculeLib,3,2);
        infoGrid.add(molecule,4,2);

        infoGrid.add(labLib,0,3);
        infoGrid.add(lab,1,3);

        infoGrid.add(riskLib,3,3);
        infoGrid.add(risk,4,3);

        infoGrid.add(descLib,0,4);
        infoGrid.add(description,1,4,2,1);
    }
}
