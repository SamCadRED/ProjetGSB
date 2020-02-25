package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

abstract class Dao implements IDao {
    public Dao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DRIVER OK !");

            String url = "jdbc:mysql://localhost:8889/gsb_app";

            Connection conn = DriverManager.getConnection(url,"root","root");
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
        return null;
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
