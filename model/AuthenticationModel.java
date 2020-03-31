package model;

import classe.User;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationModel extends Dao {
    public AuthenticationModel() {
        super();
        this.table = "`User`";
    }

    public User checkAuth(String login, String password) throws NoSuchAlgorithmException {
        User u = new User();

        String hashWord = hashString(password);
        String SQL = "SELECT * FROM " + this.table + " WHERE `login` = " + "'" + login + "';";
        System.out.println(SQL);
        try {
            ResultSet rs = this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY)
                    .executeQuery(SQL);
            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setLogin(rs.getString(2));
                u.setNom(rs.getString(3));
                u.setPrenom(rs.getString(4));
                u.setPassword(rs.getString(5));
            }
            if (u.getLogin().equals(login) && u.getPassword().equals(hashWord)) {
                return u;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User createAuth(User u) {
        return u;
    }

    public String hashString(String string) {
        String hashCode;
        hashCode = string;

        return hashCode;
    }
}
