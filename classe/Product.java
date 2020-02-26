package classe;

public class Product {
    private int idProduct;
    private String productName;
    private String productRef;
    private double price;
    private String molecule;
    private int risk;

    public Product(String productName, String productRef, double price, String molecule, int risk) {
        this.productName = productName;
        this.productRef = productRef;
        this.price = price;
        this.molecule = molecule;
        this.risk = risk;
    }
    public Product(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductRef() {
        return productRef;
    }

    public void setProductRef(String productRef) {
        this.productRef = productRef;
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
