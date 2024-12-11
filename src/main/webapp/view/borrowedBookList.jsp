<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/11/2024
  Time: 12:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách sách đã cho thuê</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="text-center mb-4">Danh sách sách đã cho thuê</h2>

    <!-- Hiển thị thông báo lỗi hoặc kết quả -->
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="alert alert-success">${message}</div>
    </c:if>

    <form action="/library/borrowedBooks" method="get" class="mb-4">
        <div class="row">
            <div class="col-md-5">
                <input type="text" name="bookName" class="form-control" placeholder="Tìm kiếm theo tên sách" value="${param.bookName}">
            </div>
            <div class="col-md-5">
                <input type="text" name="studentName" class="form-control" placeholder="Tìm kiếm theo tên học sinh" value="${param.studentName}">
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary w-100">Tìm kiếm</button>
            </div>
        </div>
    </form>

    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th>STT</th>
            <th>Tên sách</th>
            <th>Tên học sinh</th>
            <th>Ngày mượn</th>
            <th>Ngày trả</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty borrowedBooks}">
            <tr>
                <td colspan="5" class="text-center">Không có sách nào đang được cho thuê.</td>
            </tr>
        </c:if>
        <c:forEach var="borrow" items="${borrowedBooks}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${borrow.bookName}</td>
                <td>${borrow.studentName}</td>
                <td>${borrow.borrowStart}</td>
                <td>${borrow.borrowReturn}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>

