<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/11/2024
  Time: 11:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mượn sách</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container py-5">
  <div class="card shadow-lg">
    <div class="card-header bg-primary text-white">
      <h2 class="card-title mb-0">Form Mượn Sách</h2>
    </div>
    <div class="card-body">
      <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            ${errorMessage}
          <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
      </c:if>

      <form action="/library/borrowBookForm" method="post" class="needs-validation" novalidate>
        <div class="mb-3">
          <label for="borrowId" class="form-label">Mã mượn sách (MS-XXXX):</label>
          <input type="text" id="borrowId" name="borrowId" class="form-control" placeholder="Nhập mã mượn sách" required />
          <div class="invalid-feedback">Vui lòng nhập mã mượn sách.</div>
        </div>

        <div class="mb-3">
          <label for="bookId" class="form-label">Tên sách:</label>
          <select id="bookId" name="bookId" class="form-select" required>
            <option value="" disabled selected>Chọn sách...</option>
            <c:forEach var="book" items="${bookList}">
              <option value="${book.bookId}">${book.bookName} (Còn lại: ${book.quantity})</option>
            </c:forEach>
          </select>
          <div class="invalid-feedback">Vui lòng chọn sách.</div>
        </div>

        <div class="mb-3">
          <label for="studentId" class="form-label">Tên học sinh:</label>
          <select id="studentId" name="studentId" class="form-select" required>
            <option value="" disabled selected>Chọn học sinh...</option>
            <c:forEach var="student" items="${students}">
              <option value="${student.studentId}">${student.studentName}</option>
            </c:forEach>
          </select>
          <div class="invalid-feedback">Vui lòng chọn học sinh.</div>
        </div>

        <div class="mb-3">
          <label for="borrowReturnDate" class="form-label">Ngày trả sách:</label>
          <input type="date" id="borrowReturnDate" name="borrowReturnDate" class="form-control" required />
          <div class="invalid-feedback">Vui lòng chọn ngày trả sách.</div>
        </div>

        <div class="d-flex justify-content-between">
          <button type="submit" class="btn btn-primary">Mượn sách</button>
          <a href="/library" class="btn btn-secondary">Trở về danh sách</a>
        </div>
      </form>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script>

</script>
</body>
</html>


