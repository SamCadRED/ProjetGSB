package classe;

public class Product {
    private int idProduct;
    private String nameProduct;
    private String refProduct;
    private double price;
    private String molecule;
    private int risk;

    public Product() {}

    public Product(int idProduct) {
        this.idProduct = idProduct;
    }

    public Product(String nameProduct, String refProduct, double price, String molecule, int risk) {
        this.nameProduct = nameProduct;
        this.refProduct = refProduct;
        this.price = price;
        this.molecule = molecule;
        this.risk = risk;
    }

    public Product(int idProduct, String nameProduct, String refProduct, double price, String molecule, int risk) {
        this.idProduct = idProduct;
        this.nameProduct = nameProduct;
        this.refProduct = refProduct;
        this.price = price;
        this.molecule = molecule;
        this.risk = risk;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getProductName() {
        return nameProduct;
    }

    public void setProductName(String productName) {
        this.nameProduct = productName;
    }

    public String getProductRef() {
        return refProduct;
    }

    public void setProductRef(String productRef) {
        this.refProduct = productRef;
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

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        this.risk = risk;
    }
}
