package com.example.demo.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class UserSession {

    private String username;
    private boolean examTaken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isExamTaken() {
        return examTaken;
    }

    public void setExamTaken(boolean examTaken) {
        this.examTaken = examTaken;
    }
}
