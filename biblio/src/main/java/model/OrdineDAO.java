package model;

import java.sql.*;

public class OrdineDAO {
    public void doSave(Ordine ordine) {
        try (Connection connection = ConnectionPool.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ordine (isbn, cliente) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ordine.getIsbn());
            preparedStatement.setString(2, ordine.getCliente());
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
}
