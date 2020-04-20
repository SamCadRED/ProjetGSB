package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddProductForm extends AnchorPane {
    public WindowHeader header = new WindowHeader("Ajouter un produit", "Retour");
    public AnchorPane mainPane = new AnchorPane();
    public Label productAddedLabel = new Label("Produit Ajouté !");

    Label productNameLib = new Label("Produit :");
    Label productRefLib = new Label("Reference :");
    Label priceLib = new Label("Prix (TTC) :");
    Label moleculeLib = new Label("Molécule :");
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
        this.getChildren().addAll(header, mainPane, btnCancel, addProduct, productAddedLabel);
    }

    public void setComposition(){
        mainPane.setPrefSize(580,250);
        mainPane.setLayoutX(10);
        mainPane.setLayoutY(80);
        mainPane.getChildren().addAll(productNameLib, productName, productRefLib, productRef, priceLib, price, moleculeLib, molecule, manufacturerLib, manufacturer, quantityLib, quantity, descLib, description);

        productNameLib.setId("labelBold");
        productRefLib.setId("labelBold");
        priceLib.setId("labelBold");
        moleculeLib.setId("labelBold");
        manufacturerLib.setId("labelBold");
        quantityLib.setId("labelBold");
        descLib.setId("labelBold");

        productAddedLabel.setVisible(false);
        productAddedLabel.setId("validationMessage");
        productAddedLabel.setLayoutX(10);
        productAddedLabel.setLayoutY(80);

        productNameLib.setLayoutX(0);
        productNameLib.setLayoutY(20);
        productName.setLayoutX(0);
        productName.setLayoutY(38);

        productRefLib.setLayoutX(200);
        productRefLib.setLayoutY(20);
        productRef.setLayoutX(200);
        productRef.setLayoutY(38);

        priceLib.setLayoutX(400);
        priceLib.setLayoutY(20);
        price.setLayoutX(400);
        price.setLayoutY(38);

        moleculeLib.setLayoutX(0);
        moleculeLib.setLayoutY(100);
        molecule.setLayoutX(0);
        molecule.setLayoutY(118);

        manufacturerLib.setLayoutX(200);
        manufacturerLib.setLayoutY(100);
        manufacturer.setLayoutX(200);
        manufacturer.setLayoutY(118);

        quantityLib.setLayoutX(400);
        quantityLib.setLayoutY(100);
        quantity.setLayoutX(400);
        quantity.setLayoutY(118);

        descLib.setLayoutX(0);
        descLib.setLayoutY(185);
        description.setLayoutX(0);
        description.setLayoutY(207);
        description.setPrefWidth(580);

        btnCancel.setCancelButton(true);
        btnCancel.setLayoutX(10);
        btnCancel.setLayoutY(350);
        addProduct.setDefaultButton(true);
        addProduct.setLayoutX(100);
        addProduct.setLayoutY(350);

        // Expressions Régulières (RegEx) permettant de n'accepter que certains caractères dans les champs "prix" et "quantité"
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
    }
}
