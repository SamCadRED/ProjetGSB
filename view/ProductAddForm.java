package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;

public class ProductAddForm extends AnchorPane {
    public WindowHeader header = new WindowHeader("Ajouter un produit", "Retour");
    GridPane mainGrid = new GridPane();
    GridPane formPanel = new GridPane();

    Label productNameLib = new Label("Produit :");
    Label productRefLib = new Label("Reference :");
    Label priceLib = new Label("Prix (TTC) :");
    Label moleculeLib = new Label("Molecule :");
    Label manufacturerLib = new Label("Fabriquant :");
    Label riskLib = new Label("Risque :");
    Label descLib = new Label("Description :");

    public TextField productName = new TextField();
    public TextField productRef = new TextField();
    public TextField price = new TextField();
    public TextField molecule = new TextField();
    public TextField manufacturer = new TextField();
    public TextField risk = new TextField();
    public TextField description = new TextField();

    public Button addProduct = new Button("Ajouter");
    public Button btnCancel = new Button("Annuler");

    public ProductAddForm(){
        super();
        setComposition();
        this.setBackground(Background.EMPTY);
        this.getChildren().addAll(header, mainGrid);
    }

    public void setComposition(){
        addProduct.setDefaultButton(true);
        btnCancel.setCancelButton(true);

        formPanel.setPrefSize(580,280);

        formPanel.add(productNameLib,0,0);
        formPanel.add(productName,1,0);
        formPanel.add(productRefLib,2,0);
        formPanel.add(productRef,3,0);

        formPanel.add(moleculeLib,0,1);
        formPanel.add(molecule,1,1);
        formPanel.add(manufacturerLib,2,1);
        formPanel.add(manufacturer,3,1);

        formPanel.add(priceLib,0,2);
        formPanel.add(price,1,2);
        formPanel.add(riskLib,2,2);
        formPanel.add(risk,3,2);

        formPanel.add(descLib,0,3);
        formPanel.add(description,1,3,3,1);

        mainGrid.setLayoutX(10);
        mainGrid.setLayoutY(70);
        mainGrid.setVgap(10);
        mainGrid.setHgap(10);
        mainGrid.add(formPanel, 0,0);
        mainGrid.add(addProduct,0,1);
        mainGrid.add(btnCancel,0,1);
    }
}
