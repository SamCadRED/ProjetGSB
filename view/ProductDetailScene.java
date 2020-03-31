package view;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class ProductDetailScene extends AnchorPane {
    public WindowHeader header = new WindowHeader("Détails du produit", "Retour");
    GridPane infoPanel = new GridPane();
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
        this.getChildren().addAll(header, infoPanel);
    }

    private void setComposition() {
        infoPanel.setLayoutY(50);
        infoPanel.setPrefSize(550, 400);
        infoPanel.setPadding(new Insets(20));
        infoPanel.setHgap(30);
        infoPanel.setVgap(30);

        infoPanel.add(productNameLib,0,1);
        infoPanel.add(productName,1,1);

        infoPanel.add(productRefLib,3,1);
        infoPanel.add(productRef,4,1);

        infoPanel.add(priceLib,0,2);
        infoPanel.add(price,1,2);

        infoPanel.add(moleculeLib,3,2);
        infoPanel.add(molecule,4,2);

        infoPanel.add(labLib,0,3);
        infoPanel.add(lab,1,3);

        infoPanel.add(riskLib,3,3);
        infoPanel.add(risk,4,3);

        infoPanel.add(descLib,0,4);
        infoPanel.add(description,1,4,2,1);
    }
}
