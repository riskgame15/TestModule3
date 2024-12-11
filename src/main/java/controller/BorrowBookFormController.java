package controller;

import model.Book;
import model.Student;
import service.LibraryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@WebServlet("/library/borrowBookForm")
public class BorrowBookFormController extends HttpServlet {
    private LibraryService libraryService = new LibraryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> bookList = libraryService.getAllBooks();
        List<Student> students = libraryService.getAllStudents();

        request.setAttribute("bookList", bookList);
        request.setAttribute("students", students);
        request.getRequestDispatcher("/view/borrowBookForm.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String borrowId = request.getParameter("borrowId");
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        String borrowReturnDate = request.getParameter("borrowReturnDate");

        // Kiểm tra borrowId hợp lệ
        if (!borrowId.matches("MS-\\d{4}")) {
            request.setAttribute("errorMessage", "Mã mượn sách không hợp lệ (MS-XXXX).");
            doGet(request, response);
            return;
        }

        // Kiểm tra ngày trả sách
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date borrowDate = new Date();
            Date returnDate = sdf.parse(borrowReturnDate);
            if (returnDate.before(borrowDate)) {
                request.setAttribute("errorMessage", "Ngày trả sách không được trước ngày mượn.");
                doGet(request, response);
                return;
            }
        } catch (ParseException e) {
            request.setAttribute("errorMessage", "Ngày trả sách không hợp lệ.");
            doGet(request, response);
            return;
        }

        String message = libraryService.borrowBook(bookId, studentId, borrowReturnDate);
        request.setAttribute("message", message);
        response.sendRedirect("/library");
    }
}

//@WebServlet("/library/borrowBookForm")
//public class BorrowBookFormController extends HttpServlet {
//    private LibraryService libraryService = new LibraryService();
//
//    // Hiển thị form mượn sách
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int bookId = Integer.parseInt(request.getParameter("bookId"));
//        Book book = libraryService.getAllBooks().stream()
//                .filter(b -> b.getBookId() == bookId)
//                .findFirst().orElse(null);
//        List<Student> students = libraryService.getAllStudents();
//
//        if (book == null || book.getQuantity() == 0) {
//            request.setAttribute("errorMessage", "Sách không còn trong thư viện.");
//            request.getRequestDispatcher("/view/bookList.jsp").forward(request, response);
//            return;
//        }
//
//        request.setAttribute("book", book);
//        request.setAttribute("students", students);
//        request.getRequestDispatcher("/view/borrowBookForm.jsp").forward(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        // Xử lý mượn sách ở đây, gọi method borrowBook
//        String borrowId = request.getParameter("borrowId");
//        int bookId = Integer.parseInt(request.getParameter("bookId"));
//        int studentId = Integer.parseInt(request.getParameter("studentId"));
//        String borrowReturnDate = request.getParameter("borrowReturnDate");
//
//        // Kiểm tra tính hợp lệ của borrowId
//        if (!borrowId.matches("MS-\\d{4}")) {
//            request.setAttribute("errorMessage", "Mã mượn sách không hợp lệ (MS-XXXX).");
//            doGet(request, response);
//            return;
//        }
//
//        // Kiểm tra ngày trả sách không được trước ngày mượn
//        try {
//            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//            Date borrowDate = new Date(); // Ngày mượn là ngày hiện tại
//            Date returnDate = sdf.parse(borrowReturnDate);
//            if (returnDate.before(borrowDate)) {
//                request.setAttribute("errorMessage", "Ngày trả sách không được trước ngày mượn.");
//                doGet(request, response);
//                return;
//            }

