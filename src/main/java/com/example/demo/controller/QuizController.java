package com.example.demo.controller;

import com.example.demo.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class QuizController {

    @Autowired
    private HttpSession httpSession;

    @PostMapping("/evaluateQuiz")
    public String evaluateQuiz(HttpServletRequest request, Model model) {
        Quiz quiz = (Quiz) httpSession.getAttribute("quiz");
        if (quiz == null) {
            quiz = new Quiz();
            httpSession.setAttribute("quiz", quiz);
        }

        int totalQuestions = quiz.getQuestions().size();
        int[] userAnswers = new int[totalQuestions];
        try {
            for (int i = 0; i < totalQuestions; i++) {
                userAnswers[i] = Integer.parseInt(request.getParameter("q" + i));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input", e);
        }

        int correctAnswers = quiz.evaluate(userAnswers);
        httpSession.setAttribute("score", correctAnswers);
        httpSession.setAttribute("examTaken", true);

        // Save user score to context attributes (if needed)
        String username = (String) httpSession.getAttribute("username");
        ArrayList<String> users = (ArrayList<String>) httpSession.getServletContext().getAttribute("users");
        ArrayList<Integer> scores = (ArrayList<Integer>) httpSession.getServletContext().getAttribute("scores");
        if (users == null) {
            users = new ArrayList<>();
            httpSession.getServletContext().setAttribute("users", users);
        }
        if (scores == null) {
            scores = new ArrayList<>();
            httpSession.getServletContext().setAttribute("scores", scores);
        }
        // Add user's score if not already recorded
        if (!users.contains(username)) {
            users.add(username);
            scores.add(correctAnswers);
        } else {
            int index = users.indexOf(username);
            scores.set(index, correctAnswers);
        }

        model.addAttribute("score", correctAnswers); // Pass score to result.jsp
        return "redirect:/result"; // Redirect to result.jsp
    }
}
