package model;

import java.io.*;
import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

public class LibroDAO {
    // tutti
    public synchronized Collection<Libro> doRetrieveAll() throws SQLException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<Libro> products = new LinkedList<>();

        String selectSQL = "SELECT * FROM Libro";

        try {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
            {
                Libro bean = new Libro();

                bean.setIsbn(rs.getString("ISBN"));
                bean.setTitolo(rs.getString("TITOLO"));
                bean.setAutore(rs.getString("AUTORE"));
                bean.setEditore(rs.getString("EDITORE"));
                bean.setPrezzo(rs.getFloat("PREZZO"));
                bean.setDescrizione(rs.getString("DESCRIZIONE"));
                bean.setNomeCategoria(rs.getString("NOME_CATEGORIA"));
                bean.setSconto(rs.getInt("SCONTO"));
                products.add(bean);
            }

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                connection.close();
            }
        }
        return products;
    }

    // per isbn
    public synchronized static Libro doRetrieveByKey(String isbn) throws SQLException {
        try (Connection con = ConnectionPool.getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT isbn, titolo, autore, editore, prezzo, descrizione, nome_categoria, sconto from Libro WHERE isbn=?");
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Libro libro = new Libro(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getFloat(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8)
                        );
                return libro;
            }
            return null;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    // inserimento
    public synchronized void doSave(Libro libro) throws SQLException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO Libro (isbn, titolo, autore, editore, prezzo, descrizione, nome_categoria, sconto) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            connection = ConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, libro.getIsbn());
            preparedStatement.setString(2, libro.getTitolo());
            preparedStatement.setString(3, libro.getAutore());
            preparedStatement.setString(4, libro.getEditore());
            preparedStatement.setFloat(5, libro.getPrezzo());
            preparedStatement.setString(6, libro.getDescrizione());
            preparedStatement.setString(7, libro.getNomeCategoria());
            preparedStatement.setInt(8,libro.getSconto());
            preparedStatement.executeUpdate();

        } finally
        {
            try
            {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally
            {
                connection.close();
            }
        }
    }
    // rimozione
    public synchronized static void doDelete(String isbn) throws SQLException {
        try (Connection con = ConnectionPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM libro WHERE isbn=?");
            ps.setString(1, isbn);
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("DELETE error.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // get categorie
    public Collection<String> getAllCategorie() {
        try(Connection con = ConnectionPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT DISTINCT nome_categoria FROM libro");
            LinkedList<String> categorie = new LinkedList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String categoria = rs.getString(1);
                categorie.add(categoria);
            }
            return categorie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
