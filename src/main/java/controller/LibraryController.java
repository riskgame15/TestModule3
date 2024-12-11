package controller;


import model.Book;
import model.Student;
import service.LibraryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LibraryController", urlPatterns = "/library")
public class LibraryController extends HttpServlet {
    private LibraryService libraryService = new LibraryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = libraryService.getAllBooks();
        request.setAttribute("books", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/bookList.jsp");
        dispatcher.forward(request, response);
    }
}

