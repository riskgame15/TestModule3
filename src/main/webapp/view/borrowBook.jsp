<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/11/2024
  Time: 10:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mượn sách</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJ5bGrFkFhgt5NdrPVzHW9VgfmBfqaZZpQQRjZm6DgFj5+q6jwObpiK3z5bD" crossorigin="anonymous">
</head>
<body>
<div class="container mt-5">
    <h2 class="mb-4">Form mượn sách</h2>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <form action="/library" method="post">
        <input type="hidden" name="bookId" value="${param.bookId}" />

        <div class="mb-3">
            <label for="borrowId" class="form-label">Mã mượn sách (MS-XXXX):</label>
            <input type="text" id="borrowId" name="borrowId" class="form-control" required />
        </div>

        <div class="mb-3">
            <label for="bookName" class="form-label">Tên sách:</label>
            <input type="text" id="bookName" name="bookName" class="form-control" value="${param.bookName}" disabled />
        </div>

        <div class="mb-3">
            <%--@declare id="studentid"--%><label for="studentId" class="form-label">Tên học sinh:</label>
            <select name="studentId" class="form-select" required>
                <c:forEach var="student" items="${students}">
                    <option value="${student.studentId}">${student.studentName}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label for="borrowStart" class="form-label">Ngày mượn:</label>
            <input type="text" id="borrowStart" name="borrowStart" class="form-control" value="${fn:formatDate(now, 'dd/MM/yyyy')}" disabled />
        </div>

        <div class="mb-3">
            <label for="borrowReturnDate" class="form-label">Ngày trả sách:</label>
            <input type="text" id="borrowReturnDate" name="borrowReturnDate" class="form-control" required />
        </div>

        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-primary">Mượn sách</button>
            <a href="/library" class="btn btn-secondary">Quay lại danh sách sách</a>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz4fnFO9gybFf2V0XjJSKOnwJYp7xBpPDRdV+6bG4nD6XM2q8vE6z+fnD2" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0p5nYyYlrW9v3yW4tB9/gVv2gL2vXz6Yf8htg+gvoQx8f8eq" crossorigin="anonymous"></script>
</body>
</html>


