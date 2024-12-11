package model;

public class BorrowBook {
    private int borrowId;
    private int bookId;
    private int studentId;
    private Boolean status;
    private String borrowStart;
    private String borrowReturn;

    public BorrowBook(int borrowId, int bookId, int studentId, Boolean status, String borrowStart, String borrowReturn) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.status = status;
        this.borrowStart = borrowStart;
        this.borrowReturn = borrowReturn;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getBorrowStart() {
        return borrowStart;
    }

    public void setBorrowStart(String borrowStart) {
        this.borrowStart = borrowStart;
    }

    public String getBorrowReturn() {
        return borrowReturn;
    }

    public void setBorrowReturn(String borrowReturn) {
        this.borrowReturn = borrowReturn;
    }
}
