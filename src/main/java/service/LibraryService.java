package service;

import DAO.BookDAO;
import DAO.BorrowBookDAO;
import DAO.StudentDAO;
import model.Book;
import model.BorrowBook;
import model.Student;

import java.util.List;

public class LibraryService {
    private BookDAO bookDAO = new BookDAO();
    private StudentDAO studentDAO = new StudentDAO();
    private BorrowBookDAO borrowBookDAO = new BorrowBookDAO();

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    public String borrowBook(int bookId, int studentId, String borrowReturnDate) {
        Book book = bookDAO.getAllBooks().stream().filter(b -> b.getBookId() == bookId).findFirst().orElse(null);
        if (book == null || book.getQuantity() <= 0) {
            return "Sách không tồn tai";
        }
        // Tạo thẻ mượn
        BorrowBook borrowBook = new BorrowBook(0, bookId, studentId, true, new java.util.Date().toString(), borrowReturnDate);
        borrowBookDAO.addBorrowBook(borrowBook);
        // Giảm số lượng sách
        bookDAO.decreaseQuantity(bookId);
        return "Mượn sách thành công";
    }

    public List<BorrowBook> getBorrowedBooks() {
        return borrowBookDAO.getBorrowedBooks();
    }
    public Book getBookById(int bookId) {
        return bookDAO.getAllBooks().stream()
                .filter(book -> book.getBookId() == bookId)
                .findFirst()
                .orElse(null);
    }
    public Student getStudentById(int studentId) {
        return studentDAO.getAllStudents().stream()
                .filter(student -> student.getStudentId() == studentId)
                .findFirst()
                .orElse(null);
    }
}
