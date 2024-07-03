<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>
<body>
<h1>${message}</h1>
<c:if test="${not empty userSession.username}">
    <p>Welcome, ${userSession.username}! (<a href="logout">Logout</a>)</p>

    <ul>
        <li><a href="index">Take Exam</a></li>
        <c:if test="${examTaken}">
            <li><a href="index">Retake Exam</a></li>
        </c:if>
        <li><a href="grades">View Grades</a></li>
    </ul>
</c:if>
</body>
</html>
