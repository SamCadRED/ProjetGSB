package model;

import classe.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

abstract class Dao implements IDao {

    protected Connection conn;
    protected String table;

    public Dao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DRIVER OK !");

            String url = "jdbc:mysql://localhost:8889/gsb_app";

            conn = DriverManager.getConnection(url,"root","root");
            System.out.println("Connection succesful !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Object getById(int id) {
        User u = new User();

        try {
            ResultSet rs = (ResultSet) this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)
                    .executeQuery("SELECT * FROM "+table+" WHERE idUser = " + id);

            if (rs.first()) {
                u = new User(rs.getInt("idUser"),
                        rs.getString("nom"),
                        rs.getString("prenom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public Object add(Object item) {
        return null;
    }

    @Override
    public Object update(Object item) {
        return null;
    }

    @Override
    public boolean delete(Object item) {
        return false;
    }
}
