<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.demo.Question" %>
<%@ page import="com.example.demo.Quiz" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result</title>
    <style>
        .answer {
            display: none;
        }
    </style>
    <script>
        function reviewAnswer() {
            var answers = document.getElementsByClassName("answer");
            for (var i = 0; i < answers.length; i++) {
                answers[i].style.display = "block";
            }
            document.getElementById("reviewAnswerBtn").style.display = "none";
            document.getElementById("hideAnswerBtn").style.display = "inline-block";
            document.getElementById("retakeExamBtn").style.display = "inline-block";
        }

        function hideAnswer() {
            var answers = document.getElementsByClassName("answer");
            for (var i = 0; i < answers.length; i++) {
                answers[i].style.display = "none";
            }
            document.getElementById("reviewAnswerBtn").style.display = "inline-block";
            document.getElementById("hideAnswerBtn").style.display = "none";
            document.getElementById("retakeExamBtn").style.display = "inline-block";
        }
    </script>
</head>
<body>
    <h1>Your Result</h1>
    <p>Score: ${score}</p> <!-- Display score passed from controller -->
    
    <button onclick="window.location.href='home.jsp'">Go Back</button>
    <button id="reviewAnswerBtn" onclick="reviewAnswer()">Review Answer</button>
    <button id="hideAnswerBtn" onclick="hideAnswer()" style="display: none;">Hide Answer</button>
    <button id="retakeExamBtn" onclick="window.location.href='index.jsp'">Retake Exam</button>

    <div>
        <% 
        Quiz quiz = (Quiz) session.getAttribute("quiz");
        int totalQuestions = quiz.getQuestions().size();
        int[] userAnswers = (int[]) session.getAttribute("userAnswers");

        for (int i = 0; i < totalQuestions; i++) {
            Question question = quiz.getQuestions().get(i);
            String userOption = question.getOptions()[userAnswers[i]];
            String correctOption = question.getOptions()[question.getCorrectOption()];
        %>
            <div class="answer">
                <p>Question <%= i + 1 %>: <%= question.getQuestion() %></p>
                <p>Your Answer: <%= userOption %></p>
                <p>Correct Answer: <%= correctOption %></p>
            </div>
        <% } %>
    </div>
</body>
</html>
