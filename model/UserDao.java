package model;

import classe.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends Dao<User> {


    public UserDao() {
        super();
        this.table = "User";
    }

    @Override
    public boolean add(User obj) {
        String login = obj.getLogin();
        String nom = obj.getNom();
        String prenom = obj.getPrenom();
        String password = obj.getPassword();
        boolean isAdmin = obj.isAdmin();
        String SQL = "INSERT INTO `User` (`idUser`, `login`, `nom`, `prenom`, `motDePasse`, `isAdmin`) VALUES ";
        try {
            this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(SQL + "(NULL, " + "'" + login + "', '" + nom + "', '" + prenom + "', '" + password + "', " + isAdmin + " )");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(nom);
            System.out.println(prenom);
            return false;
        }
    }

    @Override
    public boolean update(User obj) {
        int id = obj.getId();
        String nom = obj.getNom();
        String prenom = obj.getPrenom();
        String SQL = "UPDATE `User` SET `nom`='";
        try {
            this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(SQL+nom+"',`prenom`='"+prenom+"' WHERE `idUser`="+id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(User obj) {
        int id = obj.getId();
        String SQL = "DELETE FROM `User` WHERE `idUser`=";
        try {
            this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(SQL+id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User find(int id) {
        User user = new User();
        try {
            ResultSet rs = this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM User WHERE idUser = " + id);

            if (rs.first()) {
                user = new User(id,rs.getString("login"), rs.getString("nom"), rs.getString("prenom"), rs.getString("motDePasse"), rs.getBoolean("isAdmin"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public List<User> fetchAllUser() {
        List<User> items = new ArrayList<User>();
        User u;
        try {
            ResultSet rs = this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM "+this.table+" ORDER BY login ASC;");
            while (rs.next()) {
                u = new User();
                u.setId(rs.getInt(1));
                u.setLogin(rs.getString(2));
                u.setNom(rs.getString(3));
                u.setPrenom(rs.getString(4));
                u.setPassword(rs.getString(5));
                u.setAdmin(rs.getBoolean(6));
                items.add(u);
            }
            return items;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
