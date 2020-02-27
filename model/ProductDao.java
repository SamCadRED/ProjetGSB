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
