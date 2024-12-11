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
    <title>Mượn sách</title>
</head>
<body>
<h2>Form mượn sách</h2>
<c:if test="${not empty errorMessage}">
    <div style="color: red;">${errorMessage}</div>
</c:if>
<form action="/library" method="post">
    <%--@declare id="studentid"--%><input type="hidden" name="bookId" value="${param.bookId}" />
    <label for="borrowId">Mã mượn sách (MS-XXXX):</label>
    <input type="text" id="borrowId" name="borrowId" required/><br/><br/>

    <label for="bookName">Tên sách:</label>
    <input type="text" id="bookName" name="bookName" value="${param.bookName}" disabled/><br/><br/>

    <label for="studentId">Tên học sinh:</label>
    <select name="studentId" required>
        <c:forEach var="student" items="${students}">
            <option value="${student.studentId}">${student.studentName}</option>
        </c:forEach>
    </select><br/><br/>

    <label for="borrowStart">Ngày mượn:</label>
    <input type="text" id="borrowStart" name="borrowStart" value="${fn:formatDate(now, 'dd/MM/yyyy')}" disabled/><br/><br/>

    <label for="borrowReturnDate">Ngày trả sách:</label>
    <input type="text" id="borrowReturnDate" name="borrowReturnDate" required/><br/><br/>

    <input type="submit" value="Mượn sách" />
</form>

<br/>
<a href="/library">Trở về danh sách sách</a>
</body>
</html>

