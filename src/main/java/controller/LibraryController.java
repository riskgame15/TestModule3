package controller;


import model.Book;
import service.LibraryService;

import javax.servlet.RequestDispatcher;
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

@WebServlet(name = "LibraryController", urlPatterns = "/library")
public class LibraryController extends HttpServlet {
        private LibraryService libraryService = new LibraryService();

        // Hiển thị danh sách sách
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            List<Book> books = libraryService.getAllBooks();
            request.setAttribute("books", books);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/view/bookList.jsp");
            dispatcher.forward(request, response);
        }

        // Xử lý mượn sách
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String borrowId = request.getParameter("borrowId");
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            int studentId = Integer.parseInt(request.getParameter("studentId"));
            String borrowReturnDate = request.getParameter("borrowReturnDate");

            // Kiểm tra tính hợp lệ của borrowId
            if (!borrowId.matches("MS-\\d{4}")) {
                request.setAttribute("errorMessage", "Mã mượn sách không hợp lệ (MS-XXXX).");
                doGet(request, response);
                return;
            }

            // Kiểm tra ngày trả sách không được trước ngày mượn
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date borrowDate = new Date(); // Ngày mượn là ngày hiện tại
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

            // Mượn sách
            String message = libraryService.borrowBook(bookId, studentId, borrowReturnDate);
            request.setAttribute("message", message);
            doGet(request, response);
        }

    }

