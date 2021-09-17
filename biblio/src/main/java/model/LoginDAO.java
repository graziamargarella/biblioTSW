package model;

import java.sql.*;
import java.util.UUID;

public class LoginDAO {
    public void doSave(Login login) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO login (id, mail, token, time) VALUES(?, ?,?,?)", Statement.RETURN_GENERATED_KEYS);
            String id = UUID.randomUUID().toString();
            ps.setString(1, id);
            ps.setString(2, login.getEmail());
            ps.setString(3, login.getToken());
            ps.setTimestamp(4, login.getTime());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
            login.setId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}