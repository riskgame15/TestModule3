package DAO;

import model.BorrowBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowBookDAO {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/library";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "123456";

    private Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    // Thêm thông tin mượn sách
    public void addBorrowBook(BorrowBook borrowBook) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO BorrowBook (bookId, studentId, status, borrowStart, borrowReturn) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, borrowBook.getBookId());
                stmt.setInt(2, borrowBook.getStudentId());
                stmt.setBoolean(3, borrowBook.getStatus());
                stmt.setString(4, borrowBook.getBorrowStart());
                stmt.setString(5, borrowBook.getBorrowReturn());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy sách đang mượn
    public List<BorrowBook> getBorrowedBooks() {
        List<BorrowBook> borrowBooks = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT * FROM BorrowBook WHERE status = true";
            try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    borrowBooks.add(new BorrowBook(
                            rs.getInt("borrowId"),
                            rs.getInt("bookId"),
                            rs.getInt("studentId"),
                            rs.getBoolean("status"),
                            rs.getString("borrowStart"),
                            rs.getString("borrowReturn")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowBooks;
    }
}
