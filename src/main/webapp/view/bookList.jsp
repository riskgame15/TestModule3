<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/11/2024
  Time: 10:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách sách</title>
</head>
<body>
<h2>Danh sách sách trong thư viện</h2>
<c:if test="${not empty message}">
    <div style="color: green;">${message}</div>
</c:if>
<c:if test="${not empty errorMessage}">
    <div style="color: red;">${errorMessage}</div>
</c:if>

<table border="1">
    <thead>
    <tr>
        <th>STT</th>
        <th>Tên sách</th>
        <th>Tác giả</th>
        <th>Miêu tả</th>
        <th>Số lượng</th>
        <th>Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.bookId}</td>
            <td>${book.bookName}</td>
            <td>${book.author}</td>
            <td>${book.description}</td>
            <td>${book.quantity}</td>
            <td>
                <c:if test="${book.quantity > 0}">
                    <a href="/library/borrowBookForm.jsp?bookId=${book.bookId}&bookName=${book.bookName}">Mượn sách</a>
                </c:if>
                <c:if test="${book.quantity == 0}">
                    Hết sách
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
