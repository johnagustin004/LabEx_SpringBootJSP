<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.demo.Question" %>
<%@ page import="com.example.demo.Quiz" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Take Exam</title>
</head>
<body>
    <h2>Exam Questions</h2>
    <form id="examForm" action="result.jsp" method="post"> <!-- Updated action to result.jsp -->
        <%
            com.example.demo.Quiz quiz = (com.example.demo.Quiz) session.getAttribute("quiz");
            if (quiz == null) {
                // If quiz is not in session, create a new quiz object and set it in session
                quiz = new com.example.demo.Quiz();
                session.setAttribute("quiz", quiz);
            }

            ArrayList<com.example.demo.Question> questions = quiz.getQuestions();
            for (int i = 0; i < questions.size(); i++) {
                com.example.demo.Question question = questions.get(i);
        %>
        <fieldset>
            <legend><%= i + 1 %>. <%= question.getQuestion() %></legend>
            <%
                String[] options = question.getOptions();
                for (int j = 0; j < options.length; j++) {
            %>
            <input type="radio" id="q<%= i %>" name="q<%= i %>" value="<%= j %>">
            <label for="q<%= i %>"><%= options[j] %></label><br>
            <% } %>
        </fieldset>
        <br>
        <% } %>
        <button type="submit">Submit</button>
        <button type="reset">Reset</button>
    </form>
</body>
</html>
