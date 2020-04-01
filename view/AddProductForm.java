package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AddProductForm extends AnchorPane {
    public WindowHeader header = new WindowHeader("Ajouter un produit", "Retour");
    public GridPane mainGrid = new GridPane();
    GridPane formPanel = new GridPane();

    public Label productAddedLabel = new Label("Produit Ajouté !");

    Label productNameLib = new Label("Produit :");
    Label productRefLib = new Label("Reference :");
    Label priceLib = new Label("Prix (TTC) :");
    Label moleculeLib = new Label("Molecule :");
    Label manufacturerLib = new Label("Fabriquant :");
    Label quantityLib = new Label("Quantité (mg) :");
    Label descLib = new Label("Description :");

    public TextField productName = new TextField();
    public TextField productRef = new TextField();
    public TextField price = new TextField();
    public TextField molecule = new TextField();
    public TextField manufacturer = new TextField();
    public TextField quantity = new TextField();
    public TextField description = new TextField();

    public Button addProduct = new Button("Ajouter");
    public Button btnCancel = new Button("Annuler");

    public AddProductForm(){
        super();
        setComposition();
        this.setBackground(Background.EMPTY);
        this.getChildren().addAll(header, mainGrid, productAddedLabel);
    }

    public void setComposition(){
        addProduct.setDefaultButton(true);
        btnCancel.setCancelButton(true);

        productNameLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        productRefLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        priceLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        moleculeLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        manufacturerLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        quantityLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        descLib.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,3})?")) {
                    price.setText(oldValue);
                }
            }
        });

        quantity.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}?")) {
                    quantity.setText(oldValue);
                }
            }
        });

        productAddedLabel.setVisible(false);
        productAddedLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        productAddedLabel.setLayoutX(10);
        productAddedLabel.setLayoutY(80);

        formPanel.setPrefSize(580,250);
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

        formPanel.add(descLib,0,3,2,1);
        formPanel.add(description,1,3,4,1);

        mainGrid.setLayoutX(10);
        mainGrid.setLayoutY(80);
        mainGrid.setVgap(10);
        mainGrid.setHgap(10);
        mainGrid.setVisible(true);
        mainGrid.add(formPanel, 0,0,2,1);
        mainGrid.add(addProduct,0,1);
        mainGrid.add(btnCancel,1,1);
    }
}
