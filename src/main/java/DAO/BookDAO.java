package DAO;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/library";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "123456";

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }


    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE quantity > 0";
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int bookId = rs.getInt("bookId");
                String bookName = rs.getString("bookName");
                String author = rs.getString("author");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                books.add(new Book(bookId, bookName, author, description, quantity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean updateQuantity(int bookId, int quantity) {
        String sql = "UPDATE book SET quantity = ? WHERE bookId = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, bookId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Giảm số lượng sách khi mượn
    public void decreaseQuantity(int bookId) {
        try (Connection connection = getConnection()) {
            String query = "UPDATE Book SET quantity = quantity - 1 WHERE bookId = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, bookId);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

