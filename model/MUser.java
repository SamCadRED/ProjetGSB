package model;

import classe.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MUser extends Dao {

    public MUser() {
        super();
    }

    public User getUserByID(int id) {
        User u = new User();

        try {
            ResultSet rs = (ResultSet) this.conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)
                    .executeQuery("SELECT * FROM User WHERE idUser = " + id);

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
}
