package com.example.demo.controller;

import com.example.demo.model.UserSession;
import com.example.demo.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    private UserSession userSession;

    @GetMapping("/")
    public String home(Model model) {
        if (userSession.getUsername() == null) return "redirect:/login";

        model.addAttribute("message", "Hello, Spring Boot with JSP!");
        model.addAttribute("userSession", userSession);

        // Determine if the user has taken the exam
        boolean examTaken = userSession.isExamTaken();
        model.addAttribute("examTaken", examTaken);

        return "home";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        // Your authentication logic goes here (e.g., check username/password against database)
        System.out.println("username: " + username + ", password: " + password);
        if ("red".equals(username) && "red".equals(password)) {
            userSession.setUsername(username);
            userSession.setExamTaken(false); // Reset exam taken status on login
            return "redirect:/";
        } else {
            return "redirect:/login?error=invalid username or password";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        userSession.setUsername(null);
        userSession.setExamTaken(false);
        return "redirect:/login";
    }

    @GetMapping("/index")
    public String takeExam(Model model) {
        if (userSession.getUsername() == null) return "redirect:/login";
        
        // Logic for taking the exam or retaking it
        boolean examTaken = userSession.isExamTaken();
        if (!examTaken) {
            // First time taking the exam
            Quiz quiz = new Quiz();
            userSession.setExamTaken(true);
            model.addAttribute("quiz", quiz);
        } else {
            // Retaking the exam (if needed)
            // Logic for resetting quiz state or showing previous attempts
            // For simplicity, assuming resetting quiz state here
            Quiz quiz = new Quiz(); // Reset quiz for retake
            model.addAttribute("quiz", quiz);
        }
        
        return "index"; // Assumes index.jsp is used for the exam page
    }

    @GetMapping("/grades")
    public String viewGrades() {
        if (userSession.getUsername() == null) return "redirect:/login";
        // Logic for viewing grades
        return "grades";
    }
}
