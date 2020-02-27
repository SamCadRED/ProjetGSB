package model;

import classe.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Dao<T> {

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

    // Permet d'ajouter un objet
    public boolean add(T obj) { return false; }

    // Permet de mettre à jour un objet
    public  boolean update(T obj) { return false; }

    // Permet de supprimer l'objet
    public  boolean delete (T obj) { return false; }

}
