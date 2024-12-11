package DAO;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
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

        public List<Student> getAllStudents() {
            List<Student> students = new ArrayList<>();
            try (Connection connection = getConnection()) {
                String query = "SELECT * FROM Student";
                try (PreparedStatement stmt = connection.prepareStatement(query);
                     ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        students.add(new Student(
                                rs.getInt("studentId"),
                                rs.getString("studentName"),
                                rs.getString("studentClass")
                        ));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return students;
        }
    }

