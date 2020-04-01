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
    public Label manufacturer = new Label("");
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

    }
}
