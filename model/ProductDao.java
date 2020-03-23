package model;

import classe.Product;
import classe.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ProductDao extends Dao {

    public ProductDao() {
        super();
        this.table = "Product";
    }

    public boolean add(Product obj){
        int idProduct = obj.getIdProduct();
        String name = obj.getProductName();
        String ref = obj.getProductRef();
        double price = obj.getPrice();
        String molecule = obj.getMolecule();
        int risk = obj.getRisk();
        String SQL = "INSERT INTO `Product` (`idProduct`, `nameProduct`, `refProduct`, `price`, `molecule`, `risk`) VALUES (NULL, '";
        try {
            this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(SQL + name + "', '" + ref + "', " + price + "', '" + molecule + "', '"+ risk + "')'");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(name + ref + price + molecule + risk);
            return false;
        }
    }

    public boolean update(Product obj) {
        int id = obj.getIdProduct();
        String name = obj.getProductName();
        String ref = obj.getProductRef();
        double price = obj.getPrice();
        String molecule = obj.getMolecule();
        int risk = obj.getRisk();
        String SQL = "UPDATE `Product` SET `name`='";
        try {
            this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(SQL + name + "',`refProduct`='" + ref + "' `price`='" + price + "' `molecule`='" + molecule + "' `risk`='" + risk + "' WHERE `idProduct`="+id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Product obj) {
        int id = obj.getIdProduct();
        String SQL = "DELETE FROM `Product` WHERE `idProduct`=";
        try {
            this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(SQL + id + ";");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Product find(int id) {
        Product product = new Product();

        try {
            ResultSet rs = this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM Product WHERE idProduct = " + id);

            if (rs.first()) {
                product = new Product(
                        id,
                        rs.getString("nameProduct"),
                        rs.getString("refProduct"),
                        rs.getDouble("price"),
                        rs.getString("molecule"),
                        rs.getInt("risk")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}
