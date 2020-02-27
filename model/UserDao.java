package model;

import classe.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends Dao<User> {

    public UserDao() {
        super();
    }

    @Override
    public boolean add(User obj) {
        String nom = obj.getNom();
        String prenom = obj.getPrenom();
        try {
            this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeUpdate(
                    "INSERT INTO `User` (`idUser`, `nom`, `prenom`) VALUES (NULL, '"+nom+"', '"+prenom+"')"
            );
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
        try {
            this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeUpdate("UPDATE `User` SET `nom`='"+nom+"',`prenom`='"+prenom+"' WHERE `idUser`="+id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(User obj) {
        int id = obj.getId();
        try {
            this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeUpdate("DELETE FROM `User` WHERE `idUser`="+id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User find(int id) {
        User user = new User();

        try {
            ResultSet rs = this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            ).executeQuery("SELECT * FROM User WHERE idUser = " + id);

            if (rs.first()) {
                user = new User(id, rs.getString("nom"), rs.getString("prenom"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
