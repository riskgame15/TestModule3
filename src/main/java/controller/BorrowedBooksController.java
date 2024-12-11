package controller;

import model.Book;
import model.BorrowBook;
import model.Student;
import service.LibraryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(name = "BorrowedBooksController", urlPatterns = "/library/borrowedBooks")
public class BorrowedBooksController extends HttpServlet {
    private LibraryService libraryService = new LibraryService();

    // Hiển thị danh sách sách đang mượn
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        String studentName = request.getParameter("studentName");

        // Lấy danh sách các bản ghi mượn sách từ cơ sở dữ liệu
        List<BorrowBook> borrowedBooks = libraryService.getBorrowedBooks();

        // Lọc theo tên sách nếu có
        if (bookName != null && !bookName.isEmpty()) {
            borrowedBooks = borrowedBooks.stream()
                    .filter(bb -> {
                        // Tìm sách tương ứng với bookId và so sánh tên sách
                        Book book = libraryService.getBookById(bb.getBookId());
                        return book != null && book.getBookName().toLowerCase().contains(bookName.toLowerCase());
                    })
                    .collect(Collectors.toList());
        }

        // Lọc theo tên học sinh nếu có
        if (studentName != null && !studentName.isEmpty()) {
            borrowedBooks = borrowedBooks.stream()
                    .filter(bb -> {
                        // Tìm học sinh tương ứng với studentId và so sánh tên học sinh
                        Student student = libraryService.getStudentById(bb.getStudentId());
                        return student != null && student.getStudentName().toLowerCase().contains(studentName.toLowerCase());
                    })
                    .collect(Collectors.toList());
        }

        // Bổ sung thông tin `bookName` và `studentName` vào danh sách
        List<Map<String, String>> detailedBorrowedBooks = borrowedBooks.stream()
                .map(bb -> {
                    Map<String, String> details = new HashMap<>();
                    Book book = libraryService.getBookById(bb.getBookId());
                    Student student = libraryService.getStudentById(bb.getStudentId());
                    details.put("bookName", book != null ? book.getBookName() : "N/A");
                    details.put("studentName", student != null ? student.getStudentName() : "N/A");
                    details.put("borrowStart", bb.getBorrowStart());
                    details.put("borrowReturn", bb.getBorrowReturn());
                    return details;
                })
                .collect(Collectors.toList());
        // Chuyển danh sách các sách đang mượn tới trang JSP
        request.setAttribute("borrowedBooks", borrowedBooks);
        request.getRequestDispatcher("/view/borrowedBooksList.jsp").forward(request, response);
    }
}


