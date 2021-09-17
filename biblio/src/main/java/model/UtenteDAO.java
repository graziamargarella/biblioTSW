package model;

import java.sql.*;

public class UtenteDAO {

    public static Utente doRetrieveByEmailPassword(String email, String password) {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT mail, password, nome, cognome, isAdmin FROM utente WHERE mail=? AND password=?");
            ps.setString(1,email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                Utente u = new Utente();
                u.setMail(rs.getString(1));
                u.setPassword(rs.getString(2));
                u.setNome(rs.getString(3));
                u.setCognome(rs.getString(4));
                u.setAdmin(rs.getBoolean(5));
                return u;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void doSave(Utente utente)
    {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO utente (mail, password, nome, cognome, isAdmin) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, utente.getMail());
            preparedStatement.setString(2, utente.getPassword());
            preparedStatement.setString(3, utente.getNome());
            preparedStatement.setString(4, utente.getCognome());
            preparedStatement.setBoolean(5, utente.checkIsAdmin());
            if (preparedStatement.executeUpdate() != 1)
            {
                throw new RuntimeException("INSERT error");
            }
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public synchronized static Utente doRetrieveByEmail(String email)
    {
        try (Connection connection = ConnectionPool.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT mail, password, nome, cognome, isAdmin FROM utente WHERE mail=?");
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                Utente u = new Utente();
                u.setMail(rs.getString(1));
                u.setPassword(rs.getString(2));
                u.setNome(rs.getString(3));
                u.setCognome(rs.getString(4));
                u.setAdmin(rs.getBoolean(5));
                return u;
            }
            return null;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}