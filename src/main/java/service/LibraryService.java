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

    // Lấy danh sách sách
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    // Lấy danh sách học sinh
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    // Mượn sách
    public String borrowBook(int bookId, int studentId, String borrowReturnDate) {
        // Kiểm tra sách còn hay không
        Book book = bookDAO.getAllBooks().stream().filter(b -> b.getBookId() == bookId).findFirst().orElse(null);
        if (book == null || book.getQuantity() <= 0) {
            return "Sách không còn";
        }
        // Tạo thẻ mượn
        BorrowBook borrowBook = new BorrowBook(0, bookId, studentId, true, new java.util.Date().toString(), borrowReturnDate);
        borrowBookDAO.addBorrowBook(borrowBook);
        // Giảm số lượng sách
        bookDAO.decreaseQuantity(bookId);
        return "Mượn sách thành công";
    }

    // Lấy sách đã được mượn
    public List<BorrowBook> getBorrowedBooks() {
        return borrowBookDAO.getBorrowedBooks();
    }
}
