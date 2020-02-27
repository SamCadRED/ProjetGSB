package model;

import classe.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

abstract class Dao<T> {

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
    public abstract boolean add(T obj);

    // Permet de mettre à jour un objet
    public abstract boolean update(T obj);

    // Permet de supprimer l'objet
    public abstract boolean delete (T obj);

    // Permet de chercher un objet grâce à l'id
    public abstract T find(int id);

}
