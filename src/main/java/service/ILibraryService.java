package service;

import model.Book;
import model.BorrowBook;
import model.Student;

import java.util.List;

public interface ILibraryService {
    List<Book> getALLBooks();

    List<Student> getAllStudents();

    String borrowBook(int bookId, int studentId, String borrowReturnDate);

    List<BorrowBook> getBorrowedBooks();
}
