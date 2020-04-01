package classe;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int idProduct;
    private String nameProduct;
    private String refProduct;
    private double price;
    private String molecule;
    private String manufacturer;
    private int risk;
    private String description;

    public Product() {}

    public Product(int idProduct) {
        this.idProduct = idProduct;
    }

    public Product(String nameProduct, String refProduct, double price, String molecule, String manufacturer, int risk, String description) {
        this.nameProduct = nameProduct;
        this.refProduct = refProduct;
        this.price = price;
        this.molecule = molecule;
        this.manufacturer = manufacturer;
        this.risk = risk;
        this.description = description;
    }

    public Product(int idProduct, String nameProduct, String refProduct, double price, String molecule, String manufacturer, int risk, String description) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.refProduct = refProduct;
        this.price = price;
        this.molecule = molecule;
        this.manufacturer = manufacturer;
        this.risk = risk;
        this.description = description;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getRefProduct() {
        return refProduct;
    }

    public void setRefProduct(String refProduct) {
        this.refProduct = refProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMolecule() {
        return molecule;
    }

    public void setMolecule(String molecule) {
        this.molecule = molecule;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
