package model;

import classe.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
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
        System.out.println("checkAuth : " + login + " / " + password);
        String hashWord = stringToHash(password);
        System.out.println(hashWord);
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
                u.setAdmin(rs.getBoolean(6));
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

    // Fonction permettant de "Hasher" le mot de passe fournit en paramètre et de le retourner
    public String stringToHash(String originalString) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));

        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        String hashOut = hexString.toString();

        System.out.println(hashOut);
        return hashOut;
    }
}
